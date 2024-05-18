package com.ety.auth.config;

import com.ety.common.constant.HttpConstant;
import com.ety.common.domain.UserContext;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Feign.class)
public class DefaultFeignConfig {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			Long userId = UserContext.getUserId();
			if(userId != null) requestTemplate.request().header(HttpConstant.USER_ID_HEADER, String.valueOf(userId));
		};
	}
}
