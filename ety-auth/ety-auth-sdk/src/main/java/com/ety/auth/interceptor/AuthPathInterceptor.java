package com.ety.auth.interceptor;

import com.ety.common.constant.HttpConstant;
import com.ety.common.domain.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录状态拦截器，没登录就拦截
 */
@Slf4j
public class AuthPathInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long userId = UserContext.getUserId();
		if(userId == null){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登录用户无法访问");
			return false;
		}
		return true;
	}
}
