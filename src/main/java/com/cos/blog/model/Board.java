package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	
	@Lob // 대용량 데이터
	private String content;
	
	
	@ColumnDefault("0") // 데이터가 int 임으로 ''(홑따옴표)가 필요가 없다.
	private int count; // 조회수
	
	
	@ManyToOne //board가 many user가 one에 해당한다. (Many = board, one = user) 한명의 user는 여러개의 게시글을 쓸 수 있다는 의미
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 
	
//	sql로 했을 경우에는
//	private int userId; // forienKey를 줘야한다. 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}