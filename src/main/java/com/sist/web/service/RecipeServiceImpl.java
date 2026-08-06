package com.sist.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.web.entity.Chef;
import com.sist.web.entity.Recipe;
import com.sist.web.repository.ChefRepository;
import com.sist.web.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository rRepo;
	private final ChefRepository cRepo;
	@Override
	public List<Recipe> recipeList(int page) {
		Pageable pg=PageRequest.of(page-1,12,Sort.by(Sort.Direction.ASC,"no"));
		Page<Recipe> p=rRepo.findAll(pg);
		List<Recipe> list=new ArrayList<Recipe>();
		if(p!=null && p.hasContent()) {
			list=p.getContent();
		}
		return list;
	}

	@Override
	public int[] recipePageData(int page,int rowsize) {
		int totalpage=(int)Math.ceil(rRepo.count()/(double)rowsize);
		int startPage=((page-1)/10*10)+1;
		int endPage=((page-1)/10*10)+10;
		if(endPage>totalpage) endPage=totalpage;
		int[] pages= {page,totalpage,startPage,endPage};
		return pages;
	}

	@Override
	public List<Recipe> findByTitleContains(String title) {
		return rRepo.findByTitleContains(title);
	}

	@Override
	public List<Recipe> findByChefContains(String chef) {
		return rRepo.findByChefContains(chef);
	}

	@Override
	public List<Chef> chefList(int page) {
		Pageable pg=PageRequest.of(page-1,20,Sort.by(Sort.Direction.ASC,"no"));
		Page<Chef> p=cRepo.findAll(pg);
		List<Chef> list=new ArrayList<Chef>();
		if(p!=null && p.hasContent()) {
			list=p.getContent();
		}
		return list;
	}
	
}
