package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_order_detail;

public interface GoodsOrderDetailService {

	List<goods_order_detail> findAllDetail();

	List<goods_order_detail> findOrderDetailById(Long g_order_id);
	
	<S extends goods_order_detail> S save(S entity);

}
