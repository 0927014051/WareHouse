package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.data_month;

@Repository
public interface DataMonthRepo extends JpaRepository<data_month, Integer>{

}
