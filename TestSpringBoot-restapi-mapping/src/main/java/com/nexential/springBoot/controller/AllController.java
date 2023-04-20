package com.nexential.springBoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexential.springBoot.entity.Category;
import com.nexential.springBoot.repository.CategoryRepository;
import com.nexential.springBoot.service.finalservice;

@RestController
@RequestMapping("api/categories")
public class AllController {

	@Autowired
	CategoryRepository repository;
	@Autowired
	finalservice one;
	@GetMapping("/{pageNo}/{pageSize}")
	public List<Category> getpaginated(@PathVariable int pageNo,@PathVariable int pageSize){
		return one.findpage(pageNo, pageSize);
	}
	
	@GetMapping("sort")
	public Iterable<Category> getsort(@RequestParam String field){
		return repository.findAll(Sort.by(Direction.DESC,field));
	}
	@GetMapping("sort1")
	public Iterable<Category> getsorta(@RequestParam String field){
		return repository.findAll(Sort.by(Direction.ASC,field));
	}
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category user){
		Category saveduser = repository.save(user);
		return new ResponseEntity<>(saveduser,HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<Category>> getall(){
		List<Category> users = repository.findAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Category> update(@RequestBody Category cat){
		return new ResponseEntity<>(one.update(cat),HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Optional<Category> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<Category>getuserbyid(@PathVariable Long id){
		Optional<Category> user = repository.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
