package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;


//해당 jpaRepository는 user 테이블을 관리하는 Repository이고 이 User 테이블의 primaryKey는 integer야
//DAO
//자동으로 bean등록이 된다.
//@Repository -> spring과 달리 jpaRepository를 extends 하면 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}
