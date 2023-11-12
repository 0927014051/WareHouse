package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.goods_issue;
import com.javaweb.reponsitory.GoodsIssueRepo;
import com.javaweb.service.GoodsIssueService;

@Service
public class GoodsIssueServiceImpl implements GoodsIssueService{
	
	@Autowired
	private GoodsIssueRepo goodsIssueRepo;
	
	@Override
	public List<goods_issue> findAll(){
		
		return goodsIssueRepo.findAll();
	}
	
	@Override
	public goods_issue findIssueById(Long id) {
		return goodsIssueRepo.findIssueById(id);
	}
	
	@Override
	public <S extends goods_issue> S save(S entity) {
		
		return goodsIssueRepo.save(entity);
	}
	
	@Override
	public long count() {
		return goodsIssueRepo.count();
	}

}
