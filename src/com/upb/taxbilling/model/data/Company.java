package com.upb.taxbilling.model.data;

public class Company {
	
	private String Address;
	private String EmployerBussinesName;
	private Integer NitNumber;
	
	//Constructor method with parameters
	
	public Company(String Address,String EmployerBussinesName,Integer NitNumber)
	{		
		this.Address = Address;
		this.EmployerBussinesName = EmployerBussinesName;
		this.NitNumber = NitNumber;
	}
	
	//Getters And Setters
	
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
