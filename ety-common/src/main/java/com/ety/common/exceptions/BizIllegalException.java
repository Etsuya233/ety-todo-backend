package com.ety.common.exceptions;

import lombok.Getter;

@Getter
public class BizIllegalException extends BaseException {
	private static final String msg = "业务异常！";

	public BizIllegalException() {}

	public BizIllegalException(String message) {
		super(message);
	}


}
