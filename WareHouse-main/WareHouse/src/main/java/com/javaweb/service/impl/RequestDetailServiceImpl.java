package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.request;
import com.javaweb.entity.request_detail;
import com.javaweb.reponsitory.RequestDetailRepo;
import com.javaweb.service.RequestDetailService;

@Service
public class RequestDetailServiceImpl implements RequestDetailService{
	
	@Autowired
	private RequestDetailRepo requestDetailRepo;
	
	public 	List<request_detail> findAllRequestDetail(Long id){
		return requestDetailRepo.findAllRequestDetail(id);
	}
	
	@Override
	public <S extends request_detail> S save(S entity) {
		
		return requestDetailRepo.save(entity);
	}

}
