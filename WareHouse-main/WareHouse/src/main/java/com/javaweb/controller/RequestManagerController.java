package com.javaweb.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.entity.box;
import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_order_detail;
import com.javaweb.entity.inventory_report;
import com.javaweb.entity.inventory_report_detail;
import com.javaweb.entity.product;
import com.javaweb.entity.request;
import com.javaweb.entity.request_detail;
import com.javaweb.entity.shelf;
import com.javaweb.entity.store;
import com.javaweb.entity.supplier;
import com.javaweb.model.ProductRequest;
import com.javaweb.model.RequestImport;
import com.javaweb.service.impl.BoxServiceImpl;
import com.javaweb.service.impl.GoodsOrderDetailServiceImpl;
import com.javaweb.service.impl.GoodsOrderServiceImpl;
import com.javaweb.service.impl.InventoryReportDetailServiceImpl;
import com.javaweb.service.impl.InventoryReportServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;
import com.javaweb.service.impl.RequestDetailServiceImpl;
import com.javaweb.service.impl.RequestServiceImpl;
import com.javaweb.service.impl.ShelfServiceImpl;
import com.javaweb.service.impl.StoreServiceImpl;
import com.javaweb.service.impl.SupplierServiceImpl;

@Controller
@RequestMapping("manager")
public class RequestManagerController {

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

	@Autowired
	BoxServiceImpl boxServiceImpl;

	@Autowired
	ShelfServiceImpl shelfServiceImpl;

	@Autowired
	InventoryReportDetailServiceImpl inventoryReportDetailServiceImpl;

	@Autowired
	InventoryReportServiceImpl inventoryReportServiceImpl;

	@Autowired
	StoreServiceImpl storeServiceImpl;

	@RequestMapping("ex_request")
	public String ex_requestManager(ModelMap model) {
		model.addAttribute("role_id", UserController.getStaff().getRole_id());

		List<product> listProducts = productServiceImpl.findAll();
		model.addAttribute("staff", UserController.getStaff());
		model.addAttribute("listProduct", listProducts);
		LocalDate currentDate = LocalDate.now();
		List<store> store = storeServiceImpl.findAll();
		model.addAttribute("store", store);
		model.addAttribute("date", currentDate);
		return "ex_request";
	}

	@RequestMapping("request")
	public String requestManager(ModelMap model) {

		List<request> listProducts = requestServiceImpl.findAll();
		model.addAttribute("staff", UserController.getStaff());
		model.addAttribute("listRequest", listProducts);
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("role_id", UserController.getStaff().getRole_id());

		model.addAttribute("date", currentDate);
		return "request";
	}

	@RequestMapping("requestImport/{staffId}")
	public String requestManagerImportByStaff(@PathVariable("staffId") Long staff_id, ModelMap model) {

		List<request> listProducts = requestServiceImpl.findRequestImportByid(staff_id);
		model.addAttribute("staff", UserController.getStaff());
		model.addAttribute("listRequest", listProducts);
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("role_id", UserController.getStaff().getRole_id());

		model.addAttribute("date", currentDate);
		return "request";
	}

	@RequestMapping("requestExport/{staffId}")
	public String requestManageExportrByStaff(@PathVariable("staffId") Long staff_id, ModelMap model) {

		List<request> listProducts = requestServiceImpl.findRequestExportByid(staff_id);
		model.addAttribute("staff", UserController.getStaff());
		model.addAttribute("listRequest", listProducts);
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("role_id", UserController.getStaff().getRole_id());

		model.addAttribute("date", currentDate);
		return "request";
	}

	@RequestMapping("import_request/{idRequest}")
	public String import_requestManager(@PathVariable Long idRequest, ModelMap model) {
		model.addAttribute("idRequest", idRequest);
		List<request_detail> request_detail = requestDetailServiceImpl.findAllRequestDetail(idRequest);
		request request = requestServiceImpl.findRequestById(idRequest);
		model.addAttribute("request", request);
		model.addAttribute("requestDetail", request_detail);
		model.addAttribute("role_id", UserController.getStaff().getRole_id());
		return "import_request";
	}

