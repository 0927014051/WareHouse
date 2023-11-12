package com.javaweb.service;
import java.util.List;
import java.util.Optional;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_order_detail;
import com.javaweb.entity.product;
public interface GoodsOrderService {

	List<goods_order> findAll();
	
	goods_order findOrderById(Long id);
	
	<S extends goods_order> S save(S entity);
	List<goods_order> listOrderByDateLimit();

}
