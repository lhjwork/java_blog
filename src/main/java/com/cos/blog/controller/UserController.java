package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.KoToEnService;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/**
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private User user;

	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {

		return "/user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {

		return "/user/updateForm";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수
		// POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// Retrofit2
		// OkHttp
		// RestTemplate

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("grant_type", "authorization_code");
		params.add("client_id", "e5d9276604db6c6fa151deb069c0ac99");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);

//		ResponseEntity response = rt.exchange(code, null, kakaoReTokenRequest, null, params);
//		
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class // 응답을 받을 type : String이라는 class
		);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		System.out.println("카카오 엑세스 토큰 :" + oauthToken.getAccess_token());

		String AccessToken = oauthToken.getAccess_token();
		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders header2 = new HttpHeaders();
		header2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		header2.add("Authorization", "Bearer " + AccessToken);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(header2);

//		ResponseEntity response = rt.exchange(code, null, kakaoReTokenRequest, null, params);
//		
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class // 응답을 받을 type : String이라는 class
		);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		
		
		System.out.println("카카오 아이디 (번호) :" + kakaoProfile.getId());
		System.out.println("카카오 username  :" + kakaoProfile.getProperties().nickname);
		
		//UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("블로그서버 패스워드 :" + cosKey);
		
		UUID garbagePassword = UUID.randomUUID();
		
		KoToEnService koToEnService = new KoToEnService();
		
		String koToEnKakaoUsername = koToEnService.KoToEnTransForm(kakaoProfile.getProperties().nickname);
		
		System.out.println(" 보기 변환 된거:" +koToEnKakaoUsername);
		
		
		User kakaoUser = user.builder()
				.username(koToEnKakaoUsername+"_"+kakaoProfile.getId())
				.password(cosKey)
				.oauth("kakao")
				.build();
		
		// 가입자 혹은 비가입자 체크 해서 처리
		User originUser = userService.UserInfoFind(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
			kakaoUser.setRole(RoleType.USER);
			userService.SignUpService(kakaoUser);
		}
		
		System.out.println("자동 로그인을 진행합니다.");
		//로그인 처리
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		
		return "redirect:/";
	}

	// 토큰 요청 발급 주소(post)
	// https://kauth.kakao.com/oauth/token
}
