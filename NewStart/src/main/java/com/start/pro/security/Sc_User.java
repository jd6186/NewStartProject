package com.start.pro.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Sc_User implements UserDetails {

	

	private static final long serialVersionUID = -1230485721195931703L;
	
	private String user_email;
	private String user_pw;
	private String user_grade;
	private String user_type;

	public Sc_User() {}

	public Sc_User(String user_email, String user_pw, String user_grade, String user_type) {
		super();
		this.user_email = user_email;
		this.user_pw = user_pw;
		this.user_grade = user_grade;
		this.user_type = user_type;
	}

	


	public String getUser_email() {
		return user_email;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		System.out.println("유저권한은?"+"ROLE_"+user_grade);
		auth.add(new SimpleGrantedAuthority("ROLE_"+user_grade));
		return auth;
	}

	@Override
	public String getPassword() {
		return user_pw;
	}

	@Override
	public String getUsername() {
		return user_email;
	}

	//계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
//		return (!user_type.equalsIgnoreCase("L")) ? true : false;
		return true;
	}

	//비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	@Override
	public String toString() {
		return "Sc_User [user_email=" + user_email + ", user_pw=" + user_pw + ", user_grade=" + user_grade
				+ ", user_type=" + user_type + "]";
	}

	
}
