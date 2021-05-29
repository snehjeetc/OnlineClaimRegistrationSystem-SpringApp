package com.oncrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oncrs.models.ClaimData;

@Repository
public interface IClaimDataRepository extends JpaRepository<ClaimData, Long>{

}
