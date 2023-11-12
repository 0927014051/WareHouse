package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.role;


@Repository
public interface RoleRepo extends JpaRepository<role,Long>{
	

}
