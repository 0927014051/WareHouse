package com.javaweb.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.goods_order_detail;

@Repository
public interface GoodsOrderDetailRepo extends JpaRepository<goods_order_detail, Long>{
	@Query(value = "SELECT * FROM goods_order_detail WHERE g_order_id = ?1  ", nativeQuery = true)
	List<goods_order_detail> findOrderDetailById(Long g_order_id);
	

}
