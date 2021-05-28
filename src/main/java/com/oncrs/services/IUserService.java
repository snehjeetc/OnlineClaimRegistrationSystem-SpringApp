package com.oncrs.services;

import java.util.List;
import java.util.Optional;

import com.oncrs.auth.ApplicationUser;
import com.oncrs.dtos.LoginDetailsDTO;
import com.oncrs.models.UserInfo;

public interface IUserService {
	Optional<ApplicationUser> loadUserByUserName(String username);
	UserInfo registerUser(UserInfo newUser);
	List<UserInfo> getUsers();
}
