package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.entity.inventory_report_detail;

@Repository
public interface InventoryReportDetailRepo extends JpaRepository<inventory_report_detail, Long>{
	
	@Query(value = "SELECT * FROM inventory_report_detail WHERE report_id = ?1  ", nativeQuery = true)
	List<inventory_report_detail> findInventoryReporDetailBytId(Long id); 
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE inventory_report_detail SET quantity = ?2, amount = ?3, unit_price = ?4, quality = ?5 WHERE detail_id = ?1", nativeQuery = true)
	void updateInventoryDetailByDetail_id(Long detail_id, Integer quantity, Long amount, Long unit_price,Integer quality);
	
	
}
