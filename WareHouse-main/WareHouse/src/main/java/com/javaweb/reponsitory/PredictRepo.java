package com.javaweb.reponsitory;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.prediction;
import com.javaweb.model.ResultPredict;

@Repository
public interface PredictRepo extends JpaRepository<prediction, Long> {
	
	@Query(value = "SELECT * FROM prediction WHERE product_id = ?1  ", nativeQuery = true)
	prediction findPredictionByIdProduct(String product_id);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE prediction SET predicted_value = ?3 WHERE product_id = ?1 AND predict_id = ?2", nativeQuery = true)
	void updatePrediction(String product_id, Long predict_id, Double value);
	
	 @Query("SELECT p.product_id, p.product_name, pr.predicted_value " +
	           "FROM product p " +
	           "JOIN prediction pr ON p.product_id = pr.product_id " +
	           "WHERE p.inventory_number <= pr.quantity_inventory")
	    List<ResultPredict> findMatchingProducts();

}
