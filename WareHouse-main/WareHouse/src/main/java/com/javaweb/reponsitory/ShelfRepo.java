package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.shelf;

@Repository
public interface ShelfRepo extends JpaRepository<shelf, Long>{

	@Query(value = "SELECT * FROM shelf WHERE shelf_name = ?1  ", nativeQuery = true)
	shelf findShelfIdbyName(String name);
	
	@Query(value = "SELECT * FROM shelf WHERE shelf_id = ?1  ", nativeQuery = true)
	shelf findShelfIdbyShelfId(Long name);
}
