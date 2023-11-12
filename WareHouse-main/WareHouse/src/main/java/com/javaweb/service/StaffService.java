package com.javaweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.javaweb.entity.staffs;

public interface StaffService {
	List<staffs> findAll();

	staffs findUserAccount(String username);
	
	void updateStaff(String address, Date birthday, String first_name, String last_name, String phone, String gender, Long staff_id );
	
	<S extends staffs> S save(S entity);
	
	void changePassword(Long staff_id, String password);
	
    boolean existsByEmail(String email);

    boolean existsByPassword(String password);



}
