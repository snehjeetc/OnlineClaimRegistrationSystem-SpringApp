package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;

public interface IClaimDataService {
	List<ClaimData> getAllClaims(String userid);
	void createClaim(ClaimDataDTO claimPolicy);
	ClaimData getClaim(String userid, Long claimNumber);
}
