package com.sist.web.service;
import java.util.*;

import com.sist.web.entity.Chef;
import com.sist.web.entity.Recipe;
public interface RecipeService {
	public List<Recipe> recipeList(int page);
	public int[] recipePageData(int page,int rowsize);
	public List<Recipe> findByTitleContains(String title);
	public List<Recipe> findByChefContains(String chef);
	public List<Chef> chefList(int page);
}
