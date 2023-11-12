package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_receipt_detail;
import com.javaweb.reponsitory.GoodsReceiptDetailRepo;
import com.javaweb.reponsitory.GoodsReceiptRepo;
import com.javaweb.service.GoodsReceiptDetailService;
import com.javaweb.service.GoodsReceiptService;

@Service
public class GoodsReceiptDetailServiceImpl implements GoodsReceiptDetailService {

	@Autowired
	private GoodsReceiptDetailRepo goodsReceiptDetailRepo;

	@Override
	public List<goods_receipt_detail> findDetailByRecieptId(Long id) {
		return goodsReceiptDetailRepo.findDetailByRecieptId(id);
	}

	@Override
	public <S extends goods_receipt_detail> Iterable<S> saveAll(Iterable<S> entities) {
		return goodsReceiptDetailRepo.saveAll(entities);
	}

}
