package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sist.web.service.DataBoardService;
import com.sist.web.vo.DataBoardVO;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DataBoardController {
	private final DataBoardService dService;
	
	@GetMapping("/databoard/list")
	public String databoard_list(@RequestParam(value="page",defaultValue="1")int page,Model model) {
		List<DataBoardVO> list=dService.databoardListData(page);
		model.addAttribute("list", list);
		int[] pages=dService.databoardPageData(page);
		String[] tags= {"curpage","totalpage","startPage","endPage","count"};
		for(int i=0;i<pages.length;i++) {
			model.addAttribute(tags[i], pages[i]);
		}
		model.addAttribute("main_html", "databoard/list");
		return "main/main";
	}
	
	@GetMapping("/databoard/insert")
	public String databoard_insert(Model model) {
		model.addAttribute("main_html", "databoard/insert");
		return "main/main";
	}
	
	@PostMapping("/databoard/insert_ok")
	public String databoard_insert_ok(@ModelAttribute("vo")DataBoardVO vo,HttpServletRequest request) throws Exception {
		String uploadDir=request.getServletContext().getRealPath("/upload");
		System.out.println(uploadDir);
		File dir=new File(uploadDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		List<MultipartFile> files=vo.getFiles();
		String filename="";
		String filesize="";
		boolean bCheck=false; // 파일 여부 구분
		for(MultipartFile file:files) {
			if(!file.isEmpty()) {
				String oname=file.getOriginalFilename();
				File f=new File(uploadDir,oname);
				if(f.exists()) {
					String name=oname.substring(0,oname.lastIndexOf("."));
					String ext=oname.substring(oname.lastIndexOf("."));
					int count=1;
					while(f.exists()) {
						String newName=name+"("+count+")"+ext;
						f=new File(uploadDir+"/"+newName);
						count++;
					}
				}
				bCheck=true;
				Path path=Paths.get(uploadDir,f.getName()); // 운영체제에 따라 / or \ 자동으로
				Files.copy(file.getInputStream(), path);
				filename+=f.getName()+",";
				filesize+=f.length()+",";
			}
		}
		if(bCheck) {
			filename=filename.substring(0,filename.lastIndexOf(","));
			filesize=filesize.substring(0,filesize.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			vo.setFilecount(files.size());
			
		}else {
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		dService.databoardInsert(vo);
		return "redirect:/databoard/list";
	}
}
