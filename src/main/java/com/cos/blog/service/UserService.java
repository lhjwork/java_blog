package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	
	@Transactional
	public int SignUpService(User user) {
		
	
		try {
			
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: 회원가입()" + e.getMessage());
		}
		
		return -1;
	}

	
	
	
	
	
}


// 전통적 방식의 로그인
//@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성: 데이터가 일치하는 가)
//public User loginService(User user) {
//
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	
//}

