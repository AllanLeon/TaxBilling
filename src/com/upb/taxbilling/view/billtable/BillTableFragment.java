package com.upb.taxbilling.view.billtable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;

/**
 * The fragment where the table (list) of bills is stored.
 * @author Allan Leon
 * @author Franco Montiel
 * @author Gina Cardozo
 */
public class BillTableFragment extends Fragment {

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
	    popUpMessage(view);
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
     * Adds a empty row to the bill table.
     * @param view
     * @throws Exception 
     */
    public void onClickAddButton(View view) {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	TableRow newRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	//Bill b1 = addElectronicBill();
    	Bill b2 = addManualBill();
    	BillRow row = new BillRow(contentTable.getContext(), getNextBillNumber(newRow),b2);
    	contentTable.addView(row);
    	
    //	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    //	TableRow lastRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    //	BillRow row = new BillRow(contentTable.getContext(), getNextBillNumber(lastRow));
    //	contentTable.addView(row);
    }

    /**
     * Returns the next bill number of a given row, if the row is empty it return zero.
     * @param lastRow the row of which the next number is calculated
     * @return the next bill number
     */
    private int getNextBillNumber(TableRow lastRow) {
    	int number = 0;
    	try {
    		EditText lastNumber = (EditText) lastRow.getChildAt(0);
    		String text = lastNumber.getText().toString();
    		number = Integer.parseInt(text) + 1;
    	} catch (Exception e) {
    		number = 1;
    	}
    	return number;
    }
    
    public Bill addElectronicBill() {
    	String dateString = "31/05/2014";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
        Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Bill> elec = new ArrayList<Bill>();
    	Bill b1 = new Bill(1008565022,9032,3904001124321L, convertedDate, 5.80, "F8-27-08-0B-70");
    	elec.add(b1);
    	return b1;
    }
    
    public Bill addManualBill() {
		ArrayList<Bill> man = new ArrayList<Bill>();
    	Bill b1 = new Bill(1008565022,9032,3904001124321L);
    	man.add(b1);
    	return b1;
    }
    
    public void popUpMessage(View v) {
        //ash
    	AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

    	alert.setTitle("Title");
    	alert.setMessage("Message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(v.getContext());
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  Editable value = input.getText();
    	  // Do something with value!
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
