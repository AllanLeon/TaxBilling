package com.upb.taxbilling.model.data;

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
	private int autorizationNumber;
	private Date emissionDate;
	private Double amount;
	private String controlCode;
	private Date limitEmissionDate;
	private Double iceAmount;
	private Double noTaxSaleAmount;
	private int taxpayerNIT;
	private String taxpayerName;

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
	public Bill(int nit, String name, int billNumber, int autorizationNumber,
			Date emissionDate, Double amount, String controlCode,
			Date limitEmissionDate, Double iceAmount, Double noTaxSaleAmount,
			int taxpayerNIT, String taxpayerName) {
		this.nit = nit;
		this.name = name;
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = emissionDate;
		this.amount = amount;
		this.controlCode = controlCode;
		this.limitEmissionDate = limitEmissionDate;
		this.iceAmount = iceAmount;
		this.noTaxSaleAmount = noTaxSaleAmount;
		this.taxpayerNIT = taxpayerNIT;
		this.taxpayerName = taxpayerName;
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
	public Bill(int nit, String name, int billNumber, int autorizationNumber,
			Date emissionDate, Double amount, String controlCode,
			Date limitEmissionDate, int taxpayerNIT, String taxpayerName) {
		super();
		this.nit = nit;
		this.name = name;
		this.billNumber = billNumber;
		this.autorizationNumber = autorizationNumber;
		this.emissionDate = emissionDate;
		this.amount = amount;
		this.controlCode = controlCode;
		this.limitEmissionDate = limitEmissionDate;
		this.iceAmount = 0.0;
		this.noTaxSaleAmount = 0.0;
		this.taxpayerNIT = taxpayerNIT;
		this.taxpayerName = taxpayerName;
	}
}
