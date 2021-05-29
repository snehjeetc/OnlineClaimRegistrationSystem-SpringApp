package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;

public interface IClaimDataService {
	List<ClaimData> getAllClaims();
	ClaimData createClaim(ClaimDataDTO claimPolicy);
	ClaimData getClaim(Long claimNumber);
}
