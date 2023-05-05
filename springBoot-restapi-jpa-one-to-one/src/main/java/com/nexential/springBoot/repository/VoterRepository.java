package com.nexential.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.nexential.springBoot.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Integer> ,PagingAndSortingRepository<Voter,Integer>  {
//
//	@Query("select new com.nexential.springBoot.DTO.Joinquery(v.name,p.logo from Voter v join v.party p")
//	public String joininfo();
}
