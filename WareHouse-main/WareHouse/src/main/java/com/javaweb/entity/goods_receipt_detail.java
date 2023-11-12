package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "goods_receipt_detail")
public class goods_receipt_detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_receipt_detail_id;
	@Column
	private Long g_receipt_id;
	
	@Column
	private String product_id;
	
	@Column
	private int actual_quantity;
	

	
	
	
	@Column
	private long price;
	
	@ManyToOne
	@JoinColumn(name = "g_receipt_id",insertable = false, updatable = false)
	@JsonBackReference
	private goods_receipt goods_receipt;
	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false, updatable = false)
	@JsonBackReference
	private product product;

	public Long getG_receipt_detail_id() {
		return g_receipt_detail_id;
	}

	public void setG_receipt_detail_id(Long g_receipt_detail_id) {
		this.g_receipt_detail_id = g_receipt_detail_id;
	}

	public Long getG_receipt_id() {
		return g_receipt_id;
	}

	public void setG_receipt_id(Long g_receipt_id) {
		this.g_receipt_id = g_receipt_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public goods_receipt getGoods_receipt() {
		return goods_receipt;
	}

	public void setGoods_receipt(goods_receipt goods_receipt) {
		this.goods_receipt = goods_receipt;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}

	public int getActual_quantity() {
		return actual_quantity;
	}

	public void setActual_quantity(int actual_quantity) {
		this.actual_quantity = actual_quantity;
	}


	

}
