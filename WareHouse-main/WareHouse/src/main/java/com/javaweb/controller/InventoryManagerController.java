package com.javaweb.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.data_month;
import com.javaweb.entity.goods_issue;
import com.javaweb.entity.goods_issue_detail;
import com.javaweb.entity.goods_order_detail;
import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.entity.inventory_report;
import com.javaweb.entity.inventory_report_detail;
import com.javaweb.entity.invoice;
import com.javaweb.entity.prediction;
import com.javaweb.entity.product;
import com.javaweb.entity.request;
import com.javaweb.entity.request_detail;
import com.javaweb.service.impl.BoxServiceImpl;
import com.javaweb.service.impl.DataMonthServiceImpl;
import com.javaweb.service.impl.GoodsIssueDetailServiceImpl;
import com.javaweb.service.impl.GoodsIssueServiceImpl;
import com.javaweb.service.impl.GoodsOrderDetailServiceImpl;
import com.javaweb.service.impl.GoodsReceiptDetailServiceImpl;
import com.javaweb.service.impl.GoodsReceiptServiceImpl;
import com.javaweb.service.impl.InventoryReportDetailServiceImpl;
import com.javaweb.service.impl.InventoryReportServiceImpl;
import com.javaweb.service.impl.InvoiceServiceImpl;
import com.javaweb.service.impl.PredictServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;
import com.javaweb.service.impl.RequestDetailServiceImpl;
import com.javaweb.service.impl.RequestServiceImpl;

@Controller
@RequestMapping("manager")
public class InventoryManagerController {

	@Autowired
	InventoryReportServiceImpl inventoryReportServiceImpl;

	@Autowired
	InventoryReportDetailServiceImpl inventoryReportDetailServiceImpl;

	@Autowired
	GoodsOrderDetailServiceImpl goodsOrderDetailServiceImpl;

	@Autowired
	RequestDetailServiceImpl requestDetailServiceImpl;

	@Autowired
	GoodsReceiptDetailServiceImpl goodsReceiptDetailServiceImpl;

	@Autowired
	PredictServiceImpl predictServiceImpl;

	@Autowired
	GoodsReceiptServiceImpl goodsReceiptServiceImpl;

	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	InvoiceServiceImpl invoiceServiceImpl;

	@Autowired
	GoodsIssueDetailServiceImpl goodsIssueDetailServiceImpl;

	@Autowired
	GoodsIssueServiceImpl goodsIssueServiceImpl;

	@Autowired
	RequestServiceImpl requestServiceImpl;

	@Autowired
	BoxServiceImpl boxServiceImpl;

	@Autowired
	DataMonthServiceImpl dataMonthServiceImpl;

	@RequestMapping("inventory")
	public String inventoryManager(ModelMap model) {

		List<inventory_report> listInventory = inventoryReportServiceImpl.findAll();
		model.addAttribute("listInventory", listInventory);
		model.addAttribute("role_id", UserController.getStaff().getRole_id());
		model.addAttribute("staff", UserController.getStaff());

		return "inventory_report";
	}

