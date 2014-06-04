package com.upb.taxbilling.view;

import java.io.FileWriter;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class ExportBill extends Fragment{
	
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
	
	public void ExportDataRegister(String[] arrayUser){
		
		//Bill bill = new Bill(12345, "name", 111, 222, null, 33.3, "controlCode", null, 44.4, 55.5, 54321, "taxpayerName");
		
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("d:/prueba.txt");
            pw = new PrintWriter(fichero);
 
            for (int i = 0; i < arrayUser.length; i++)
            {
                pw.println(arrayUser[i]);
            }
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } finally{
            	try {
            		if (null != fichero)
            			fichero.close();
                	} catch (Exception e2) {
                		e2.printStackTrace();
                	}
             	 }
		
	}
	
	public String[] UserData()
	{
		RegisterFragment rf = new RegisterFragment();
		String  User = (""+rf.getDataTaxpayer().getNameLastname()+","
				  +rf.getDataTaxpayer().getAddress()+","
				  +rf.getDataTaxpayer().getIdentityNumber()+","
				  +rf.getDataTaxpayer().getExpeditionPlace()+","
				  +rf.getDataCompany().getEmployerBussinesName()+","
				  +rf.getDataCompany().getNitNumber()+","
				  +rf.getDataCompany().getAddress()+"");
		String [] ArrayUser = User.split(",");
		return ArrayUser;
	}
	
	public void BillData()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		BillTableFragment btf =  new BillTableFragment();
		//btf.getArrayListBill();
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		String bill = (""+ArrayBillData.get(0).getNit()+"|"
						 +ArrayBillData.get(0).getBillNumber()+"|"
						 +ArrayBillData.get(0).getAutorizationNumber()+"|"
						 +df.format(ArrayBillData.get(0).getEmissionDate())+"");
	}
	
	
}
