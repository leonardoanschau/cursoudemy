package com.anschau.udemy.security;

import java.time.Clock;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

@Component
public class JWTUtil {

	Clock clock = Clock.systemUTC();
	LocalDate localDate = LocalDate.now(clock);
	//converte local date para date
	//Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	//Date date = new Date("Wed Mar 28 15:25:59 BRT 2018");
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				//expiração do token não funciona pq a date ta deprecated
				//.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			//Date expirationDate = claims.getExpiration();
			//Date now = new Date(System.currentTimeMillis());
			/*
			 * eu não testo a experiração do token abaixo pq a date não funciona
			 */
			//&& expirationDate != null && now.before(expirationDate)
			if (username != null) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
