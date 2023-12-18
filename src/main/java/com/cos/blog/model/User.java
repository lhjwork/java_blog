package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
//Entity : 테이블 화 -> User 클래스가 자동으로 MySQL에 테이블이 생성된다. 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //Builder 경우는 가장 아래에 있는 것이 좋다. 
//@DynamicInsert // insert시에 null인 부분을 제외 시켜준다. 
@Component
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY.TABLE) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	
	@Column(nullable=false,length = 30, unique=true)
	private String username; //아이디
	
	@Column(nullable=false,length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable=false,length = 50)
	private String email;
	

	//@ColumnDefault("user")
	//DB는 RoleType이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. //admin , user, manager -> 도메인 설정을 해 줄 수 있다.
	
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate; 
}