package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.data_month;
import com.javaweb.reponsitory.DataMonthRepo;
import com.javaweb.service.DataMonthService;

@Service
public class DataMonthServiceImpl implements DataMonthService{
	
	@Autowired
	DataMonthRepo dataMonthRepo;
	
	@Override
	public 	List<data_month> findAll(){
		return dataMonthRepo.findAll();
	}

}
