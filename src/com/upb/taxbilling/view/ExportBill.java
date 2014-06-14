package com.upb.taxbilling.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 * The fragment where the information about a User and Bill is export to a file
 * @author Kevin Aguilar
 */
public class ExportBill extends Fragment{
		
	private double totalAmount;
	private boolean sdAvailable = false;
	private boolean sdWriteAccess = false;

	Button export;
	TextView nameAndLastName;
	TextView address;
	TextView expeditionPlace;
	TextView identityNumber;
	TextView addressCompany;
	TextView employerBussinesName;
	TextView nitNumber;
	TextView email;
	TextView showTotalAmount;
	TextView showPaymentOnAccount;
	
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
		if(rf.getCheck())
		{
			
			this.showUserData(this.userData());
			this.showBillAmount();
			export.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					clickExport(v);
				}
			});
			
		}
		else
		{
			Toast.makeText(getActivity(), "Faltan Datos de Usuario", Toast.LENGTH_SHORT).show();
		}
	    return view;
	    
	}
	
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
	 * Method to export user data and bill data
	 * This method is called by the button Export
	 * @param v
	 */
	
	public void clickExport(View v)
	{
		this.exportDataRegister(this.userData(),this.billData());
	}
	
	/**
	 * Method to create the file and write user data and bill data
	 * This method receives as parameters an ArrayList of user data and an ArrayList of bill data
	 * @param ArrayUser
	 * @param ArrayBill
	 */
	
	public void exportDataRegister(ArrayList<String> arrayUser, ArrayList<String> arrayBill){
		
		String status = Environment.getExternalStorageState();
		
		Boolean sdAccesoEscritura = false;
		Boolean sdDisponible = false;

		/**
		 * Two "if" to check availabilities SD memory  
		 */		
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
		catch (Exception ex) {
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
		}		
	}
	
	/**
	 * This method returns an ArrayList of user data sorted  
	 * @return
	 */
	public ArrayList<String> userData() {		
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
	 * This method returns an ArrayList of bill data sorted
	 * @return
	 */
	public ArrayList<String> billData() {
		Date now =  new Date();
		Bill bill1 = new Bill(1, 1, 1, now, 10.45, "asd123");
		Bill bill2 = new Bill(1, 2, 1, now, 10.45, "asd123");

		ArrayList<String> arrayBill = new ArrayList<String>();
		ArrayList<Bill> arrayBillData = new ArrayList<Bill>();
		arrayBillData.add(bill1);
		arrayBillData.add(bill2);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		for(int i=0; i < arrayBillData.size() ; i++) {
			arrayBill.add(Integer.toString(arrayBillData.get(i).getNit())+"|"
		              +Integer.toString(arrayBillData.get(i).getBillNumber())+"|"
		              +Long.toString(arrayBillData.get(i).getAuthorizationNumber())+"|"
		              +df.format(arrayBillData.get(i).getEmissionDate())+"|"
				      +Double.toString(arrayBillData.get(i).getAmount())+"|"
				      +arrayBillData.get(i).getControlCode());
		}
		return arrayBill;
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
	 * This method receives as parameters an ArrayList of bill data.
	 */
	public void showBillAmount(/*ArrayList<Bill> billData*/) {
		totalAmount = 0;
		Date now =  new Date();
		Bill bill1 = new Bill(1, 1, 1, now, 10.45, "asd123");
		Bill bill2 = new Bill(1, 2, 1, now, 10.45, "asd123");
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		ArrayBillData.add(bill1);
		ArrayBillData.add(bill2);
		
		for(int i = 0; i < ArrayBillData.size(); i++) {	
			totalAmount = (totalAmount + ArrayBillData.get(i).getAmount());
		}
		
		showTotalAmount.setText(Double.toString(totalAmount));
		showPaymentOnAccount.setText(Double.toString((totalAmount*0.13)));
	}
}
