package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.role;
import com.javaweb.reponsitory.RoleRepo;
import com.javaweb.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepo RoleRepo;
	
	@Override
	public List<role> findAll(){
		return RoleRepo.findAll();
	}

}
