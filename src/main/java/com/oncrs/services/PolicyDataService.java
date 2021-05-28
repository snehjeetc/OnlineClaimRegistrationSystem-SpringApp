package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;

public class PolicyDataService implements IPolicyDataService {

	@Autowired
	private IClaimDataService claimService;
	
	private List<PolicyData> policies;	
	
	private static AtomicLong autoGenerateToken;
	
	static {
		autoGenerateToken = new AtomicLong();
	}
	
	public PolicyDataService() {
		this.policies = new ArrayList<>();
	}
	
	@Override
	public List<PolicyData> getPolicies(String userId) {
		
		return null;
	}

	@Override
	public PolicyData addPolicyData(PolicyDataDTO policy) {
		
		return new PolicyData();
	}
	
	@Override
	public ClaimData claimPolicy(ClaimDataDTO claimpolicy) {
		return new ClaimData();
	}

}
