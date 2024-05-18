package com.ety.common.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
	private final String msg = "其他自定义异常！";
	private final Integer statusCode = 400;

	public BaseException(){
		super();
	}
	public BaseException(String message) {
		super(message);
	}
}
