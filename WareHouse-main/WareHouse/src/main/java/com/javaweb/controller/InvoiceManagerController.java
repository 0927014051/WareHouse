package com.javaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.entity.goods_issue;
import com.javaweb.entity.goods_issue_detail;
import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.entity.invoice;
import com.javaweb.service.impl.GoodsIssueDetailServiceImpl;
import com.javaweb.service.impl.GoodsIssueServiceImpl;
import com.javaweb.service.impl.GoodsReceiptDetailServiceImpl;
import com.javaweb.service.impl.GoodsReceiptServiceImpl;
import com.javaweb.service.impl.InvoiceServiceImpl;

@Controller
@RequestMapping("manager")
public class InvoiceManagerController {
	@Autowired
	InvoiceServiceImpl invoiceServiceImpl;

	@Autowired
	GoodsIssueDetailServiceImpl goodsIssueDetailServiceImpl;

	@Autowired
	GoodsReceiptServiceImpl goodsReceiptServiceImpl;

	@Autowired
	GoodsIssueServiceImpl goodsIssueServiceImpl;

	@Autowired
	GoodsReceiptDetailServiceImpl goodsReceiptDetailServiceImpl;

	@RequestMapping("invoice")
	public String invoiceManager(ModelMap model) {
		List<invoice> invoices = invoiceServiceImpl.findAll();
		model.addAttribute("invoices", invoices);
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		return "invoice_report";
	}

	@RequestMapping("invoiceIssue/{invoiceId}/{id}")
	public String invoiceIssueDetail(ModelMap model,  @PathVariable("invoiceId") Long invoiceId,@PathVariable("id") Long id) {
		List<goods_issue_detail> goods_issue_details = goodsIssueDetailServiceImpl.findIssueDetailById(id);
		goods_issue goodsIssue = goodsIssueServiceImpl.findIssueById(id);
		invoice invoice = invoiceServiceImpl.findInvoiceById(invoiceId);
		StringBuilder tableHtml = new StringBuilder();
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<div>");
		htmlContent.append("<span> Sales Person: ").append(goodsIssue.getStaffs().getFirst_name()).append(" ")
				.append(goodsIssue.getStaffs().getLast_name()).append("</span>");
		htmlContent.append("</div>");

		htmlContent.append("<div>");
		htmlContent.append("<span> Customer: ").append(goodsIssue.getStore().getStore_name()).append("</span>");
		htmlContent.append("</div>");

		htmlContent.append("<div>");
		htmlContent.append("<span> Address: ").append(goodsIssue.getStore().getAddress()).append("</span>");
		htmlContent.append("</div>");
		// Bắt đầu bảng
		tableHtml.append("<table id=\"product-table\">");
		tableHtml.append("<thead>");
		tableHtml.append("<tr>");
		tableHtml.append("<th>No.</th>");
		tableHtml.append("<th>ID</th>");
		tableHtml.append("<th>Name</th>");
		tableHtml.append("<th>Quantity</th>");
		tableHtml.append("<th>Unit price ($)</th>");
		tableHtml.append("<th>Amount ($)</th>");
		tableHtml.append("</tr>");
		tableHtml.append("</thead>");
		tableHtml.append("<tbody>");

		// Lặp qua danh sách invoiceDetail và thêm từng dòng vào bảng
		int dem = 1;
		for (goods_issue_detail issue : goods_issue_details) {
			tableHtml.append("<tr>");
			tableHtml.append("<td>").append(dem++).append("</td>");
			tableHtml.append("<td>").append(issue.getG_issue_detail_id()).append("</td>");
			tableHtml.append("<td>").append(issue.getProduct().getProduct_name()).append("</td>");
			tableHtml.append("<td>").append(issue.getQuantity()).append("</td>");
			tableHtml.append("<td>").append(issue.getPrice()).append("</td>");
			tableHtml.append("<td>").append(issue.getQuantity() * issue.getPrice()).append("</td>");
			tableHtml.append("</tr>");
		}

		// Kết thúc bảng
		tableHtml.append("</tbody>");
		tableHtml.append("</table>");
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		// Đặt nội dung bảng vào model để sử dụng trong mẫu Thymeleaf
		model.addAttribute("tableHtml", tableHtml.toString());
		model.addAttribute("htmlContent", htmlContent.toString());
		model.addAttribute("invoice",invoice);
		return "invoice";
	}

