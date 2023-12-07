package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;
	
	
	//http://localhost:8000/blog/dummuy/join(요청)
	//http의 body에 username,password,email 데이터를 가지고 (요청) 하면 파라미터에 쏙쏙쏙 들어감
	@PostMapping("/dummy/join") // x-www-form-url은 파라미터로 자동으로 spring이 파싱해서 key=value로 받음
	public String join(String username, String password, String email){
		
		System.out.println("username :"+ username);
		System.out.println("password :"+ password);
		System.out.println("email :"+ email);
		
		return "회원가입이 완료되었습니다.";
	}
	
	
	//http://localhost:8000/blog/dummuy/join(요청)
		//http의 body에 username,password,email 데이터를 가지고 (요청) 하면 파라미터에 쏙쏙쏙 들어감
		@PostMapping("/dummy/objectjoin") // 오브젝트로도 받을 수 있다. 
		public String Objectjoin(User user){
			System.out.println("id :"+ user.getId());
			System.out.println("username :"+ user.getUsername());
			System.out.println("password :"+ user.getPassword());
			System.out.println("email :"+ user.getEmail());
			System.out.println("role :"+ user.getRole());
			System.out.println("createDate :"+ user.getCreateDate());
			
			userRepository.save(user);
			return "회원가입이 완료되었습니다.";
		}
}	
