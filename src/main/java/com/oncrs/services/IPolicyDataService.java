package com.oncrs.services;

import java.util.List;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;

public interface IPolicyDataService {
	List<PolicyData> getPolicies(String userId);
	PolicyData addPolicyData(PolicyDataDTO policy);
	ClaimData claimPolicy(ClaimDataDTO claimpolicy);
}
