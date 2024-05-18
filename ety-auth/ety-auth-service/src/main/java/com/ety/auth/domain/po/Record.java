package com.ety.auth.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("record")
public class Record {

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	private Long userId;

	private Integer method;

	private LocalDateTime createTime;
}
