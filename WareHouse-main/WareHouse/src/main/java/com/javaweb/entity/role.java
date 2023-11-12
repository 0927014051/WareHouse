package com.javaweb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
public class role {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	private Long role_id;
	
	@Column
	private String role_name;
	
	@OneToMany(mappedBy = "role")
	private List<staffs> staffs;

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public List<staffs> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<staffs> staffs) {
		this.staffs = staffs;
	}
	
	
	
	
}
