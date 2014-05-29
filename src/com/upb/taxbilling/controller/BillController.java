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
		bills = new ArrayList<Bill>();
	}
	
	/**
	 * Adds a given bill to the list.
	 * @param bill the bill that will be added.
	 */
	public void addBill(Bill bill) {
		bills.add(bill);
	}
	
	/**
	 * Removes a bill in a given position.
	 * @param position of the bill that will be removed.
	 */
	public void removeBillAt(int position) {
		bills.remove(position);
	}
	
	/**
	 * @return the list of bills. 
	 */
	public List<Bill> getBillList() {
		return bills;
	}
	
	/**
	 * Returns a bill in a given position.
	 * @param position of the bill that will be returned.
	 * @return a bill in a given position.
	 */
	public Bill getBillAt(int position) {
		return bills.get(position);
	}
}
