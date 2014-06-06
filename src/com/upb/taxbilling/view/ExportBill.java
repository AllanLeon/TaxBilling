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
		this.ExportDataRegister(this.UserData(),this.BillData());
	}
	
	public void ExportDataRegister(ArrayList<String> ArrayUser, ArrayList<String> ArrayBill){
		
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
		
		fout.write("DiCaprio");
		fout.write("\n");
		for(int i=0; i < ArrayUser.size(); i++)
		{
		    	fout.write(ArrayUser.get(i));
		    	fout.write("\n");
		}
		Toast.makeText(getActivity(), "Exportando Datos de Usuario", Toast.LENGTH_SHORT).show();
		
		for(int i=0; i < ArrayBill.size(); i++)
		{
		    	fout.write(ArrayBill.get(i));
		    	fout.write("\n");
		}
		Toast.makeText(getActivity(), "Exportando Facturas", Toast.LENGTH_SHORT).show();
		fout.close();
		    
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
		}		
	}
	
	public ArrayList<String> UserData()
	{		
		RegisterFragment rf =  new RegisterFragment();
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
	
	public ArrayList<String> BillData()
	{
		Date now =  new Date();
		Bill bill1 = new Bill(1, 1, 1, now, 10.45, "asd123");
		Bill bill2 = new Bill(1, 2, 1, now, 10.45, "asd123");

		ArrayList<String> ArrayBill = new ArrayList<String>();
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		ArrayBillData.add(bill1);
		ArrayBillData.add(bill2);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		for(int i=0; i < ArrayBillData.size() ; i++)
		{
			ArrayBill.add(Integer.toString(ArrayBillData.get(i).getNit())+"|"
		              +Integer.toString(ArrayBillData.get(i).getBillNumber())+"|"
		              +Integer.toString(ArrayBillData.get(i).getAutorizationNumber())+"|"
		              +df.format(ArrayBillData.get(i).getEmissionDate())+"|"
				      +Double.toString(ArrayBillData.get(i).getAmount())+"|"
				      +ArrayBillData.get(i).getControlCode());
		}
		return ArrayBill;
	}
}
