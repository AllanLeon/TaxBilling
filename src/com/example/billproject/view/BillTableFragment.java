package com.example.billproject.view;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.billproject.R;

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
		final Button button = 
                (Button) view.findViewById(R.id.ButtonAdd);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bill_table, container, false);
            return rootView;
        }
    }

    /**
     * Adds a row with the given data of the bill to the given table.
     * @param contentTable the table where the row will be added.
     * @param number of the bill
     * @param nit of the bill
     * @param billNumber of the bill
     * @param orderNumber of the bill
     * @param date of the bill
     * @param amount of the bill
     * @param controlCode of the bill
     */
    public void addRow(TableLayout contentTable, String number, String nit, 
    		String billNumber, String orderNumber, String date, String amount,
    		String controlCode) {    	
    	TableRow contentRow = new TableRow(contentTable.getContext());
    	
    	EditText t1 = new EditText(contentTable.getContext());
    	t1.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t1.setText(number);
    	t1.setEnabled(false);
    	
    	EditText t2 = new EditText(contentTable.getContext());
    	t2.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t2.setText(nit);
    	
    	EditText t3 = new EditText(contentTable.getContext());
    	t3.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t3.setText(billNumber);
    	
    	EditText t4 = new EditText(contentTable.getContext());
    	t4.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t4.setText(orderNumber);
    	
    	EditText t5 = new EditText(contentTable.getContext());
    	t5.setInputType(InputType.TYPE_CLASS_DATETIME);
    	t5.setText(date);
    	
    	EditText t6 = new EditText(contentTable.getContext());
    	t6.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t6.setText(amount);
    	
    	EditText t7 = new EditText(contentTable.getContext());
    	t7.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t7.setText(controlCode);
    	
    	contentRow.addView(t1);
    	contentRow.addView(t2);
    	contentRow.addView(t3);
    	contentRow.addView(t4);
    	contentRow.addView(t5);
    	contentRow.addView(t6);
    	contentRow.addView(t7);
    	setRowStyle(contentRow);
    	
    	contentTable.addView(contentRow);
    }

    /**
     * This method executes when the addButton is pressed.
     * Adds a empty row to the bill table.
     * @param view
     */
    public void onClickAddButton(View view) {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	TableRow lastRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
    	int number = getNextBillNumber(lastRow);
    	addRow(contentTable, Integer.toString(number), "", "", "", "", "", "");
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

    /**
     * Sets the text style of a given row.
     * @param row the row of which the style will be set
     */
    private void setRowStyle(TableRow row) {
    	for(int i = 0; i < 7; i++) {
    		EditText view = (EditText) row.getChildAt(i);
        	view.setTextColor(Color.rgb(1, 3, 38));
    	}
    }
}