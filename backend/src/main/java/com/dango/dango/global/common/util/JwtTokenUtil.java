package com.dango.dango.global.common.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dango.dango.domain.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil {
	private final long ACCESS_TOKEN_VALID_PERIOD = 1000L * 60 ;//* 60 * 24; // access token 의 기한은 하루
	private final long REFRESH_TOKEN_VALID_PERIOD = 1000L * 60 * 60 * 24 * 7; // refresh token의 기한은 일주일
	private SecretKey key;
	public JwtTokenUtil(@Value("${jwt.secret}")String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(User user) {
		Claims claims = Jwts.claims();
		claims.put("username", user.getUsername());

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}

	public String createRefreshToken() {
		Claims claims = Jwts.claims();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}

	public String extractToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer"))
			return bearerToken.substring(7);
		return null;
	}

	public boolean validateToken(String token) {
		try {
			parseJwt(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.error("잘못된 JWT 서명입니다");
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰입니다.");
			throw new ExpiredJwtException(e.getHeader(), e.getClaims(),"만료된 JWT 토큰입니다");
		}  catch (IllegalArgumentException e) {
			log.error("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}


	public Claims extractClaims(String token) throws SignatureException {
		try {
			return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			log.error("SignatureException occurred while extracting claims from token: {}", e.getMessage());
			throw e;
		}
	}

	public String extractUsername(String token){
		Claims claims = extractClaims(token);
		return (String)claims.get("username");
	}

	public Claims parseJwt(String jwt){
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
			.parseClaimsJws(jwt).getBody();
		return claims;
	}
}
