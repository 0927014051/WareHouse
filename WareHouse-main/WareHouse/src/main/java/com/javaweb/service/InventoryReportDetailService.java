package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.inventory_report_detail;

public interface InventoryReportDetailService {
	List<inventory_report_detail> findDetailByRecieptId(Long id); 
	void updateInventoryDetailByDetail_id(Long detail_id, Integer quantity, Long amount, Long unit_price,Integer quality);
	<S extends inventory_report_detail> S save(S entity);

}
