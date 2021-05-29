package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.exception.PolicyException;
import com.oncrs.exception.PolicyException.PolicyExceptionType;
import com.oncrs.models.ClaimData;
import com.oncrs.repositories.IClaimDataRepository;

@Service
public class ClaimDataService implements IClaimDataService {

	@Autowired
	private IClaimDataRepository claimDataRepo;
	
	@Override
	public List<ClaimData> getAllClaims() {
		return this.claimDataRepo.findAll();
	}


	@Override
	public ClaimData getClaim(Long claimNumber) {
		return this.claimDataRepo.getById(claimNumber);
	}
	
}
