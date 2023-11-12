package com.javaweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_order_detail;
import com.javaweb.reponsitory.GoodsOderRepo;
import com.javaweb.reponsitory.GoodsOrderDetailRepo;
import com.javaweb.service.GoodsOrderService;
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService{
	
	@Autowired
	private GoodsOderRepo goodsOderRepo;
	
	
	@Override
	public List<goods_order> findAll(){
		return goodsOderRepo.findAll();
	}
	
	@Override
	public goods_order findOrderById(Long id) {
		
		return goodsOderRepo.findOrderById(id);
	}
	
	@Override
	public <S extends goods_order> S save(S entity) {
		return goodsOderRepo.save(entity);
	}
	
	@Override
	public 	List<goods_order> listOrderByDateLimit(){
		return goodsOderRepo.listOrderByDateLimit();
	}

}
