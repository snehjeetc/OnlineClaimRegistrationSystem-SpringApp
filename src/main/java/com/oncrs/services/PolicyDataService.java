package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.exception.PolicyException;
import com.oncrs.exception.PolicyException.PolicyExceptionType;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;
import com.oncrs.repositories.IPolicyDataRepository;

@Service
public class PolicyDataService implements IPolicyDataService {

	@Autowired
	private IClaimDataService claimService;
	
	@Autowired
	private IPolicyDataRepository policyDataRepo;
	
	@Override
	public List<PolicyData> getAllPolicies() {
		return this.policyDataRepo.findAll();
	}
	
	@Override
	public ClaimData claimPolicy(Long userNo, ClaimDataDTO claimpolicy) {
		PolicyData policyData = this.policyDataRepo.findPolicy(userNo, claimpolicy.getPolicyNumber());
		ClaimData newClaim = new ClaimData(0l,
										claimpolicy.getClaimReason(),
										claimpolicy.getAccidentLocationStreet(),
										claimpolicy.getCity(),
										claimpolicy.getState(),
										claimpolicy.getZip(),
										claimpolicy.getClaimType());
		
		policyData.setClaimPolicy(newClaim);
		return this.policyDataRepo.save(policyData).getClaimPolicy();
	}

	@Override
	public List<PolicyData> getPolicies(Long userNo){
		return this.policyDataRepo.findPolicyByUserNo(userNo);
	}
	
	@Override
	public List<PolicyData> getClaimedPolicies(Long userNo){
		List<PolicyData> policies = this.getPolicies(userNo);
		return policies.stream().
						filter(policy -> policy.getClaimPolicy() != null)
						.collect(Collectors.toList());
	}
	
}
