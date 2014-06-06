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
import android.widget.Button;
import android.widget.Toast;


public class ExportBill extends Fragment{
	
	boolean sdDisponible = false;
	boolean sdAccesoEscritura = false;
	Button Export;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_export_bill,
				container, false);
		Export = (Button)view.findViewById(R.id.button1);
		
		Export.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickExport(v);
			}
		});
		
		
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
		
	public void ClickExport(View v)
	{
		this.ExportDataRegister(this.UserData());
		Toast.makeText(getActivity(), "Exporting", Toast.LENGTH_SHORT).show();
	}
	
	public void ExportDataRegister(ArrayList<String> ArrayUser){
		
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
		 
		    OutputStreamWriter fout =  new OutputStreamWriter(new FileOutputStream(f));
		
		for(int i=0; i < ArrayUser.size(); i++)
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
	
	public void ExportDataBill()
	{
		
	}
	
	public ArrayList<String> UserData()
	{		
		RegisterFragment rf =  new RegisterFragment();
		ArrayList<String> ArrayUser = new ArrayList<String>();
		Toast.makeText(getActivity(), rf.getDataTaxpayer().getNameLastname(), Toast.LENGTH_SHORT).show();
		
		ArrayUser.add(rf.getDataTaxpayer().getNameLastname());
		ArrayUser.add(rf.getDataTaxpayer().getAddress());
		ArrayUser.add(Integer.toString(rf.getDataTaxpayer().getIdentityNumber()));
		ArrayUser.add(rf.getDataTaxpayer().getExpeditionPlace());
		ArrayUser.add(rf.getDataCompany().getEmployerBussinesName());
		ArrayUser.add(Integer.toString(rf.getDataCompany().getNitNumber()));
		ArrayUser.add(rf.getDataCompany().getAddress());
		
		return ArrayUser;
	}
	
	public ArrayList<String> BillData()
	{
		Bill bill1 = new Bill(12345, "name1", 111, 222, null, 33.3, "controlCode", null, 44.4, 55.5, 54321, "taxpayerName");
		Bill bill2 = new Bill(12345, "name2", 111, 222, null, 33.3, "controlCode", null, 44.4, 55.5, 54321, "taxpayerName");
		Bill bill3 = new Bill(12345, "name3", 111, 222, null, 33.3, "controlCode", null, 44.4, 55.5, 54321, "taxpayerName");

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		//BillTableFragment btf =  new BillTableFragment();
		//btf.getArrayListBill();
		ArrayList<String> ArrayBill = new ArrayList<String>();
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		ArrayBillData.add(bill1);
		ArrayBillData.add(bill2);
		ArrayBillData.add(bill3);
		
		for(int i=0; i <= ArrayBillData.size() ; i++)
		{
			ArrayBill.add(Integer.toString(ArrayBillData.get(i).getNit())+"|"
		              +"|"+Integer.toString(ArrayBillData.get(i).getBillNumber())+"|"
		              +"|"+Integer.toString(ArrayBillData.get(i).getAutorizationNumber())+"|"
		              +"|"+df.format(ArrayBillData.get(i).getEmissionDate())+"|"
				      +"|"+Double.toString(ArrayBillData.get(i).getAmount())+"|"
				      +"|"+ArrayBillData.get(i).getControlCode());
		}
		return ArrayBill;
	}
}
