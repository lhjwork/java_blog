package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//@AuthenticationPrincipal PrincipalDetail principal
	//아무것도 안적을 때와 / 만 했을 떄 저리로 감
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.contentList(pageable));
		return "index"; //@Controller는 viewResolver 작동!! 해당 index 페이지로 model 정보 전달
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
