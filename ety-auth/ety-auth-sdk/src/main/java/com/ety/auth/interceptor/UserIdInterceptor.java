package com.ety.auth.interceptor;

import com.ety.common.constant.HttpConstant;
import com.ety.common.domain.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户ID拦截器，无论登不登录都要拦截
 */
@Slf4j
public class UserIdInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String userIdStr = request.getHeader(HttpConstant.USER_ID_HEADER);
		if(userIdStr == null){
			return true;
		}
		try {
			long userId = Long.parseLong(userIdStr);
			UserContext.setUserId(userId);
			return true;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		UserContext.removeUserId();
	}
}
