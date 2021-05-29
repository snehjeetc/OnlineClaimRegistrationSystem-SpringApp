package com.oncrs.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claim_data_table")
public class ClaimData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="claim_no")
	private Long claimNo;
	
	private String claimReason;
	private String accidentLocationStreet;
	private String city;
	private String state;
	private Integer zip;
	private String claimType;	
	
}
