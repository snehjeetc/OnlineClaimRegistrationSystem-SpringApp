package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.exception.PolicyException;
import com.oncrs.exception.PolicyException.PolicyExceptionType;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;

@Service
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
	public List<PolicyData> getAllPolicies() {
		return this.policies;
	}

	@Override
	public PolicyData addPolicyData(PolicyDataDTO policy, Long userNo) {
		PolicyData policyData = new PolicyData(autoGenerateToken.getAndIncrement(),
											policy.getAccountNumber(), 
											policy.getAccountNumber(),
											userNo,
											null
										);
		this.policies.add(policyData);
		return policyData;
		
	}
	
	@Override
	public ClaimData claimPolicy(ClaimDataDTO claimpolicy) {
		ClaimData addedClaimToRepo = this.claimService.createClaim(claimpolicy);
		PolicyData forPolicy = this.policies
										.stream()
										.filter(policy -> policy.getPolicyNumber().equals(claimpolicy.getPolicyNumber()))
										.findFirst()
										.orElseThrow(()->
												new PolicyException(PolicyExceptionType.POLICIES_NOT_FOUND,
																	"Policy with " + claimpolicy.getPolicyNumber() + " not found"));
		
		forPolicy.setClaimPolicy(addedClaimToRepo);
		return addedClaimToRepo;
	}

}