	@RequestMapping("purchase_order/{idRequest}")
	public String purchase_orderManager(@PathVariable Long idRequest, ModelMap model, HttpSession session) {
		model.addAttribute("idRequest", idRequest);
		session.setAttribute("request_id", idRequest);
		model.addAttribute("role_id", UserController.getStaff().getRole_id());
		List<request_detail> request_detail = requestDetailServiceImpl.findAllRequestDetail(idRequest);
		List<supplier> suppliers = supplierServiceImpl.findAll();
		model.addAttribute("supplier", suppliers);
		String htmlData = (String) session.getAttribute("htmlData");
		model.addAttribute("htmlData", htmlData);
		request request = requestServiceImpl.findRequestById(idRequest);
		model.addAttribute("request", request);
		model.addAttribute("requestDetail", request_detail);
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("date", currentDate);
		return "purchase_order";
	}

	@PostMapping("getDetail/{idRequest}")
	public String purchase_orderManagerPost(@PathVariable Long idRequest, ModelMap model,
			@RequestBody Map<String, String> data, HttpSession session) {
		String supplier_id = data.get("supplier_id");
		Long id = Long.parseLong(supplier_id);
		supplier supplier = supplierServiceImpl.findSupplierById(id);
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<br>\n");
		htmlBuilder.append("<span>Supplier Name: </span><span> " + supplier.getSupplier_name() + "</span>\n");
		htmlBuilder.append("<br>\n");
		htmlBuilder.append("<span>Address: </span><span> " + supplier.getAddress() + "</span>\n");
		htmlBuilder.append("<br>\n");
		htmlBuilder.append("<span>Phone: </span><span > " + supplier.getPhone() + "</span>");
		session.setAttribute("htmlData", htmlBuilder.toString());
		session.setAttribute("supplier_id", id);
		session.setAttribute("supplier_name", supplier.getSupplier_name());
		session.setAttribute("address", supplier.getAddress());
		session.setAttribute("phone", supplier.getPhone());
		return "redirect:/manager/purchase_order/" + idRequest;

	}

	@PostMapping("insert/order")
	public String inserOrder(@RequestBody List<Map<String, String>> orderDetail, HttpSession session) {
		Long supplier_id = (Long) session.getAttribute("supplier_id");
		Long request_id = (Long) session.getAttribute("request_id");

		LocalDate currentDate = LocalDate.now();
		// Chuyển LocalDate thành Date
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		goods_order goods_order = new goods_order();
		goods_order.setDate(date);
		goods_order.setStaff_id(UserController.getStaff().getStaff_id());
		goods_order.setSupplier_id(supplier_id);
		goods_order.setRequest_id(request_id);
		goodsOrderServiceImpl.save(goods_order);
		for (Map<String, String> item : orderDetail) {
			goods_order_detail goods_order_detail = new goods_order_detail();
			String product_id = item.get("product_id");
			String quantity = item.get("quantity");
			Integer quanty = Integer.parseInt(quantity);

			String price = item.get("price");
			Long pr = Long.parseLong(price);
			goods_order_detail.setG_order_id(goods_order.getG_order_id());
			goods_order_detail.setPrice(pr);
			goods_order_detail.setQuantity(quanty);
			goods_order_detail.setProduct_id(product_id);
			goodsOrderDetailServiceImpl.save(goods_order_detail);

			// Thực hiện xử lý dựa trên product_id và quantity
		}
		session.removeAttribute("htmlData");
		session.removeAttribute("supplier_id");
		session.removeAttribute("supplier_name");

		session.removeAttribute("address");
		session.removeAttribute("phone");
		session.removeAttribute("request_id");

		return "redirect:/manager/order";

	}

	@PostMapping("updateRequest/{idRequest}")
	public String UpdateStatusRequest(@PathVariable Long idRequest, HttpSession ss) {
		requestServiceImpl.updateRequestStatusById(idRequest, 2);
		return "redirect:/manager/request";

	}

