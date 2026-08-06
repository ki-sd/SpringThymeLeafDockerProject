package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Chef;

public interface ChefRepository extends JpaRepository<Chef, Integer> {
	
}
