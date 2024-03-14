package com.dango.dango.global.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dango.dango.domain.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil {
	private static final long ACCESS_TOKEN_VALID_PERIOD = 1000L * 60 * 60 * 24 * 8;

	static private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String createToken(User user){
		Claims claims = Jwts.claims();
		claims.put("username",user.getUsername());

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+ ACCESS_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}
	public static String createRefreshToken(){
		Claims claims = Jwts.claims();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}



}
