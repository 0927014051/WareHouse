package com.javaweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.invoice;
import com.javaweb.entity.prediction;
import com.javaweb.model.ResultPredict;
import com.javaweb.service.impl.GoodsIssueServiceImpl;
import com.javaweb.service.impl.GoodsOrderServiceImpl;
import com.javaweb.service.impl.InvoiceServiceImpl;
import com.javaweb.service.impl.PredictServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("manager")
public class DashboardManagerController {
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	GoodsIssueServiceImpl goodsIssueServiceImpl;
	
	@Autowired
	PredictServiceImpl predictServiceImpl;
	
	@Autowired
	InvoiceServiceImpl invoiceServiceImpl;
	
	@Autowired
	GoodsOrderServiceImpl goodsOrderServiceImpl;
	
	@RequestMapping("dashboard")
	public String dashboardManager(ModelMap model) throws ParseException {
		model.addAttribute("totalProduct",productServiceImpl.count());
		model.addAttribute("totalPrice",productServiceImpl.totalCostInventory());
		model.addAttribute("totalIssue",goodsIssueServiceImpl.count());
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());
		List<ResultPredict> predictions = productServiceImpl.findMatchingProducts();

		model.addAttribute("predict",predictions); 
		List<invoice> listInvoices = invoiceServiceImpl.findAllInvoiceByAccepted();
		model.addAttribute("invoice",listInvoices);
		List<Date> listDates = new ArrayList<>();
		List<goods_order> orders = goodsOrderServiceImpl.listOrderByDateLimit();
		model.addAttribute("order",orders);
		for(invoice invoice : listInvoices) {
			listDates.add(invoice.getDate());
		}
		List<Long> listTotalPrice = new ArrayList<>();

        // Xác định định dạng của chuỗi ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.S]");

        Set<Integer> uniqueMonths = new HashSet<>();
        List<Integer> uniqueMonthList = new ArrayList<>();

        for (Date date : listDates) {
            // Chuyển đổi Date thành một chuỗi ngày
            String dateString = dateFormat.format(date);

            // Chuyển đổi chuỗi ngày thành đối tượng Date
            Date parsedDate = dateFormat.parse(dateString);

            // Lấy tháng từ đối tượng Date
            int month = parsedDate.getMonth() + 1; // Tháng trong Java bắt đầu từ 0, nên cộng thêm 1.

            // Kiểm tra xem tháng đã tồn tại trong danh sách chưa
            if (!uniqueMonths.contains(month)) {
                uniqueMonths.add(month); // Đánh dấu tháng đã thấy
                uniqueMonthList.add(month); // Thêm tháng vào danh sách các tháng duy nhất
            }
        }
        for(Integer month : uniqueMonthList) {
        	Long price = invoiceServiceImpl.totalPriceByMonth(month);    
        	listTotalPrice.add(price);
        }
        Collections.sort(uniqueMonthList);

		model.addAttribute("listDate",uniqueMonthList);
		model.addAttribute("listPrice",listTotalPrice);

		return "dashboard";
	}

}
