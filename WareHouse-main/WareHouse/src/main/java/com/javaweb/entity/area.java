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
@Table(name = "area")
public class area {
	
		@Id
		@Column(name = "area_id")
		private String area_id;
		
		@Column(name = "area_name")
		private String area_name;
		
		@OneToMany(mappedBy = "area")
		private List<shelf> shelf;

		public String getArea_id() {
			return area_id;
		}

		public void setArea_id(String area_id) {
			this.area_id = area_id;
		}

		public String getArea_name() {
			return area_name;
		}

		public void setArea_name(String area_name) {
			this.area_name = area_name;
		}

		public List<shelf> getShelf() {
			return shelf;
		}

		public void setShelf(List<shelf> shelf) {
			this.shelf = shelf;
		}
		
		
		
		
		
		
}
