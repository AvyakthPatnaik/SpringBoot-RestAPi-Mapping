package com.nexential.springBoot.service;

import java.util.List;

import com.nexential.springBoot.entity.Category;

public interface finalservice {

	
	public List<Category> findpage(int pageNo,int pageSize);
	public Category update(Category cat);
	
	
	
}