	@RequestMapping("inventoryOrder/{reportId}/{orderId}")
	public String inventoryOrderDetail(@PathVariable("reportId") Long reportId, @PathVariable("orderId") Long orderId,
			ModelMap model) {

		List<inventory_report_detail> inventory_report_details = inventoryReportDetailServiceImpl
				.findDetailByRecieptId(reportId);
		inventory_report inventory_report = inventoryReportServiceImpl.findInventoryReportById(reportId);
		List<goods_order_detail> goods_order_details = goodsOrderDetailServiceImpl.findOrderDetailById(orderId);
		StringBuilder htmlBuilder = new StringBuilder();
		StringBuilder buttonAddReceipt = new StringBuilder();
		StringBuilder buttonAccept = new StringBuilder();
		StringBuilder buttonCancel = new StringBuilder();
		StringBuilder buttonSaveSend = new StringBuilder();

		int count = 1;
		for (inventory_report_detail i : inventory_report_details) {
			for (goods_order_detail goods_order_detail : goods_order_details) {

				if (i.getDetail_id() == goods_order_detail.getG_order_detail_id()) {
					htmlBuilder.append("<td>").append(count++).append("</td>");
					htmlBuilder.append("<td>");
					htmlBuilder.append("<input type=\"text\" name=\"g_order_id_detail_"
							+ goods_order_detail.getG_order_detail_id() + "\" id=\"g_order_id_"
							+ goods_order_detail.getG_order_detail_id() + "\" min=\"0\" value=\""
							+ goods_order_detail.getG_order_detail_id() + "\" readonly>");
					htmlBuilder.append("</td>");
					htmlBuilder.append("<td>").append(goods_order_detail.getProduct().getProduct_id()).append("</td>");
					htmlBuilder.append("<td>").append(goods_order_detail.getProduct().getProduct_name())
							.append("</td>");

					htmlBuilder.append("<td>").append(goods_order_detail.getPrice() * goods_order_detail.getQuantity())
							.append("</td>");
					htmlBuilder.append("<td>").append(goods_order_detail.getQuantity()).append("</td>");
					htmlBuilder.append("<td>").append(goods_order_detail.getPrice()).append("</td>");
					if (i.getQuantity() == null) {
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"number\" name=\"quantity_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quantity_"
								+ goods_order_detail.getG_order_detail_id() + "\" min=\"0\" value=\"0\">");
						htmlBuilder.append("</td>");

						// Ô input kiểu số thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"number\" name=\"price_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"price_"
								+ goods_order_detail.getG_order_detail_id() + "\" min=\"0\" value=\"0\">");
						htmlBuilder.append("</td>");

						htmlBuilder.append("<input type=\"text\" hidden name=\"product_id_"
								+ +goods_order_detail.getG_order_detail_id() + "\" id=\"product_id_"
								+ +goods_order_detail.getG_order_detail_id() + "\" value=\""
								+ goods_order_detail.getProduct().getProduct_id() + "\">");

					} else {
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"number\" name=\"quantity_" + goods_order_detail.getG_order_detail_id()
										+ "\" id=\"quantity_" + goods_order_detail.getG_order_detail_id()
										+ "\" min=\"0\" value=\"" + i.getQuantity() + "\">");
						htmlBuilder.append("</td>");

						// Ô input kiểu số thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"number\" name=\"price_" + goods_order_detail.getG_order_detail_id()
										+ "\" id=\"price_" + goods_order_detail.getG_order_detail_id()
										+ "\" min=\"0\" value=\"" + i.getAmount() + "\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append("<input type=\"text\" hidden name=\"product_id_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"product_id_"
								+ goods_order_detail.getG_order_detail_id() + "\" value=\""
								+ goods_order_detail.getProduct().getProduct_id() + "\">");

					}

