package com.upb.taxbilling.view.billtable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
	private DateFormat dateFormat;
	
	/**
	 * Default constructor of a TableRow, receives a context as a parameter.
	 * @param context
	 */
	public BillRow(Context context) {
		super(context);
		this.rowNumber = 0;
		this.isHighlighted = false;
		this.bill = new Bill();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
		this.bill = new Bill();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
    	
    	this.addView(t1);
    	this.addView(createNitEditText());
    	this.addView(createBillNumberEditText());
    	this.addView(createAuthorizationNumberEditText());
    	this.addView(createDateEditText());
    	this.addView(createAmountEditText());
    	this.addView(createControlCodeEditText());
    	
    	setFieldsStyle();
	}

    /**
     * Updates the editTexts fields of this row with the information of the bill
     * it contains.
     * @throws BillException when the bill is null
     */
    public void updateRowInfo() throws BillException {
        if (bill != null) {
        	EditText t1 = (EditText) this.getChildAt(1);
        	t1.setText(Integer.toString(bill.getNit()));
        	
        	EditText t2 = (EditText) this.getChildAt(2);
        	t2.setText(Integer.toString(bill.getBillNumber()));
        	
        	EditText t3 = (EditText) this.getChildAt(3);
        	t3.setText(Long.toString(bill.getAuthorizationNumber()));
        	
        	EditText t4 = (EditText) this.getChildAt(4);
        	t4.setText(dateFormat.format(bill.getEmissionDate()));
        	        	
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
    
    /**
     * @return new edit text that contains the nit.
     */
    private EditText createNitEditText() {
    	final EditText t2 = new EditText(this.getContext());
    	t2.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t2.setText("");
    	t2.addTextChangedListener(new TextWatcher() {
			
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the bill's nit with the nit's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				bill.setNit(Integer.parseInt(t2.getText().toString()));
			}
		});
    	return t2;
    }
    
    /**
     * @return new edit text that contains the bill number.
     */
    private EditText createBillNumberEditText() {
    	final EditText t3 = new EditText(this.getContext());
    	t3.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t3.setText("");
    	t3.addTextChangedListener(new TextWatcher() {
			
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the bill's bill number with the
    		 * bill number's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				bill.setBillNumber(Integer.parseInt(t3.getText().toString()));
			}
		});
    	return t3;
    }
    
    /**
     * @return new edit text that contains the authorization number.
     */
    private EditText createAuthorizationNumberEditText() {
    	final EditText t4 = new EditText(this.getContext());
    	t4.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t4.setText("");
    	t4.addTextChangedListener(new TextWatcher() {
			
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the bill's authorization number with the 
    		 * authorization number's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				bill.setAuthorizationNumber(Integer.parseInt(t4.getText().toString()));
			}
		});
    	return t4;
    }

    /**
     * @return new edit text that contains the date.
     */
    private EditText createDateEditText() {
    	final EditText t5 = new EditText(this.getContext());
    	t5.setInputType(InputType.TYPE_CLASS_DATETIME);
    	t5.setText("");
    	t5.addTextChangedListener(new TextWatcher() {
    		private String current = "";
    	    private String ddmmyyyy = "DDMMYYYY";
    	    private Calendar cal = Calendar.getInstance();
    	    
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				 if (!s.toString().equals(current)) {
			            String clean = s.toString().replaceAll("[^\\d.]", "");
			            String cleanC = current.replaceAll("[^\\d.]", "");

			            int cl = clean.length();
			            int sel = cl;
			            for (int i = 2; i <= cl && i < 6; i += 2) {
			                sel++;
			            }
			            //Fix for pressing delete next to a forward slash
			            if (clean.equals(cleanC)) sel--;

			            if (clean.length() < 8){
			               clean = clean + ddmmyyyy.substring(clean.length());
			            }else{
			               //This part makes sure that when we finish entering numbers
			               //the date is correct, fixing it otherways
			               int day  = Integer.parseInt(clean.substring(0,2));
			               int mon  = Integer.parseInt(clean.substring(2,4));
			               int year = Integer.parseInt(clean.substring(4,8));

			               if(mon > 12) mon = 12;
			               cal.set(Calendar.MONTH, mon-1);
			               day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
			               year = (year<1900)?1900:(year>2100)?2100:year;
			               clean = String.format("%02d%02d%02d",day, mon, year);
			            }

			            clean = String.format("%s/%s/%s", clean.substring(0, 2),
			                clean.substring(2, 4),
			                clean.substring(4, 8));
			            current = clean;
			            t5.setText(current);
			            t5.setSelection(sel < current.length() ? sel : current.length());
			        }
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the bill's date with the date's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				try {
					bill.setEmissionDate(dateFormat.parse(t5.getText().toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
    	return t5;
    }

    /**
     * @return new edit text that contains the amount.
     */
    private EditText createAmountEditText() {
    	final EditText t6 = new EditText(this.getContext());
    	t6.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t6.setText("");
    	t6.addTextChangedListener(new TextWatcher() {
			
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the bill's amount with the amount's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				bill.setAmount(Double.parseDouble(t6.getText().toString()));
			}
		});
    	return t6;
    }

    /**
     * @return new edit text that contains the control code.
     */
    private EditText createControlCodeEditText() {
    	final EditText t7 = new EditText(this.getContext());
    	t7.setInputType(InputType.TYPE_CLASS_NUMBER);
    	t7.setText("");
    	t7.addTextChangedListener(new TextWatcher() {
			
    		/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			/**
    		 * {@inheritDoc}
    		 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			/**
    		 * Updates the control code's amount with the 
    		 * control code's edit text info.
    		 */
			@Override
			public void afterTextChanged(Editable s) {
				bill.setControlCode(t7.getText().toString());
			}
		});
    	return t7;
    }
}
