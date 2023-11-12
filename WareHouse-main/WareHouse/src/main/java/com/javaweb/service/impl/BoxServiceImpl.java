package com.javaweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.box;
import com.javaweb.reponsitory.BoxRepo;
import com.javaweb.service.BoxService;

@Service
public class BoxServiceImpl implements BoxService {

	@Autowired
	BoxRepo boxRepo;

//	@Override
//	public 	void updateLimitBox(String box_id) {
//		boxRepo.updateLimitBox(box_id);
//	}

	@Override
	public List<box> findAll() {
		return boxRepo.findAll();
	}

	@Override
	public void updateLimitBox(String box_id, String shelf_id) {
		boxRepo.updateLimitBox(box_id, shelf_id);
	}

	@Override
	public box findBoxbyShelfId(Long id) {
		return boxRepo.findBoxbyShelfId(id);
	}

	@Override
	public box findBoxbyProductId(String id) {
		return boxRepo.findBoxbyProductId(id);
	}

	@Override
	public void updateQuantityBoxByProductId(String product_id, int quantity, Date date) {
		boxRepo.updateQuantityBoxByProductId(product_id, quantity, date);
	}
	
	@Override
	public 	void updateQuantityBoxIssueByProductId(String product_id,int quantity,Date date) {
		boxRepo.updateQuantityBoxIssueByProductId(product_id, quantity, date);
	}
}
