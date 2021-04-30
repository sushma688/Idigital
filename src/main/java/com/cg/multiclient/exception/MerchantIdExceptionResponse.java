package com.cg.multiclient.exception;
/**
 * This is the response of MerchantIdException
 * @author Sushma
 *
 */
public class MerchantIdExceptionResponse {
	private String id;

	

	public MerchantIdExceptionResponse(String id) {
		this.id=id;
	}

	public String getId() {
		return id;
	}

	
	
}
