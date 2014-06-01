package com.upb.taxbilling.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upb.taxbilling.R;

/**
 * The fragment where a bill row is stored.
 * @author Allan Leon
 */
public class BillRowFragment extends Fragment {

	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bill_row,
				container, false);
	    return view;
	}
}
