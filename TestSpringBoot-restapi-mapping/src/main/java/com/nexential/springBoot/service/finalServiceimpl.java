package com.nexential.springBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nexential.springBoot.entity.Category;
import com.nexential.springBoot.repository.CategoryRepository;
@Service
public class finalServiceimpl implements finalservice {

	@Autowired
	CategoryRepository repository;
	@Override
	public List<Category> findpage(int pageNo, int pageSize) {
		Pageable paging =PageRequest.of(pageNo, pageSize);
		Page<Category> pageresult=repository.findAll(paging);
		return pageresult.toList();
	}

	@Override
	public Category update(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

}
