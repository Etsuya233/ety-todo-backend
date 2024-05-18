package com.ety.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ety-auth")
public interface AuthClient {
	/**
	 * 解析JWT
	 * @param token JWT
	 * @return userId,解析失败就是null.
	 */
	@GetMapping("/auth/parseToken")
	Long parseToken(@RequestParam String token);
}
