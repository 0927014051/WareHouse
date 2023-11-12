package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.store;
@Repository
public interface StoreRepo  extends JpaRepository<store, Long>{

}
