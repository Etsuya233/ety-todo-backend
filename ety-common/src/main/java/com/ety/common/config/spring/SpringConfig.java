package com.ety.common.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringConfig {

	@Bean
	public ExceptionHandlers exceptionHandlers() {
		log.info("已注册异常处理器！");
		return new ExceptionHandlers();
	}
}