					if (i.getQuantity() == null) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					} else if ((goods_order_detail.getQuantity() - i.getQuantity()) < 0) {
						htmlBuilder.append("<td>").append(i.getQuantity() - goods_order_detail.getQuantity())
								.append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					} else if ((goods_order_detail.getQuantity() - i.getQuantity()) > 0) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append(goods_order_detail.getQuantity() - i.getQuantity())
								.append("</td>");
					} else if ((goods_order_detail.getQuantity() - i.getQuantity()) == 0) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					}

					if (i.getQuality() == null) {
						// Ô checkbox
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 1\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"1\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 2\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"2\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ ba
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 3\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"3\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append("</tr>");
					} else {
						// Ô checkbox
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 1\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"" + i.getQuality() + "\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 2\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"" + i.getQuality() + "\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ ba
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 3\" name=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" id=\"quality_"
								+ goods_order_detail.getG_order_detail_id() + "\" value = \"" + i.getQuality() + "\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append("</tr>");
					}
				}
			}

		}
		if (UserController.getStaff().getRole_id() == 2) {
			if (inventory_report.getStatus() == 2) {
				buttonAddReceipt.append(
						"<button style=\"float: right;\" type=\"submit\" class=\"btn\" id=\"buttonAccept\" onclick =\"sendValueAccept("
								+ reportId + "," + inventory_report.getG_order_id() + ")\" name=\"report_id\" value=\""
								+ reportId + "\">Insert Receipt</button>");
			} else {
				buttonAccept
						.append("<button style=\"float: right;\" type=\"submit\" class=\"btn\" onclick =\"sendAccept("
								+ reportId + "," + inventory_report.getG_order_id() + "," + 2 + ")\" value=\""
								+ reportId + "\">Accept</button>");
				buttonCancel
						.append("<button style=\"float: right;\" type=\"submit\" class=\"btn\" onclick =\"sendAccept("
								+ reportId + "," + inventory_report.getG_order_id() + "," + 4 + ")\" value=\""
								+ reportId + "\">Cancel</button>");
			}

		} else if (UserController.getStaff().getRole_id() == 3) {
			buttonSaveSend.append(
					"<input style=\"float: right;\" class=\"btn\" type=\"submit\" onclick=\"sendValue()\" value=\"Save & Send\" id=\"submitButton\" />");

		}

		model.addAttribute("buttonAccept", buttonAccept.toString());
		model.addAttribute("buttonAdd", buttonAddReceipt.toString());
		model.addAttribute("buttonSaveSend", buttonSaveSend.toString());

		model.addAttribute("buttonCancel", buttonCancel.toString());

		model.addAttribute("inventoryDetail", inventory_report_details);
		model.addAttribute("inventory", inventory_report);
		model.addAttribute("resultDetail", goods_order_details);
		model.addAttribute("tableHtml", htmlBuilder.toString());
		
		return "inventory";
	}

	@RequestMapping("inventoryRequest/{reportId}/{requestId}")
	public String inventoryRequestDetail(@PathVariable("reportId") Long reportId,
			@PathVariable("requestId") Long requestId, ModelMap model) {
		List<inventory_report_detail> inventory_report_details = inventoryReportDetailServiceImpl
				.findDetailByRecieptId(reportId);
		inventory_report inventory_report = inventoryReportServiceImpl.findInventoryReportById(reportId);
		List<request_detail> request_details = requestDetailServiceImpl.findAllRequestDetail(requestId);
		StringBuilder htmlBuilder = new StringBuilder();
		StringBuilder buttonAddIssue = new StringBuilder();
		StringBuilder buttonSaveSend = new StringBuilder();
		model.addAttribute("staff", UserController.getStaff().getRole_id());
		int checkQuantity = 0;
		long checkPrice = 0;
		int count = 1;
		StringBuilder buttonAccept = new StringBuilder();
		StringBuilder buttonCancel = new StringBuilder();
			for (request_detail request_detail : request_details) {
				for (inventory_report_detail i : inventory_report_details) {

				if (request_detail.getRequest_detail_id() == i.getDetail_id()) {
					htmlBuilder.append("<td>").append(count++).append("</td>");
					htmlBuilder.append("<td>");
					htmlBuilder.append(
							"<input type=\"text\" name=\"g_order_id_detail_" + request_detail.getRequest_detail_id()
									+ "\" id=\"g_order_id_" + request_detail.getRequest_detail_id()
									+ "\" min=\"0\" value=\"" + request_detail.getRequest_detail_id() + "\" readonly>");
					htmlBuilder.append("</td>");
					htmlBuilder.append("<td>").append(request_detail.getProduct().getProduct_id()).append("</td>");
					htmlBuilder.append("<td>").append(request_detail.getProduct().getProduct_name()).append("</td>");
					htmlBuilder.append("<td>").append(request_detail.getPrice() * request_detail.getQuantity())
							.append("</td>");
					htmlBuilder.append("<td>").append(request_detail.getQuantity()).append("</td>");
					htmlBuilder.append("<td>").append(request_detail.getPrice()).append("</td>");
					if (i.getQuantity() == null) {
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"number\" name=\"quantity_"
								+ request_detail.getRequest_detail_id() + "\" id=\"quantity_"
								+ request_detail.getRequest_detail_id() + "\" min=\"0\" value=\"0\">");
						htmlBuilder.append("</td>");

						// Ô input kiểu số thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"number\" name=\"price_"
								+ request_detail.getRequest_detail_id() + "\" id=\"price_"
								+ request_detail.getRequest_detail_id() + "\" min=\"0\" value=\"0\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append(
								"<input type=\"text\" hidden name=\"product_id_" + request_detail.getRequest_detail_id()
										+ "\" id=\"product_id_" + request_detail.getRequest_detail_id() + "\" value=\""
										+ request_detail.getProduct().getProduct_id() + "\">");

					} else {
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"number\" name=\"quantity_" + request_detail.getRequest_detail_id()
										+ "\" id=\"quantity_" + request_detail.getRequest_detail_id()
										+ "\" min=\"0\" value=\"" + i.getQuantity() + "\">");
						htmlBuilder.append("</td>");

						// Ô input kiểu số thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder
								.append("<input type=\"number\" name=\"price_" + request_detail.getRequest_detail_id()
										+ "\" id=\"price_" + request_detail.getRequest_detail_id()
										+ "\" min=\"0\" value=\"" + i.getAmount() + "\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append(
								"<input type=\"text\" hidden name=\"product_id_" + request_detail.getRequest_detail_id()
										+ "\" id=\"product_id_" + request_detail.getRequest_detail_id() + "\" value=\""
										+ request_detail.getProduct().getProduct_id() + "\">");
					}

					if (i.getQuantity() == null) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					} else if ((request_detail.getQuantity() - i.getQuantity()) > 0) {
						htmlBuilder.append("<td>").append(request_detail.getQuantity() - i.getQuantity())
								.append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					} else if ((request_detail.getQuantity() - i.getQuantity()) < 0) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append(request_detail.getQuantity() - i.getQuantity())
								.append("</td>");
					} else if ((request_detail.getQuantity() - i.getQuantity()) == 0) {
						htmlBuilder.append("<td>").append("0").append("</td>");
						htmlBuilder.append("<td>").append("0").append("</td>");
					}

					if (i.getQuality() == null) {
						// Ô checkbox
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 1\"  name=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" value = \"1\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 2\" name=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" value = \"2\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ ba
						htmlBuilder.append("<td>");
						htmlBuilder.append("<input type=\"checkbox\" class=\"quality 3\" name=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
								+ request_detail.getRequest_detail_id() + "\" value = \"3\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append("</tr>");
					} else {
						// Ô checkbox
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"checkbox\" class =\"quality 1\" onclick =\"uncheckAll(this)\" name=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" value = \"" + i.getQuality()
										+ "\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ hai
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"checkbox\" class =\"quality 2\" onclick =\"uncheckAll(this)\" name=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" value = \"" + i.getQuality()
										+ "\">");
						htmlBuilder.append("</td>");

						// Ô checkbox thứ ba
						htmlBuilder.append("<td>");
						htmlBuilder.append(
								"<input type=\"checkbox\" class =\"quality 3\" onclick =\"uncheckAll(this)\" name=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" id=\"quality_"
										+ request_detail.getRequest_detail_id() + "\" value = \"" + i.getQuality()
										+ "\">");
						htmlBuilder.append("</td>");
						htmlBuilder.append("</tr>");
					}
				}
			}

		}
		if (UserController.getStaff().getRole_id() == 2) {
			if (inventory_report.getStatus() == 2) {
				buttonAddIssue.append(
						"<button style=\"float: right;\" type=\"submit\" class=\"btn\" id=\"buttonAccept\" onclick =\"sendValueIssue("
								+ reportId + ")\" name=\"report_id\" value=\"" + reportId + "\">Insert Issue</button>");
			} else if (inventory_report.getStatus() == 1) {
				buttonAccept
						.append("<button style=\"float: right;\" type=\"submit\" class=\"btn\" onclick =\"sendAccept("
								+ reportId + "," + inventory_report.getG_order_id() + "," + 2 + ")\" value=\""
								+ reportId + "\">Accept</button>");
				buttonCancel
						.append("<button style=\"float: right;\" type=\"submit\" class=\"btn\" onclick =\"sendAccept("
								+ reportId + "," + inventory_report.getG_order_id() + "," + 4 + ")\" value=\""
								+ reportId + "\">Cancel</button>");
			}
		} else if (UserController.getStaff().getRole_id() == 3) {
			buttonSaveSend.append(
					"<input style=\"float: right;\" class=\"btn\" type=\"submit\" onclick=\"sendValue()\" value=\"Save & Send\" id=\"submitButton\" />");

		}

		model.addAttribute("buttonAccept", buttonAccept.toString());
		model.addAttribute("buttonAdd", buttonAddIssue.toString());
		model.addAttribute("buttonSaveSend", buttonSaveSend.toString());

		model.addAttribute("buttonCancel", buttonCancel.toString());

		model.addAttribute("inventoryDetail", inventory_report_details);
		model.addAttribute("inventory", inventory_report);
		model.addAttribute("resultDetail", request_details);
		model.addAttribute("tableHtml", htmlBuilder.toString());
		return "inventory";
	}

	@PostMapping("/inventory/update")
	public ResponseEntity<String> updateInventory(@RequestBody Map<String, String> params) {

		List<inventory_report_detail> listInven = new ArrayList<>();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			if (paramName.startsWith("g_order_id_")) {
				Long detailId = Long.parseLong(paramValue);
				inventory_report_detail updateInfo = new inventory_report_detail();
				updateInfo.setDetail_id(detailId);
				listInven.add(updateInfo);
			} else if (paramName.startsWith("quantity_")) {
				Integer quantity = Integer.parseInt(paramValue);
				inventory_report_detail updateInfo = listInven.get(listInven.size() - 1);
				updateInfo.setQuantity(quantity);
			} else if (paramName.startsWith("price_")) {
				Long price = Long.parseLong(paramValue);
				inventory_report_detail updateInfo = listInven.get(listInven.size() - 1);
				updateInfo.setAmount(price);
			} else if (paramName.startsWith("quality_")) {
				Integer quality = Integer.parseInt(paramValue);
				inventory_report_detail updateInfo = listInven.get(listInven.size() - 1);
				updateInfo.setQuality(quality);
			}
		} // Xử lý dữ liệu tại đây
		for (inventory_report_detail updateInfo : listInven) {
			inventoryReportDetailServiceImpl.updateInventoryDetailByDetail_id(updateInfo.getDetail_id(),
					updateInfo.getQuantity(), updateInfo.getAmount(), updateInfo.getQuantity() * updateInfo.getAmount(),
					updateInfo.getQuality());
		}
		return ResponseEntity.ok("Dữ liệu đã được xử lý thành công!");
	}

	@PostMapping("insert/receipt")
	public ResponseEntity<String> inserReceipt(@RequestBody Map<String, String> params) {
		LocalDate currentDate = LocalDate.now();
		// Chuyển LocalDate thành Date
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		// System.err.println(report_id);
		goods_receipt goods_receipt = new goods_receipt();
		goods_receipt.setDate(date);
		goods_receipt.setStaff_id(UserController.getStaff().getStaff_id());
		Long g_order_id = Long.parseLong(params.get("g_order_id"));
		goods_receipt.setG_order_id(g_order_id);
		Long report_id = Long.parseLong(params.get("report_id"));
		inventoryReportServiceImpl.updateStatusReport(report_id, 3, UserController.getStaff().getStaff_id());

		goodsReceiptServiceImpl.save(goods_receipt);
		List<inventory_report_detail> list = inventoryReportDetailServiceImpl.findDetailByRecieptId(report_id);

		List<goods_receipt_detail> combinedList = new ArrayList<>();
		long totalPrice = 0;
		int totalQuantity = 0;
		for (inventory_report_detail reportDetail : list) {
			// Duyệt qua danh sách inventory_report_detail
			String detail_id = String.valueOf(reportDetail.getDetail_id());
			Integer quantity = reportDetail.getQuantity();

			// Tìm thông tin từ params dựa trên product_id
			String priceKey = "price_" + detail_id;
			String quantityKey = "quantity_" + detail_id;
			String productKey = "product_id_" + detail_id;
			if (params.containsKey(priceKey)) {
				Long price = Long.parseLong(params.get(priceKey));
				Integer quantitySL = Integer.parseInt(params.get(quantityKey));
				String product_id = params.get(productKey);
				totalPrice = totalPrice + (quantitySL * price);
				totalQuantity += quantitySL;
				// Tạo đối tượng goods_receipt_detail và gán thông tin
				goods_receipt_detail detail = new goods_receipt_detail();
				detail.setProduct_id(product_id);
				detail.setActual_quantity(quantitySL);
				detail.setPrice(price);
				detail.setG_receipt_id(goods_receipt.getG_receipt_id());
				// Thêm detail vào danh sách kết hợp
				combinedList.add(detail);
			}
		}

		for (goods_receipt_detail detail : combinedList) {
			boxServiceImpl.updateQuantityBoxByProductId(detail.getProduct_id(), detail.getActual_quantity(), date);
			productServiceImpl.updateQuantityProductByid(detail.getProduct_id(), detail.getActual_quantity());
		}
		goodsReceiptDetailServiceImpl.saveAll(combinedList);
		invoice invoice = new invoice();
		invoice.setDate(date);
		invoice.setG_receipt_id(goods_receipt.getG_receipt_id());
		invoice.setStaff_id(UserController.getStaff().getStaff_id());
		invoice.setStatus(1);
		invoice.setTotal_price(totalPrice);
		invoice.setTotal_quantity(totalQuantity);
		invoice.setType("IMPORT");

		invoiceServiceImpl.save(invoice);

		return ResponseEntity.ok("Dữ liệu đã được xử lý thành công!");

	}

	public void predict(Long predict_id) {
		try {

			// Lấy dữ liệu từ cơ sở dữ liệu sử dụng predictionRepository
			// prediction prediction = predictServiceImpl.findPredictionByIdProduct(id);
//	            if (prediction == null) {
//	                return "Không tìm thấy bản ghi với id = " + id;
//	            }

			// Gửi dữ liệu đến máy chủ Python
			List<prediction> list = predictServiceImpl.findAll();
			// Tạo một Map để lưu trữ thông tin
			// Tạo một Map để lưu trữ thông tin dưới dạng key-value
			List<Map<String, String>> predictionList = new ArrayList<>();
			LocalDate currentDate = LocalDate.now();
			Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			for (prediction prediction : list) {
				// Lấy thông tin từ mỗi prediction
				int month = prediction.getPerdicted_month();
				String monthSend = String.valueOf(month);
				int specialEvent = prediction.getSpecial_even();
				String specialEventSend = String.valueOf(specialEvent);
				int season = prediction.getSeason();
				String seasonSend = String.valueOf(season);
				String productId = prediction.getProduct_id();

				// Tạo một Map để lưu trữ thông tin của prediction
				Map<String, String> predictionInfo = new HashMap<>();
				predictionInfo.put("month", monthSend);
				predictionInfo.put("special_event", specialEventSend);
				predictionInfo.put("season", seasonSend);
				predictionInfo.put("product_id", productId);

				// Thêm Map predictionInfo vào danh sách predictionList
				predictionList.add(predictionInfo);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonMap = objectMapper.writeValueAsString(predictionList);
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://127.0.0.1:5000/predict";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(jsonMap, headers);

			// Gửi HTTP POST request
			String response = restTemplate.postForObject(url, request, String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String, Object>> dataList = mapper.readValue(response,
					new TypeReference<List<Map<String, Object>>>() {
					});

			// Tạo một Map để lưu trữ dữ liệu với key và value
			Map<String, Double> predictionMap = new HashMap<>();

			// Lặp qua danh sách và thêm vào Map
			for (Map<String, Object> data : dataList) {
				String productId = (String) data.get("product_id");
				Double predictedValue = (Double) data.get("predicted_value");

				predictionMap.put(productId, predictedValue);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = sdf.format(date);
			List<prediction> allList = predictServiceImpl.findAll();
			for (prediction prediction : allList) {
				System.err.println(prediction.getDate_create());
			}
			for (Map.Entry<String, Double> entry : predictionMap.entrySet()) {
				String productId = entry.getKey();
				Double predictedValue = entry.getValue();
				predictServiceImpl.updatePrediction(productId, predict_id, predictedValue);
				System.out.println("Product ID: " + formattedDate + ", Predicted Value: " + predictedValue);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@PostMapping("insert/issue")
	public ResponseEntity<String> inserIssue(@RequestBody Map<String, String> params) {

		LocalDate currentDate = LocalDate.now();
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long report_id = Long.parseLong(params.get("report_id"));
		inventory_report inventory_report = inventoryReportServiceImpl.findInventoryReportById(report_id);
		request request = requestServiceImpl.findRequestById(inventory_report.getRequest_id());
		// System.err.println(report_id);
		inventoryReportServiceImpl.updateStatusReport(report_id, 3, UserController.getStaff().getStaff_id());

		goods_issue goods_issue = new goods_issue();
		goods_issue.setDate(date);
		goods_issue.setStaff_id(UserController.getStaff().getStaff_id());
		goods_issue.setStore_id(request.getStore_id());
		goodsIssueServiceImpl.save(goods_issue);
		List<inventory_report_detail> list = inventoryReportDetailServiceImpl.findDetailByRecieptId(report_id);
		List<goods_issue_detail> combinedList = new ArrayList<>();
		long totalPrice = 0;
		int totalQuantity = 0;
		for (inventory_report_detail reportDetail : list) {
			// Duyệt qua danh sách inventory_report_detail
			String detail_id = String.valueOf(reportDetail.getDetail_id());
			Integer quantity = reportDetail.getQuantity();

			// Tìm thông tin từ params dựa trên product_id
			String priceKey = "price_" + detail_id;
			String quantityKey = "quantity_" + detail_id;
			String productKey = "product_id_" + detail_id;
			if (params.containsKey(priceKey)) {
				Long price = Long.parseLong(params.get(priceKey));
				Integer quantitySL = Integer.parseInt(params.get(quantityKey));
				String product_id = params.get(productKey);
				System.err.println(product_id);
				totalPrice = totalPrice + (quantitySL * price);
				totalQuantity += quantitySL;
				// Tạo đối tượng goods_receipt_detail và gán thông tin
				goods_issue_detail detail = new goods_issue_detail();
				detail.setProduct_id(product_id);
				detail.setPrice(price);
				detail.setQuantity(quantitySL);
				detail.setG_issue_id(goods_issue.getG_issue_id());
				// Thêm detail vào danh sách kết hợp
				combinedList.add(detail);

			}
		}
		goodsIssueDetailServiceImpl.saveAll(combinedList);

		Map<String, Double> productAvgMap = new HashMap<>();

		for (goods_issue_detail issue : combinedList) {
			productServiceImpl.updateQuantityProductIssueByid(issue.getProduct_id(), issue.getQuantity());
			boxServiceImpl.updateQuantityBoxIssueByProductId(issue.getProduct_id(), issue.getQuantity(), date);
			int total_quantity = goodsIssueDetailServiceImpl.findTotalQuantityByProductId(issue.getProduct_id());
			Double avg = productServiceImpl.findAvgByProductId(issue.getProduct_id(), total_quantity, total_quantity);
			productAvgMap.put(issue.getProduct_id(), avg);

		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		int currentMonth = currentDate.getMonthValue();
		Map<String, Double> productTotal = new HashMap<>();
		List<data_month> data_months = dataMonthServiceImpl.findAll();
		for (Map.Entry<String, Double> entry : productAvgMap.entrySet()) {
			String productID = entry.getKey();
			Double avg = entry.getValue();
			for (data_month data_month : data_months) {
				if (avg > 60 && currentMonth == data_month.getMonth()) {
					prediction prediction = new prediction();
					prediction.setDate_create(sqlDate);
					prediction.setPerdicted_month(currentMonth);
					prediction.setProduct_id(productID);
					prediction.setSeason(data_month.getSeason());
					prediction.setSpecial_even(data_month.getSpecial_event());
					product product = productServiceImpl.findById(productID);
					prediction.setQuantity_inventory(product.getInventory_number());
					predictServiceImpl.save(prediction);
					System.err.println(prediction.getDate_create());
					predict(prediction.getPredict_id());
				}
			}

		}

		invoice invoice = new invoice();
		invoice.setDate(date);
		invoice.setG_issue_id(goods_issue.getG_issue_id());
		invoice.setStaff_id(UserController.getStaff().getStaff_id());
		invoice.setStatus(1);
		invoice.setTotal_price(totalPrice);
		invoice.setTotal_quantity(totalQuantity);
		invoice.setType("EXPORT");

		invoiceServiceImpl.save(invoice);

		return ResponseEntity.ok("Dữ liệu đã được xử lý thành công!");

	}

	@PostMapping("updateStatus")
	public ResponseEntity<String> updateStatusInventory(@RequestBody Map<String, String> params) {
		Long report_id = Long.parseLong(params.get("report_id"));
		int status = Integer.parseInt(params.get("status"));
		inventoryReportServiceImpl.updateStatusReport(report_id, status, UserController.getStaff().getStaff_id());
		System.err.println(report_id);
		System.err.println(status);
		return ResponseEntity.ok("Dữ liệu đã được xử lý thành công!");
	}

}
