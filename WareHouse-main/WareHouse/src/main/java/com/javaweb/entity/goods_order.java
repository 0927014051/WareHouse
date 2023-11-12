package com.javaweb.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "goods_order")
public class goods_order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_order_id;
	
	@Column
	private Date date;
	
	@Column
	private Long staff_id;
	
	@Column
	private	Long supplier_id;
	
	@Column
	private Long request_id;
	
	@ManyToOne
	@JoinColumn(name = "staff_id",insertable = false, updatable = false)
	private staffs staffs;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id",insertable = false, updatable = false)
	private supplier supplier;
	
	@OneToMany(mappedBy = "goods_order")
	@JsonBackReference
	private List<goods_order_detail> goods_order_detail;
	
	
	@OneToMany(mappedBy = "goods_order")
	private List<inventory_report> inventory_report;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id",insertable = false, updatable = false)
	private request request;

	public Long getG_order_id() {
		return g_order_id;
	}

	public void setG_order_id(Long g_order_id) {
		this.g_order_id = g_order_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}

	public Long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

	public staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(staffs staffs) {
		this.staffs = staffs;
	}

	public supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(supplier supplier) {
		this.supplier = supplier;
	}

	public List<goods_order_detail> getGoods_order_detail() {
		return goods_order_detail;
	}

	public void setGoods_order_detail(List<goods_order_detail> goods_order_detail) {
		this.goods_order_detail = goods_order_detail;
	}

	public List<inventory_report> getInventory_report() {
		return inventory_report;
	}

	public void setInventory_report(List<inventory_report> inventory_report) {
		this.inventory_report = inventory_report;
	}

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public request getRequest() {
		return request;
	}

	public void setRequest(request request) {
		this.request = request;
	}
	 

	
	
}
