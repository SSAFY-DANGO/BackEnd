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
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil {
	private static final long ACCESS_TOKEN_VALID_PERIOD = 1000L * 60 * 60 * 24 * 8;

	static private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String createToken(User user) {
		Claims claims = Jwts.claims();
		claims.put("username", user.getUsername());

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}

	public static String createRefreshToken() {
		Claims claims = Jwts.claims();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_PERIOD))
			.signWith(key)
			.compact();
	}

	public static String extractToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer"))
			return bearerToken.substring(7);
		return null;
	}

	public static boolean validateToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			Date expiration = claims.getExpiration();

			if (expiration.before(new Date())) {
				// 액세스 토큰이 만료되었을 경우
				throw new IllegalArgumentException("토큰이 만료되었습니다");
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
}
