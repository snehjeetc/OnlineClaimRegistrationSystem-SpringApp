package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;

public interface IClaimDataService {
	List<ClaimData> getAllClaims();
	ClaimData getClaim(Long claimNumber);
}
