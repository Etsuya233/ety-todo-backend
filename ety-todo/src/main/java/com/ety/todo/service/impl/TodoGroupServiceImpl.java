package com.ety.todo.service.impl;

import com.ety.common.domain.UserContext;
import com.ety.common.exceptions.BadRequestException;
import com.ety.todo.domain.po.TodoGroup;
import com.ety.todo.mapper.TodoGroupMapper;
import com.ety.todo.service.ITodoGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
@Service
public class TodoGroupServiceImpl extends ServiceImpl<TodoGroupMapper, TodoGroup> implements ITodoGroupService {

	@Override
	public void addTodoGroup(TodoGroup group) {
		if(group == null) throw new BadRequestException();
		Long userId = UserContext.getUserId();
		group.setUserId(userId);
		this.save(group);
	}

	@Override
	public List<TodoGroup> groupPojoByIds(Collection<Long> ids) {
		return this.lambdaQuery()
				.in(TodoGroup::getId, ids)
				.list();
	}
}
