package com.ety.auth.utils;

import com.ety.auth.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

	private final JwtProperties jwtProperties;
	private JwtParser jwtParser;
	private SecretKey secretKey;
	private Long ttl;

	public JwtUtils(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	@PostConstruct
	public void postConstruct(){
		byte[] secret = Base64.getDecoder().decode(jwtProperties.getSecret());
		secretKey = Keys.hmacShaKeyFor(secret);
		jwtParser = Jwts.parser()
				.verifyWith(secretKey)
				.build();
		ttl = jwtProperties.getTtl();
	}

	public Long parseToken(String token){
		Jws<Claims> parsed = jwtParser.parseSignedClaims(token);
		Claims payload = parsed.getPayload();
		String userId = payload.get("userId").toString();
		return Long.parseLong(userId);
	}

	public String generateToken(Long userId){
		return Jwts.builder()
				.expiration(Date.from(Instant.now().plusSeconds(ttl)))
				.claim("userId", userId)
				.signWith(secretKey)
				.compact();
	}
}
