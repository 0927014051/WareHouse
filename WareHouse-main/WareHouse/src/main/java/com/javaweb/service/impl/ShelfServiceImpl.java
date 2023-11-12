package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.shelf;
import com.javaweb.reponsitory.ShelfRepo;
import com.javaweb.service.ShelfService;

@Service
public class ShelfServiceImpl implements ShelfService{
	
	@Autowired
	ShelfRepo shelfRepo;
	
	@Override
	public List<shelf> findAll(){
		return shelfRepo.findAll();
	}
	
	@Override
	public shelf findShelfIdbyName(String name) {
		return shelfRepo.findShelfIdbyName(name);
	}
	
	@Override
	public shelf findShelfIdbyShelfId(Long name) {
		return shelfRepo.findShelfIdbyShelfId(name);
	}

}
