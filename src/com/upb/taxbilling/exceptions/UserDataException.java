package com.upb.taxbilling.exceptions;

import android.widget.EditText;

/**
 * Verifies that all the fields on the user register fragment aren't empty.
 * @author Kevin Aguilar
 * @author Alejandra Navarro
 */
public class UserDataException {
		
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
		public String userData(EditText nameLastname, EditText address,
				EditText expeditionPlace, EditText identityNumber, 
				EditText employerBussinesName, EditText nitNumber,
				EditText addressCompany, EditText email, EditText year) {
			if(fieldEmpty(nameLastname.getText().toString())
			   || fieldEmpty(address.getText().toString())
			   || fieldEmpty(expeditionPlace.getText().toString())
			   || fieldEmpty(identityNumber.getText().toString())
			   || fieldEmpty(employerBussinesName.getText().toString())
			   || fieldEmpty(nitNumber.getText().toString())
			   || fieldEmpty(addressCompany.getText().toString())
			   || fieldEmpty(year.getText().toString())) {
				return "Error";
			} else {
				return "";
			}
		}
		
		/**
		 * This method checks that a field is filled
		 * @param value
		 * @return
		 */
		public boolean fieldEmpty(String value) {
			boolean error = false;
			if(value.equals("")) {
				error = true;
			}			
			return error;
		}
}
