package com.upb.taxbilling.model.data;

public class Taxpayer {
		
	private String NameLastname;
	private String Address;
	private String ExpeditionPlace;
	private int IdentityNumber;

	//Constructor Method with parameters 
	 
	public Taxpayer(String NameLastname, String Address, String ExpeditionPlace, int IdentityNumber)
	{
		this.NameLastname = NameLastname;
		this.Address = Address;
		this.ExpeditionPlace = ExpeditionPlace;
		this.IdentityNumber = IdentityNumber;
	}
	
	//Getters and Setters
	
	public String getNameLastname() {
		return NameLastname;
	}

	public String getAddress() {
		return Address;
	}

	public String getExpeditionPlace() {
		return ExpeditionPlace;
	}

	public int getIdentityNumber() {
		return IdentityNumber;
	}

	public void setNameLastname(String nameLastname) {
		NameLastname = nameLastname;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setExpeditionPlace(String expeditionPlace) {
		ExpeditionPlace = expeditionPlace;
	}

	public void setIdentityNumber(int identityNumber) {
		IdentityNumber = identityNumber;
	}
	
}