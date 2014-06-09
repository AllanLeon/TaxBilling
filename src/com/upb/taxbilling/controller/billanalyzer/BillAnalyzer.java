package com.upb.taxbilling.controller.billanalyzer;

import java.util.Arrays;
import java.util.List;

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
}
