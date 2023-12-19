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

	
	@Transactional(readOnly=true)
	public Page<Board> contentList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	
	@Transactional(readOnly=true)
	public Board boardDetailRead(int id) {
		
		return  boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다." );
		});
	}
	
	
	@Transactional
	public void boardDelete(int id) {
		boardRepository.deleteById(id);
	}
	
	
	@Transactional
	public void contentModify(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("글 착기 실패:아이디를 찾을 수 없습니다." );
		}); // 영속화 완료
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료된다. 이 때 더티체킹 - 자동 업데이트가 됨 db flush -> commit 이 된다는 뜻
	}
	
	
}


