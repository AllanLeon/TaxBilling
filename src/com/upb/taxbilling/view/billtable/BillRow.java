package com.upb.taxbilling.view.billtable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.upb.taxbilling.R;
import com.upb.taxbilling.exceptions.BillException;
import com.upb.taxbilling.model.data.Bill;
import com.upb.taxbilling.view.billtable.events.RowClickedListener;

/**
 * The TableRow where a bill is stored.
 * @author Allan Leon
 */
public class BillRow extends TableRow {

	private Bill bill;
	private int rowNumber;
	private boolean isHighlighted;
	
	/**
	 * Default constructor of a TableRow, receives a context as a parameter.
	 * @param context
	 */
	public BillRow(Context context) {
		super(context);
		this.rowNumber = 0;
		this.isHighlighted = false;
		initializeComponents();
	}

	/**
	 * Constructor method that receives the context and the number of the row as
	 * parameters.
	 * @param context
	 * @param rowNumber
	 */
	public BillRow(Context context, int rowNumber) {
		super(context);
		this.rowNumber = rowNumber;
		this.isHighlighted = false;
		initializeComponents();
	}

	/**
	 * Constructor method that receives the context, the number of the row
	 * and a bill as parameters, then updates it's components based on the
	 * bill it contains.
	 * @param context
	 * @param rowNumber
	 * @param bill
	 */
	public BillRow(Context context, int rowNumber, Bill bill) {
		super(context);
		this.rowNumber = rowNumber;
		this.bill = bill;
		this.isHighlighted = false;
		initializeComponents();
		try {
			updateRowInfo();
		} catch (BillException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bill the bill to set.
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
	 * @param rowNumber the rowNumber to be set.
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
		TextView t1 = (TextView) this.getChildAt(0);
		t1.setText(Integer.toString(rowNumber));
	}
	
	/**
	 * @return returns this row's number.
	 */
	public int getRowNumber() {
		return rowNumber;
	}
	
	/**
	 * @return true if the row is highlighted else returns false.
	 */
	public boolean isHighlighted() {
		return isHighlighted;
	}
	
	/**
	 * Updates the row's highlight, if the row is already highlighted the highlight
	 * is removed, else the row is highlighted.
	 */
	public void updateHighlight() {
		if (isHighlighted) {
			isHighlighted = false;
		} else {
			isHighlighted = true;
		}
	}

	/**
	 * Initializes the seven EditText fields in this row.
	 */
	private void initializeComponents() {
		TextView t1 = new TextView(this.getContext());
    	t1.setText(Integer.toString(rowNumber));
    	t1.setOnClickListener(new RowClickedListener());
    	
    	EditText t2 = new EditText(this.getContext());
    	t2.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t2.setText("");
    	
    	EditText t3 = new EditText(this.getContext());
    	t3.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t3.setText("");
    	
    	EditText t4 = new EditText(this.getContext());
    	t4.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t4.setText("");
    	
    	EditText t5 = new EditText(this.getContext());
    	t5.setInputType(InputType.TYPE_CLASS_DATETIME);
    	t5.setText("");
    	
    	EditText t6 = new EditText(this.getContext());
    	t6.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t6.setText("");
    	
    	EditText t7 = new EditText(this.getContext());
    	t7.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t7.setText("");
    	
    	this.addView(t1);
    	this.addView(t2);
    	this.addView(t3);
    	this.addView(t4);
    	this.addView(t5);
    	this.addView(t6);
    	this.addView(t7);
    	
    	setFieldsStyle();
	}

    /**
     * Updates the editTexts fields of this row with the information of the bill
     * it contains.
     * @throws BillException when the bill is null
     */
    public void updateRowInfo() throws BillException {
        if (bill != null) {
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        	
        	EditText t1 = (EditText) this.getChildAt(1);
        	t1.setText(Integer.toString(bill.getNit()));
        	
        	EditText t2 = (EditText) this.getChildAt(2);
        	t2.setText(Integer.toString(bill.getBillNumber()));
        	
        	EditText t3 = (EditText) this.getChildAt(3);
        	t3.setText(Long.toString(bill.getAutorizationNumber()));
        	
        	EditText t4 = (EditText) this.getChildAt(4);
        	t4.setText(df.format(bill.getEmissionDate()));
        	        	
        	EditText t5 = (EditText) this.getChildAt(5);
        	t5.setText(Double.toString(bill.getAmount()));
        	
        	EditText t6 = (EditText) this.getChildAt(6);
        	t6.setText(bill.getControlCode());
        }
        throw new BillException("The bill doesn't contain information.");
    }

    /**
     * Sets the text style of the fields of this row.
     */
    private void setFieldsStyle() {
    	TextView tv = (TextView) this.getChildAt(0);
    	tv.setTextColor(Color.rgb(1, 3, 38));
    	tv.setTextSize(20);
    	tv.setTypeface(null, Typeface.BOLD);
    	tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    	for(int i = 1; i < 7; i++) {
    		EditText view = (EditText) this.getChildAt(i);
        	view.setTextColor(Color.rgb(1, 3, 38));
    	}
    	setBackgroundColor(getResources().getColor(R.color.RowNormalColor));
    	TableLayout.LayoutParams tableRowParams =
    			  new TableLayout.LayoutParams
    			  (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
    	tableRowParams.setMargins(0, 0, 0, 1);
    	setLayoutParams(tableRowParams);
    }
}
