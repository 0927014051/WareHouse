package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.staffs;
import com.javaweb.service.impl.StaffServiceImpl;
@Controller
@RequestMapping("manager")
public class StaffManagerController {
	@Autowired
	StaffServiceImpl serviceImpl;
	
	@RequestMapping("staff")
	public String staff(ModelMap model) {
		List<staffs> getStaffs = serviceImpl.findAll();
		model.addAttribute("staffList",getStaffs);
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		return "staff";
	}
	
	
	
	@PostMapping("staff/add")
	public String addStaff(@ModelAttribute("staff") staffs staff) {
		
		System.err.println("role : " + staff.getRole_id());
		System.err.println("pass : " + staff.getPassword());
		staffs saveStaffs = serviceImpl.save(staff);
		return "redirect:/manager/staff";
	}
	
}
