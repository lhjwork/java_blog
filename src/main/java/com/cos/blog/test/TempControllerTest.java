package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		
		//파일리턴 기본경로 : src/main/resources/static 인데
		// 그냥 return "home.html";을 할 경우 src/main/resources/statichome.html으로 되어있어서 못찾는다.
		// 리턴명 : /home.html로 해야 한다. 
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	

	//http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/test.jsp
		
		return "test";
	}


	
}
