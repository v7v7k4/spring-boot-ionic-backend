package com.vivs.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vivs.cursomc.security.UserSS;

public class UserService {
	public static UserSS authenticated() {
		try {
			//obter o usuário logado na forma de usuário do spring security
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
	}

}
