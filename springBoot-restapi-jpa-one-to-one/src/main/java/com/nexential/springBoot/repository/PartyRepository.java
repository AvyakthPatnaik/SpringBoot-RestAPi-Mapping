package com.nexential.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexential.springBoot.entity.Party;

public interface PartyRepository extends JpaRepository<Party ,Integer> {

}
