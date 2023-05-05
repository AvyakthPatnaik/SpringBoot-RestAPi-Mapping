package com.nexential.springBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nexential.springBoot.entity.User;
import com.nexential.springBoot.repository.UserRepository;

@Service
public class Userserviceimpl implements userService{

	@Autowired
	private UserRepository repository;

	public List<User> findPaginated(int pageNo, int PageSize) {
		Pageable paging=PageRequest.of(pageNo, PageSize) ;
		Page<User> pagedResult = repository.findAll(paging);
		return pagedResult.toList();
	}
	
	@Override
	public User update(User car) {
		return repository.save(car);
	}
}
