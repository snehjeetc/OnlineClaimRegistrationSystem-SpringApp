package com.oncrs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oncrs.models.PolicyData;
import com.oncrs.models.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long>{
	
	Optional<UserInfo> findByUserId(String user_id);
	
}
