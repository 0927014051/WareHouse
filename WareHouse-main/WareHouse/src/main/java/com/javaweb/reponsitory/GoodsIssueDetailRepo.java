package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_issue_detail;

@Repository
public interface GoodsIssueDetailRepo extends JpaRepository<goods_issue_detail, Long>{
	@Query(value = "SELECT * FROM goods_issue_detail WHERE g_issue_id = ?1  ", nativeQuery = true)
	List<goods_issue_detail> findIssueDetailById(Long id);
	
	@Query(value = "SELECT sum(quantity) as total_quantity FROM goods_issue_detail WHERE product_id = ?1  ", nativeQuery = true)
	int findTotalQuantityByProductId(String product_id);
	

}
