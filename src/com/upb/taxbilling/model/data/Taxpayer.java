package com.upb.taxbilling.model.data;

/**
 * Contains the information of a taxpayer
 * @author Vanessa Sanjinez
 * @author Gina Cardozo
 */

public class Taxpayer {
	
	/**
	 * Attributes to save the information about Taxpayer
	 */
	
	private String nameLastname;
	private String address;
	private String expeditionPlace;
	private String email;
	private int identityNumber;

	/**
	 * Constructor method with parameters
	 */
	 
	public Taxpayer(String nameLastname, String address, String expeditionPlace, String email, int identityNumber)
	{
		this.nameLastname = nameLastname;
		this.address = address;
		this.expeditionPlace = expeditionPlace;
		this.email = email;
		this.identityNumber = identityNumber;
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	
	public String getNameLastname() {
		return nameLastname;
	}

	public String getAddress() {
		return address;
	}

	public String getExpeditionPlace() {
		return expeditionPlace;
	}

	public int getIdentityNumber() {
		return identityNumber;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setNameLastname(String nameLastname) {
		this.nameLastname = nameLastname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setExpeditionPlace(String expeditionPlace) {
		this.expeditionPlace = expeditionPlace;
	}

	public void setIdentityNumber(int identityNumber) {
		this.identityNumber = identityNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
