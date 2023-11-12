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
@Table(name = "box")
public class box {
		
	@Id
	@Column(name = "box_id")
	private String box_id;
	
	@Column(name = "limit_value")
	private int limit_value;
	
	@Column(name = "status")
	private Integer status;
	
	@Column
	private Integer quantity;
	
	@Column
	private Date date;

	
	@Column(name = "shelf_id")
	private Long shelf_id;
	
	@Column
	private String product_id;
	
	
	@ManyToOne
	@JoinColumn(name = "shelf_id",insertable = false, updatable = false)
	private shelf shelf;
	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false, updatable = false)
	private product product;


	public String getBox_id() {
		return box_id;
	}

	
	public int getLimit_value() {
		return limit_value;
	}


	public void setLimit_value(int limit_value) {
		this.limit_value = limit_value;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getShelf_id() {
		return shelf_id;
	}

	public void setShelf_id(Long shelf_id) {
		this.shelf_id = shelf_id;
	}
	public shelf getShelf() {
		return shelf;
	}

	public void setShelf(shelf shelf) {
		this.shelf = shelf;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	
	
	
	
}
