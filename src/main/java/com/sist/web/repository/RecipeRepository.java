package com.sist.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.sist.web.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	public Recipe findByNo(int no);
	public Page<Recipe> findByTitleContains(String title,Pageable pg);
	public Page<Recipe> findByChefContains(String chef,Pageable pg);
	public long countByTitleContains(String title);
	public long countByChefContains(String chef);
}
