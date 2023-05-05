package com.nexential.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nexential.springBoot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> ,PagingAndSortingRepository<User,Integer>{

}
