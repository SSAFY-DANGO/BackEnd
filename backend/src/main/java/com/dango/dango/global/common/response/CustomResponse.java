package com.dango.dango.global.common.response;

import lombok.Data;

@Data
public class CustomResponse<T> {
	private int status;
	private String message;
	private T data;

	public CustomResponse(int status,T data,String message){
		this.status = status;
		this.data = data;
		this.message = message;
	}
}
