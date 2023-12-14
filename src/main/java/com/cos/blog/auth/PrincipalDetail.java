package com.cos.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;


//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에서 저장을 해준다. 
public class PrincipalDetail implements UserDetails {
	
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	
	// 계정이 만료되지 않았는지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}


	// 계정이 잠겨잇지 않았는지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}
	

	// 비밀번호가 만료되지 않았는지 리턴한다. (true :만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	
	// 계정이 활성화(사용가능)인지 리턴한다. (true:활성화)
	@Override
	public boolean isEnabled() {
	
		return true;
	}
	
	
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				
//				// spring 에서 role을 붙일 때 규칙에 해당함 _를 꼭 붙일 것.
//				return "ROLE_" + user.getRole(); // ROLE_USER 로 return됨 
//			}
//		});
		
		
		// 위의 주석을 람다 식으로 변경한 경우
		collectors.add(()->{return "ROLE_" + user.getRole();});
		
		return collectors;
	}
}
