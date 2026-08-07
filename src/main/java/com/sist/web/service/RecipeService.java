package com.sist.web.service;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sist.web.entity.Chef;
import com.sist.web.entity.Recipe;
import com.sist.web.entity.RecipeDetail;
public interface RecipeService {
	public List<Recipe> recipeList(int page);
	public int[] recipePageData(int page,int rowsize);
	public Page<Recipe> findByTitleContains(String title,int page);
	public Page<Recipe> findByChefContains(String chef,int page);
	public List<Chef> chefList(int page);
	public int[] recipePageDataFind(int page,int rowsize,int type,String fd);
	public RecipeDetail recipeDetail(int no);
}
