package com.dango.dango.global.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dango.dango.domain.user.service.ReissueService;
import com.dango.dango.global.common.util.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final UserDetailsService userDetailsService;
	private final ReissueService reissueService;
	private final JwtTokenUtil jwtTokenUtil;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token = jwtTokenUtil.extractToken(request);

		if(token != null){
			try{
				if(jwtTokenUtil.validateToken(token)){
					// 액세스 토큰의 만료기간이 다되었는지 확인하자
					String username = jwtTokenUtil.extractUsername(token);
					request.setAttribute("username",username);
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}else{
					throw new IllegalArgumentException("유효하지 않은 토큰입니다");
				}
			}catch (IllegalArgumentException e){
				response.setHeader("Expired-Token","true");
				//토큰의 기간이 만료되었음을 알려주자
			}
		}
		filterChain.doFilter(request,response);
	}

}
