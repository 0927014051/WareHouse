package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.request;

@Repository
public interface RequestRepo extends JpaRepository<request, Long>{
	
	@Query(value = "SELECT * FROM request WHERE request_id = ?1  ", nativeQuery = true)
	request findRequestById(Long id);
	
	@Query(value = "SELECT * FROM request WHERE status = 2  ", nativeQuery = true)
	List<request> findRquestStatusById();
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE request SET status = ?2 WHERE request_id = ?1", nativeQuery = true)
	void updateRequestStatusById(Long request_id, int status);
	
	@Query(value = "SELECT *  FROM request WHERE status = 2 AND type = 'IMPORT' AND staff_warehouse_id = ?1", nativeQuery = true)
	List<request> findRequestImportByid(Long staff_id);
	
	@Query(value = "SELECT *  FROM request WHERE status = 1 AND type = 'EXPORT' AND staff_sale_id = ?1", nativeQuery = true)
	List<request> findRequestExportByid(Long staff_id);




}
