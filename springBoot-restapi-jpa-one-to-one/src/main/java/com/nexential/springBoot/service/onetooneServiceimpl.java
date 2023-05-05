package com.nexential.springBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexential.springBoot.entity.Voter;
import com.nexential.springBoot.repository.VoterRepository;

@Service
public class onetooneServiceimpl implements onetooneservice{

	@Autowired
	private VoterRepository repository;

	public List<Voter> findPaginated(int pageNo, int pageSize) {
		Pageable paging=PageRequest.of(pageNo, pageSize) ;
		Page<Voter> pagedResult = repository.findAll(paging);
		return pagedResult.toList();
	}
	
	
}
