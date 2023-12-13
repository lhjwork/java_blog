package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//final 붙은 것에 대한 생성자 생성
//@RequiredArgsConstructor 
//public class Member {
//	private final int id;
//	private final String username;
//	private final String password;
//	private final String email;
//	
//	
//}
//@Getter
//@Sette

//Data : getter, setter 한번에 생성
//@Data
//@AllArgsConstructor
//@NoArgsConstructor // 디폴트 생성자
//public class Member {
//	private int id;
//	private String username;
//	private String password;
//	private String email;
//	
//	
//}


@Data
@NoArgsConstructor // 디폴트 생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
	
}
