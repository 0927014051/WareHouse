package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.product;
import com.javaweb.entity.staffs;
import com.javaweb.model.ResultPredict;

public interface ProductService {
	
	long count();
	List<product> findAll();
	<S extends product> S save(S entity);
	void updateQuantityProductByid(String product_id, int quantity);
	
	product findById(String id);
	
	void updateQuantityProductIssueByid(String product_id, int quantity);

	Double findAvgByProductId(String productId, int quantity_first, int quantity_last);
	
	long totalCostInventory();
	
    List<ResultPredict> findMatchingProducts();
	int quantityProductById(String product_id);



}
