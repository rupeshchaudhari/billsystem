package com.setu.billsystem.model;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Value("${schemeId}")
	String schemeId;
	@Value("${secret}")
	String secret;
	
}
