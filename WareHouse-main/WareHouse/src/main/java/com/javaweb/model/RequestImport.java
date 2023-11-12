package com.javaweb.model;

import java.util.List;

public class RequestImport {

	private Long staff_id;
    private String reason;
    private Long store_id;
    private List<ProductRequest> productList;
	public Long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<ProductRequest> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductRequest> productList) {
		this.productList = productList;
	}
	public Long getStore_id() {
		return store_id;
	}
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	
    
    
}