	@PostMapping("cancelRequest/{idRequest}")
	public String UpdateStatusRequestCancel(@PathVariable Long idRequest, HttpSession ss) {
		requestServiceImpl.updateRequestStatusById(idRequest, 3);
		return "redirect:/manager/request";

	}

//	@RequestMapping("update1")
//	public String update1() {
//		List<box> listbox = boxServiceImpl.findAll();
//		List<shelf> shelfs = shelfServiceImpl.findAll();
//		int shelfIndex = 0;
//		for (box box : listbox) {
//			if(box.getShelf_id() == null) {
//		    if (shelfIndex < shelfs.size()) {
//		    	
//		        Long shelfId = shelfs.get(shelfIndex).getShelf_id();
//		        boxServiceImpl.updateLimitBox(box.getBox_id(), shelfId);
//		        shelfIndex++;
//		    	}
//		    } else {
//		        System.err.println("thanhcong");
//		    }
//		}
//		return "redirect:/manager/dashboard";
//	}

	// Request_export

	@RequestMapping("export_request/{idRequest}")
	public String export_requestManager(@PathVariable Long idRequest, ModelMap model) {
		model.addAttribute("idRequest", idRequest);
		model.addAttribute("role_id", UserController.getStaff().getRole_id());
		List<request_detail> request_detail = requestDetailServiceImpl.findAllRequestDetail(idRequest);
		request request = requestServiceImpl.findRequestById(idRequest);
		model.addAttribute("request", request);
		model.addAttribute("requestDetail", request_detail);
		return "export_request";
	}

	@PostMapping("export_request/updateStatus")
	public String updateStatusExport_Request(@RequestBody Long request_id, HttpSession session) {

		requestServiceImpl.updateRequestStatusById(request_id, 2);

		return "redirect:/manager/request";

	}

	@PostMapping("insert/inventoryExport")
	public String inserInventoryOrder(@RequestBody Long request_id, HttpSession session) {
		List<request_detail> findRequest_details = requestDetailServiceImpl.findAllRequestDetail(request_id);
		LocalDate currentDate = LocalDate.now();
		// Chuyển LocalDate thành Date
		Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		inventory_report inventory_report = new inventory_report();
		inventory_report.setDate(date);
		inventory_report.setStaff_id(UserController.getStaff().getStaff_id());
		inventory_report.setStatus(1);
		inventory_report.setType("EXPORT");
		inventory_report.setRequest_id(request_id);
		inventoryReportServiceImpl.save(inventory_report);
		for (request_detail request_detail : findRequest_details) {
			inventory_report_detail inventory_report_detail = new inventory_report_detail();
			inventory_report_detail.setDetail_id(request_detail.getRequest_detail_id());
			inventory_report_detail.setReport_id(inventory_report.getReport_id());
			inventoryReportDetailServiceImpl.save(inventory_report_detail);
		}
		return "redirect:/manager/inventory";
	}

	@PostMapping("product/exportRequest")
	public ResponseEntity<String> handleImportRequest(@RequestBody RequestImport importRequestDTO) {
        // Xử lý dữ liệu từ importRequestDTO
		Long staff_id = importRequestDTO.getStaff_id();
        String reason = importRequestDTO.getReason();
		Long store_id = importRequestDTO.getStore_id();
		
        List<ProductRequest> productList = importRequestDTO.getProductList();
        LocalDate currentDate = LocalDate.now();
  			 // Chuyển LocalDate thành Date
    	        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	        
    	        request request = new request();
    	        request.setStaff_sale_id(staff_id);
    	        request.setStatus(1);
    	        request.setType("EXPORT");
    	        request.setReason(reason);
    	        request.setDate(date);
    	        request.setStore_id(store_id);
    	     requestServiceImpl.save(request);
    	        Long requestId = request.getRequest_id();
    	        // Đối với từng sản phẩm trong danh sách productList, bạn có thể thực hiện các thao tác cần thiết
    	        
    	        for (ProductRequest product : productList) {
    	            request_detail request_detail = new request_detail();
    	            String product_id = product.getProduct_id();
    	            String product_name = product.getProduct_name();
    	            long price = product.getPrice();
    	            int quantity = product.getQuantity();
    	            String note = product.getNote();
    	            // Thực hiện xử lý dữ liệu sản phẩm
    	            request_detail.setProduct_id(product_id);
    	            request_detail.setPrice(price);
    	            request_detail.setQuantity(quantity);
    	            request_detail.setRequest_id(requestId);
    	           requestDetailServiceImpl.save(request_detail);
  		  }
  		  

	// Trả về phản hồi (ví dụ: "Thành công") nếu cần
	return ResponseEntity.ok("Thành công");
}

}
