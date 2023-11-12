package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.supplier;

public interface SupplierService {
	List<supplier> findAll();
	supplier findSupplierById(Long id);
}
