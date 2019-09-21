package com.vivs.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component //para poder ser injetada em outras classe como component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			//retorna o usuário
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis()); //testar se o token está expirado
			//o meu instante atual é menor do que a data de expiração
			if(username != null && expirationDate != null) {
				return true;
			}
		}
		
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null)
			return claims.getSubject();
		return null;
	}
		
}
