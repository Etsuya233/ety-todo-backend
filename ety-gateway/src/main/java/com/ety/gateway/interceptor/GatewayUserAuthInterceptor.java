package com.ety.gateway.interceptor;

import com.ety.auth.utils.JwtUtils;
import com.ety.common.constant.HttpConstant;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 网关鉴权，检查JWT，不管过不过都pass
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class GatewayUserAuthInterceptor implements GlobalFilter, Ordered {

	private final JwtUtils jwtUtils;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//获取JWT
		ServerHttpRequest request = exchange.getRequest();
		List<String> authorizations = request.getHeaders().get("Authorization");
		if(authorizations == null || authorizations.isEmpty()) return chain.filter(exchange);
		if(!authorizations.get(0).startsWith("Bearer ")){
			return chain.filter(exchange);
		}
		String token = authorizations.get(0).substring(7); //Bearer ...
		//验证JWT
		try{
			Long userId = jwtUtils.parseToken(token);
			request.mutate().header(HttpConstant.USER_ID_HEADER, userId.toString());
		} catch (ExpiredJwtException e){
			//do nothing
		} catch (Exception e){
			log.error("解析JWT失败（不是过期）或者Header异常！{}", e.getMessage());
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
