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
@Table(name ="supplier")
public class supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long supplier_id;
	
	@Column
	private String supplier_name;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	@OneToMany(mappedBy = "supplier")
	private List<goods_order> goods_order;
	
	@OneToMany(mappedBy = "supplier")
	private List<product> product;

	public Long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<goods_order> getGoods_order() {
		return goods_order;
	}

	public void setGoods_order(List<goods_order> goods_order) {
		this.goods_order = goods_order;
	}

	public List<product> getProduct() {
		return product;
	}

	public void setProduct(List<product> product) {
		this.product = product;
	}
	
	

}
