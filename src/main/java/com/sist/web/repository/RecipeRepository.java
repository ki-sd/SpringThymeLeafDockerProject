package com.sist.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.sist.web.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	public Recipe findByNo(int no);
	public Page<Recipe> findByTitleContains(String title,Pageable pg);
	public Page<Recipe> findByChefContains(String chef,Pageable pg);
	public long countByTitleContains(String title);
	public long countByChefContains(String chef);
	@Query(value="""
			SELECT *
			FROM recipe
			WHERE no IN(SELECT no FROM recipe
						INTERSECT
						SELECT no FROM recipedetail)
			ORDER BY no ASC
			OFFSET :start ROWS FETCH NEXT 12 ROWS ONLY
			""",nativeQuery=true)
	public List<Recipe> recipeListData(@Param("start")int start);
	@Query(value="""
			SELECT COUNT(*)
			FROM recipe
			WHERE no IN(SELECT no FROM recipe
						INTERSECT
						SELECT no FROM recipedetail)
			""",nativeQuery=true)
	public int recipeListCount();
}
