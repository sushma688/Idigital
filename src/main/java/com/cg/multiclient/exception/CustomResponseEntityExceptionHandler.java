package com.cg.multiclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * This class is to handle the  Exception .
 * @author Sushma
 *
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
/**
 * 
 * To handle MerchantIdException
 * @param e
 * @param request
 * @return
 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleMerchantIdException(MerchantIdException e, WebRequest request){
		MerchantIdExceptionResponse exceptionResponse=new MerchantIdExceptionResponse(e.getMessage());
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	
}