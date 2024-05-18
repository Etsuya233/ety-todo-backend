package com.ety.auth.controller;

import com.ety.api.dto.UserLoginDTO;
import com.ety.auth.service.IAuthService;
import com.ety.common.domain.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final IAuthService authService;

	/**
	 * 账号密码登录
	 * @param loginDTO dto
	 * @return jwt的token
	 */
	@PostMapping("/login")
	public R<String> loginWithPassword(@RequestBody UserLoginDTO loginDTO){
		String token = authService.loginWithPassword(loginDTO);
		return R.success(token);
	}

	@GetMapping("/parseToken")
	public Long parseToken(@RequestParam String token){
		return authService.parseToken(token);
	}
}
