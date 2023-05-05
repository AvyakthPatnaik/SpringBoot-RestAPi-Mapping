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

import com.nexential.springBoot.entity.Voter;
import com.nexential.springBoot.repository.VoterRepository;
import com.nexential.springBoot.service.onetooneservice;
//import com.nexential.springBoot.service.onetooneservice;

@RestController
public class onetooneController {

	@Autowired
	VoterRepository voterrepo;
	@Autowired
	onetooneservice one;
	
	//pagination/Selecting
	
		@GetMapping("/voter/{pageNo}/{pageSize}")
		public List<Voter> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
			return one.findPaginated(pageNo, pageSize);
		}
		
		
		//sorting in Desc
		
		
		@GetMapping("/sorting")
		public Iterable<Voter> getAllByCols (@RequestParam String field1) {
			return voterrepo.findAll(Sort.by(Direction.DESC, field1));
		}
		
		//sorting in Asc
		
		
		@GetMapping("/sorting1")
		public Iterable<Voter> getAllByCols1 (@RequestParam String field1) {
			return voterrepo.findAll(Sort.by(Direction.ASC, field1));
		}
		
	
	//insert
	@PostMapping("/voter")
    public ResponseEntity<Voter> createUser(@RequestBody Voter user){
		Voter savedUser = voterrepo.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
	
	
	//Select
    // http://localhost:8080/voter
    @GetMapping("/voter")
    public ResponseEntity<List<Voter>> getAllUsers(){
        List<Voter> users = voterrepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
 // Select By ID
    // http://localhost:8080/voter/1
    @GetMapping("/voter/{id}")
    public ResponseEntity<Voter> getUserById(@PathVariable int id){
        Optional<Voter> user = voterrepo.findById(id);
        if(user.isPresent()) {
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    //Update
    // http://localhost:8080/voter/1
    @PutMapping("/voter/{id}")
    public ResponseEntity<Voter> updateUser(@PathVariable("id") int id,
                                           @RequestBody Voter u){
    	 Optional<Voter> user = voterrepo.findById(id);
         if(user.isPresent()) {
        	 user.get().setName(u.getName());
        	 user.get().setAge(u.getAge());
        	 user.get().setParty(u.getParty());
         return new ResponseEntity<>(voterrepo.save(user.get()), HttpStatus.OK);
         }
         else {
         	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    
    
    //Delete
    // http://localhost:8080/voter/1
    @DeleteMapping("/voter/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
    	 Optional<Voter> user = voterrepo.findById(id);
    	 if(user.isPresent()) {
    		 voterrepo.deleteById(id);
    	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	        }
    	        else {
    	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	        }
    }
    
    
//    //JOIN QUERY
//    @GetMapping("/joininfo")
//    public String joininfo() {
//    	return service.getJoinInfo();
//    }
}
