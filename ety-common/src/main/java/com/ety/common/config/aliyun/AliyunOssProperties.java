package com.ety.common.config.aliyun;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ety.aliyun.oss")
@ConditionalOnClass(OSSClient.class)
@Data
public class AliyunOssProperties {
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
}
