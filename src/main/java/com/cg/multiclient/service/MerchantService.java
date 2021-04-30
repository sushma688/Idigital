package com.cg.multiclient.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.multiclient.domain.Merchant;

import com.cg.multiclient.exception.MerchantIdException;
import com.cg.multiclient.repositories.MerchantRepository;
/**
 * This MerchantService class is used to implement the methods which are in IMerchantService.
 * @author Sushma
 *
 */
@Service
public class MerchantService implements IMerchantService {
	@Autowired
	private MerchantRepository merchantRepository;
/**
 * This saveOrUpdate method is used to save the Details of Merchant .
 * It calls the save method in the Repository layer of Merchant.
 */
	public Merchant saveOrUpdate(Merchant merchant) {
			return merchantRepository.save(merchant);
	}
/**
 * This findProductById method is used to find the Product Details based on the Product Id.
 * In this method it calls the findById method in the Repository layer of Merchant.
 */
	public Merchant findProductById(int productId) {
		Merchant merchant = merchantRepository.findById(productId);
		if (merchant == null) 
			throw new MerchantIdException("Product Id " + productId + " not available");
		return merchant;
	}
/**
 * This findAllProducts method will List the all Products.
 * In this method it calls the findAll method in MerchantRepository layer.
 */
	public Iterable<Merchant> findAllProducts() {
		return merchantRepository.findAll();
	}
/**
 * This deleteProductByProductId method will delete the Product Details by ProductId.
 * This method will call the delete method in Merchant Repository.
 */
	public void deleteProductByProductId(int productId) {
		Merchant merchant=findProductById(productId);
		if(merchant==null) {
			throw new MerchantIdException("Product Id " + productId + " not available");
		}
		merchantRepository.delete(merchant);
	}



	
	
}
