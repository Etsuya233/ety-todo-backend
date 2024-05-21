package com.ety.todo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ety.common.domain.PageRes;
import com.ety.common.domain.UserContext;
import com.ety.common.exceptions.BadRequestException;
import com.ety.todo.domain.dto.TodoUpdateDTO;
import com.ety.todo.domain.po.Todo;
import com.ety.todo.domain.po.TodoGroup;
import com.ety.todo.domain.query.TodoPageQuery;
import com.ety.todo.domain.vo.TodoVO;
import com.ety.todo.mapper.TodoMapper;
import com.ety.todo.service.ITodoGroupService;
import com.ety.todo.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

	private final ITodoGroupService todoGroupService;
	private final MappingsEndpoint mappingsEndpoint;

	@Override
	public void addTodo(Todo todo) {
		if(todo == null) throw new BadRequestException();
		Long userId = UserContext.getUserId();
		todo.setUserId(userId);
		this.save(todo);
	}

	@Override
	public void updateTodo(Todo todo) {
		if(todo == null) throw new BadRequestException();
		Long userId = UserContext.getUserId();
		todo.setUserId(userId);
		this.updateById(todo);
	}

	@Override
	public void deleteTodo(Long id) {
		this.removeById(id);
	}

	@Override
	public PageRes<TodoVO> pageTodo(TodoPageQuery todoPageQuery) {
		if(todoPageQuery == null) throw new BadRequestException();
		Long userId = UserContext.getUserId();
		//分页查询
		Page<Todo> pageResult = this.lambdaQuery()
				.eq(Todo::getUserId, userId)
				.eq(todoPageQuery.getGroupId() != null && Long.parseLong(todoPageQuery.getGroupId()) != 0, Todo::getGroupId, todoPageQuery.getGroupId())
				.eq(todoPageQuery.getType() != null, Todo::getType, todoPageQuery.getType())
				.page(todoPageQuery.toMpPage());
		List<Todo> records = pageResult.getRecords();
		if(records == null || records.isEmpty()) return PageRes.empty();
		//查询组信息
		Set<Long> groupIds = records.stream().map(Todo::getGroupId).collect(Collectors.toSet());
		List<TodoGroup> todoGroups = todoGroupService.groupPojoByIds(groupIds);
		Map<Long, TodoGroup> groupMap = todoGroups.stream().collect(Collectors.toMap(TodoGroup::getId, Function.identity()));
		//封装信息
		List<TodoVO> ret = records.stream().map(r -> {
			TodoVO vo = BeanUtil.copyProperties(r, TodoVO.class);
			TodoGroup group = groupMap.get(vo.getGroupId());
			if (group != null) {
				vo.setGroupName(group.getName());
				vo.setGroupColor(group.getColor());
			}
			return vo;
		}).toList();
		return PageRes.of(pageResult, ret);
	}

	@Override
	public void completeTodoBatch(List<TodoUpdateDTO> dto) {
		if(CollUtil.isEmpty(dto)) return;
		Long userId = UserContext.getUserId();
		//分离完成和未完成的ID
		List<Long> incompleteIds = dto.stream()
				.filter(d -> !d.getStatus())
				.map(TodoUpdateDTO::getId)
				.toList();
		List<Long> completedIds = dto.stream()
				.filter(TodoUpdateDTO::getStatus)
				.map(TodoUpdateDTO::getId)
				.toList();
		//更新数据库
		if(CollUtil.isNotEmpty(completedIds)) this.lambdaUpdate()
				.eq(Todo::getUserId, userId)
				.in(Todo::getId, completedIds)
				.set(Todo::getStatus, true)
				.update();
		if(CollUtil.isNotEmpty(incompleteIds)) this.lambdaUpdate()
				.eq(Todo::getUserId, userId)
				.in(Todo::getId, incompleteIds)
				.set(Todo::getStatus, false)
				.update();
	}
}
