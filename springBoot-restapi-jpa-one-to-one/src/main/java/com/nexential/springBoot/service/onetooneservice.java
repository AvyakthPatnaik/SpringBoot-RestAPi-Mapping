package com.nexential.springBoot.service;

import java.util.List;

import com.nexential.springBoot.entity.Voter;


public interface onetooneservice {

	List<Voter> findPaginated(int pageNo,int PigeSize);

	
}
