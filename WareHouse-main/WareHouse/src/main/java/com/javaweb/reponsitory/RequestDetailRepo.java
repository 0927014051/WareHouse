package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.request_detail;

@Repository
public interface RequestDetailRepo extends JpaRepository<request_detail, Long>{
	@Query(value = "SELECT * FROM request_detail WHERE request_id = ?1  ", nativeQuery = true)
	List<request_detail> findAllRequestDetail(Long id);
	

}
