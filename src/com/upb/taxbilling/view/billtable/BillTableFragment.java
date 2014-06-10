package com.upb.taxbilling.view.billtable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
	ArrayList<Bill> electronicBills = new ArrayList<Bill>();
	ArrayList<Bill> manualBills = new ArrayList<Bill>();

	
	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bill_table,
				container, false);
		final Button button = (Button) view.findViewById(R.id.ButtonAdd);
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                	onClickAddButton(v);
	            }
	        });
	    return view;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
    	contentTable.addView(row);;
   	}
    
    /**
     * Adds a new bill to the bill table.
     * @param view
     */
    public void runManualBill(final View view) {
    	//Bill b1 = addElectronicBill();
    	final TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	final TableRow newRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	final Bill b2 = newManualBill();

    	//Launches first pop-up message and waits for the user to type a value.
		popUpMessage(view, "Importe faltante", "Introduzca el importe correspondiente a la factura anteriormente registrada:", new PromptRunnable(){
			/**
			 * Saves the typed value into the Bill object at the Amount attribute.
			 * At user confirmation, launches the second pop-up message.
			 */
			public void run() {
				value = this.getValue();
				impValue = value;
				Double convertedImp = Double.parseDouble(impValue);
				b2.setAmount(convertedImp);
				popUpMessage(view, "Fecha faltante", "Introduzca la fecha de emisión correspondiente a la factura anteriormente registrada:", new PromptRunnable(){
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
							convertedDate = new SimpleDateFormat("dd/mm/yyyy").parse(dateValue);
							b2.setEmissionDate(convertedDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						b2.setEmissionDate(convertedDate);
						manualBills.add(b2);
						BillRow row = new BillRow(contentTable.getContext(), getNextRowNumber(newRow), b2);
						contentTable.addView(row);
					}
				});

			}
		});
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
     * Creates a defined electronic bill.
     * @return a user defined electronic bill with all fields filled.
     */
    public Bill newElectronicBill() {
    	String dateString = "31/05/2014";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
        Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Bill elec1 = new Bill(1008565022,9032,3904001124321L, convertedDate, 5.80, "F8-27-08-0B-70");
    	return elec1;
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
     * Launches a pop up message with a EditText component and OK/Cancel buttons for user input.
     * @param view 
     * @param title Sets a Title for the pop-up message.
     * @param mess Sets an Information Message for the user input. 
     * @param postrun Functional class that waits for the user input, validation
     * 				  and then executes the next command. 
     */
    public void popUpMessage(View view, String customTitle, String customMessage, final PromptRunnable postrun) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
    	alert.setTitle(customTitle);
    	alert.setMessage(customMessage);

    	final EditText input = new EditText(view.getContext());
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			value = input.getText().toString();
    			dialog.dismiss();
    			postrun.setValue(value);
    			postrun.run();
    			return;
    		}
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    		}
    	});
    	alert.show();
    }
}
