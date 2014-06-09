package com.upb.taxbilling.controller.billanalyzer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.upb.taxbilling.exceptions.BillException;
import com.upb.taxbilling.model.data.Bill;

public class BillAnalyzer {
	
	private static String DIVIDER = "\\|";
	
	public BillAnalyzer() {
	}
	
	/**
	 * Divides a given string into tokens using the divider of this class
	 * and then returns a list of the tokens obtained.
	 * @param billText string to be divided in tokens
	 * @return a list of the tokens obtained
	 */
	public List<String> tokenizeBillText(String billText) {
		List<String> billInfo = Arrays.asList(billText.split(DIVIDER));
		return billInfo;
	}
	
	/**
	 * Creates a manual bill with a given list of a bill's information.
	 * @param billInfo information of the bill
	 * @return a new manual bull
	 * @throws BillException if the bill couldn't be created
	 */
	private Bill createManualBill(List<String> billInfo) throws BillException {
		try {
			int nit = Integer.parseInt(billInfo.get(0));
			String name = billInfo.get(1);
			int autorizationNumber = Integer.parseInt(billInfo.get(2));
			Date limitEmissionDate = new SimpleDateFormat(
					"dd/MM/yyyy", Locale.ENGLISH).parse(billInfo.get(3));
			int aux = 4;
			Double ammount = 0.0;
			if (billInfo.size() == 7) {
				ammount = Double.parseDouble(billInfo.get(aux));
				aux++;
			}
			int economicActivity = Integer.parseInt(billInfo.get(aux));
			int subsidiary = Integer.parseInt(billInfo.get(aux + 1));
			
			return new Bill(nit, name, autorizationNumber, limitEmissionDate,
					ammount, economicActivity, subsidiary);
		} catch (Exception ex) {
			throw new BillException("El formato de la factura manual es incorrecto," +
					"el problema puede ser debido a:\n" + ex.getMessage());
		}
	}
}
