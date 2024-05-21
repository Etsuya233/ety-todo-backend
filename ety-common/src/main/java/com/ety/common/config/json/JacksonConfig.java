package com.ety.common.config.json;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass({Jackson2ObjectMapperBuilder.class, WebMvcConfigurer.class})
@Slf4j
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return jacksonObjectMapperBuilder -> {
			log.info("已注册Jackson转换器。");
			jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance); //将Long转化为String
			jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
		};
	}
}
