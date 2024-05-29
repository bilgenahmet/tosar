package com.tosar.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tosar.SecureUser;
import com.tosar.dao.UserDao;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return buildUserForAuthentication(userDao.getUserByUserName(username), buildUserAuthority("USER"));
	}

	private SecureUser buildUserForAuthentication(com.tosar.model.User user, Set<GrantedAuthority> authorities) {
		return new SecureUser(user, authorities, true, true, true, true);
	}

	private Set<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> result = new HashSet<GrantedAuthority>();
		result.add(new SimpleGrantedAuthority("ROLE_" + userRole));

		return result;
	}
}
