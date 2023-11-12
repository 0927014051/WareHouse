package com.javaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goods_issue_detail")
public class goods_issue_detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long g_issue_detail_id;
	
	@Column
	private Long g_issue_id;
	
	@Column
	private String product_id;
	
	@Column
	private int quantity;
	
	@Column
	private long price;
	
	@ManyToOne
	@JoinColumn(name = "g_issue_id",insertable = false,updatable = false)
	private goods_issue goods_issue;
	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private product product;

	public Long getG_issue_detail_id() {
		return g_issue_detail_id;
	}

	public void setG_issue_detail_id(Long g_issue_detail_id) {
		this.g_issue_detail_id = g_issue_detail_id;
	}

	public Long getG_issue_id() {
		return g_issue_id;
	}

	public void setG_issue_id(Long g_issue_id) {
		this.g_issue_id = g_issue_id;
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

	public goods_issue getGoods_issue() {
		return goods_issue;
	}

	public void setGoods_issue(goods_issue goods_issue) {
		this.goods_issue = goods_issue;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}
	
	
	
	
	

}
