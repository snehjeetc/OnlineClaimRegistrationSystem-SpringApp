package com.oncrs.dtos;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetailsDTO {
	
	@NotNull
	String userId;
	@NotNull
	String password;
	String roleId;
}
