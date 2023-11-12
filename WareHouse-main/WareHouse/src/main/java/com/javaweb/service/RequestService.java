package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.request;

public interface RequestService {

	List<request> findAll();
	request findRequestById(Long id);
	<S extends request> S save(S entity);
	List<request> findRquestStatusById();
	void updateRequestStatusById(Long request_id, int status);

	List<request> findRequestImportByid(Long staff_id);
	List<request>  findRequestExportByid(Long staff_id);

}
