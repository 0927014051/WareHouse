package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.area;

@Repository
public interface AreaRepo extends JpaRepository<area, Long>{
	
}
