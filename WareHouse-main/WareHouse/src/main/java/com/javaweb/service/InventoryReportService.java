package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.inventory_report;

public interface InventoryReportService {
	
	List<inventory_report> findAll();
	inventory_report findInventoryReportById(Long id);
	void updateStatusReport(Long reportId, int status,Long staff_id_accept);
	<S extends inventory_report> S save(S entity);
	
	inventory_report findInventoryByRequestId(Long id);

}
