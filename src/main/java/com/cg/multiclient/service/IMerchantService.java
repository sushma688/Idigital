package com.cg.multiclient.service;

import com.cg.multiclient.domain.Merchant;

/**
 * This is the IMerchantService interface, If contains only abstract methods.
 * @author Sushma
 *
 */
public interface IMerchantService {
	/**
	 * This saveOrUpdate method will add the Product details to the Data Base.
	 * @param merchant
	 *
	 */
	Merchant saveOrUpdate(Merchant merchant);
	/**
	 * This findProductById method will find the product details based on Product Id.
	 * @param productId
	 * @return Product Details
	 */
	Merchant findProductById(int productId);
	/**
	 * This findAllProducts method will find the List of Products .
	 * @return List of Products
	 */
	Iterable<Merchant> findAllProducts();
	/**
	 * This deleteProductById method will delete the Product Details based on the ProductId.
	 * @param id
	 */
	void deleteProductByProductId(int productId);
}
