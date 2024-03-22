package com.dango.dango.global.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dango.dango.global.common.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		try{
			filterChain.doFilter(request,response);
		}catch (ExpiredJwtException e){
			setErrorResponse(HttpStatus.BAD_REQUEST,response,e);
		}catch (RuntimeException e){
			setErrorResponse(HttpStatus.BAD_REQUEST,response,e);
		}
	}

	public void setErrorResponse(HttpStatus status,HttpServletResponse res,Throwable ex) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();

		res.setStatus(status.value());
		res.setContentType("application/json; charset=UTF-8");
		ErrorResponse errorResponse = new ErrorResponse(status.value(),ex.getMessage());

		res.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}

}
