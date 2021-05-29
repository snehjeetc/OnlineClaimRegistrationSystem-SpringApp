package com.oncrs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oncrs.models.PolicyData;

@Repository
public interface IPolicyDataRepository extends JpaRepository<PolicyData, Long>{
	
	@Query(value = "SELECT * FROM policy_data_table WHERE policy_no = :policyNo && user_no = :userNo", nativeQuery=true)
	PolicyData findPolicy(Long userNo, Long policyNo);
	
	@Query(value = "SELECT * FROM policy_data_table WHERE user_no = :usedrNo", nativeQuery=true)
	List<PolicyData> findPolicyByUserNo(Long userNo);
}
