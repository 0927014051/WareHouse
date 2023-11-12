package com.javaweb.service;

import java.util.Date;
import java.util.List;

import com.javaweb.entity.box;

public interface BoxService {
	
	//void updateLimitBox(String box_id);
	List<box> findAll();
	void updateLimitBox(String box_id,String shelf_id) ;
	box findBoxbyShelfId(Long id);
	box findBoxbyProductId(String id);
	void updateQuantityBoxByProductId(String product_id,int quantity,Date date);
	void updateQuantityBoxIssueByProductId(String product_id,int quantity,Date date);


}
