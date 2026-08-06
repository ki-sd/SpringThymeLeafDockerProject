package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.sist.web.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	public Recipe findByNo(int no);
	public List<Recipe> findByTitleContains(String title);
	public List<Recipe> findByChefContains(String chef);
}
