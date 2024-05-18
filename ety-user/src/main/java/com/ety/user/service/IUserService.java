package com.ety.user.service;

import com.ety.api.dto.UserDTO;
import com.ety.user.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-10
 */
public interface IUserService extends IService<User> {

	void register(User user);

	UserDTO getUserAllInfoByName(String name);
}
