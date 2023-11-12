package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.inventory_report;
import com.javaweb.entity.inventory_report_detail;
import com.javaweb.reponsitory.InventoryReportRepo;
import com.javaweb.service.InventoryReportService;

@Service
public class InventoryReportServiceImpl implements InventoryReportService {

	@Autowired
	private InventoryReportRepo inventoryReportRepo;

	@Override
	public List<inventory_report> findAll() {

		return inventoryReportRepo.findAll();
	}

	@Override
	public inventory_report findInventoryReportById(Long id) {
		return inventoryReportRepo.findInventoryReportById(id);
	}

	@Override
	public void updateStatusReport(Long reportId, int status, Long staff_id_accept) {
		inventoryReportRepo.updateStatusReport(reportId, status, staff_id_accept);
	}

	@Override
	public <S extends inventory_report> S save(S entity) {
		return inventoryReportRepo.save(entity);
	}

	@Override
	public inventory_report findInventoryByRequestId(Long id) {

		return inventoryReportRepo.findInventoryByRequestId(id);
	}

}
