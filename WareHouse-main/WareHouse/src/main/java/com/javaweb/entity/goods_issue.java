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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "goods_issue")
public class goods_issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_issue_id;
	
	@Column
	private Date date;
	
	@Column
	private long staff_id;
	
	@Column
	private long store_id;
	
	
	@ManyToOne
	@JoinColumn(name = "staff_id", insertable = false, updatable = false)
	private staffs staffs;
	
	@ManyToOne
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private store store;
	
	@OneToMany(mappedBy = "goods_issue")
	private List<goods_issue_detail> goods_issue_detail;
	
	@OneToOne(mappedBy = "goods_issue")
	@JsonBackReference
	private invoice invoice;

	public Long getG_issue_id() {
		return g_issue_id;
	}

	public void setG_issue_id(Long g_issue_id) {
		this.g_issue_id = g_issue_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(staffs staffs) {
		this.staffs = staffs;
	}

	public store getStore() {
		return store;
	}

	public void setStore(store store) {
		this.store = store;
	}

	public List<goods_issue_detail> getGoods_issue_detail() {
		return goods_issue_detail;
	}

	public void setGoods_issue_detail(List<goods_issue_detail> goods_issue_detail) {
		this.goods_issue_detail = goods_issue_detail;
	}

	public invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(invoice invoice) {
		this.invoice = invoice;
	}
	
	
	
	

}
