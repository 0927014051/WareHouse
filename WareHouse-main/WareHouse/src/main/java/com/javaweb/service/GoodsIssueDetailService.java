package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.goods_issue_detail;
import com.javaweb.entity.goods_receipt_detail;

public interface GoodsIssueDetailService {
	List<goods_issue_detail> findIssueDetailById(Long id);
	
	<S extends goods_issue_detail> Iterable<S> saveAll(Iterable<S> entities) ;
	
	int findTotalQuantityByProductId(String product_id);

}
