package com.upb.taxbilling.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;
import com.upb.taxbilling.view.billtable.BillTableFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony.Sms.Conversations;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class ExportBill extends Fragment{
	
	boolean sdDisponible = false;
	boolean sdAccesoEscritura = false;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_export_bill,
				container, false);
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
	
	public void ExportDataRegister(ArrayList<String> ArrayUser){
		
		//Bill bill = new Bill(12345, "name", 111, 222, null, 33.3, "controlCode", null, 44.4, 55.5, 54321, "taxpayerName");
		String estado = Environment.getExternalStorageState();
		 
		if (estado.equals(Environment.MEDIA_MOUNTED))
		{
		    sdDisponible = true;
		    sdAccesoEscritura = true;
		}
		else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
		    sdDisponible = true;
		    sdAccesoEscritura = false;
		}
		else
		{
		    sdDisponible = false;
		    sdAccesoEscritura = false;
		}
		try
		{
		    File ruta_sd = Environment.getExternalStorageDirectory();
		 
		    File f = new File(ruta_sd.getAbsolutePath(), "Factura.txt");
		 
		    OutputStreamWriter fout =
		        new OutputStreamWriter(
		            new FileOutputStream(f));
		
		for(int i=0; i <= ArrayUser.size(); i++)
		{
		    	fout.write(ArrayUser.get(i));
		    	fout.write("\n");
		} 
		fout.close();
		    
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
		}
		
	}
	
	public ArrayList<String> UserData()
	{
		RegisterFragment rf = new RegisterFragment();
		ArrayList<String> ArrayUser = new ArrayList<String>();
		ArrayUser.add(rf.getDataTaxpayer().getNameLastname());
		ArrayUser.add(rf.getDataTaxpayer().getAddress());
		ArrayUser.add(Integer.toString(rf.getDataTaxpayer().getIdentityNumber()));
		ArrayUser.add(rf.getDataTaxpayer().getExpeditionPlace());
		ArrayUser.add(rf.getDataCompany().getEmployerBussinesName());
		ArrayUser.add(Integer.toString(rf.getDataCompany().getNitNumber()));
		ArrayUser.add(rf.getDataCompany().getAddress());
		return ArrayUser;
	}
	
	public void BillData()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		BillTableFragment btf =  new BillTableFragment();
		//btf.getArrayListBill();
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		
		ArrayList<String> ArrayBill = new ArrayList<String>();
		ArrayBill.add(Integer.toString(ArrayBillData.get(0).getNit()));
		ArrayBill.add(Integer.toString(ArrayBillData.get(0).getBillNumber()));
		ArrayBill.add(Integer.toString(ArrayBillData.get(0).getAutorizationNumber()));
		ArrayBill.add(df.format(ArrayBillData.get(0).getEmissionDate()));
		ArrayBill.add(Double.toString(ArrayBillData.get(0).getAmount()));
		ArrayBill.add(ArrayBillData.get(0).getControlCode());

	}
	
	
}
