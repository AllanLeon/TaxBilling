package com.upb.taxbilling.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

	    Bundle args = getArguments();
	    if (args != null) {
	        //mImageResourceId = args.getInt(KEY_ARG_IMAGE_RES_ID, StaticData.getImageIds()[0]);
	    }
	    else {
	        //mImageResourceId = StaticData.getImageIds()[0];
	    }
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
}
