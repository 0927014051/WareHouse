package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.request;
import com.javaweb.reponsitory.RequestRepo;
import com.javaweb.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	private RequestRepo requestRepo;
	
	@Override
	public 	List<request> findAll(){
		return requestRepo.findAll();
	}
	
	@Override
	public 	request findRequestById(Long id) {
		return requestRepo.findRequestById(id);
	}

	@Override
	public <S extends request> S save(S entity) {
		
		return requestRepo.save(entity);
	}
	
	@Override
	public 	List<request> findRquestStatusById(){
		
		return requestRepo.findRquestStatusById();
	}
	
	@Override
	public 	void updateRequestStatusById(Long request_id,int status) {
		requestRepo.updateRequestStatusById(request_id,status);
	}
	
	@Override
	public 	List<request> findRequestImportByid(Long staff_id){
		return requestRepo.findRequestImportByid(staff_id);
	}
	@Override
	public 	List<request> findRequestExportByid(Long staff_id){
		return requestRepo.findRequestExportByid(staff_id);
	}
}
