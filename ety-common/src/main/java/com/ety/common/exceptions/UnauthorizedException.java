package com.ety.common.exceptions;

import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException {
	private final String msg = "请求未授权！";
	private final Integer statusCode = 401;

	public UnauthorizedException() {}

	public UnauthorizedException(String message) {
		super(message);
	}
}
