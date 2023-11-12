package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.shelf;

public interface ShelfService {

	List<shelf> findAll();
	
	shelf findShelfIdbyName(String name);
	
	shelf findShelfIdbyShelfId(Long name);
}
