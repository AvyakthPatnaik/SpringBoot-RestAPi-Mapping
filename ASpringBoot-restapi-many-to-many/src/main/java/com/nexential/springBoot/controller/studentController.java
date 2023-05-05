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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexential.springBoot.entity.Student;
import com.nexential.springBoot.entity.Teacher;
import com.nexential.springBoot.repsitory.StudentRepository;

@RestController
@RequestMapping("/students")
public class studentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
    
    @DeleteMapping("{id}")
   	ResponseEntity<Void> delete(@PathVariable("id") Long id){
   		Optional<Student> user = studentRepository.findById(id);
   		if(user.isPresent()) {
   			studentRepository.deleteById(id);
   			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   		}
   		else {
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
   	}
    
    @GetMapping("/{pageNo}/{pageSize}")
    public List<Student> findpage(int pageNo, int pageSize) {
		Pageable paging =PageRequest.of(pageNo, pageSize);
		Page<Student> pageresult=studentRepository.findAll(paging);
		return pageresult.toList();
	}
    
    
    
    @GetMapping("sort")
	public Iterable<Student> getsort(@RequestParam String field){
		return studentRepository.findAll(Sort.by(Direction.DESC,field));
	}
	
	//Sorting in Ascending
	
	@GetMapping("sort1")
	public Iterable<Student> getsorta(@RequestParam String field){
		return studentRepository.findAll(Sort.by(Direction.ASC,field));
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Student> updateUser(@PathVariable("id") Long id,
                                           @RequestBody Teacher u){
    	 Optional<Student> user = studentRepository.findById(id);
         if(user.isPresent()) {
        	 user.get().setName(u.getName());
         return new ResponseEntity<>(studentRepository.save(user.get()), HttpStatus.OK);
         }
         else {
         	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
}
