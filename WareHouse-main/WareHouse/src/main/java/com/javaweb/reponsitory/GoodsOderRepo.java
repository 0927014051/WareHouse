package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_order;

@Repository
public interface GoodsOderRepo extends JpaRepository<goods_order, Long>{
	
	@Query(value = "SELECT * FROM goods_order WHERE g_order_id = ?1  ", nativeQuery = true)
	goods_order findOrderById(Long id);
	
	@Query(value = "SELECT * FROM warehouse.goods_order ORDER BY date DESC LIMIT 5", nativeQuery = true)
	List<goods_order> listOrderByDateLimit();


}
