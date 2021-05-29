package com.oncrs.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "policy_data_table")
public class PolicyData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="policy_no")
	private Long policyNo;
	
	private Long accountNumber;
	private double premiumAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ClaimData claimPolicy;
}
