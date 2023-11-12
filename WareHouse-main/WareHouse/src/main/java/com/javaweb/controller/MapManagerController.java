package com.javaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.entity.box;
import com.javaweb.entity.product;
import com.javaweb.entity.shelf;
import com.javaweb.service.impl.BoxServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;
import com.javaweb.service.impl.ShelfServiceImpl;

@Controller
@RequestMapping("manager")
public class MapManagerController {

	@Autowired
	ShelfServiceImpl shelfServiceImpl;

	@Autowired
	BoxServiceImpl boxServiceImpl;
	
	@Autowired
	ProductServiceImpl productServiceImpl;

	@RequestMapping("map")
	public String mapManager(ModelMap model) {
		model.addAttribute("staff",UserController.getStaff());

		return "map";
	}

	@PostMapping("search")
	public String searchProductWithMap(@RequestParam("product_id") String product_id, ModelMap model) {
		box box = boxServiceImpl.findBoxbyProductId(product_id);
		shelf shelf = shelfServiceImpl.findShelfIdbyShelfId(box.getShelf_id());
		// box box = boxServiceImpl.findBoxbyShelfId(shelf.getShelf_id());
		model.addAttribute("staff",UserController.getStaff());

		// Sử dụng phương thức split để cắt chuỗi
		String[] parts = box.getBox_id().split("-");
		product product = productServiceImpl.findById(product_id);
		// Lấy phần đầu tiên sau khi cắt chuỗi
		String result = parts[0];
		model.addAttribute("shelf_name", shelf.getShelf_name());
		model.addAttribute("box_id", result);
		model.addAttribute("box", box);
		model.addAttribute("product",product);
		model.addAttribute("product_id", product_id);
		System.err.println(box.getLimit_value());
		System.err.println(shelf.getShelf_name());
		return "map";
	}

}
