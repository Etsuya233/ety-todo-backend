package com.ety.todo.domain.vo;

import lombok.Data;

@Data
public class TodoGroupVO {
	private Long id;

	private Long userId;

	private String name;

	private String color;

	private String description;

	private String icon;

	private Integer completedCount;

	private Integer totalCount;
}
