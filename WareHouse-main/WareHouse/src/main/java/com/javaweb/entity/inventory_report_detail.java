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
@Table(name = "inventory_report_detail")
public class inventory_report_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long inventory_id;
	
	@Column
	private Integer quantity;
	
	@Column
	private Long unit_price;
	
	@Column
	private Long amount;

	@Column
	private Long detail_id;
	
	@Column
	private Long report_id;
	
	@Column
	private Integer quality;
	
	@ManyToOne
	@JoinColumn(name = "report_id",insertable = false, updatable = false)
	private inventory_report inventory_report;

	public Long getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Long inventory_id) {
		this.inventory_id = inventory_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Long unit_price) {
		this.unit_price = unit_price;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}

	public inventory_report getInventory_report() {
		return inventory_report;
	}

	public void setInventory_report(inventory_report inventory_report) {
		this.inventory_report = inventory_report;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	

	
	

}
