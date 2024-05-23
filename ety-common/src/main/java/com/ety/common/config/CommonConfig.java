package com.ety.common.config;

import com.ety.common.config.aliyun.AliyunOssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliyunOssProperties.class)
public class CommonConfig {
}
