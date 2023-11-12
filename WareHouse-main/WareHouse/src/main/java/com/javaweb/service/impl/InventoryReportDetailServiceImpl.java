package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.entity.inventory_report_detail;
import com.javaweb.reponsitory.InventoryReportDetailRepo;
import com.javaweb.service.InventoryReportDetailService;

@Service
public class InventoryReportDetailServiceImpl implements InventoryReportDetailService {

	@Autowired
	private InventoryReportDetailRepo inventoryReportDetailRepo;

	@Override
	public List<inventory_report_detail> findDetailByRecieptId(Long id) {

		return inventoryReportDetailRepo.findInventoryReporDetailBytId(id);
	}

	@Override
	public void updateInventoryDetailByDetail_id(Long detail_id, Integer quantity, Long amount, Long unit_price,Integer quality) {
		inventoryReportDetailRepo.updateInventoryDetailByDetail_id(detail_id, quantity, amount, unit_price, quality);
	}
	
	@Override 
	public 	<S extends inventory_report_detail> S save(S entity) {
		return inventoryReportDetailRepo.save(entity);
	}

}
