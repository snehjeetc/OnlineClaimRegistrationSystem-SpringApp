package com.oncrs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oncrs.models.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long>{
	
	@Query(value = "SELECT * FROM user_info_table where user_id = :userId LIMIT 1", nativeQuery = true)
	List<UserInfo> findByUserId(String userId);
	
}
