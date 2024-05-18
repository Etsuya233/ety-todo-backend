package com.ety.common.config.spring;

import com.ety.common.domain.R;
import com.ety.common.exceptions.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

	@ExceptionHandler(BaseException.class)
	public R<String> baseExceptionHander(BaseException e){
		log.error("错误:{}\n" +
				"错误代码：{}\n" +
				"默认错误信息：{}\n" +
				"错误原因：{}", e.getMessage(), e.getStatusCode(), e.getMsg(), e.getCause());
		//下面这一段是把Response的状态码设置为对应的（避免重复处理）
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(requestAttributes != null) {
			ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
			HttpServletResponse response = sra.getResponse();
			if(response != null){
				response.setStatus(e.getStatusCode());
			}
		}
		return R.error(e.getStatusCode(), e.getMessage() == null? e.getMsg() : e.getMessage());
	}

}
