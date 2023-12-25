package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cos.blog.model.RoleType;
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
	public User UserInfoFind(String username) {
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		
		return user;
	}

	
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

	
	@Transactional
	public void userInfoUpdate(User user) {
		System.out.println("ssdfs"+ user);
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고,영속화되니 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다. 
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원정보를 찾을 수 없습니다.");
		});
		
		//Oauth가 있는 애들은 패스워드가 절대 지워지지 않게 함
		// Validate 체크
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
		}
		
		
		persistance.setEmail(user.getEmail());
		
		
		
		
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commmit 이 자동으로 된다. 
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 넘겨줌
	
	}
	
	
	
	
	
	
}


// 전통적 방식의 로그인
//@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성: 데이터가 일치하는 가)
//public User loginService(User user) {
//
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	
//}

