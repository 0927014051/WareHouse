package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.goods_issue;
import com.javaweb.entity.goods_order;

public interface GoodsIssueService {

		List<goods_issue> findAll();
		goods_issue findIssueById(Long id);
		
		<S extends goods_issue> S save(S entity);
		
		long count();
}
