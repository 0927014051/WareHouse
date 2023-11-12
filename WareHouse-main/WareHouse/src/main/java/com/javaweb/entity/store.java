package com.javaweb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long store_id;
	
	@Column
	private String store_name;
	
	@Column
	private String address;
	
	@Column
	private String phone;
	
	@OneToMany(mappedBy = "store")
	private List<goods_issue> goods_issues;
	
	@OneToMany(mappedBy = "store")
	private List<request> request;

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<goods_issue> getGoods_issues() {
		return goods_issues;
	}

	public void setGoods_issues(List<goods_issue> goods_issues) {
		this.goods_issues = goods_issues;
	}
	
	
	
	

}
