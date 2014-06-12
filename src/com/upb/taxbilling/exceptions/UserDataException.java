package com.upb.taxbilling.exceptions;

import java.util.ArrayList;

import android.widget.EditText;
import android.widget.Toast;

import com.upb.taxbilling.model.data.Company;
import com.upb.taxbilling.model.data.Taxpayer;
import com.upb.taxbilling.view.RegisterFragment;

public class UserDataException {
		
		/**
		 * This attribute stores the message error
		 */
		private String message;
		
		/**
		 * This method is the constructor and
         * this does not receive parameters
		 */
		public UserDataException()
		{
		}
		
		/**
		 * This method checks that all user data were entered
		 * @param nameLastname
		 * @param address
		 * @param expeditionPlace
		 * @param identityNumber
		 * @param employerBussinesName
		 * @param nitNumber
		 * @param addressCompany
		 * @param email
		 * @return
		 */
		public String userData(EditText nameLastname, EditText address,EditText expeditionPlace,EditText identityNumber,
								EditText employerBussinesName,EditText nitNumber,EditText addressCompany, EditText email)
		{
			if(fieldEmpty(nameLastname.getText().toString())
			   || fieldEmpty(address.getText().toString())
			   || fieldEmpty(expeditionPlace.getText().toString())
			   || fieldEmpty(identityNumber.getText().toString())
			   || fieldEmpty(employerBussinesName.getText().toString())
			   || fieldEmpty(nitNumber.getText().toString())
			   || fieldEmpty(addressCompany.getText().toString()))
			{
				return message = "error";
			}
			else
			{
				return message = "";
			}
		}
		
		/**
		 * This method checks that a field is filled
		 * @param value
		 * @return
		 */
		public boolean fieldEmpty(String value)
		{
			boolean error = false;
			
			if(value.equals(""))
			{
				error = true;
			}			
			return error;
			
		}
		
}
