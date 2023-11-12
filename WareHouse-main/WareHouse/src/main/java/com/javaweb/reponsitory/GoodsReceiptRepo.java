package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;

@Repository
public interface GoodsReceiptRepo extends JpaRepository<goods_receipt, Long>{
	
	@Query(value = "SELECT * FROM goods_receipt WHERE g_receipt_id = ?1  ", nativeQuery = true)
	goods_receipt findReceiptById(Long id);

}
