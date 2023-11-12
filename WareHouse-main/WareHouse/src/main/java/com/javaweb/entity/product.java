package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class product {
	
	@Id
	@Column
	private String product_id;
	
	@Column
	private String product_name;
	
	@Column
	private String color;
	
	@Column
	private String material;
	
	@Column
	private int inventory_number;
	
	@Column
	private String size;
	
	@Column
	private Date mfg;
	
	@Column
	private Long category_id;
	
	@Column
	private Long supplier_id;
	
	@Column
	private String img;
	
	@Column
	private String object;
	
	@Column
	private long price;
	
	
	
	@OneToMany(mappedBy = "product")
	private List<request_detail> request_detail;
	
	@OneToMany(mappedBy = "product")
	private List<prediction> prediction;
	
	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false, updatable = false)
	private category category;
	
	@OneToMany(mappedBy = "product")
	private List<box> box;
	
	
	@OneToMany(mappedBy = "product")
	@JsonManagedReference
	private List<goods_order_detail> goods_order_detail;
	
	@OneToMany(mappedBy = "product")
	private List<goods_issue_detail> goods_issue_detail;

	@ManyToOne
	@JoinColumn(name = "supplier_id",insertable = false, updatable = false)
	private supplier supplier;
	
	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public int getInventory_number() {
		return inventory_number;
	}


	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public Date getMfg() {
		return mfg;
	}


	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}


	public Long getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}


	public Long getSupplier_id() {
		return supplier_id;
	}


	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public List<request_detail> getRequest_detail() {
		return request_detail;
	}


	public void setRequest_detail(List<request_detail> request_detail) {
		this.request_detail = request_detail;
	}


	public category getCategory() {
		return category;
	}


	public void setCategory(category category) {
		this.category = category;
	}


	

	public List<box> getBox() {
		return box;
	}


	public void setBox(List<box> box) {
		this.box = box;
	}


	public List<goods_order_detail> getGoods_order_detail() {
		return goods_order_detail;
	}


	public void setGoods_order_detail(List<goods_order_detail> goods_order_detail) {
		this.goods_order_detail = goods_order_detail;
	}


	public String getObject() {
		return object;
	}


	public void setObject(String object) {
		this.object = object;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public List<goods_issue_detail> getGoods_issue_detail() {
		return goods_issue_detail;
	}


	public void setGoods_issue_detail(List<goods_issue_detail> goods_issue_detail) {
		this.goods_issue_detail = goods_issue_detail;
	}


	public supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(supplier supplier) {
		this.supplier = supplier;
	}


	public List<prediction> getPrediction() {
		return prediction;
	}


	public void setPrediction(List<prediction> prediction) {
		this.prediction = prediction;
	}
	
	
	
	

}
