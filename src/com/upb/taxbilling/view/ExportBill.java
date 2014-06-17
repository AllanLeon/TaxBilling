package com.upb.taxbilling.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;
import com.upb.taxbilling.view.billtable.BillTableFragment;

/**
 * The fragment where the information about a User and Bill is export to a file
 * @author Kevin Aguilar
 */
public class ExportBill extends Fragment{
		
	private double totalAmount;
	private boolean sdAvailable;
	private boolean sdWriteAccess;
	private Button export;
	private TextView nameAndLastName;
	private TextView address;
	private TextView expeditionPlace;
	private TextView identityNumber;
	private TextView addressCompany;
	private TextView employerBussinesName;
	private TextView nitNumber;
	private TextView email;
	private TextView showTotalAmount;
	private TextView showPaymentOnAccount;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_export_bill,
				container, false);
		
		nameAndLastName = (TextView)view.findViewById(R.id.textView8);
		address = (TextView)view.findViewById(R.id.textView9);
		identityNumber = (TextView)view.findViewById(R.id.textView10);
		expeditionPlace = (TextView)view.findViewById(R.id.textView11);
		employerBussinesName  = (TextView)view.findViewById(R.id.textView12);
		nitNumber = (TextView)view.findViewById(R.id.textView13);
		addressCompany = (TextView)view.findViewById(R.id.textView14);
		email = (TextView)view.findViewById(R.id.textView20);
		showTotalAmount = (TextView)view.findViewById(R.id.textView16);
		showPaymentOnAccount = (TextView)view.findViewById(R.id.textView18);
		export = (Button)view.findViewById(R.id.button1);
		
		RegisterFragment rf = new RegisterFragment();
		if(rf.getCheck()) {	
			this.showUserData(this.getUserData());
			this.showBillAmount();
			export.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					clickExport(v);
				}
			});
		}
		else {
			Toast.makeText(getActivity(), "Faltan Datos de Usuario", Toast.LENGTH_SHORT).show();
		}
	    return view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	/**
	 * Method to export user data and bill data
	 * This method is called by the button Export
	 * @param v
	 */
	
	public void clickExport(View v)	{
		exportData(getUserData(), convertBillsMapToStringArray());
	}
	
	/**
	 * Method to create the file and write user data and bill data
	 * This method receives as parameters an ArrayList of user data and an ArrayList of bill data
	 * @param ArrayUser
	 * @param ArrayBill
	 */
	public void exportData(List<String> arrayUser, List<String> arrayBill) {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
		    sdAvailable = true;
		    sdWriteAccess = true;
		} else if (status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
		    sdAvailable = true;
		    sdWriteAccess = false;
		} else {
		    sdAvailable = false;
		    sdWriteAccess = false;
		}
		
		try	{
			if (sdAvailable && sdWriteAccess) {
			    File ruta_sd = Environment.getExternalStorageDirectory();
			    File f = new File(ruta_sd.getAbsolutePath(), "Factura.txt");
			    OutputStreamWriter fout =  new OutputStreamWriter(new FileOutputStream(f));
			
			    fout.write("DiCaprio");
			    fout.write("\n");
			    for(int i=0; i < arrayUser.size(); i++) {
			   		fout.write(arrayUser.get(i));
			   		fout.write("\n");
			    }
			    Toast.makeText(getActivity(), "Exportando Datos de Usuario", Toast.LENGTH_SHORT).show();
			    fout.write("\n");
			    for(int i=0; i < arrayBill.size(); i++) {
			    	fout.write(arrayBill.get(i));
			    	fout.write("\n");
			    }
			    Toast.makeText(getActivity(), "Exportando Facturas", Toast.LENGTH_SHORT).show();
			    fout.close();
			}
		}
		catch (Exception ex) {
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
		}		
	}
	
	/**
	 * This method returns an ArrayList of user data sorted  
	 * @return
	 */
	public ArrayList<String> getUserData() {		
		RegisterFragment rf =  new RegisterFragment();
		ArrayList<String> arrayUser = new ArrayList<String>();
		
		arrayUser.add(rf.getDataTaxpayer().getEmail());
		arrayUser.add(rf.getDataTaxpayer().getNameLastname());
		arrayUser.add(rf.getDataTaxpayer().getAddress());
		arrayUser.add(Integer.toString(rf.getDataTaxpayer().getIdentityNumber()));
		arrayUser.add(rf.getDataTaxpayer().getExpeditionPlace());
		arrayUser.add(rf.getDataCompany().getEmployerBussinesName());
		arrayUser.add(Integer.toString(rf.getDataCompany().getNitNumber()));
		arrayUser.add(rf.getDataCompany().getAddress());
		
		return arrayUser;
	}
	
	/**
	 * @return the information of a bill in the form of a string.
	 */
	public String getBillInfoString(Bill bill) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		String info = Integer.toString(bill.getNit())+"|"
		              +Integer.toString(bill.getBillNumber())+"|"
		              +Long.toString(bill.getAuthorizationNumber())+"|"
		              +df.format(bill.getEmissionDate())+"|"
				      +Double.toString(bill.getAmount())+"|"
				      +bill.getControlCode();
		return info;
	}
	
	/**
	 * This method show user data.
	 * This method receives as parameters an ArrayList of user data
	 * @param UserData
	 */
	public void showUserData(ArrayList<String> userData) {
		nameAndLastName.setText(userData.get(1));
		address.setText(userData.get(2));
		identityNumber.setText(userData.get(3));
		expeditionPlace.setText(userData.get(4));
		employerBussinesName.setText(userData.get(5));
		nitNumber.setText(userData.get(6));
		addressCompany.setText(userData.get(7));
		email.setText(userData.get(0));
	}
	
	/**
	 * This method show total amount and payment on account.
	 */
	public void showBillAmount() {
		totalAmount = 0;
		for(int i : BillTableFragment.getBillList().keySet()) {	
			totalAmount = (totalAmount + BillTableFragment.getBillList().get(i).getAmount());
		}
		showTotalAmount.setText(Double.toString(totalAmount));
		showPaymentOnAccount.setText(Double.toString((totalAmount*0.13)));
	}
	
	/**
	 * Converts the bills' map in BillTableFragment into an array of strings.
	 * @return ArrayList of string of the bills 
	 */
	private List<String> convertBillsMapToStringArray() {
		List<String> billsInfo = new ArrayList<String>();
		for(int i : BillTableFragment.getBillList().keySet()) {
			billsInfo.add(getBillInfoString(BillTableFragment.getBillList().get(i)));
		}
		return billsInfo;
	}
}
