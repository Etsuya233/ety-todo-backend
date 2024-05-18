package com.ety.todo.controller;


import com.ety.common.domain.PageDTO;
import com.ety.common.domain.PageRes;
import com.ety.common.domain.R;
import com.ety.todo.domain.po.Todo;
import com.ety.todo.domain.query.TodoPageQuery;
import com.ety.todo.domain.vo.TodoVO;
import com.ety.todo.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Etsuya
 * @since 2024-05-14
 */
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
	private final ITodoService todoService;

	@PostMapping
	public R<Void> addTodo(@RequestBody Todo todo) {
		todoService.addTodo(todo);
		return R.success();
	}

	@PutMapping
	public R<Void> updateTodo(@RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return R.success();
	}

	@DeleteMapping
	public R<Void> deleteTodo(Long id) {
		todoService.deleteTodo(id);
		return R.success();
	}

	@GetMapping("/page")
	public R<PageRes<TodoVO>> pageTodo(TodoPageQuery todoPageQuery){
		PageRes<TodoVO> ret = todoService.pageTodo(todoPageQuery);
		return R.success(ret);
	}

}
