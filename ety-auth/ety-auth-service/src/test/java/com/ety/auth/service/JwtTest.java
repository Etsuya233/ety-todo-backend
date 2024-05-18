package com.ety.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class JwtTest {
	@Test
	public void test(){
		SecretKey key = Jwts.SIG.HS384.key().build();
		String encoded = Encoders.BASE64.encode(key.getEncoded());
		System.out.println(encoded);
	}
}
