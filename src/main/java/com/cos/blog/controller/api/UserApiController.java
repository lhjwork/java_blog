package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {// username, password, email
		System.out.println("UserApi"+ user);
		user.setRole(RoleType.USER);
		int result = userService.SignUpService(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		
		
	}
	
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {//RequsetBody가 안걸려 있으면 json 데이터를 못 받는다. 
			System.out.println("UserApi"+ user);
			
			userService.userInfoUpdate(user);
			
			// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음
			// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
			
			//세션 등록
			//DB의 값이 변경이 된 후 로그인 값을 확인해서 조회가 가능하므로 DB 값 변경 후 처리해야함
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		
		return new ResponseDto<Integer>(HttpStatus.OK,1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		
		
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