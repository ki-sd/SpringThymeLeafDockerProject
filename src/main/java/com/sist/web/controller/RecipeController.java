package com.sist.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

import com.sist.web.entity.Chef;
import com.sist.web.entity.Recipe;
import com.sist.web.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	private final RecipeService rService;
	
	@GetMapping("/main/main")
	public String recipe_list(@RequestParam(value="page",defaultValue="1")int page,Model model) {
		List<Recipe> list=rService.recipeList(page);
		int[] pages=rService.recipePageData(page,12);
		model.addAttribute("list", list);
		model.addAttribute("curpage", pages[0]);
		model.addAttribute("totalpage", pages[1]);
		model.addAttribute("startPage", pages[2]);
		model.addAttribute("endPage", pages[3]);
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
	
	@GetMapping("/recipe/chef_list")
	public String recipe_chef(@RequestParam(value="page",defaultValue="1")int page,Model model) {
		List<Chef> list=rService.chefList(page);
		int[] pages=rService.recipePageData(page,20);
		model.addAttribute("list", list);
		model.addAttribute("curpage", pages[0]);
		model.addAttribute("totalpage", pages[1]);
		model.addAttribute("startPage", pages[2]);
		model.addAttribute("endPage", pages[3]);
		model.addAttribute("main_html", "recipe/chef");
		return "main/main";
	}
	
	@GetMapping("/recipe/find")
	public String recipe_find(@RequestParam(value="type",required=false)Integer type,@RequestParam(value="fd",required=false)String fd,Model model) {
		if(type!=null || fd!=null) {
			model.addAttribute("type", type);
			model.addAttribute("fd",fd);
		}
		model.addAttribute("main_html", "recipe/find");
		return "main/main";
	}
}
