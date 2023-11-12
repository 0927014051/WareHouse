package com.javaweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.prediction;
import com.javaweb.model.ResultPredict;
import com.javaweb.reponsitory.PredictRepo;
import com.javaweb.service.PredictService;

@Service
public class PredictServiceImpl implements PredictService{
	
	@Autowired
	PredictRepo predictServiceImpl;
	
	@Override
	public 	prediction findPredictionByIdProduct(String product_id) {
		return predictServiceImpl.findPredictionByIdProduct(product_id);
	}
	
	@Override
	public List<prediction> findAll(){
		return predictServiceImpl.findAll();
	}
	
	@Override
	public 	<S extends prediction> S save(S entity) {
		return predictServiceImpl.save(entity);
	}
	
	@Override
	public void updatePrediction(String product_id, Long predict_id, Double value) {
		predictServiceImpl.updatePrediction(product_id, predict_id, value);
	}
	
	@Override
	public List<ResultPredict> findMatchingProducts(){
		return predictServiceImpl.findMatchingProducts();
	}

}
