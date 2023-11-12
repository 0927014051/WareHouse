package com.javaweb.service;

import java.util.Date;
import java.util.List;

import com.javaweb.entity.prediction;
import com.javaweb.model.ResultPredict;

public interface PredictService {
	
	prediction findPredictionByIdProduct(String product_id);
	List<prediction> findAll();
	<S extends prediction> S save(S entity);
	
	void updatePrediction(String product_id, Long predict_id, Double value);
	
    List<ResultPredict> findMatchingProducts();



}
