package com.javaweb.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prediction")
public class prediction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long predict_id;
	
	@Column
	private int special_even;
	
	@Column
	private int season;
	
	@Column
	private Integer predicted_value;
	
	@Column
	private Date date_create;
	
	@Column
	private int perdicted_month;
	
	@Column
	private Integer quantity_inventory;
	
	@Column
	private String product_id;
	

	
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false, updatable = false)
	private product product;

	public Long getPredict_id() {
		return predict_id;
	}

	public void setPredict_id(Long predict_id) {
		this.predict_id = predict_id;
	}

	public int getSpecial_even() {
		return special_even;
	}

	public void setSpecial_even(int special_even) {
		this.special_even = special_even;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int sesson) {
		this.season = sesson;
	}

	public Integer getPredicted_value() {
		return predicted_value;
	}

	public void setPredicted_value(Integer predicted_value) {
		this.predicted_value = predicted_value;
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public int getPerdicted_month() {
		return perdicted_month;
	}

	public void setPerdicted_month(int perdicted_month) {
		this.perdicted_month = perdicted_month;
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

	public Integer getQuantity_inventory() {
		return quantity_inventory;
	}

	public void setQuantity_inventory(Integer quantity_inventory) {
		this.quantity_inventory = quantity_inventory;
	}
	
	
	

}
