package com.cos.blog.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
	


	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/api/user")
	public ResponseDto<Integer> save( User user) {
		System.out.println("UserApi");
		return new ResponseDto<Integer>(200,1);
	}
}
