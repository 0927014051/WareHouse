package com.javaweb.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_order_detail;
import com.javaweb.entity.inventory_report;
import com.javaweb.entity.inventory_report_detail;
import com.javaweb.service.impl.GoodsOrderDetailServiceImpl;
import com.javaweb.service.impl.GoodsOrderServiceImpl;
import com.javaweb.service.impl.InventoryReportDetailServiceImpl;
import com.javaweb.service.impl.InventoryReportServiceImpl;

@Controller
@RequestMapping("manager")
public class OrderManagerController {
	@Autowired
	GoodsOrderServiceImpl goodsOrderServiceImpl;
	
	@Autowired
	GoodsOrderDetailServiceImpl goodsOrderDetailServiceImpl;
	
	@Autowired
	InventoryReportServiceImpl inventoryReportServiceImpl;
	
	@Autowired
	InventoryReportDetailServiceImpl inventoryReportDetailServiceImpl;
	
	@RequestMapping("order")
	public String managerOrder(ModelMap model) {
		
		List<goods_order> listOrder = goodsOrderServiceImpl.findAll();
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		model.addAttribute("listOrder",listOrder);
		
		return "orders";
	}
	
    @RequestMapping("/orderDetail/{orderId}")
    public String orderDetail(@PathVariable Long orderId,ModelMap model) {
    	
    	List<goods_order_detail> orderDetails = goodsOrderDetailServiceImpl.findOrderDetailById(orderId);
    	model.addAttribute("orderDetail",orderDetails);
    	long total_price = 0;
    	
    	for (goods_order_detail orderDetail : orderDetails) {
    	    total_price += (orderDetail.getPrice()*orderDetail.getQuantity()); // Giả sử có một phương thức getPrice() để lấy giá trị từ đối tượng goods_order_detail
    	}
    	model.addAttribute("role_id",UserController.getStaff().getRole_id());
    	model.addAttribute("total_price", total_price); // Gắn tổng giá trị vào model
    	goods_order order = goodsOrderServiceImpl.findOrderById(orderId);
    	model.addAttribute("order",order);
    	return "purchase_order_warehouse";
    }
    
    @PostMapping("insert/inventory")
    public String inserInventoryOrder(@RequestBody Long g_order_id, HttpSession session) {
    	List<goods_order_detail> findOrder_details = goodsOrderDetailServiceImpl.findOrderDetailById(g_order_id);
    	LocalDate currentDate = LocalDate.now();
		// Chuyển LocalDate thành Date
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	inventory_report inventory_report = new inventory_report();
    	inventory_report.setDate(date);
    	inventory_report.setStaff_id(UserController.getStaff().getStaff_id());
    	inventory_report.setStatus(1);
    	inventory_report.setType("IMPORT"); 
    	inventory_report.setG_order_id(g_order_id);
    	inventoryReportServiceImpl.save(inventory_report);
    	for(goods_order_detail goods_order_detail : findOrder_details) {
        	inventory_report_detail inventory_report_detail = new inventory_report_detail();
    		inventory_report_detail.setDetail_id(goods_order_detail.getG_order_detail_id());
        	inventory_report_detail.setReport_id(inventory_report.getReport_id());
        	inventoryReportDetailServiceImpl.save(inventory_report_detail); 		
    	}   		
		return "redirect:/manager/inventory";
    }
}
