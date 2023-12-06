package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable=false, length = 200)
	private String content;
	
	@ManyToOne // 여러개의 답변은 하나의 개시글에 존재 할 수 있다.
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변은 하나의 유저가 작성 할 수 있다.
	@JoinColumn(name="userId")
	private User user;
	
	
	@CreationTimestamp
	private Timestamp createDate;
}