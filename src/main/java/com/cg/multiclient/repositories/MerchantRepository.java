package com.cg.multiclient.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.multiclient.domain.Merchant;

/**
 * This MerchantRepository will perform all the CRUD operation on Employee 
 * 
 * @author Sushma
 *
 */

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer>{
	/**
	 * This method will find the Product details by Product Id
	 * @param id
	 * @return Employee
	 */
	Merchant findById(int productId);

	

	
	
}
