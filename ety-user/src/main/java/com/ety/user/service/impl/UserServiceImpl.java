package com.ety.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ety.api.dto.UserDTO;
import com.ety.common.config.aliyun.AliyunOssProperties;
import com.ety.common.exceptions.BadRequestException;
import com.ety.common.exceptions.BizIllegalException;
import com.ety.user.domain.po.User;
import com.ety.user.mapper.UserMapper;
import com.ety.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-10
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public void register(User user) {
		//验证
		if(user.getName() == null || StrUtil.isBlank(user.getName()) || StrUtil.isBlank(user.getPassword())){
			throw new BadRequestException("请求参数不足！");
		}
		//查看是否有同名用户
		Long count = this.lambdaQuery()
				.eq(User::getName, user.getName()).count();
		if(count > 0){
			throw new BizIllegalException("用户已存在！");
		}
		//加密密码
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		//存入数据库
		this.save(user);
	}

	@Override
	public UserDTO getUserAllInfoByName(String name) {
		User user = this.lambdaQuery().eq(User::getName, name).one();
		if(user == null) return null;
		return BeanUtil.copyProperties(user, UserDTO.class);
	}

	@Override
	public User getUserById(Long id) {
		if(id == null) throw new BadRequestException("ID不存在！");
		User user = this.getById(id);
		if(user == null){
			throw new BizIllegalException("用户不存在！");
		}
		return user;
	}

}
