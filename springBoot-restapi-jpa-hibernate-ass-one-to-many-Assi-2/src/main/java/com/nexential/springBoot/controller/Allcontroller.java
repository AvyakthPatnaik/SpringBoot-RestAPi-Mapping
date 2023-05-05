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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nexential.springBoot.entity.User;
import com.nexential.springBoot.repository.UserRepository;
import com.nexential.springBoot.service.userService;

@RestController
//@RequestMapping("/user")
public class Allcontroller {

	@Autowired
	UserRepository userRepository;
	@Autowired
	userService userService;
	//pagination/Selecting

		@GetMapping("/api/{pageNo}/{pageSize}")
		public List<User> getPaginated(@PathVariable int pageNo, @PathVariable int pageSize) {
			return userService.findPaginated(pageNo, pageSize);
		}

		// sorting in Desc

		@GetMapping("/sorting")
		public Iterable<User> getAllByCols(@RequestParam String field1) {
			return userRepository.findAll(Sort.by(Direction.DESC, field1));
		}

		// sorting in Asc

		@GetMapping("/sorting1")
		public Iterable<User> getAllByCols1(@RequestParam String field1) {
			return userRepository.findAll(Sort.by(Direction.ASC, field1));
		}
		
		//insert
	//	http://localhost:8080/api/categories?page=3
		@PostMapping("/api")
	    public ResponseEntity<User> createUser(@RequestBody User user){
			User savedUser = userRepository.save(user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }
		
		
		//Select
	    @GetMapping("/api")
	    public ResponseEntity<List<User>> getAllUsers(){
	        List<User> users = userRepository.findAll();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	    
	    
	 // Select By ID
	    @GetMapping("/api/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable int id){
	        Optional<User> user = userRepository.findById(id);
	        if(user.isPresent()) {
	        return new ResponseEntity<>(user.get(), HttpStatus.OK);
	        }
	        else {
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    
	    //Update
	    @PutMapping("/api")
	    public ResponseEntity<User> updateEmployee(@RequestBody User emp) {
	        return new ResponseEntity<>(userService.update(emp), HttpStatus.CREATED);
	    }
	    
	  
	    //Delete
	    
	    @DeleteMapping("/api/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
	    	 Optional<User> user = userRepository.findById(id);
	    	 if(user.isPresent()) {
	    		 userRepository.deleteById(id);
	    	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	        }
	    	        else {
	    	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	        }
	    }
}
