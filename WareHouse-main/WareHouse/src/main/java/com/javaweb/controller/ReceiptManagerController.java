package com.javaweb.controller;

import java.util.List;
import java.util.Map;

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

import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.service.impl.GoodsReceiptDetailServiceImpl;
import com.javaweb.service.impl.GoodsReceiptServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("manager")
public class ReceiptManagerController {
	
	@Autowired
	GoodsReceiptServiceImpl goodsReceiptServiceImpl;
	
	@Autowired
	GoodsReceiptDetailServiceImpl goodsReceiptDetailServiceImpl;
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@RequestMapping("receipt")
	public String goodsReceipt(ModelMap model) {
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		List<goods_receipt> listReceip = goodsReceiptServiceImpl.findAll();
		model.addAttribute("listReceipt",listReceip);		
		return "g__receipt";
	}
	
	@RequestMapping("/receipt/{receiptId}")
	public String receiptDetail(@PathVariable Long receiptId,ModelMap model ) {
		
		List<goods_receipt_detail> receiptDetail = goodsReceiptDetailServiceImpl.findDetailByRecieptId(receiptId);
		goods_receipt goods_receipt = goodsReceiptServiceImpl.findReceiptById(receiptId);
		model.addAttribute("receiptDetail",receiptDetail);
		model.addAttribute("receipt",goods_receipt);
		return "goods_receipt";
	}
	
}
