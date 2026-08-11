package com.sist.web.restcontroller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.Recipe;
import com.sist.web.entity.RecipeDetail;
import com.sist.web.service.RecipeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class RecipeRestController {
	private final RecipeService rService;
	
	@GetMapping("/recipe/list_vue")
	public ResponseEntity<Map<String,Object>> recipe_list(@RequestParam(value="page",defaultValue="1")int page) {
		Map<String,Object> map=new HashMap<>();
		try {
			List<Recipe> list=rService.recipeList(page);
			int[] pages=rService.recipePageData(page,12);
			map.put("list",list);
			map.put("curpage", pages[0]);
			map.put("totalpage", pages[1]);
			map.put("startPage", pages[2]);
			map.put("endPage", pages[3]);
			map.put("count", pages[4]);
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/recipe/detail_vue")
	public ResponseEntity<Map<String,Object>> recipe_detail_vue(@RequestParam("no")int no){
		Map<String,Object> map=new HashMap<>();
		RecipeDetail vo=new RecipeDetail();
		try {
			vo=rService.recipeDetail(no);
			map.put("vo", vo);
			List<String> mList=new ArrayList<String>();
			List<String> iList=new ArrayList<String>();
			String[] makes=vo.getFoodmake().split("\n");
			for(String s:makes)
			{
				StringTokenizer st=new StringTokenizer(s,"^");
				mList.add(st.nextToken());
				iList.add(st.nextToken());
			}
			map.put("mList", mList);
			map.put("iList", iList);
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(map);
	}
	
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
			map.put("list",list);
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
