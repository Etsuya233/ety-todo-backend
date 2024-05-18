package com.ety.user.mapper;

import com.ety.user.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
