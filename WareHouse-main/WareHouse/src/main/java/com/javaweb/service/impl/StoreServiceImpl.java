package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.store;
import com.javaweb.reponsitory.StoreRepo;
import com.javaweb.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreRepo storeRepo;
	
	@Override
	public List<store> findAll(){
		
		return storeRepo.findAll();
	}

}
