package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;

public class ClaimDataService implements IClaimDataService {

	private static AtomicLong autoGenerateToken;
	
	private List<ClaimData> claims;
	
	static {
		autoGenerateToken = new AtomicLong();
	}
	
	public ClaimDataService() {
		this.claims = new ArrayList<>();
	}
	
	@Override
	public List<ClaimData> getAllClaims(String userid) {
		
		return null;
	}

	@Override
	public void createClaim(ClaimDataDTO claimPolicy) {
		
		
	}

	@Override
	public ClaimData getClaim(String userid, Long claimNumber) {
		
		return null;
	}
	
}
