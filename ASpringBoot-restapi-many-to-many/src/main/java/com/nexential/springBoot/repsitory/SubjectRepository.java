package com.nexential.springBoot.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexential.springBoot.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
