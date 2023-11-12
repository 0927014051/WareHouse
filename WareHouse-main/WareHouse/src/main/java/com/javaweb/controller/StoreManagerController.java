package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.store;
import com.javaweb.service.impl.StoreServiceImpl;

@Controller
@RequestMapping("manager")
public class StoreManagerController {
	
	@Autowired
	StoreServiceImpl storeServiceImpl;
	
	@RequestMapping("store")
	public String storeManager(ModelMap model) {
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		List<store> listStore = storeServiceImpl.findAll();
		model.addAttribute("listStore",listStore);
		return "store";
	}

}
