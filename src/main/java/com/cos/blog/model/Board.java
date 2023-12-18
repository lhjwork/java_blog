package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
	
	
	private int count; // 조회수
	
	
	@ManyToOne(fetch=FetchType.EAGER) //board가 many user가 one에 해당한다. (Many = board, one = user) 한명의 user는 여러개의 게시글을 쓸 수 있다는 의미
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 
	
	
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다. (난 FK가 아니에요.) DB에 컬럼을 만들지 마세요.
	private List<Reply> reply;
	
	
//	sql로 했을 경우에는
//	private int userId; // forienKey를 줘야한다. 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}