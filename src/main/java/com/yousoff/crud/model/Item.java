package com.yousoff.crud.model;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
@RequiredArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Item {
	
	@NonNull
	@EqualsAndHashCode.Include
	private String id;
	@NonNull
	private String name;
	@NonNull
	private String description;
	@NonNull
	private Date createdDate;
	private Date updatedDate;
	@NonNull
	private String createdBy;
	private String updatedBy;
	
}
