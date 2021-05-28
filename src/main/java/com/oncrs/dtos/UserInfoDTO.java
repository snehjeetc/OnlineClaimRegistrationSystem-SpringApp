package com.oncrs.dtos;

import java.util.List;

import com.oncrs.models.PolicyData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
	private String userId;
	private String role;
	private List<PolicyData> policies;
}
