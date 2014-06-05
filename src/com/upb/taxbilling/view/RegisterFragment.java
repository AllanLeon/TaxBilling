package com.upb.taxbilling.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Company;
import com.upb.taxbilling.model.data.Taxpayer;

/**
 * The fragment where the information about a bill is registered.
 * @author Kevin Aguilar
 * @author Vanessa Sanjinez
 * @author Alejandra Navarro
 */
public class RegisterFragment extends Fragment {
	
	Button SaveButton;
	EditText NameLastname;
	EditText Address;
	EditText ExpeditionPlace;
	EditText IdentityNumber;
	EditText EmployerBussinesName;
	EditText NitNumber;
	EditText AddressCompany;
	Taxpayer taxpayer;
	Company company;
	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register,
				container, false);
		
		SaveButton = (Button)view.findViewById(R.id.button1);
		NameLastname = (EditText)view.findViewById(R.id.editText1);
		Address = (EditText)view.findViewById(R.id.editText2);
		ExpeditionPlace = (EditText)view.findViewById(R.id.editText4);
		IdentityNumber = (EditText)view.findViewById(R.id.editText3);
		EmployerBussinesName  = (EditText)view.findViewById(R.id.editText5);
		NitNumber = (EditText)view.findViewById(R.id.editText6);
		AddressCompany = (EditText)view.findViewById(R.id.editText7);
		
		// Save Data Register
		
		SaveButton.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				// Instance of the classes Company and Taxpayer
				
				taxpayer = new Taxpayer(NameLastname.getText().toString(), Address.getText().toString(), ExpeditionPlace.getText().toString(), Integer.parseInt(IdentityNumber.getText().toString()));
				company = new Company(AddressCompany.getText().toString(), EmployerBussinesName.getText().toString(), Integer.parseInt(NitNumber.getText().toString()));
			}
		});
		
	    return view;
	}

	/**
     * {@inheritDoc}
     */
    @Override
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
    
    public Taxpayer getDataTaxpayer()
    {
  	
		return taxpayer;
		
    }
    
    public Company getDataCompany()
    {
  	
		return company;
		
    }
    
}
