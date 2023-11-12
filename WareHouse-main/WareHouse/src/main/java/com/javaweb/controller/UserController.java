package com.javaweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.entity.staffs;
import com.javaweb.function.PasswordGenerator;
import com.javaweb.mail.EmailService;
import com.javaweb.service.impl.StaffServiceImpl;
import org.apache.commons.lang3.StringUtils;

@Controller
public class UserController {

	@Autowired
	StaffServiceImpl staffServiceImpl;

	@Autowired
	private EmailService emailService;

	private static staffs staffs;
	private static String OTPMAIN;

	@RequestMapping("home")
	public String home(HttpSession ss, ModelMap model) {
		staffs = null;
		return "login";
	}

	@PostMapping(value = "login/submit")
	public String checklogin(HttpSession ss, HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, ModelMap model, RedirectAttributes ra) {

		if (username.equals("") || password.equals("")) {
			ra.addFlashAttribute("message", 1);
			return "redirect:/home";
		} else if (staffServiceImpl.existsByEmail(username) == false) {
			ra.addFlashAttribute("message", 2);
			return "redirect:/home";

		} else if (staffServiceImpl.existsByPassword(password) == false) {
			ra.addFlashAttribute("message", 3);
			return "redirect:/home";

		}
		staffs = staffServiceImpl.findUserAccount(username);
		if (staffs != null) {

			if (staffs.getEmail().equals(username) && staffs.getPassword().trim().equals(password.trim())) {
				ss.setAttribute("role_id", staffs.getRole_id());
				return "redirect:/manager/dashboard";
			}
		}
		return "redirect:/home";
	}

	@RequestMapping("profile")
	public String profileStaff(HttpSession ss, ModelMap model) {

		model.addAttribute("staffs", UserController.getStaff());
		return "profile";
	}

	@PostMapping(value = "profile/update")
	public String updateProfile(@RequestParam("staff_name") String staff_name,
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("address") String address,
			@RequestParam("birthday") String birthday, @RequestParam("gender") String gender, ModelMap model) {
		String pattern = "yyyy-MM-dd"; // Định dạng của chuỗi ngày
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		Long staff_id = staffs.getStaff_id();
		try {
			// Sử dụng SimpleDateFormat để chuyển đổi chuỗi thành Date
			date = sdf.parse(birthday);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Tách giá trị staffName thành getFirst_name và getLast_name
		String firstName = StringUtils.substringBefore(staff_name, " ");
		String lastName = StringUtils.substringAfter(staff_name, " ");
		staffServiceImpl.updateStaff(address, date, firstName, lastName, phone, gender, staff_id);
		staffs = staffServiceImpl.findUserAccount(username);
		return "redirect:/profile";
	}

	private static String encodeEmail(String email) {

		if (email == null) {
			return "";
		}
		int atIndex = email.indexOf("@");
		if (atIndex == -1) {
			return email;
		}

		String prefix = email.substring(0, atIndex);
		String domain = email.substring(atIndex);

		StringBuilder encodedPrefix = new StringBuilder();
		for (int i = 0; i < prefix.length(); i++) {
			encodedPrefix.append("*");
		}

		return encodedPrefix.toString() + domain;

	}

	@RequestMapping("password/change")
	public String changePassword(ModelMap model) {

		String email = encodeEmail(staffs.getEmail());
		model.addAttribute("email", email);
		return "changePass";
	}

	@PostMapping("password/sendOTP")
	public String sendOTP() throws MessagingException {
		String OTP = PasswordGenerator.generateRandomOTP(6);
		OTPMAIN = OTP;
		String from = "MY-WAREHOUSE";
		String to = staffs.getEmail();
		String body = "<h2>Vui lòng nhập OTP sau để xác nhận email của bạn: " + OTP + "</h2>";
		String subject = "XÁC NHẬN EMAIL";
		emailService.sendEmail(from, to, subject, body);
		System.err.println(OTP);
		return "redirect:/password/change";
	}

	private static boolean checkOTP(String otp) {
		if (OTPMAIN.equals(otp.trim())) {
			return true;
		}
		return false;
	}

	@RequestMapping("changePassword")
	public String pageChangePassword() {
		return "passwordchange";
	}

	@PostMapping("password/checkOTP")
	public ResponseEntity<String> changePassword1(@RequestBody Map<String, String> params, RedirectAttributes ra) throws MessagingException {
		String otp = params.get("otp");

		System.err.println(OTPMAIN.trim());
		System.err.println(otp.trim());
		if (checkOTP(otp)) {
			// Khi checkOTP() trả về true, chuyển đến /manager/changePassword
			return ResponseEntity.ok("true");
		} else {
			// Khi checkOTP() trả về false, chuyển đến /manager/home
			return ResponseEntity.ok("false");
		}
	}

	@PostMapping("update/password")
	public String updatePassword(@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword, @RequestParam("renewpassword") String renewpassword) {
		if (staffs.getPassword().equals(oldpassword.trim()) && newpassword.equals(renewpassword)) {
			staffServiceImpl.changePassword(staffs.getStaff_id(), newpassword);
			return "redirect:/profile";
		} else {
			return "redirect:/changePassword";

		}

	}

	public static staffs getStaff() {
		return staffs;
	}

}
