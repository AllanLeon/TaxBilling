package com.upb.taxbilling.controller;

import java.util.ArrayList;
import java.util.List;

import com.upb.taxbilling.model.data.Bill;

/**
 * Contains a list of bills and has all the necessary methods to manage it.
 * @author Allan Leon
 * @author Franco Montiel
 */
public class BillController {

	private List<Bill> bills;

	/**
	 * Constructor that receives a bill list as a parameter.
	 * @param bills an already created list.
	 */
	public BillController(List<Bill> bills) {
		this.bills = bills;
	}

	/**
	 * Constructor that initializes the bill list.
	 */
	public BillController() {
		this.bills = new ArrayList<Bill>();
	}
}
