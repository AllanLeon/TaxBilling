package com.upb.taxbilling.view.qr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upb.taxbilling.MainMenu;
import com.upb.taxbilling.R;
import com.upb.taxbilling.MainMenu.PlaceholderFragment;
import com.upb.taxbilling.qr.QRDecoder;
import com.upb.taxbilling.view.billtable.BillTableFragment;

/**
 * Fragment that decodes a hard-coded qr image.
 * @author Alicia Arce
 * @author Bethsy Pinedo
 * @author Graciela Quiroga
 * @author Rolando Lopez
 * @author Guillermo Torrez
 * @author Carlos Abasto
 */
public class LQ1Fragment extends Fragment {

	private TextView textbox;

	/**
     * {@inheritDoc}
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lq1,
				container, false);
		/**
		 * Declaration of interface entities
		 */
		textbox = (TextView) view.findViewById(R.id.textView1);
		final Button btn1 = (Button) view.findViewById(R.id.button1);
		final Button btn2 = (Button) view.findViewById(R.id.button2);
		final TextView text1 = textbox;
		final ImageView imgv = (ImageView) view.findViewById(R.id.imageView1);
		/**
		 * Shows the QR code(saved in resources) on a Imageview
		 */
		imgv.setImageResource(R.drawable.qr_code);
		/**
		 * On Click button Event
		 */
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/**
				 * Binarization of the image of the QR Code
				 */
				Bitmap qrCodeBitmap = BitmapFactory.decodeResource(
						getResources(), R.drawable.qr_code);
				try {
					String msg = QRDecoder.decodeQRCode(qrCodeBitmap);
					text1.setText(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				MainMenu main = (MainMenu) getActivity();
				main.getActionBar().setSelectedNavigationItem(2);
				
				//BillTableFragment btf = new BillTableFragment();
				//btf.runManualBill(btf.getView());
				//main.getFragmentManager().beginTransaction()
                //.replace(R.id.container, btf)
                //.commit();
			}
		});

		return view;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * Handle action bar item clicks here. The action bar will automatically
		 * handle clicks on the Home/Up button, so longas you specify a parent
		 * activity in AndroidManifest.xml.
		 */
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Saves the bill's text into a text file.
	 * @param arg0
	 */
	public void saveQRBillText(View arg0) {
		String str = textbox.getText().toString();
		try {
			arg0.getContext();
			FileOutputStream fos = arg0.getContext().openFileOutput("textFile.txt",
					Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			/**
			 * Writes the String on the .txt file
			 */
			osw.write(str);
			osw.flush();
			osw.close();
			/**
			 * Shows that the file has been saved
			 */
			Toast.makeText(arg0.getContext(), "Guardado",
					Toast.LENGTH_SHORT).show();
			/**
			 * Clear the textView
			 */
			textbox.setText(" ");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
