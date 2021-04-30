package com.cg.multiclient.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
/**
 * This is MerchantIdException which extends RunTimeException.
 * This class is called only when Exception is thrown.
 * @author Sushma
 *
 */
public class MerchantIdException extends RuntimeException {

	public MerchantIdException() {
		super();
	}
	
	public MerchantIdException(String errMsg) {
		super(errMsg);
	}
}
