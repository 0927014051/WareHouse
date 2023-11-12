package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_order_detail;
import com.javaweb.reponsitory.GoodsOrderDetailRepo;
import com.javaweb.service.GoodsOrderDetailService;

@Service
public class GoodsOrderDetailServiceImpl implements GoodsOrderDetailService{
	
	@Autowired
	private GoodsOrderDetailRepo goodsOrderDetailRepo;
	@Override
	public List<goods_order_detail> findAllDetail(){
		return goodsOrderDetailRepo.findAll();
	}
	
	@Override
	public List<goods_order_detail> findOrderDetailById(Long g_order_id){
		return goodsOrderDetailRepo.findOrderDetailById(g_order_id);
		
	}
	
	@Override
	public <S extends goods_order_detail> S save(S entity) {
		return goodsOrderDetailRepo.save(entity);
	}

}
