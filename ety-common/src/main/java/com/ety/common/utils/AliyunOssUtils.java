package com.ety.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.VoidResult;
import com.ety.common.config.aliyun.AliyunOssProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Component
@ConditionalOnClass(OSSClient.class)
public class AliyunOssUtils {

	private final OSS oss;
	private final AliyunOssProperties aliyunOssProperties;

	public AliyunOssUtils(AliyunOssProperties aliyunOssProperties) {
		this.aliyunOssProperties = aliyunOssProperties;
		oss = new OSSClientBuilder().build(
				aliyunOssProperties.getEndpoint(),
				aliyunOssProperties.getAccessKeyId(),
				aliyunOssProperties.getAccessKeySecret());
	}

	public VoidResult deleteObject(String key){
		return oss.deleteObject(aliyunOssProperties.getBucketName(), key);
	}

	public String putObject(String key, InputStream inputStream, int expMin){
		oss.putObject(aliyunOssProperties.getBucketName(), key, inputStream);
		URL url = oss.generatePresignedUrl(aliyunOssProperties.getBucketName(), key, Date.from(Instant.now().plus(expMin, ChronoUnit.MINUTES)));
		return url.toString();
	}
}
