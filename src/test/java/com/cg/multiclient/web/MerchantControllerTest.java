package com.cg.multiclient.web;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.multiclient.domain.Merchant;
import com.cg.multiclient.exception.MerchantIdException;
import com.cg.multiclient.service.MerchantService;
import com.cg.multiclient.service.MapValidationErrorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)

@WebMvcTest(value = MerchantController.class)

/**
 * 
 * @author Sushma
 *
 */

class MerchantControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MerchantService merchantService; 
	@MockBean
	MapValidationErrorService mapValidationErrorService; 
	
	
	@Test
	void test_getProductById() throws Exception{
		
		BDDMockito.given(merchantService.findProductById(Mockito.anyInt())).willReturn(new Merchant(1,"Sushma",23,"T-Shirt",10000,2,"Quality is Good"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/1"))
		.andExpect(status().isOk()) //expected 200 --------actual 404
		.andDo(print())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$").isMap()) // expected {} ------- actual Blank
		.andExpect(jsonPath("$.productId").value(23))
		.andExpect(jsonPath("$.productName").value("T-Shirt"))
		.andExpect(jsonPath("$.productPrice").value(10000))
		.andExpect(jsonPath("$.productQuantity").value(2))
		.andExpect(jsonPath("$.productDescription").value("Quality is Good"))
		.andExpect(jsonPath("$.merchantId").value(1))
		.andExpect(jsonPath("$.merchantName").value("Sushma"));
		
}

	@Test

	void test_getProductById_ThrowMerchantNotFoundException() throws Exception{
		
		BDDMockito.given(merchantService.findProductById(Mockito.anyInt())).willThrow(new MerchantIdException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/1"))
		.andExpect(status().isBadRequest());
		
	}

	@Test
	void test_deleteProductById() throws Exception{
		
		Merchant merchant=new Merchant(1,"Sushma",23,"T-Shirt",10000,2,"Quality is Good");
		List<Merchant> merchants=new ArrayList<>();
		merchants.add(merchant);
		BDDMockito.given(merchantService.findProductById(Mockito.anyInt())).willReturn(new Merchant(1,"Sushma",23,"T-Shirt",10000,2,"Quality is Good"));
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/projects/1"))
		.andExpect(content().string("Product with Id : 1 Deleted!"));
		}

	@Test
 void testGetAllProduct() throws Exception {
		String URI = "/api/projects/all";
		Merchant merchant=new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect");
		merchant.setProductId(23);
		merchant.setProductName("Shirt");
		merchant.setProductPrice(10000);
		merchant.setProductQuantity(2);
		merchant.setProductDescription("Quality is perfect");
		merchant.setMerchantId(1);
		merchant.setMerchantName("sushma");

	      Merchant merchant1=new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect");
	      merchant1.setProductId(23);
			merchant1.setProductName("Shirt");
			merchant1.setProductPrice(10000);
			merchant1.setProductQuantity(2);
			merchant1.setProductDescription("Quality is perfect");
			merchant1.setMerchantId(1);
			merchant1.setMerchantName("sushma");
		List<Merchant> merchantList = new ArrayList<>();
		merchantList.add(merchant);
		merchantList.add(merchant1);

		String jsonInput = this.converttoJson(merchantList);

		Mockito.when(merchantService.findAllProducts()).thenReturn(merchantList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	 void testAddProduct() throws Exception {
		String URI = "/api/projects";
		Merchant merchant=new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect");
		merchant.setProductId(23);
		merchant.setProductName("Shirt");
		merchant.setProductPrice(10000);
		merchant.setProductQuantity(2);
		merchant.setProductDescription("Quality is perfect");
		merchant.setMerchantId(1);
		merchant.setMerchantName("sushma");

		String jsonInput = this.converttoJson(merchant);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
			.content(jsonInput)
			.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
	int status=mvcResult.getResponse().getStatus();
	assertEquals(status,HttpStatus.CREATED.value());
}
private String converttoJson(Object merchant) throws JsonProcessingException {
ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(merchant);
}
}


