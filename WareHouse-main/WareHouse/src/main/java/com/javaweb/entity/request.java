package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long request_id;
	
	@Column
	private Date date;
	
	@Column
	private String type;
	
	@Column
	private int status;
	
	@Column
	private Long staff_warehouse_id;
	
	@Column
	private Long staff_sale_id;
	
	@Column
	private String reason;
	
	@Column
	private Long store_id;
	
	@ManyToOne
	@JoinColumn(name = "staff_warehouse_id",insertable = false, updatable = false)
	private staffs staff_warehouse;
	
	@ManyToOne
	@JoinColumn(name = "store_id",insertable = false, updatable = false)
	private store store;
	
	@ManyToOne
	@JoinColumn(name = "staff_sale_id",insertable = false, updatable = false)
	private staffs staff_sale;

	@OneToMany(mappedBy = "request")
	private List<request_detail> request_detail;
	
	@OneToMany(mappedBy = "request")
	private List<inventory_report> inventory_report;

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getStaff_warehouse_id() {
		return staff_warehouse_id;
	}

	public void setStaff_warehouse_id(Long staff_warehouse_id) {
		this.staff_warehouse_id = staff_warehouse_id;
	}

	public Long getStaff_sale_id() {
		return staff_sale_id;
	}

	public void setStaff_sale_id(Long staff_sale_id) {
		this.staff_sale_id = staff_sale_id;
	}

	public staffs getStaff_warehouse() {
		return staff_warehouse;
	}

	public void setStaff_warehouse(staffs staff_warehouse) {
		this.staff_warehouse = staff_warehouse;
	}

	public staffs getStaff_sale() {
		return staff_sale;
	}

	public void setStaff_sale(staffs staff_sale) {
		this.staff_sale = staff_sale;
	}

	public List<request_detail> getRequest_detail() {
		return request_detail;
	}

	public void setRequest_detail(List<request_detail> request_detail) {
		this.request_detail = request_detail;
	}

	public List<inventory_report> getInventory_report() {
		return inventory_report;
	}

	public void setInventory_report(List<inventory_report> inventory_report) {
		this.inventory_report = inventory_report;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public store getStore() {
		return store;
	}

	public void setStore(store store) {
		this.store = store;
	}
	
	
	
	

}
