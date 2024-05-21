package com.ety.todo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ety.common.domain.UserContext;
import com.ety.common.exceptions.BadRequestException;
import com.ety.todo.domain.po.TodoGroup;
import com.ety.todo.domain.vo.TodoGroupVO;
import com.ety.todo.mapper.TodoGroupMapper;
import com.ety.todo.service.ITodoGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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

	@Override
	public List<TodoGroupVO> getAllGroup() {
		Long userId = UserContext.getUserId();
		//查询组列表
		List<TodoGroup> groups = this.lambdaQuery()
				.eq(TodoGroup::getUserId, userId)
				.list();
		//查询组统计
		List<TodoGroupVO> groupStats = this.getBaseMapper().getGroupStats(userId);
		Map<Long, TodoGroupVO> statsMap = groupStats.stream().collect(Collectors.toMap(TodoGroupVO::getId, Function.identity()));
		List<TodoGroupVO> list = groups.stream().map(g -> {
			TodoGroupVO vo = BeanUtil.copyProperties(g, TodoGroupVO.class);
			TodoGroupVO stat = statsMap.get(g.getId());
			if (stat != null) {
				vo.setCompletedCount(stat.getCompletedCount());
				vo.setTotalCount(stat.getTotalCount());
			} else {
				vo.setCompletedCount(0);
				vo.setTotalCount(0);
			}
			return vo;
		}).toList();
		return list;
	}
}
