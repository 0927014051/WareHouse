package com.javaweb.reponsitory;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.staffs;

@Repository
public interface StaffRepo extends JpaRepository<staffs, Long>{
	@Query(value = "SELECT * FROM staffs where email = ?1", nativeQuery = true)
	staffs findUserAccount(String email);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE staffs s SET s.address = ?1,s.birthday = ?2, s.first_name = ?3, s.last_name = ?4,s.phone = ?5, s.gender = ?6 WHERE s.staff_id = ?7", nativeQuery = true)
	void updateStaff(String address, Date birthday, String first_name, String last_name, String phone, String gender, Long staff_id );
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE staffs s SET s.password = ?2 WHERE s.staff_id = ?1", nativeQuery = true)
	void changePassword(Long staff_id, String password);
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM staffs s WHERE s.email = ?1")
    boolean existsByEmail(String email);
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM staffs s WHERE s.password = ?1")
    boolean existsByPassword(String password);
	
	
	
	
}
