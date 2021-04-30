package com.cg.multiclient.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT")
/**
 * This Merchant Component is used as a data traveler object from layer to layer
 * 
 * @author Sushma
 *
 */
public class Merchant {
	/**
	 * Id of the Merchant
	 */
	@Column
	private int merchantId;
	/**
	 * Name of the Merchant
	 */
	@Column
	private String merchantName;
	@Id
	@GeneratedValue
	@Column
	private int productId;
	/**
	 * Name of the Product
	 */
	@Column
	private String productName;
	/**
	 * Price of a Product
	 */
	@Column
	private int productPrice;
	/**
	 * Quantity of a Product
	 */
	@Column
	private int productQuantity;
	/**
	 * Description of a Product
	 */
	@Column
	private String productDescription;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public Merchant() {
		
	}
	public Merchant(int merchantId, String merchantName, int productId, String productName, int productPrice,
			int productQuantity, String productDescription) {
		super();
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productDescription = productDescription;
	}
	@Override
	public String toString() {
		return "Merchant [merchantId=" + merchantId + ", merchantName=" + merchantName + ", productId=" + productId
				+ ", productName=" + productName + ", productPrice=" + productPrice + ", productQuantity="
				+ productQuantity + ", productDescription=" + productDescription + "]";
	}
	
	
	
	
	
}