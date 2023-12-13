package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	
	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {// username, password, email
		System.out.println("UserApi"+ user);
		user.setRole(RoleType.USER);
		int result = userService.SignUpService(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		
		
	}
	
	

}

// 전통적 방식의 로그인

//@PostMapping("/api/user/login")
//public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
//	System.out.println("UserApiController : login 호출됨");
//	User principal = userService.loginService(user); // 접근주체 (principal)
//	System.out.println("principal 확인"+ principal);
//	if(principal != null) {
//		System.out.println("42");
//		session.setAttribute("principal", principal); // 세션이 만들어 짐
//		System.out.println("44");
//	}else {
//		System.out.println("46");
//	}
//	return new ResponseDto<Integer>(HttpStatus.OK,1);
//	
//}