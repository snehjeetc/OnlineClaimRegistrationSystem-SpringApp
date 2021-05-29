package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;

@Service
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
	public List<ClaimData> getAllClaims() {
		return this.claims;
	}

	@Override
	public ClaimData createClaim(ClaimDataDTO claimPolicy) {
		ClaimData claimToBeAdded = new ClaimData(autoGenerateToken.getAndIncrement(),
													claimPolicy.getClaimReason(),
													claimPolicy.getAccidentLocationStreet(),
													claimPolicy.getCity(),
													claimPolicy.getState(),
													claimPolicy.getZip(),
													claimPolicy.getClaimType());
		this.claims.add(claimToBeAdded);
		return claimToBeAdded;
	}

	@Override
	public ClaimData getClaim(Long claimNumber) {
		ClaimData claimData = this.claims
									.stream()
									.filter(claim -> claimNumber.equals(claim.getClaimNo()))
									.findFirst()
									.orElse(null);
		return claimData;
	}
	
}
