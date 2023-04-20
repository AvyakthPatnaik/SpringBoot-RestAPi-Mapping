package com.nexential.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nexential.springBoot.entity.Category;

public interface CategoryRepository
		extends JpaRepository<Category, Long>,PagingAndSortingRepository<Category, Long> {

}
