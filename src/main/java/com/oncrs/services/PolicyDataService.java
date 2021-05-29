package com.oncrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.exception.PolicyException;
import com.oncrs.exception.PolicyException.PolicyExceptionType;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;
import com.oncrs.repositories.IPolicyDataRepository;

@Service
public class PolicyDataService implements IPolicyDataService {

	
	@Autowired
	private IPolicyDataRepository policyDataRepo;
	
	@Override
	public List<PolicyData> getAllPolicies() {
		return this.policyDataRepo.findAll();
	}
	
	@Override
	public ClaimData claimPolicy(Long policyNo, ClaimDataDTO claimpolicy) {
		PolicyData policyData = this.policyDataRepo.findById(policyNo)
													.orElseThrow(()->new PolicyException(
																	PolicyExceptionType.POLICY_NOT_FOUND,
																	"Policy with " + policyNo + " not found"));
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

	
}
