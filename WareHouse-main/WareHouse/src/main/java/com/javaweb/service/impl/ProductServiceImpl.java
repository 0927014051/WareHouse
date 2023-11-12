package com.javaweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.product;
import com.javaweb.entity.staffs;
import com.javaweb.model.ResultPredict;
import com.javaweb.reponsitory.ProductRepo;
import com.javaweb.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Override
	public long count() {
		return productRepo.count();
	}

	@Override
	public List<product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public <S extends product> S save(S entity) {

		return productRepo.save(entity);
	}

	@Override
	public void updateQuantityProductByid(String product_id, int quantity) {
		productRepo.updateQuantityProductByid(product_id, quantity);
	}

	@Override
	public product findById(String id) {
		Optional<product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			// Xử lý trường hợp không tìm thấy sản phẩm
			return null; // hoặc ném một ngoại lệ tùy theo yêu cầu của bạn
		}
	}

	@Override
	public void updateQuantityProductIssueByid(String product_id, int quantity) {
		productRepo.updateQuantityProductIssueByid(product_id, quantity);
	}

	@Override
	public Double findAvgByProductId(String productId, int quantity,int quantity_last) {
		return productRepo.findAvgByProductId(productId, quantity,quantity_last);
	}
	
	@Override
	public 	long totalCostInventory() {
		return productRepo.totalCostInventory();
	}
	
	@Override
	public 	    List<ResultPredict> findMatchingProducts(){
		return productRepo.findMatchingProducts();
	}
	
	@Override
	public 	int quantityProductById(String product_id) {
		return productRepo.quantityProductById(product_id);
	}
}