	@RequestMapping("invoiceReceipt/{invoiceId}/{id}")
	public String invoiceReceiptDetail(ModelMap model, @PathVariable("invoiceId") Long invoiceId,@PathVariable("id") Long id) {
		List<goods_receipt_detail> goods_receipt_details = goodsReceiptDetailServiceImpl.findDetailByRecieptId(id);
		goods_receipt goods_receipt = goodsReceiptServiceImpl.findReceiptById(id);
		invoice invoice = invoiceServiceImpl.findInvoiceById(invoiceId);
		StringBuilder tableHtml = new StringBuilder();
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<div>");
		htmlContent.append("<span> Sales Person: ").append(goods_receipt.getStaffs().getFirst_name()).append(" ")
				.append(goods_receipt.getStaffs().getLast_name()).append("</span>");
		htmlContent.append("</div>");

		htmlContent.append("<div>");
		htmlContent.append("<span> Customer: ").append(goods_receipt.getGoods_order().getSupplier().getSupplier_name()).append("</span>");
		htmlContent.append("</div>");

		htmlContent.append("<div>");
		htmlContent.append("<span> Address: ").append(goods_receipt.getGoods_order().getSupplier().getAddress()).append("</span>");
		htmlContent.append("</div>");
		// Bắt đầu bảng
		tableHtml.append("<table id=\"product-table\">");
		tableHtml.append("<thead>");
		tableHtml.append("<tr>");
		tableHtml.append("<th>No.</th>");
		tableHtml.append("<th>ID</th>");
		tableHtml.append("<th>Name</th>");
		tableHtml.append("<th>Quantity</th>");
		tableHtml.append("<th>Unit price ($)</th>");
		tableHtml.append("<th>Amount ($)</th>");
		tableHtml.append("</tr>");
		tableHtml.append("</thead>");
		tableHtml.append("<tbody>");

		// Lặp qua danh sách invoiceDetail và thêm từng dòng vào bảng
		int dem = 1;
		for (goods_receipt_detail receipt : goods_receipt_details) {
			tableHtml.append("<tr>");
			tableHtml.append("<td>").append(dem++).append("</td>");
			tableHtml.append("<td>").append(receipt.getG_receipt_id()).append("</td>");
			tableHtml.append("<td>").append(receipt.getProduct().getProduct_name()).append("</td>");
			tableHtml.append("<td>").append(receipt.getActual_quantity()).append("</td>");
			tableHtml.append("<td>").append(receipt.getPrice()).append("</td>");
			tableHtml.append("<td>").append(receipt.getActual_quantity() * receipt.getPrice()).append("</td>");
			tableHtml.append("</tr>");
		}

		// Kết thúc bảng
		tableHtml.append("</tbody>");
		tableHtml.append("</table>");
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		// Đặt nội dung bảng vào model để sử dụng trong mẫu Thymeleaf
		model.addAttribute("tableHtml", tableHtml.toString());
		model.addAttribute("htmlContent", htmlContent.toString());
		model.addAttribute("invoice",invoice);
		return "invoice";
	}
	
	@PostMapping("invoice/updateStatus/{invoiceId}")
	public String updateStatusInvoice(@PathVariable("invoiceId") Long invoiceId) {
		invoiceServiceImpl.updateStatusInvoice(invoiceId, 2, UserController.getStaff().getStaff_id());
		return "redirect:/manager/invoice" ;
	}

}
