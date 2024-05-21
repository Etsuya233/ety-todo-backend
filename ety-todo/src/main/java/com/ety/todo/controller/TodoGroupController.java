package com.ety.todo.controller;


import com.ety.common.domain.R;
import com.ety.todo.domain.po.TodoGroup;
import com.ety.todo.domain.vo.TodoGroupVO;
import com.ety.todo.service.ITodoGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
@RestController
@RequestMapping("/todo-group")
@RequiredArgsConstructor
public class TodoGroupController {
	private final ITodoGroupService todoGroupService;

	@PostMapping
	public R<Void> addTodoGroup(@RequestBody TodoGroup group) {
		todoGroupService.addTodoGroup(group);
		return R.success();
	}

	@GetMapping
	public R<List<TodoGroupVO>> getAllGroup(){
		List<TodoGroupVO> ret = todoGroupService.getAllGroup();
		return R.success(ret);
	}


}
