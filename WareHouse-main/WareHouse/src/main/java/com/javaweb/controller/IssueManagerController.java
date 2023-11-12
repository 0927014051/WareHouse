package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.goods_issue;
import com.javaweb.entity.goods_issue_detail;
import com.javaweb.service.GoodsIssueService;
import com.javaweb.service.impl.GoodsIssueDetailServiceImpl;
import com.javaweb.service.impl.GoodsIssueServiceImpl;

@Controller
@RequestMapping("manager")
public class IssueManagerController {
	@Autowired
	GoodsIssueServiceImpl goodsIssueServiceImpl;
	
	@Autowired
	GoodsIssueDetailServiceImpl goodsIssueDetailServiceImpl;
	
	@RequestMapping("issue")
	public String goodsIssue(ModelMap model) {
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		List<goods_issue> listIssue =  goodsIssueServiceImpl.findAll();
		model.addAttribute("listIssue",listIssue);
		return "g__issue";
	}
	
	@RequestMapping("issue/{issue_id}")
	public String goodsIssue(@PathVariable Long issue_id, ModelMap model) {
		
		goods_issue goods_issue = goodsIssueServiceImpl.findIssueById(issue_id);
		List<goods_issue_detail> goods_issue_details = goodsIssueDetailServiceImpl.findIssueDetailById(issue_id);
		model.addAttribute("issue",goods_issue);
		model.addAttribute("issueDetail", goods_issue_details);
		return "goods_issue";
		
	}
}
