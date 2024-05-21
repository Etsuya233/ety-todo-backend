package com.ety.todo.domain.query;

import com.ety.common.domain.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TodoPageQuery extends PageDTO {
	/**
	 * 假如groupId为0,则是查询全部
	 */
	private String groupId;
	/**
	 * 重要程度。
	 */
	private String type;
}
