package com.anschau.udemy.security;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
	
	Date date = new Date(System.currentTimeMillis());
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
}
