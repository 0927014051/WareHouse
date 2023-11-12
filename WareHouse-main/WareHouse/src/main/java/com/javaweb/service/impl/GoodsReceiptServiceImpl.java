package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.reponsitory.GoodsReceiptRepo;
import com.javaweb.service.GoodsReceiptService;

@Service
public class GoodsReceiptServiceImpl implements GoodsReceiptService{
	
	@Autowired
	private GoodsReceiptRepo goodsReceiptRepo;
	
	@Override
	public List<goods_receipt> findAll(){
		return goodsReceiptRepo.findAll();
	}
	
	@Override
	public goods_receipt findReceiptById(Long id) {
		
		return goodsReceiptRepo.findReceiptById(id);
	}
	
	@Override
	public 	<S extends goods_receipt> S save(S entity) {
		return goodsReceiptRepo.save(entity);
	}

	
}
