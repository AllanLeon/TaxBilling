package com.upb.taxbilling.model.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Contains the information of a bill.
 * @author Allan Leon
 * @author Franco Montiel
 */
public class Bill {

	private int nit;
	private String name;
	private int billNumber;
	private long autorizationNumber;
	private Date emissionDate;
	private Double amount;
	private String controlCode;
	private Date limitEmissionDate;
	private Double iceAmount;
	private Double noTaxSaleAmount;
	private String taxpayerNIT;
	private String taxpayerName;
	private int economicActivity;
	private int subsidiary;

	/**
	 * Constructor with all the parameters of an electronic bill.
	 * @param nit
	 * @param name
	 * @param billNumber
	 * @param autorizationNumber
	 * @param emissionDate
	 * @param amount
	 * @param controlCode
	 * @param limitEmissionDate
	 * @param iceAmount
	 * @param noTaxSaleAmount
	 * @param taxpayerNIT
	 * @param taxpayerName
	 */
	public Bill(int nit, String name, int billNumber, long autorizationNumber,
			Date emissionDate, Double amount, String controlCode,
			Date limitEmissionDate, Double iceAmount, Double noTaxSaleAmount,
			String taxpayerNIT, String taxpayerName) {
		this.nit = nit;
		this.name = name;
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = emissionDate;
		this.amount = amount;
		this.controlCode = controlCode;
		this.limitEmissionDate = calculateLimitEmmisionDate();
		this.iceAmount = iceAmount;
		this.noTaxSaleAmount = noTaxSaleAmount;
		this.taxpayerNIT = taxpayerNIT;
		this.taxpayerName = taxpayerName;
		this.economicActivity = 0;
		this.subsidiary = 0;
	}

	/**
	 * Constructor without the optional parameters of an electronic bill.
	 * @param nit
	 * @param name
	 * @param billNumber
	 * @param autorizationNumber
	 * @param emissionDate
	 * @param amount
	 * @param controlCode
	 * @param limitEmissionDate
	 * @param taxpayerNIT
	 * @param taxpayerName
	 */
	public Bill(int nit, String name, int billNumber, long autorizationNumber,
			Date emissionDate, Double amount, String controlCode,
			Date limitEmissionDate, String taxpayerNIT, String taxpayerName) {
		this.nit = nit;
		this.name = name;
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = new Date();
		this.amount = amount;
		this.controlCode = controlCode;
		this.limitEmissionDate = limitEmissionDate;
		this.iceAmount = 0.0;
		this.noTaxSaleAmount = 0.0;
		this.taxpayerNIT = taxpayerNIT;
		this.taxpayerName = taxpayerName;
		this.economicActivity = 0;
		this.subsidiary = 0;
	}
	
	/**
	 * Constructor with only the parameters of the bills' table.
	 * @param nit
	 * @param billNumber
	 * @param autorizationNumber
	 * @param emissionDate
	 * @param amount
	 * @param controlCode
	 * @param limitEmissionDate 
	 */
	public Bill(int nit, int billNumber, long autorizationNumber,
			Date emissionDate, Double amount, String controlCode) {
		this.nit = nit;
		this.name = "";
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = emissionDate;
		this.amount = amount;
		this.controlCode = controlCode;
		this.limitEmissionDate = calculateLimitEmmisionDate();
		this.iceAmount = 0.0;
		this.noTaxSaleAmount = 0.0;
		this.taxpayerNIT = "";
		this.taxpayerName = "";
		this.economicActivity = 0;
		this.subsidiary = 0;
	}
	
	/**
	 * Constructor of a manual bill.
	 * @param nit
	 * @param name
	 * @param autorizationNumber
	 * @param limitEmissionDate
	 * @param amount
	 * @param economicActivity
	 * @param subsidiary
	 */
	public Bill(int nit, String name, int autorizationNumber, Date limitEmissionDate,
			double amount, int economicActivity, int subsidiary) {
		this.nit = nit;
		this.name = name;
		this.billNumber = 0;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = new Date();
		this.amount = amount;
		this.controlCode = "";
		this.limitEmissionDate = limitEmissionDate;
		this.iceAmount = 0.0;
		this.noTaxSaleAmount = 0.0;
		this.taxpayerNIT = "";
		this.taxpayerName = "";
		this.economicActivity = economicActivity;
		this.subsidiary = subsidiary;
	}
	
