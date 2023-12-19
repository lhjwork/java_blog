package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	

	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.contentWrite(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK,1);
		
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.boardDelete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
		
		boardService.contentModify(id, board);
		
		return new ResponseDto<Integer>(HttpStatus.OK,1);
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