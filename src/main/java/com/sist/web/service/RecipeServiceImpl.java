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
import com.sist.web.entity.RecipeDetail;
import com.sist.web.repository.ChefRepository;
import com.sist.web.repository.DetailRepository;
import com.sist.web.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository rRepo;
	private final ChefRepository cRepo;
	private final DetailRepository dRepo;
	@Override
	public List<Recipe> recipeList(int page) {
//		Pageable pg=PageRequest.of(page-1,12,Sort.by(Sort.Direction.ASC,"no"));
//		Page<Recipe> p=rRepo.findAll(pg);
//		List<Recipe> list=new ArrayList<Recipe>();
//		if(p!=null && p.hasContent()) {
//			list=p.getContent();
//		}
		List<Recipe> list=rRepo.recipeListData((page*12)-12);
		return list;
	}

	@Override
	public int[] recipePageData(int page,int rowsize) {
		int count=rRepo.recipeListCount();
		int totalpage=(int)Math.ceil(count/(double)rowsize);
		int startPage=((page-1)/10*10)+1;
		int endPage=((page-1)/10*10)+10;
		if(endPage>totalpage) endPage=totalpage;
		int[] pages= {page,totalpage,startPage,endPage,count};
		return pages;
	}

	@Override
	public Page<Recipe> findByTitleContains(String title,int page) {
		Pageable pg=PageRequest.of(page-1,12,Sort.by(Sort.Direction.ASC,"no"));
		return rRepo.findByTitleContains(title,pg);
	}

	@Override
	public Page<Recipe> findByChefContains(String chef,int page) {
		Pageable pg=PageRequest.of(page-1,12,Sort.by(Sort.Direction.ASC,"no"));
		return rRepo.findByChefContains(chef,pg);
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

	@Override
	public int[] recipePageDataFind(int page,int rowsize,int type,String fd) {
		int totalpage=0;
		if(type==1) {
			totalpage=(int)Math.ceil(rRepo.countByTitleContains(fd)/(double)rowsize);
		}else {
			totalpage=(int)Math.ceil(rRepo.countByChefContains(fd)/(double)rowsize);
		}
		int startPage=((page-1)/10*10)+1;
		int endPage=((page-1)/10*10)+10;
		if(endPage>totalpage) endPage=totalpage;
		int[] pages= {page,totalpage,startPage,endPage};
		return pages;
	}

	@Override
	public RecipeDetail recipeDetail(int no) {
		return dRepo.findByNo(no);
	}
	
}
