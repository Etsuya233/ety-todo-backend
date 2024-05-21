package com.ety.todo.service;

import com.ety.todo.domain.po.TodoGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ety.todo.domain.vo.TodoGroupVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
public interface ITodoGroupService extends IService<TodoGroup> {

	void addTodoGroup(TodoGroup group);

	List<TodoGroup> groupPojoByIds(Collection<Long> ids);

	List<TodoGroupVO> getAllGroup();
}
