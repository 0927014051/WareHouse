package com.javaweb.controller.staff;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.request_detail;
import com.javaweb.entity.supplier;
import com.javaweb.service.impl.GoodsOrderDetailServiceImpl;
import com.javaweb.service.impl.GoodsOrderServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;
import com.javaweb.service.impl.RequestDetailServiceImpl;
import com.javaweb.service.impl.RequestServiceImpl;
import com.javaweb.service.impl.SupplierServiceImpl;

@Controller
@RequestMapping("staff")
public class RequestStaffController {

	@Autowired
	RequestServiceImpl requestServiceImpl;

	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	RequestDetailServiceImpl requestDetailServiceImpl;

	@Autowired
	SupplierServiceImpl supplierServiceImpl;

	@Autowired
	GoodsOrderServiceImpl goodsOrderServiceImpl;
	
	@Autowired
	GoodsOrderDetailServiceImpl goodsOrderDetailServiceImpl;
	
	
//	@RequestMapping("purchase_order")
//	public String purchase_ordeStaff(ModelMap model) {
//		
//		
//		
//		return "purchase_order_warehouse";
//	}
}
