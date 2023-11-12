package com.javaweb.reponsitory;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.javaweb.entity.inventory_report;

public interface InventoryReportRepo extends JpaRepository<inventory_report, Long>{
	@Query(value = "SELECT * FROM inventory_report WHERE report_id = ?1  ", nativeQuery = true)
	inventory_report findInventoryReportById(Long id);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE inventory_report SET status = ?2, staff_id_accept = ?3 WHERE report_id = ?1", nativeQuery = true)
	void updateStatusReport(Long reportId, int status,Long staff_id_accept);
	
	@Query(value = "SELECT * FROM inventory_report WHERE request_id = ?1  ", nativeQuery = true)
	inventory_report findInventoryByRequestId(Long id);
}
