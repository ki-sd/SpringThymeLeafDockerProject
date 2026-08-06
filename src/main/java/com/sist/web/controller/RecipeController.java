package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

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
		int[] pages=rService.recipePageData(page);
		model.addAttribute("list", list);
		model.addAttribute("curpage", pages[0]);
		model.addAttribute("totalpage", pages[1]);
		model.addAttribute("startPage", pages[2]);
		model.addAttribute("endPage", pages[3]);
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
}
