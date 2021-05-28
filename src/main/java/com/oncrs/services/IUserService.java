package com.oncrs.services;

import java.util.List;
import java.util.Optional;

import com.oncrs.auth.ApplicationUser;
import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.dtos.UserInfoDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;
import com.oncrs.models.UserInfo;

public interface IUserService {
	Optional<ApplicationUser> loadUserByUserName(String username);
	UserInfoDTO registerUser(UserInfo newUser);
	List<UserInfo> getUsers();
	List<PolicyData> getPolicies(String userid);
	List<ClaimData> getClaims(String userid);
	UserInfoDTO createPolicy(PolicyDataDTO policy);
	UserInfoDTO createClaim(ClaimDataDTO claim);
	UserInfoDTO generateReport(String userid);
	ClaimData getClaim(String userId, Long claimNumber);
}
