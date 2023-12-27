package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	

	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.contentWrite(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK,1);
		
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.boardDelete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
		
		boardService.contentModify(id, board);
		
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	
	
	// 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다. 
	// dto 사용하지 않은 이유는 !! 프로젝트가 작아서 
	// 프로젝트가 커지면 field가 커져서 아래 처럼 Reply를 바로 받는 방법은 좋지 않다. 
	//@RequestBody가 더이상 필요 없어졌다함.
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.replyWrite(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK,1);
		
	}
	
	@DeleteMapping("/api/board/{bordId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		
		boardService.replyDelete(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	
	

}

