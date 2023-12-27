package com.cos.blog;

import org.junit.Test;

import com.cos.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
		Reply reply = Reply.builder().id(1).user(null).board(null).content("안녕").build();
		
		System.out.println("reply Object Test :" + reply); //오브젝트 출력시에 toString이 호출됨.
				
	
	}
}
