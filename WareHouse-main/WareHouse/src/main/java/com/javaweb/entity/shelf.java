package com.javaweb.entity;

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
@Table(name = "shelf")
public class shelf {

	@Id
	@Column
	private Long shelf_id;
	
	@Column
	private String shelf_name;
	
	@Column
	private String area_id;
	
	@OneToMany(mappedBy = "shelf")
	private List<box> box;
	
	@ManyToOne
	@JoinColumn(name = "area_id",insertable = false, updatable = false)
	private area area;

	public Long getShelf_id() {
		return shelf_id;
	}

	public void setShelf_id(Long shelf_id) {
		this.shelf_id = shelf_id;
	}

	public String getShelf_name() {
		return shelf_name;
	}

	public void setShelf_name(String shelf_name) {
		this.shelf_name = shelf_name;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public List<box> getBox() {
		return box;
	}

	public void setBox(List<box> box) {
		this.box = box;
	}

	public area getArea() {
		return area;
	}

	public void setArea(area area) {
		this.area = area;
	}
	
	
}
