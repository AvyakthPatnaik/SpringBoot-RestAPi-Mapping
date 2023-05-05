package com.nexential.springBoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexential.springBoot.entity.Teacher;
import com.nexential.springBoot.repsitory.TeacherRepository;

@RestController
@RequestMapping("/teachers")
public class Teachercontroller {

    @Autowired
    TeacherRepository teacherRepository;
    //Select

    @GetMapping
    List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
    //insert
    @PostMapping
    Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    
    //Sorting in Descending
    
    @GetMapping("sort")
	public Iterable<Teacher> getsort(@RequestParam String field){
		return teacherRepository.findAll(Sort.by(Direction.DESC,field));
	}
	
	//Sorting in Ascending
	
	@GetMapping("sort1")
	public Iterable<Teacher> getsorta(@RequestParam String field){
		return teacherRepository.findAll(Sort.by(Direction.ASC,field));
	}
	
	 //Update
	
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateUser(@PathVariable("id") Long id,
                                           @RequestBody Teacher u){
    	 Optional<Teacher> user = teacherRepository.findById(id);
         if(user.isPresent()) {
        	 user.get().setName(u.getName());
         return new ResponseEntity<>(teacherRepository.save(user.get()), HttpStatus.OK);
         }
         else {
         	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    
    //Delete
    @DeleteMapping("{id}")
   	ResponseEntity<Void> delete(@PathVariable("id") Long id){
   		Optional<Teacher> user = teacherRepository.findById(id);
   		if(user.isPresent()) {
   			teacherRepository.deleteById(id);
   			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   		}
   		else {
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
   	}
    
    
    @GetMapping("/{pageNo}/{pageSize}")
    public List<Teacher> findpage(int pageNo, int pageSize) {
		Pageable paging =PageRequest.of(pageNo, pageSize);
		Page<Teacher> pageresult=teacherRepository.findAll(paging);
		return pageresult.toList();
	}
    
    
    
}
