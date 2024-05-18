package com.ety.common.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

	private final String msg = "请求异常！";

	public BadRequestException(){
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}
}
