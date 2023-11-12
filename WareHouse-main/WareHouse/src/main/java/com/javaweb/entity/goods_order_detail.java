package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "goods_order_detail")
public class goods_order_detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_order_detail_id;
	
	@Column
	private Long g_order_id;
	
	@Column 
	private String product_id;
	
	@Column
	private int quantity;
	
	@Column
	private long price;
	
	@ManyToOne
	@JoinColumn(name = "g_order_id",insertable = false, updatable = false)
	@JsonBackReference
	private goods_order goods_order;
	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false, updatable = false)
	@JsonBackReference
	private product product;

	public Long getG_order_detail_id() {
		return g_order_detail_id;
	}

	public void setG_order_detail_id(Long g_order_detail_id) {
		this.g_order_detail_id = g_order_detail_id;
	}

	public Long getG_order_id() {
		return g_order_id;
	}

	public void setG_order_id(Long g_order_id) {
		this.g_order_id = g_order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public goods_order getGoods_order() {
		return goods_order;
	}

	public void setGoods_order(goods_order goods_order) {
		this.goods_order = goods_order;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}
	
	

}
