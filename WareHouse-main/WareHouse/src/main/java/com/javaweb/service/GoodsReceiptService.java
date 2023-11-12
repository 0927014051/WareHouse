package com.javaweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_order;
import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;

@Service
public interface GoodsReceiptService {
	List<goods_receipt> findAll();
	
	goods_receipt findReceiptById(Long id);
	<S extends goods_receipt> S save(S entity) ;
	
}
