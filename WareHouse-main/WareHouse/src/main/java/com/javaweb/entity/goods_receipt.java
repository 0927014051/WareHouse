package com.javaweb.entity;

import java.util.Date;
import java.util.List;

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
@Table(name = "goods_receipt")
public class goods_receipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_receipt_id;
	
	@Column
	private Date date;
	
	@Column
	private Long staff_id;
	
	@Column
	private Long g_order_id;	
	 
	 @OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "g_order_id",insertable = false, updatable = false)
	 private goods_order goods_order;
	
	@OneToMany(mappedBy = "goods_receipt")
	private List<goods_receipt_detail> goods_receipt_detail;
	
	@ManyToOne
	@JoinColumn(name = "staff_id",insertable = false, updatable = false)
	@JsonBackReference
	private staffs staffs;
	
	@OneToOne(mappedBy = "goods_receipt")
	@JsonBackReference
	private invoice invoice;

	public Long getG_receipt_id() {
		return g_receipt_id;
	}

	public void setG_receipt_id(Long g_receipt_id) {
		this.g_receipt_id = g_receipt_id;
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

	public Long getG_order_id() {
		return g_order_id;
	}

	public void setG_order_id(Long g_order_id) {
		this.g_order_id = g_order_id;
	}

	

	public goods_order getGoods_order() {
		return goods_order;
	}

	public void setGoods_order(goods_order goods_order) {
		this.goods_order = goods_order;
	}

	public List<goods_receipt_detail> getGoods_receipt_detail() {
		return goods_receipt_detail;
	}

	public void setGoods_receipt_detail(List<goods_receipt_detail> goods_receipt_detail) {
		this.goods_receipt_detail = goods_receipt_detail;
	}

	public staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(staffs staffs) {
		this.staffs = staffs;
	}

	public invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(invoice invoice) {
		this.invoice = invoice;
	}
	
	
	
	
	
	

}
