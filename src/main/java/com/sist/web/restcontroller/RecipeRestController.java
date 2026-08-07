package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.Recipe;
import com.sist.web.service.RecipeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeRestController {
	private final RecipeService rService;
	
	@RequestMapping("/recipe/find_vue")
	public ResponseEntity<Map<String,Object>> recipe_find(@RequestParam(value="page",defaultValue="1")int page,@RequestParam(value="type",defaultValue="1")int type,@RequestParam(value="fd",defaultValue="고구마")String fd) {
		Page<Recipe> p=null;
		Map<String,Object> map=new HashMap<>();
		try {
			if(type==1) {
				p=rService.findByTitleContains(fd, page);
			}else if(type==2) {
				p=rService.findByChefContains(fd, page);
			}
			List<Recipe> list=p.getContent();
			int[] pages=rService.recipePageDataFind(page, 12, type, fd);
			map.put("find_list",list);
			map.put("curpage", pages[0]);
			map.put("totalpage", pages[1]);
			map.put("startPage", pages[2]);
			map.put("endPage", pages[3]);
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(map);
	}
}
