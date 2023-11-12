package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.supplier;
import com.javaweb.reponsitory.SupplierRepo;
import com.javaweb.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepo supplierRepo;
	
	@Override
	public List<supplier> findAll(){
		return supplierRepo.findAll();
	}
	
	@Override
	public supplier findSupplierById(Long id) {
		return supplierRepo.findSupplierById(id);
	}

}
