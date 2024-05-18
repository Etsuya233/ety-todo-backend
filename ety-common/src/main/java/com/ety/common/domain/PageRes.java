package com.ety.common.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageRes<T> {
	private Long total;
	/**
	 * 当前页数
	 */
	private Long pageNo;
	/**
	 * 当前页的数据量
	 */
	private Long pageSize;
	private List<T> records;

	public static <T> PageRes<T> of(Long total, Long pageNo, Long pageSize, List<T> records) {
		PageRes<T> pageRes = new PageRes<>();
		pageRes.setTotal(total);
		pageRes.setPageNo(pageNo);
		pageRes.setPageSize(pageSize);
		pageRes.setRecords(records);
		return pageRes;
	}

	public static <T> PageRes<T> of(Page<T> mpPage){
		PageRes<T> pageRes = new PageRes<>();
		pageRes.setRecords(mpPage.getRecords());
		pageRes.setTotal(mpPage.getTotal());
		pageRes.setPageNo(mpPage.getCurrent());
		pageRes.setPageSize(mpPage.getSize());
		return pageRes;
	}

	public static <T> PageRes<T> empty(){
		PageRes<T> pageRes = new PageRes<>();
		pageRes.setRecords(new ArrayList<>());
		pageRes.setTotal(0L);
		pageRes.setPageNo(0L);
		pageRes.setPageSize(0L);
		return pageRes;
	}

	public static <T, R> PageRes<T> of(Page<R> mpPage, List<T> records){
		PageRes<T> pageRes = new PageRes<>();
		pageRes.setTotal(mpPage.getTotal());
		pageRes.setPageNo(mpPage.getCurrent());
		pageRes.setPageSize(mpPage.getSize());
		pageRes.setRecords(records);
		return pageRes;
	}

}
