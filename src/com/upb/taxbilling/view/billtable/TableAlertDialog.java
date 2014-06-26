package com.upb.taxbilling.view.billtable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.upb.taxbilling.controller.billanalyzer.BillAnalyzer;
import com.upb.taxbilling.exceptions.BillException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The alert dialog that pops up when asking for additional data for the manual bill registration.
 * @author Franco Montiel
 * @author Guillermo Torrez
 */	
public class TableAlertDialog {
	
	private String value;
	
    /**
     * Launches a pop up message with a EditText component and OK/Cancel buttons for user input.
     * @param view 
     * @param postrun Functional class that waits for the user input, validation
     * 				  and then executes the next command. 
     */
    public void controlCodePopUpMessage(View view, final TablePromptRunnable postrun) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
    	alert.setTitle("Codigo de Control faltante");
    	alert.setMessage("Introduzca el codigo de control correspondiente a la factura anteriormente registrada:");

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

	/**
	 * Launches a pop up message with a EditText component and OK/Cancel buttons for user input.
	 * @param view 
	 * @param postrun Functional class that waits for the user input, validation
	 * 				  and then executes the next command. 
	 */
	public void datePopUpMessage(final View view, final TablePromptRunnable postrun) {
		AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
		alert.setTitle("Fecha faltante");
		alert.setMessage("Introduzca la fecha de emisión correspondiente a la factura anteriormente registrada:");
		
		final DatePicker dp = new DatePicker(view.getContext());
		dp.setCalendarViewShown(false);
		alert.setView(dp);
		
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				int day = dp.getDayOfMonth();
				int month = dp.getMonth() + 1;
				int year = dp.getYear();
				value = day + "/" + month + "/" + year;
				Date eDate;
				try {
					eDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(value);
					if (BillAnalyzer.verifyBillDate(eDate)) {
	                	dialog.dismiss();
	    				postrun.setValue(value);
	    				postrun.run();
	                } else {
	            		throw new BillException("Fecha de emision invalida");
	            	}
				} catch (ParseException e) {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
		            alertDialog.setTitle("Problema con la fecha");
		            alertDialog.setMessage("Hubo un error al registrar la fecha\n\n"
		            		+ "Detalles del error:\n" + e.toString());
		            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        		public void onClick(DialogInterface dialog, int whichButton) {
		        			dialog.dismiss();
		        		}
		        	});
		            alertDialog.show();
				} catch (BillException e) {
					Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}                
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
