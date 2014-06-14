package com.upb.taxbilling.view.billtable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;

/**
 * The fragment where the table (list) of bills is stored.
 * @author Allan Leon
 * @author Franco Montiel
 * @author Gina Cardozo
 */
public class BillTableFragment extends Fragment {
	
	private String value;
	private String impValue;
	private String dateValue;
	private static ArrayList<Bill> bills;
	
	{
		bills = new ArrayList<Bill>();
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bill_table, container, false);
		final Button buttonAdd = (Button) view.findViewById(R.id.ButtonAdd);
	        buttonAdd.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                	onClickAddButton(v);
	            }
	        });
        final Button buttonRemove = (Button) view.findViewById(R.id.ButtonRemove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                	onClickRemoveButton(v);
            }
        });
        final Button buttonClean = (Button) view.findViewById(R.id.ButtonClean);
        buttonClean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                	onClickCleanButton(v);
            }
        });
	    return view;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks
    	// on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method executes when the addButton is pressed.
     * Adds a blank row to the table.
     * @param view
     */
    public void onClickAddButton(View view) {
		TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	TableRow lastRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	BillRow row = new BillRow(contentTable.getContext(), getNextRowNumber(lastRow));
    	contentTable.addView(row);
    	updateBillList();
   	}
    
    /**
     * This method executes when the removeButton is pressed.
     * Removes all the highlighted rows in the table and updates the remaining 
     * rows' numbers.
     * @param view
     */
    public void onClickRemoveButton(View view) {
    	removeHighlightedRows();
    	updateRowNumbers();
    	updateBillList();
   	}
    
    /**
     * This method executes when the clean button is pressed.
     * Removes all the rows in the table.
     * @param view
     */
    public void onClickCleanButton(View view) {
    	cleanTable();
    	updateBillList();
   	}
    
    /**
     * Adds a new bill to the bill table.
     * @param view
     */
    public void runManualBill(final View view) {
    	final TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	final TableRow newRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	final Bill b2 = newManualBill();
    	final TableAlertDialog tad = new TableAlertDialog();

    	//Launches first pop-up message and waits for the user to type a value.
		tad.cashPopUpMessage(view, new TablePromptRunnable(){
			/**
			 * Saves the typed value into the Bill object at the Amount attribute.
			 * At user confirmation, launches the second pop-up message.
			 */
			public void run() {
				value = this.getValue();
				impValue = value;
				Double convertedImp = Double.parseDouble(impValue);
				b2.setAmount(convertedImp);
				tad.datePopUpMessage(view, new TablePromptRunnable() {
					/**
					 * Saves the typed value into the Bill object at the Date attribute.
					 * At user confirmation, prints the Bill object with the inserted data
					 * into the table. Also aggregates the bill to an independent ManualBill Array.
					 */
					public void run() {
						value = this.getValue();
						dateValue = value;
						Date convertedDate = null;
						try {
							convertedDate = new SimpleDateFormat("dd/mm/yyyy", Locale.US).parse(dateValue);
							b2.setEmissionDate(convertedDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						b2.setEmissionDate(convertedDate);
						BillRow row = new BillRow(contentTable.getContext(), getNextRowNumber(newRow), b2);
						contentTable.addView(row);
						updateBillList();
					}
				});
			}
		});
    }
    
    /**
     * Adds a new bill to the bill table.
     * @param view
     */
    public void runElectronicBill(final View view) {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	TableRow newRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	Bill b1 = newElectronicBill();
		BillRow row = new BillRow(contentTable.getContext(), getNextRowNumber(newRow), b1);
		contentTable.addView(row);
		updateBillList();
    }
    
    /**
     * Returns the next row number of a given row, if the row is empty it return zero.
     * @param lastRow the row of which the next number is calculated
     * @return the next row number
     */
    private int getNextRowNumber(TableRow lastRow) {
    	int number = 0;
    	try {
    		TextView lastNumber = (TextView) lastRow.getChildAt(0);
    		String text = lastNumber.getText().toString();
    		number = Integer.parseInt(text) + 1;
    	} catch (Exception e) {
    		number = 1;
    	}
    	return number;
    }
    
    /**
     * Creates a defined manual bill.
     * @return a user created manual bill with only the electronic parameters.
     */
    public Bill newManualBill() {
    	Bill manu1 = new Bill(1008565022,9032,3904001124321L);    	
    	return manu1;
    }

    /**
     * Creates a defined electronic bill.
     * @return a user defined electronic bill with all fields filled.
     */
    public Bill newElectronicBill() {
    	String dateString = "31/05/2014";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.US); 
        Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	Bill elec1 = new Bill(1008565022,9032,3904001124321L, convertedDate, 5.80, "F8-27-08-0B-70");
    	return elec1;
    }
    
    /**
     * Updates the row number of all the rows in the contentTable.
     */
    public void updateRowNumbers() {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	for(int i = 1; i < contentTable.getChildCount(); i++) {
    		BillRow row = (BillRow) contentTable.getChildAt(i);
    		row.setRowNumber(i);
    	}
    }
    
    /**
     * Removes the highlighted rows of the content table.
     */
    public void removeHighlightedRows() {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	for (int i = 1; i < contentTable.getChildCount(); i++) {
    		BillRow row = (BillRow) contentTable.getChildAt(i);
    		if (row.isHighlighted()) {
    			contentTable.removeViewAt(i);
    			i--;
    		}
    	}
    }
    
    /**
     * Cleans all the rows in the table.
     */
    public void cleanTable() {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	contentTable.removeViews(1, contentTable.getChildCount() - 1);
    }
    
    /**
     * Updates the list of bills based on the rows of the table.
     */
    public void updateBillList() {
    	bills.clear();
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	for(int i = 1; i < contentTable.getChildCount(); i++) {
    		BillRow row = (BillRow) contentTable.getChildAt(i);
    		bills.add(row.getBill());
    	}
    }
}
