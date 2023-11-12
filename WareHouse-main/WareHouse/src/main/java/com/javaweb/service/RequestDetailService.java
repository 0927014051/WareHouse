package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.request_detail;

public interface RequestDetailService {
	List<request_detail> findAllRequestDetail(Long id);
	<S extends request_detail> S save(S entity);

}
