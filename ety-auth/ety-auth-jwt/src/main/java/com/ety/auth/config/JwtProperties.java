package com.ety.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("ety.auth.jwt")
public class JwtProperties {
	/**
	 * 密钥
	 */
	private String secret;
	/**
	 * TTL
	 */
	private Long ttl;
}
