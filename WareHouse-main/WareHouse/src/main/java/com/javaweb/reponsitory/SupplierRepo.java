package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.supplier;

public interface SupplierRepo  extends JpaRepository<supplier, Long>{
	@Query(value = "SELECT * FROM supplier WHERE supplier_id = ?1  ", nativeQuery = true)
	supplier findSupplierById(Long id);

}
