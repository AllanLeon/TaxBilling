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
	
	private String address;
	private String employerBussinesName;
	private Integer nitNumber;
	
	/**
	 * Constructor method with parameters
	 * @param Address
	 * @param EmployerBussinesName
	 * @param NitNumber
	 */
	
	public Company(String address,String employerBussinesName,Integer nitNumber)
	{		
		this.address = address;
		this.employerBussinesName = employerBussinesName;
		this.nitNumber = nitNumber;
	}
	
	/**
	 * Getters And Setters
	 * @return
	 */
	
	public String getAddress() 
	{
		return address;
	}

	public String getEmployerBussinesName() 
	{
		return employerBussinesName;
	}

	public Integer getNitNumber() 
	{
		return nitNumber;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public void setEmployerBussinesName(String employerBussinesName) 
	{
		this.employerBussinesName = employerBussinesName;
	}

	public void setNitNumber(Integer nitNumber) 
	{
		this.nitNumber = nitNumber;
	}
	
}
