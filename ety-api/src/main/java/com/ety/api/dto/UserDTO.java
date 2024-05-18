package com.ety.api.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;

	private String name;

	private String password;

	private Boolean vip;
}
