package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;



//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

	
	@Autowired 
	private BoardRepository boardRepository;
	
	
	
	@Transactional
	public void contentWrite(Board board, User user) { // title. content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	public Page<Board> contentList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	
	
}


// 전통적 방식의 로그인
//@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성: 데이터가 일치하는 가)
//public User loginService(User user) {
//
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	
//}

