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
@Table(name = "inventory_report")
public class inventory_report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long report_id;
	
	@Column
	private Date date;
	
	@Column 
	private String type;
	
	@Column
	private int status;
	
	@Column
	private Long g_order_id;
	
	@Column
	private Long request_id;
	
	@Column
	private Long staff_id;
	
	
	@Column(insertable = false, nullable = true)
	private Long staff_id_accept;
	
	@ManyToOne 
	@JoinColumn(name = "g_order_id",insertable = false, updatable = false)
	private goods_order goods_order;
	
	@ManyToOne
	@JoinColumn(name = "request_id",insertable = false, updatable = false)
	private request request;
	
	@ManyToOne
	@JoinColumn(name = "staff_id",insertable = false, updatable = false)
	private staffs staffs;
	
	@ManyToOne
	@JoinColumn(name = "staff_id_accept",insertable = false, updatable = false)
	private staffs staff_accept;
	
	@OneToMany(mappedBy = "inventory_report")
	private List<inventory_report_detail> inventory_report_detail;

	public List<inventory_report_detail> getInventory_report_detail() {
		return inventory_report_detail;
	}

	public void setInventory_report_detail(List<inventory_report_detail> inventory_report_detail) {
		this.inventory_report_detail = inventory_report_detail;
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getG_order_id() {
		return g_order_id;
	}

	public void setG_order_id(Long g_order_id) {
		this.g_order_id = g_order_id;
	}

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}

	public goods_order getGoods_order() {
		return goods_order;
	}

	public void setGoods_order(goods_order goods_order) {
		this.goods_order = goods_order;
	}

	public request getRequest() {
		return request;
	}

	public void setRequest(request request) {
		this.request = request;
	}

	public staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(staffs staffs) {
		this.staffs = staffs;
	}

	public Long getStaff_id_accept() {
		return staff_id_accept;
	}

	public void setStaff_id_accept(Long staff_id_accept) {
		this.staff_id_accept = staff_id_accept;
	}

	public staffs getStaff_accept() {
		return staff_accept;
	}

	public void setStaff_accept(staffs staff_accept) {
		this.staff_accept = staff_accept;
	}
	
	
	
	

}
