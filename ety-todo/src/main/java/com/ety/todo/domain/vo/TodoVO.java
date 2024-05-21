package com.ety.todo.domain.vo;

import lombok.Data;

@Data
public class TodoVO {
	private Long id;

	private Long userId;

	private Long groupId;

	private String groupName;

	private String groupColor;

	private String content;

	private Integer type;

	private Boolean status;
}
