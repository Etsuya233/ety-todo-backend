package com.ety.todo.service;

import com.ety.common.domain.PageDTO;
import com.ety.common.domain.PageRes;
import com.ety.todo.domain.po.Todo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ety.todo.domain.query.TodoPageQuery;
import com.ety.todo.domain.vo.TodoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
public interface ITodoService extends IService<Todo> {

	void addTodo(Todo todo);

	void updateTodo(Todo todo);

	void deleteTodo(Long id);

	PageRes<TodoVO> pageTodo(TodoPageQuery todoPageQuery);
}
