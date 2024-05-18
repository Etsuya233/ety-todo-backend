package com.ety.user.controller;


import com.ety.api.dto.UserDTO;
import com.ety.common.domain.R;
import com.ety.user.domain.po.User;
import com.ety.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final IUserService userService;

	@PostMapping("/register")
	public R<Void> register(@RequestBody User user){
		userService.register(user);
		return R.success();
	}

	@GetMapping("/all")
	public UserDTO getUserAllInfoByName(@RequestParam("name") String name){
		return userService.getUserAllInfoByName(name);
	}

}
