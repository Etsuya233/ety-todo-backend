package com.ety.auth.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "ety.auth")
public class AuthPathProperties {
	/**
	 * 是否开启身份认证功能，包括获取用户ID及登录拦截。
	 */
	private Boolean enable = true;
	/**
	 * 需要登录验证的路径。
	 */
	private List<String> includePaths;
	/**
	 * 不需要登录的路径。
	 */
	private List<String> excludePaths;
}
