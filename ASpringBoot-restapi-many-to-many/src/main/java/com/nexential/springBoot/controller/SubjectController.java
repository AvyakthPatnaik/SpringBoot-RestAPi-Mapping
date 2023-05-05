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
import com.nexential.springBoot.entity.Subject;
import com.nexential.springBoot.entity.Teacher;
import com.nexential.springBoot.repsitory.StudentRepository;
import com.nexential.springBoot.repsitory.SubjectRepository;
import com.nexential.springBoot.repsitory.TeacherRepository;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    Subject addStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();
        subject.enrolledStudents.add(student);
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    Subject assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        subject.setTeacher(teacher);
        return subjectRepository.save(subject);
    }
    
    
    @DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Optional<Subject> user = subjectRepository.findById(id);
		if(user.isPresent()) {
			subjectRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateUser(@PathVariable("id") Long id,
                                           @RequestBody Teacher u){
    	 Optional<Subject> user = subjectRepository.findById(id);
         if(user.isPresent()) {
        	 user.get().setName(u.getName());
         return new ResponseEntity<>(subjectRepository.save(user.get()), HttpStatus.OK);
         }
         else {
         	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    
    
    @GetMapping("/{pageNo}/{pageSize}")
    public List<Subject> findpage(int pageNo, int pageSize) {
		Pageable paging =PageRequest.of(pageNo, pageSize);
		Page<Subject> pageresult=subjectRepository.findAll(paging);
		return pageresult.toList();
	}
    
    
    
    @GetMapping("sort")
	public Iterable<Subject> getsort(@RequestParam String field){
		return subjectRepository.findAll(Sort.by(Direction.DESC,field));
	}
	
	//Sorting in Ascending
	
	@GetMapping("sort1")
	public Iterable<Subject> getsorta(@RequestParam String field){
		return subjectRepository.findAll(Sort.by(Direction.ASC,field));
	}
}
