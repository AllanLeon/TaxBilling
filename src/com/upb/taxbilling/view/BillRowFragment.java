package com.upb.taxbilling.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;

/**
 * The fragment where a bill row is stored.
 * @author Allan Leon
 */
public class BillRowFragment extends Fragment {

	private static String KEY_ARG_NIT = "nit";
	private static String KEY_ARG_BILL_NUMBER= "billNumber";
	private static String KEY_ARG_AUTORIZATION_NUMBER = "autorizationNumber";
	private static String KEY_ARG_DATE = "date";
	private static String KEY_ARG_AMOUNT = "amount";
	private static String KEY_ARG_CONTROL_CODE = "controlCode";
	
	Bill bill;
	
	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TableLayout table = (TableLayout) inflater.inflate(R.id.ContentTable,
				container, false);
		TableRow row = new TableRow(table.getContext());
		addViewsToRow(row);
	    return row;
	}

	/**
	 * Creates a new instance of a bill row fragment with a given bill and adds the
	 * given bill data to the bundle arguments.
	 * @param bill
	 * @return
	 */
	public static BillRowFragment newInstance(Bill bill) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		BillRowFragment brf = new BillRowFragment();
	    brf.setBill(bill);

	    // Get arguments passed in, if any
	    Bundle args = brf.getArguments();
	    if (args == null) {
	        args = new Bundle();
	    }
	    // Add parameters to the argument bundle
	    args.putInt(KEY_ARG_NIT, bill.getNit());
	    args.putInt(KEY_ARG_BILL_NUMBER, bill.getBillNumber());
	    args.putInt(KEY_ARG_AUTORIZATION_NUMBER, bill.getAutorizationNumber());
	    args.putString(KEY_ARG_DATE, df.format(bill.getEmissionDate()));
	    args.putDouble(KEY_ARG_AMOUNT, bill.getAmount());
	    args.putString(KEY_ARG_CONTROL_CODE, bill.getControlCode());
	    brf.setArguments(args);

	    return brf;
	}

	/**
	 * Updates the bill attribute with a given bill.
	 * @param bill
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	/**
	 * @return the bill that this row contains.
	 */
	public Bill getBill() {
		return bill;
	}

	/**
	 * Adds seven EditText fields in a given row and updates the info of them.
	 * @param row in which the fields will be added
	 */
	private void addViewsToRow(TableRow row) {
		EditText t1 = new EditText(row.getContext());
    	t1.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t1.setText(Integer.toString(getNextBillNumber()));
    	t1.setEnabled(false);
    	
    	EditText t2 = new EditText(row.getContext());
    	t2.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t2.setText("");
    	
    	EditText t3 = new EditText(row.getContext());
    	t3.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t3.setText("");
    	
    	EditText t4 = new EditText(row.getContext());
    	t4.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t4.setText("");
    	
    	EditText t5 = new EditText(row.getContext());
    	t5.setInputType(InputType.TYPE_CLASS_DATETIME);
    	t5.setText("");
    	
    	EditText t6 = new EditText(row.getContext());
    	t6.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t6.setText("");
    	
    	EditText t7 = new EditText(row.getContext());
    	t7.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t7.setText("");
    	
    	row.addView(t1);
    	row.addView(t2);
    	row.addView(t3);
    	row.addView(t4);
    	row.addView(t5);
    	row.addView(t6);
    	row.addView(t7);
    	
    	updateRowInfo(row);
	}
	
	/**
     * Returns the next bill number of the bill table.
     * @return the next bill number
     */
    private int getNextBillNumber() {
    	TableLayout contentTable = (TableLayout) getActivity().findViewById(R.id.ContentTable);
    	TableRow lastRow = (TableRow) contentTable.getChildAt(contentTable.getChildCount()-1);
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
     * Updates the information with a bill information of a given row if the
     * arguments aren't empty.
     * @param row of which the fields will be updated
     */
    private void updateRowInfo(TableRow row) {
    	Bundle args = getArguments();
        if (args != null) {
        	EditText t1 = (EditText) row.getChildAt(1);
        	t1.setText(Integer.toString(args.getInt(KEY_ARG_NIT)));
        	
        	EditText t2 = (EditText) row.getChildAt(2);
        	t2.setText(Integer.toString(args.getInt(KEY_ARG_BILL_NUMBER)));
        	
        	EditText t3 = (EditText) row.getChildAt(3);
        	t3.setText(Integer.toString(args.getInt(KEY_ARG_AUTORIZATION_NUMBER)));
        	
        	EditText t4 = (EditText) row.getChildAt(4);
        	t4.setText(args.getString(KEY_ARG_DATE));
        	
        	EditText t5 = (EditText) row.getChildAt(5);
        	t5.setText(Double.toString(args.getInt(KEY_ARG_AMOUNT)));
        	
        	EditText t6 = (EditText) row.getChildAt(6);
        	t6.setText(args.getString(KEY_ARG_CONTROL_CODE));
        }
    }
}
