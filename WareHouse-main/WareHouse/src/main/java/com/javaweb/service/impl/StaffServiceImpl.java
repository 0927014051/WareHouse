package com.javaweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.staffs;
import com.javaweb.reponsitory.StaffRepo;
import com.javaweb.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepo staffRepo;
	
	@Override
	public List<staffs> findAll(){
		return staffRepo.findAll();
	}
	
	@Override
	public staffs findUserAccount(String username){
		return staffRepo.findUserAccount(username);
	}
	
	@Override
	public void updateStaff(String address, Date birthday, String first_name, String last_name, String phone, String gender, Long staff_id ) {
		  staffRepo.updateStaff(address, birthday, first_name, last_name, phone, gender, staff_id);
	}
	
	@Override
	public <S extends staffs> S save(S entity) {
		return staffRepo.save(entity);
	}
	
	@Override
	public 	void changePassword(Long staff_id, String password) {
		staffRepo.changePassword(staff_id, password);
	}
	
	@Override
	public boolean existsByEmail(String email) {
		
		return staffRepo.existsByEmail(email);
	}
	
	@Override
	public     boolean existsByPassword(String password) {
		return staffRepo.existsByPassword(password);
	}


}
