package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.auth.PrincipalDetail;

@Controller
public class BoardController {

	//아무것도 안적을 때와 / 만 했을 떄 저리로 감
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤로에서 세션을 어떻게 찾는지?
		System.out.println("로그인 사용자 아이디"+ principal.getUsername());
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
