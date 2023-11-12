package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.product;
import com.javaweb.model.ResultPredict;

@Repository
public interface ProductRepo extends JpaRepository<product, String>{
	@Modifying
    @Transactional
	@Query(value = "UPDATE product SET inventory_number = inventory_number + ?2 WHERE product_id = ?1", nativeQuery = true)
	void updateQuantityProductByid(String product_id, int quantity);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE product SET inventory_number = inventory_number - ?2 WHERE product_id = ?1", nativeQuery = true)
	void updateQuantityProductIssueByid(String product_id, int quantity);
	
	@Query(value = "SELECT (?2/ (inventory_number + ?3)) * 100 as total FROM product WHERE product_id = ?1", nativeQuery = true)
    Double findAvgByProductId(String productId, int quantity_first, int quantity_last);
	
	
	@Query(value = "SELECT sum(price*inventory_number) as total FROM product", nativeQuery = true)
	long totalCostInventory();
	
	 @Query("SELECT NEW com.javaweb.model.ResultPredict(p.product_id, a.product_name, p.predicted_value) " +
	           "FROM prediction p " +
	           "LEFT JOIN goods_receipt gr ON p.date_create <= gr.date " +
	           "LEFT JOIN goods_receipt_detail dt ON gr.g_receipt_id = dt.g_receipt_id " +
	           "AND dt.product_id = p.product_id " +
	           "JOIN product a ON a.product_id = p.product_id " +
	           "WHERE dt.product_id IS NULL")
	    List<ResultPredict> findMatchingProducts();
	
	@Query(value = "SELECT inventory_number FROM product WHERE product_id = ?1", nativeQuery = true)
	int quantityProductById(String product_id);

	
	

}
