package com.nexential.springBoot.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexential.springBoot.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
