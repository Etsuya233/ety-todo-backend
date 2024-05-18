package com.ety.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {

	private Integer code;
	private String msg;
	private T data;

	public static R<Void> success(){
		return new R<>(200, "success", null);
	}

	public static <T> R<T> success(T data) {
		return new R<>(200, "success", data);
	}

	public static <T> R<T> error(Integer code, String msg){
		return new R<>(code, msg, null);
	}
}
