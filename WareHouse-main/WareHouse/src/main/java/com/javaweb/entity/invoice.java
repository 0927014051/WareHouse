package com.javaweb.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long invoice_id;
	
	@Column
	private Date date;
	
	@Column
	private long total_price;
	
	@Column
	private int total_quantity;
	
	@Column
	private int status;
	
	@Column
	private String type;
	
	@Column
	private Long staff_id;
	
	@Column
	private Long staff_id_accept;
	
	@Column
	private Long g_issue_id;
	
	@Column
	private Long g_receipt_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "g_receipt_id",insertable = false, updatable = false)
	private goods_receipt goods_receipt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "g_issue_id",insertable = false, updatable = false)
	private goods_issue goods_issue;
	
	@ManyToOne
	@JoinColumn(name = "staff_id",insertable = false, updatable = false)
	private staffs staffs;
	@ManyToOne
	@JoinColumn(name = "staff_id_accept",insertable = false, updatable = false)
	private staffs staff_accept;

	public Long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}

	public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Long staff_id) {
		this.staff_id = staff_id;
	}

	public Long getG_issue_id() {
		return g_issue_id;
	}

	public void setG_issue_id(Long g_issue_id) {
		this.g_issue_id = g_issue_id;
	}

	public Long getG_receipt_id() {
		return g_receipt_id;
	}

	public void setG_receipt_id(Long g_receipt_id) {
		this.g_receipt_id = g_receipt_id;
	}

	public goods_receipt getGoods_receipt() {
		return goods_receipt;
	}

	public void setGoods_receipt(goods_receipt goods_receipt) {
		this.goods_receipt = goods_receipt;
	}

	public goods_issue getGoods_issue() {
		return goods_issue;
	}

	public void setGoods_issue(goods_issue goods_issue) {
		this.goods_issue = goods_issue;
	}

	public staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(staffs staffs) {
		this.staffs = staffs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
