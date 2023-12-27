package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.dto.ReplySaveRequestDto;

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

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}
	
//	public void update(User user, Board board, String content) {
//		setUser(user);
//		setBoard(board);
//		setContent(content);
//	}
//	
}