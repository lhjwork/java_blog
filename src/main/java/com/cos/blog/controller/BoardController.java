package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;



@Controller
public class BoardController {
	
	@Autowired
	private PrincipalDetail principal;

	
	//@AuthenticationPrincipal PrincipalDetail principal
	//아무것도 안적을 때와 / 만 했을 떄 저리로 감
	@GetMapping({"", "/"})
	public String index() { // 컨트롤로에서 세션을 어떻게 찾는지?

		return "index";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
