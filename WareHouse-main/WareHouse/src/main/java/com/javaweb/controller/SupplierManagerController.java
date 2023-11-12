package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.supplier;
import com.javaweb.service.impl.SupplierServiceImpl;

@Controller
@RequestMapping("manager")
public class SupplierManagerController {
	
	@Autowired
	SupplierServiceImpl supplierServiceImpl;
	
	@RequestMapping("supplier")
	public String supplierManager(ModelMap model) {
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		List<supplier> listSupplier = supplierServiceImpl.findAll();
		model.addAttribute("listSupplier",listSupplier);
		return "supplier";
	}

}
