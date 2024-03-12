package com.dango.dango.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationRequest {

	@NotBlank(message = "아이디는 비어있을 수 없습니다")
	@Email
	private String username;

	@NotBlank(message = "비밀번호는 비어있을 수 없습니다")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",message = "비밀번호는 최소 8자, 최소 하나의 문자 및 하나의 숫자를 포함해야합니다")
	private String password;

	@NotBlank(message = "닉네임은 비어있을 수 없습니다")
	private String nickname;

}
