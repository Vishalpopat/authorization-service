package com.uxpsystems.assignment.service.impl;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uxpsystems.assignment.models.User;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = -5183502782822728424L;

	private Integer userId;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String username, String password) {
		this.userId = id;
		this.username = username;
		this.password = password;
	}

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(
				user.getUserId(), 
				user.getUsername(), 
				user.getPassword());
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(userId, user.userId);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public Integer getUserId() {
		return userId;
	}
}
