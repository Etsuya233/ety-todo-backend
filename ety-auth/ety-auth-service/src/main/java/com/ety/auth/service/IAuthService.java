package com.ety.auth.service;

import com.ety.api.dto.UserLoginDTO;

public interface IAuthService {
	String loginWithPassword(UserLoginDTO loginDTO);

	Long parseToken(String token);
}
