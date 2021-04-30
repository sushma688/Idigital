package com.cg.multiclient.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.multiclient.domain.Merchant;
import com.cg.multiclient.service.MerchantService;
import com.cg.multiclient.service.MapValidationErrorService;
/**
 * @author Sushma
 *
 */
@RestController
/**
 * This Request Mapping method will map http Requests.
 * @author Sushma
 *
 */
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	/**
	 * This createNewProduct method will create the Product Details to the Data Base
	 * @param product
	 * @param result
	 * @return Merchant, Product
	 */
	//@RequestMapping(method=RequestMethod.POST)
	@PostMapping("")
	public ResponseEntity<Merchant> createNewProduct(@Valid @RequestBody Merchant merchant, BindingResult result) {
		
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return (ResponseEntity<Merchant>) errorMap;
		Merchant addProduct = merchantService.saveOrUpdate(merchant);
		return new ResponseEntity<>(addProduct, HttpStatus.CREATED);
	}
	@PostMapping("/update")
	public ResponseEntity<Merchant> updateProduct(@Valid @RequestBody Merchant merchant, BindingResult result) {
		Merchant mm=merchantService.findProductById((int)merchant.getProductId());
		mm.setMerchantId(merchant.getMerchantId());
		mm.setMerchantName(merchant.getMerchantName());
		mm.setProductId(merchant.getProductId());
		mm.setProductName(merchant.getProductName());
		mm.setProductPrice(merchant.getProductPrice());
		mm.setProductQuantity(merchant.getProductQuantity());
		mm.setProductDescription(merchant.getProductDescription());
		Merchant mf=merchantService.saveOrUpdate(mm);
		return new ResponseEntity<>(mf, HttpStatus.CREATED);
		
	}
	/**
	 * This getProductById method will give the Details of Product based on the productId
	 * @param id
	 * @return Product
	 */
	@GetMapping("/{productId}")
	public ResponseEntity<Merchant> getProductById(@PathVariable int productId){
		return new ResponseEntity<>( merchantService.findProductById(productId),HttpStatus.OK);
	}
	/**
	 * This getAllProducts method is used the get the List of Products
	 * @return List of Products
	 */
	@GetMapping("/all")
	public Iterable<Merchant> getAllProducts(){
		return merchantService.findAllProducts();
	}
	/**
	 * This deleteProduct method is used to delete the details of Product based on ProductId.
	 * @param id
	 * @return message
	 */
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productId){
		merchantService.deleteProductByProductId(productId);
		return new ResponseEntity<> ("Product with Id : "+productId+" Deleted!",HttpStatus.OK);
	}
}
