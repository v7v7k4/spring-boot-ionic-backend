package com.vivs.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//Tentar acessar algum endpoint - ex: pedidos/1, entra no headers e adiciona a authorization com o token (bearer) que foi gerado no login
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtutil;
	
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtutil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		//analisa o token para ver se o token é valido.
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7)); //header sem o Bearer 
			if(auth != null) {
				//diferente de nulo tudo ok com o token, igual a nulo token inválido
				//liberar acesso
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		//continua fazendo a requisição normalmente depois dos testes acima
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtutil.tokenValido(token)) {
			String username = jwtutil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
