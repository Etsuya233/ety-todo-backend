package com.ety.todo.mapper;

import com.ety.todo.domain.po.TodoGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ety.todo.domain.vo.TodoGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
public interface TodoGroupMapper extends BaseMapper<TodoGroup> {

	List<TodoGroupVO> getGroupStats(@Param("userId") Long userId);

}
