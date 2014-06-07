package com.upb.taxbilling.controller.billanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillAnalyzer {
	
	private static String DIVIDER = "\\|";
	
	public BillAnalyzer() {
	}
	
	/**
	 * Divides a given string into tokens using the bill pattern and then returns a list of
	 * the tokens obtained.
	 * @param billText string to be divided in tokens
	 * @return a list of the tokens obtained
	 */
	public List<String> tokenizeBillText(String billText) {
		List<String> billInfo = new ArrayList<String>();
		while(!billText.equals("")) {
		}
		return billInfo;
	}
}
