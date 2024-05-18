package com.ety.auth.config;

import com.ety.auth.interceptor.AuthPathInterceptor;
import com.ety.auth.interceptor.UserIdInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(AuthPathProperties.class) //Enable support for @ConfigurationProperties annotated beans.
public class AuthPathConfiguration implements WebMvcConfigurer {

	private final AuthPathProperties authPathProperties;

	/**
	 * 添加拦截器
	 * @param registry 参数
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加userId拦截器
		registry.addInterceptor(new UserIdInterceptor()).order(0);
		log.info("已添加用户信息拦截器。");
		if(!authPathProperties.getEnable()){
			return;
		}
		//添加登录状态拦截器
		InterceptorRegistration registration = registry.addInterceptor(new AuthPathInterceptor()).order(1);
		if(authPathProperties.getIncludePaths() != null) registration.addPathPatterns(authPathProperties.getIncludePaths());
		if(authPathProperties.getExcludePaths() != null) registration.excludePathPatterns(authPathProperties.getExcludePaths());
		log.info("已添加登录拦截器。");
	}
}

