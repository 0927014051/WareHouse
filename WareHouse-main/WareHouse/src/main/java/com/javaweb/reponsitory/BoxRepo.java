package com.javaweb.reponsitory;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.box;

@Repository
public interface BoxRepo extends JpaRepository<box, String>{
//	@Modifying
//    @Transactional
//	@Query(value = "UPDATE box SET limit_value = 200 WHERE box_id = ?1", nativeQuery = true)
//	void updateLimitBox(String box_id);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE box SET product_id = ?2 WHERE box_id = ?1", nativeQuery = true)
	void updateLimitBox(String box_id,String shelf_id);
	
	@Query(value = "SELECT * FROM box WHERE shelf_id = ?1  ", nativeQuery = true)
	box findBoxbyShelfId(Long id);
	
	@Query(value = "SELECT * FROM box WHERE product_id = ?1  ", nativeQuery = true)
	box findBoxbyProductId(String id);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE box SET quantity = quantity + ?2,date = ?3 WHERE product_id = ?1", nativeQuery = true)
	void updateQuantityBoxByProductId(String product_id,int quantity,Date date);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE box SET quantity = quantity - ?2,date = ?3 WHERE product_id = ?1", nativeQuery = true)
	void updateQuantityBoxIssueByProductId(String product_id,int quantity,Date date);
	
}
