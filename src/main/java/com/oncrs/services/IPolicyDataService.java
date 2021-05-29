package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;

public interface IPolicyDataService {
	List<PolicyData> getAllPolicies();
	ClaimData claimPolicy(Long userNo, ClaimDataDTO claimpolicy);
	List<PolicyData> getPolicies(Long userNo);
	List<PolicyData> getClaimedPolicies(Long userNo);
}
