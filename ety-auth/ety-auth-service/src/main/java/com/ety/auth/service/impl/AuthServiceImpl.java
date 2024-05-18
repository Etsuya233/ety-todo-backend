package com.ety.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ety.api.client.UserClient;
import com.ety.api.dto.UserDTO;
import com.ety.api.dto.UserLoginDTO;
import com.ety.auth.service.IAuthService;
import com.ety.auth.utils.JwtUtils;
import com.ety.common.domain.R;
import com.ety.common.exceptions.BadRequestException;
import com.ety.common.exceptions.BizIllegalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {

	private final JwtUtils jwtUtils;
	private final UserClient userClient;
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public String loginWithPassword(UserLoginDTO loginDTO) {
		if(loginDTO == null){
			throw new BadRequestException("请求参数错误！");
		}
		UserDTO user = userClient.getUserAllInfoByName(loginDTO.getName()); //TODO 有个bug,拦截器拦阻了Feign的请求
		if(user == null){
			throw new BadRequestException("用户不存在！");
		}
		if(user.getPassword() == null || !bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
			throw new BizIllegalException("密码错误！");
		}
		return jwtUtils.generateToken(user.getId());
	}

	@Override
	public Long parseToken(String token) {
		Long userId = null;
		if(StrUtil.isNotBlank(token)){
			return null;
		}
		try {
			userId = jwtUtils.parseToken(token);
		} catch (Exception e){
			log.info("token无法解析，可能已过期。{}", e.getMessage());
		}
		return userId;
	}
}
