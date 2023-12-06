package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
//Entity : 테이블 화 -> User 클래스가 자동으로 MySQL에 테이블이 생성된다. 
@Entity
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY.TABLE) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	
	@Column(nullable=false,length = 30)
	private String username; //아이디
	
	@Column(nullable=false,length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable=false,length = 50)
	private String email;
	
	@ColumnDefault("'user'") // -> " 'user' " 홑따옴표가 적용되어 있다.
	private String role; //Enum을 쓰는게 좋다. //admin , user, manager -> 도메인 설정을 해 줄 수 있다.
	
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate; 
}
