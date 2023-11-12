package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_issue_detail;
import com.javaweb.reponsitory.GoodsIssueDetailRepo;
import com.javaweb.service.GoodsIssueDetailService;

@Service
public class GoodsIssueDetailServiceImpl implements GoodsIssueDetailService{
	@Autowired
	private GoodsIssueDetailRepo goodsIssueDetailRepo;
	
	@Override
	public 	List<goods_issue_detail> findIssueDetailById(Long id){
		return goodsIssueDetailRepo.findIssueDetailById(id);
	}
	
	@Override
	public <S extends goods_issue_detail> Iterable<S> saveAll(Iterable<S> entities) {
		return goodsIssueDetailRepo.saveAll(entities);
	}
	
	@Override
	public 	int findTotalQuantityByProductId(String product_id) {
		return goodsIssueDetailRepo.findTotalQuantityByProductId(product_id);
	}
}
