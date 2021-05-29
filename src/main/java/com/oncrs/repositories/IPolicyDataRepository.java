package com.oncrs.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oncrs.models.PolicyData;

@Repository
public interface IPolicyDataRepository extends JpaRepository<PolicyData, Long>{
	
}
