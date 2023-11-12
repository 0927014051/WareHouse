package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_receipt_detail;

@Repository
public interface GoodsReceiptDetailRepo extends JpaRepository<goods_receipt_detail, Long> {

	@Query(value = "SELECT * FROM goods_receipt_detail WHERE g_receipt_id = ?1  ", nativeQuery = true)
	List<goods_receipt_detail> findDetailByRecieptId(Long id); 
	
	
}