	/**
	 * Constructor of a manual bill without the optional parameters.
	 * @param nit
	 * @param name
	 * @param autorizationNumber
	 * @param limitEmissionDate
	 * @param economicActivity
	 * @param subsidiary
	 */
	public Bill(int nit, String name, int autorizationNumber,
			Date limitEmissionDate, int economicActivity, int subsidiary) {
		this.nit = nit;
		this.name = name;
		this.billNumber = 0;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = new Date();
		this.amount = 0.0;
		this.controlCode = "";
		this.limitEmissionDate = limitEmissionDate;
		this.iceAmount = 0.0;
		this.noTaxSaleAmount = 0.0;
		this.taxpayerNIT = "";
		this.taxpayerName = "";
		this.economicActivity = economicActivity;
		this.subsidiary = subsidiary;
	}
	
	/**
	 * Constructor with only the parameters of the bills' table of an manual bill.
	 * @param nit
	 * @param billNumber
	 * @param autorizationNumber
	 */
	public Bill(int nit, int billNumber, long autorizationNumber) {
		this.nit = nit;
		this.name = null;
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = null;
		this.amount = null;
		this.controlCode = null;
		this.limitEmissionDate = new Date();
		this.iceAmount = null;
		this.noTaxSaleAmount = null;
		this.taxpayerNIT = "";
		this.taxpayerName = "";
	}

	/**
	 * @return the nit
	 */
	public int getNit() {
		return nit;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the billNumber
	 */
	public int getBillNumber() {
		return billNumber;
	}

	/**
	 * @return the autorizationNumber
	 */
	public long getAutorizationNumber() {
		return autorizationNumber;
	}

	/**
	 * @return the emissionDate
	 */
	public Date getEmissionDate() {
		return emissionDate;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @return the controlCode
	 */
	public String getControlCode() {
		return controlCode;
	}

	/**
	 * @return the limitEmissionDate
	 */
	public Date getLimitEmissionDate() {
		return limitEmissionDate;
	}

	/**
	 * @return the iceAmount
	 */
	public Double getIceAmount() {
		return iceAmount;
	}

	/**
	 * @return the noTaxSaleAmount
	 */
	public Double getNoTaxSaleAmount() {
		return noTaxSaleAmount;
	}

	/**
	 * @return the taxpayerNIT
	 */
	public String getTaxpayerNIT() {
		return taxpayerNIT;
	}

	/**
	 * @return the taxpayerName
	 */
	public String getTaxpayerName() {
		return taxpayerName;
	}

	/**
	 * @return the economicActivity
	 */
	public int getEconomicActivity() {
		return economicActivity;
	}

	/**
	 * @return the subsidiary
	 */
	public int getSubsidiary() {
		return subsidiary;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(int nit) {
		this.nit = nit;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param billNumber the billNumber to set
	 */
	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	/**
	 * @param autorizationNumber the autorizationNumber to set
	 */
	public void setAutorizationNumber(long autorizationNumber) {
		this.autorizationNumber = autorizationNumber;
	}

	/**
	 * @param emissionDate the emissionDate to set
	 */
	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @param controlCode the controlCode to set
	 */
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	/**
	 * @param limitEmissionDate the limitEmissionDate to set
	 */
	public void setLimitEmissionDate(Date limitEmissionDate) {
		this.limitEmissionDate = limitEmissionDate;
	}

	/**
	 * @param iceAmount the iceAmount to set
	 */
	public void setIceAmount(Double iceAmount) {
		this.iceAmount = iceAmount;
	}

	/**
	 * @param noTaxSaleAmount the noTaxSaleAmount to set
	 */
	public void setNoTaxSaleAmount(Double noTaxSaleAmount) {
		this.noTaxSaleAmount = noTaxSaleAmount;
	}

	/**
	 * @param taxpayerNIT the taxpayerNIT to set
	 */
	public void setTaxpayerNIT(String taxpayerNIT) {
		this.taxpayerNIT = taxpayerNIT;
	}

	/**
	 * @param taxpayerName the taxpayerName to set
	 */
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}

	/**
	 * @param economicActivity the economicActivity to set
	 */
	public void setEconomicActivity(int economicActivity) {
		this.economicActivity = economicActivity;
	}

	/**
	 * @param subsidiary the subsidiary to set
	 */
	public void setSubsidiary(int subsidiary) {
		this.subsidiary = subsidiary;
	}
	
	/**
	 * In this method 120 days has been add to emission date which become limit emission date's bill
	 */
	public Date calculateLimitEmmisionDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(emissionDate);
		c.add(Calendar.DAY_OF_MONTH, +120);  
		return c.getTime();		
	}
	
	/**
	 * This method compares the update date with the limit emission date's bill
	 */
	public boolean VerifyBill(){
        Date today = new Date();
        if (today.getTime() > limitEmissionDate.getTime()) {
            return false;
        } else {
            return true;
        }
    }
}
