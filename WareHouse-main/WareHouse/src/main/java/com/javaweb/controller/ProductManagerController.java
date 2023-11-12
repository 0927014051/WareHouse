package com.javaweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.entity.box;
import com.javaweb.entity.product;
import com.javaweb.entity.request;
import com.javaweb.entity.request_detail;
import com.javaweb.entity.shelf;
import com.javaweb.model.ProductRequest;
import com.javaweb.model.RequestData;
import com.javaweb.model.RequestImport;
import com.javaweb.service.impl.BoxServiceImpl;
import com.javaweb.service.impl.ProductServiceImpl;
import com.javaweb.service.impl.RequestDetailServiceImpl;
import com.javaweb.service.impl.RequestServiceImpl;
import com.javaweb.service.impl.ShelfServiceImpl;

@Controller
@RequestMapping("manager")
public class ProductManagerController {
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	RequestServiceImpl requestServiceImpl;
	
	@Autowired
	RequestDetailServiceImpl requestDetailServiceImpl;
	
	@Autowired
	BoxServiceImpl boxServiceImpl;
	
	@Autowired
	ShelfServiceImpl shelfServiceImpl;

	@RequestMapping("product")
	public String product(ModelMap model) {
		model.addAttribute("role_id",UserController.getStaff().getRole_id());
		model.addAttribute("staff",UserController.getStaff());

		List<product> list_product = productServiceImpl.findAll();
		model.addAttribute("productList", list_product);
		model.addAttribute("staff", UserController.getStaff());
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("date", currentDate);
		return "product";
	}

	@RequestMapping("/addProduct")
	public String addProduct() {

		return "addProduct";
	}

	@PostMapping("/product/add")
	public String productAdd(@ModelAttribute("product") product product) {

		System.err.println("product: " + product.getProduct_name());
		System.err.println("product: " + product.getMaterial());
		return "redirect:/manager/product";
	}

	@PostMapping("/add")
	public ResponseEntity<String> processSpanValues(@RequestBody List<RequestData> requestDataList) {
		String idProduct = "";
		String temp = "";
		String temp1 = "";
		String product_id = requestDataList.get(0).getProduct_id();
		List<String> spanSizeList = requestDataList.get(1).getSpanSizeList();
		List<String> spanColorList = requestDataList.get(2).getSpanColorList();
		String product_name = requestDataList.get(3).getProduct_name();
		String object = requestDataList.get(4).getObject();
		Long category_id = requestDataList.get(5).getCategory_id();
		Long supplier_id = requestDataList.get(6).getSupplier_id();
		String material = requestDataList.get(7).getMaterial();
		List<String> imageList = requestDataList.get(8).getImageList();
		String mfg = requestDataList.get(9).getMfg();
		Long price = (requestDataList.get(10).getPrice());
		String img = "";
		String pattern = "yyyy-MM-dd"; // Định dạng của chuỗi ngày
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		int imageIndex = 0; // Biến đếm để theo dõi hình ảnh được sử dụng

		try {
			// Sử dụng SimpleDateFormat để chuyển đổi chuỗi thành Date
			date = sdf.parse(mfg);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		product product = new product();
		for (String getSize : spanSizeList) {
			String check = "";
			temp = "";
			temp += getSize;
			check = product_id + temp;
			for (String getColor : spanColorList) {
				temp1 = "";
				char firstColor = getColor.charAt(0);
				int length = getColor.length();
				char lastColor = getColor.charAt(length - 1);
				String total = String.valueOf(firstColor) + String.valueOf(lastColor);
				temp1 += total.toUpperCase();
				idProduct = check + temp1;

				product.setProduct_id(idProduct);
				product.setCategory_id(category_id);
				product.setColor(getColor);
				// Đặt chuỗi img lên đối tượng product
				product.setMaterial(material);
				product.setMfg(date);
				product.setPrice(price);

				product.setObject(object);
				String productName = product_name + " - " + getSize + " - " + getColor;
				product.setProduct_name(productName);
				product.setSize(getSize);
				product.setSupplier_id(supplier_id);
				if (!imageList.isEmpty()) {
					// Lấy hình ảnh theo biến đếm imageIndex
					String imgg = imageList.get(imageIndex);
					product.setImg(imgg);
					imageIndex = (imageIndex + 1) % imageList.size(); // Đảm bảo quay lại đầu danh sách khi cần

					// In ra imgg để kiểm tra hình ảnh đã được gán cho sản phẩm

				}

				productServiceImpl.save(product);

			}
		}
		// Tạo danh sách sản phẩm

		return ResponseEntity.ok("Dữ liệu đã được xử lý thành công!");
	}
	
	@PostMapping("product/importRequest")
	public ResponseEntity<String> handleImportRequest(@RequestBody RequestImport importRequestDTO) {
        // Xử lý dữ liệu từ importRequestDTO
		Long staff_id = importRequestDTO.getStaff_id();
        String reason = importRequestDTO.getReason();
        List<ProductRequest> productList = importRequestDTO.getProductList();
        LocalDate currentDate = LocalDate.now();

        // Chuyển LocalDate thành Date
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request request = new request();
        request.setStaff_warehouse_id(staff_id);
        request.setStatus(1);
        request.setType("IMPORT");
        request.setReason(reason);
        request.setDate(date);
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
	
	@RequestMapping("addProduct1")
	public String addProduct1() {
		
		List<product> list = productServiceImpl.findAll();
		
			List<box> listbox = boxServiceImpl.findAll();
			int shelfIndex = 0;
			for (box box : listbox) {
				if(box.getProduct_id() == null) {
			    if (shelfIndex < 270) {
			    	
			        String shelfId = list.get(shelfIndex).getProduct_id();
			        boxServiceImpl.updateLimitBox(box.getBox_id(), shelfId);
			        shelfIndex++;
			    	}
			    } else {
			        System.err.println("thanhcong");
			    }
			}
			
		return "redirect:/manager/dashboard";
	}
	}


