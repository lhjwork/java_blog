package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html 파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;
	
	
	//{id}주소로 파라미터를 전달 받을 수 있다.
	//http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		//그럼 return null 이 리터이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		//Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!!!
	
//	   User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//		   @Override
//		public User get() {
//			// TODO Auto-generated method stub
//			return new User();
//		}
//	   });
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.id :"+id);
			}
		});
		
		// 람다식 위의 코드를 줄 아래처럼 줄일 수 있다.
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
	   return user;
	}
	
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
			
			
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return "회원가입이 완료되었습니다.";
		}
}	
