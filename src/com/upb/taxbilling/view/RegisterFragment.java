package com.upb.taxbilling.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.upb.taxbilling.R;
import com.upb.taxbilling.exceptions.UserDataException;
import com.upb.taxbilling.model.data.Company;
import com.upb.taxbilling.model.data.Taxpayer;

/**
 * The fragment where the information about a bill is registered.
 * @author Kevin Aguilar
 * @author Vanessa Sanjinez
 * @author Alejandra Navarro
 */
public class RegisterFragment extends Fragment {
	
	/**
	 *EditText attributes to get user information
	 *Taxpayer and Company attributes to save user information  
	 */
	private static boolean isChecked = false;
	private static Taxpayer taxpayer;
	private static Company company;
	
	private Button saveButton;
	private EditText nameLastname;
	private EditText address;
	private EditText expeditionPlace;
	private EditText identityNumber;
	private EditText employerBussinesName;
	private EditText nitNumber;
	private EditText addressCompany;
	private EditText email;
	
	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register,
				container, false);
		
		nameLastname = (EditText)view.findViewById(R.id.editText1);
		address = (EditText)view.findViewById(R.id.editText2);
		expeditionPlace = (EditText)view.findViewById(R.id.editText4);
		identityNumber = (EditText)view.findViewById(R.id.editText3);
		employerBussinesName  = (EditText)view.findViewById(R.id.editText5);
		nitNumber = (EditText)view.findViewById(R.id.editText6);
		addressCompany = (EditText)view.findViewById(R.id.editText7);
		email = (EditText)view.findViewById(R.id.editText10);
		saveButton = (Button)view.findViewById(R.id.button1);
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clickSaveData(v);
			}
		});
	    return view;
	}
	
	/**
	 * Method to save user data in taxpayer and company
	 * @param v
	 */
	public void clickSaveData(View v) {
		UserDataException usde = new UserDataException();
		if(usde.userData(nameLastname, address, expeditionPlace,
				identityNumber, employerBussinesName, nitNumber,
				addressCompany, email).equals("")) {
		taxpayer = new Taxpayer(nameLastname.getText().toString(),
				address.getText().toString(), expeditionPlace.getText().toString(),
				email.getText().toString(), Integer.parseInt(identityNumber.getText().toString()));
		company = new Company(addressCompany.getText().toString(),
				employerBussinesName.getText().toString(),
				Integer.parseInt(nitNumber.getText().toString()));
			Toast.makeText(getActivity(), "Guardando", Toast.LENGTH_SHORT).show();
			isChecked = true;
		} else {
			Toast.makeText(getActivity(), "Faltan Datos de Usuario", Toast.LENGTH_SHORT).show();
			isChecked = false;
		}
	}
	
	/**
	 * @return true if it's checked else it returns false.
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * Method to return information saved in Taxpayer
	 * This return an Taxpayer
	 * @return
	 */
    public Taxpayer getDataTaxpayer() {	
		return taxpayer;		
    }
    
    /**
     * Method to return information saved in Taxpayer
     * This return an Company
     * @return
     */
    public Company getDataCompany() {  	
		return company;		
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
}
