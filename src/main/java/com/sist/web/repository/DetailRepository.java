package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.RecipeDetail;

public interface DetailRepository extends JpaRepository<RecipeDetail, Integer> {
	
}
