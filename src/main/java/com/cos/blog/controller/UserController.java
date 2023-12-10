package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	//아무것도 안적을 때와 / 만 했을 떄 저리로 감
	@GetMapping({"","/"})
	public String index() {
		
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
