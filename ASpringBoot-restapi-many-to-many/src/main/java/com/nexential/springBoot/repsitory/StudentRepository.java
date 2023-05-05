package com.nexential.springBoot.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexential.springBoot.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
