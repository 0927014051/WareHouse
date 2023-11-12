package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.category;

@Repository
public interface CategoryRepo extends JpaRepository<category, Long>{

}
