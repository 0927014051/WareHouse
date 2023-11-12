package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaweb.entity.goods_issue;

public interface GoodsIssueRepo extends JpaRepository<goods_issue, Long>{

	@Query(value = "SELECT * FROM goods_issue WHERE g_issue_id = ?1  ", nativeQuery = true)
	goods_issue findIssueById(Long id);
	
	
}
