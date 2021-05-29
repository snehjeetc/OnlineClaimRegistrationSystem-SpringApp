package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;

public interface IPolicyDataService {
	List<PolicyData> getAllPolicies();
	ClaimData claimPolicy(Long policyNo, ClaimDataDTO claimpolicy);
}
