package com.cg.multiclient.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cg.multiclient.domain.Merchant;
import com.cg.multiclient.exception.MerchantIdException;
import com.cg.multiclient.repositories.MerchantRepository;
import com.cg.multiclient.service.MerchantService;

/**
 * This class is used to test the methods which are implemented in service layer.
 * @author Sushma
 *
 */

class MerchantServiceTest {
	
	@Mock
	MerchantRepository merchantRepository;
	
	@InjectMocks
	MerchantService merchantService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * This Test Case is used to test the method named by findProductByProductId.
	 */
	@Test
	void test_findProductByProductId() {
		BDDMockito.given(merchantRepository.findById(0)).willReturn((new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect")));
		Merchant merchant= merchantService.findProductById(0);
		assertNotNull(merchant);
		assertEquals(1,merchant.getMerchantId());
		assertEquals("sushma",merchant.getMerchantName());
		assertEquals(23,merchant.getProductId());
	    assertEquals("Shirt",merchant.getProductName());
		assertEquals(10000,merchant.getProductPrice());
		assertEquals(2,merchant.getProductQuantity());
		assertEquals("Quality is perfect",merchant.getProductDescription());
	}
	/**
	 * This Test Case is used to test the method named by findProductByProductId.
	 */

	@Test
	void test_findProductById_ThrowMerchantNotFoundException() {
		BDDMockito.given(merchantRepository.findById(23)).willThrow(new MerchantIdException());
		assertThrows(MerchantIdException.class, ()->merchantService.findProductById(3));
		
	}
	/**
	 * This Test Case is used to test the method named by SaveOrUpdate .
	 */

	@Test
	void testSaveOrUpdate() {
		Merchant merchant=new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect");
		merchant.setProductId(23);
		merchant.setProductName("Shirt");
		merchant.setProductPrice(10000);
		merchant.setProductQuantity(2);
		merchant.setProductDescription("Quality is perfect");
		merchant.setMerchantId(1);
		merchant.setMerchantName("sushma");
		
		Mockito.when(merchantRepository.save(merchant)).thenReturn(merchant);
        assertThat(merchantService.saveOrUpdate(merchant)).isEqualTo(merchant);
	}
	/**
	 * This Test Case is used to test the method named by findAllProducts.
	 */
	@Test
	void test_FindAllProducts() {
		 Merchant merchant1=new Merchant(1,"sushma",23,"Shirt",10000,2,"Quality is perfect");
		 merchant1.setProductId(23);
		 merchant1.setProductName("Shirt");
		 merchant1.setProductPrice(10000);
		 merchant1.setProductQuantity(2);
		 merchant1.setProductDescription("Quality is perfect");
		 merchant1.setMerchantId(1);
		 merchant1.setMerchantName("sushma");
	
		 Merchant merchant2=new Merchant(1,"Manisha",23,"T-Shirt",10000,2,"Quality is Good");
		 merchant2.setProductId(23);
		 merchant2.setProductName("T-Shirt");
		 merchant2.setProductPrice(10000);
		 merchant2.setProductQuantity(2);
		 merchant2.setProductDescription("Quality is Good");
		 merchant2.setMerchantId(1);
		 merchant2.setMerchantName("Manisha");
	
	  List<Merchant> productList=new ArrayList<Merchant>();
	  productList.add(merchant1);
	  productList.add(merchant2);
	  Mockito.when(merchantRepository.findAll()).thenReturn(productList);
	  assertEquals(merchantService.findAllProducts(),productList);
	}
	/**
	 * This Test Case is used to test the method named by deleteProductById.
	 */

	@Test
	void test_deleteProductById()
	{
		BDDMockito.given(merchantRepository.findById(1)).willReturn((new Merchant(1,"Manisha",23,"T-Shirt",10000,2,"Quality is Good")));
		merchantService.deleteProductByProductId(1);
		Merchant merchant=new Merchant(1,"Manisha",0,"T-Shirt",10000,2,"Quality is Good");
		assertEquals(0,merchant.getProductId());
	}
}