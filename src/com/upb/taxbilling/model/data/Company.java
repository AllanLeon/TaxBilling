package com.upb.taxbilling.model.data;

/**
 * Contains the information of a company
 * @author Kevin Aguilar
 * @author Alejandra Navarro
 */

public class Company {
	
	/**
	 * Attributes to save the information about Company
	 */
	
	private String Address;
	private String EmployerBussinesName;
	private Integer NitNumber;
	
	/**
	 * Constructor method with parameters
	 * @param Address
	 * @param EmployerBussinesName
	 * @param NitNumber
	 */
	
	public Company(String Address,String EmployerBussinesName,Integer NitNumber)
	{		
		this.Address = Address;
		this.EmployerBussinesName = EmployerBussinesName;
		this.NitNumber = NitNumber;
	}
	
	/**
	 * Getters And Setters
	 * @return
	 */
	
	public String getAddress() 
	{
		return Address;
	}

	public String getEmployerBussinesName() 
	{
		return EmployerBussinesName;
	}

	public Integer getNitNumber() 
	{
		return NitNumber;
	}

	public void setAddress(String address) 
	{
		Address = address;
	}

	public void setEmployerBussinesName(String employerBussinesName) 
	{
		EmployerBussinesName = employerBussinesName;
	}

	public void setNitNumber(Integer nitNumber) 
	{
		NitNumber = nitNumber;
	}
	
}
