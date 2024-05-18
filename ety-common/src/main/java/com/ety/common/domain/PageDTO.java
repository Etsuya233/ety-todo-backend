package com.ety.common.domain;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageDTO {
	private long pageNo;
	private long pageSize;
	private String orderBy;
	private Boolean isDesc;

	public <T> Page<T> toMpPage(){
		Page<T> page = new Page<>(pageNo, pageSize);
		if(orderBy != null && !orderBy.isEmpty()){
			page.addOrder(isDesc? OrderItem.desc(orderBy) : OrderItem.asc(orderBy));
		}
		return page;
	}

	public <T> Page<T> toMpPage(String orderBy, Boolean isDesc){
		Page<T> page = new Page<>(pageNo, pageSize);
		page.addOrder(isDesc? OrderItem.desc(orderBy) : OrderItem.asc(orderBy));
		return page;
	}
}
