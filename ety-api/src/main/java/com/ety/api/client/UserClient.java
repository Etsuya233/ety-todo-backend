package com.ety.api.client;

import com.ety.api.dto.UserDTO;
import com.ety.common.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ety-user")
public interface UserClient {
	@GetMapping("/user/all")
	UserDTO getUserAllInfoByName(@RequestParam("name") String name);
}
