package com.upb.taxbilling;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.upb.taxbilling.view.ExportBill;
import com.upb.taxbilling.view.RegisterFragment;
import com.upb.taxbilling.view.billtable.BillTableFragment;
import com.upb.taxbilling.view.qr.QRCameraFragment;

/**
 * The main menu of the application, this is the first activity that the compiler starts.
 * This class contains the main fragment and the action bar.
 * @author Allan Leon
 * @author Franco Montiel
 * @author Gina Cardozo
 */
public class MainMenu extends Activity implements ActionBar.OnNavigationListener {

	static ActionBar actionBar;
	
    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	private boolean doubleBackToExitPressedOnce;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        // Set up the action bar to show a dropdown list.
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                getString(R.string.title_menu),
                                getString(R.string.title_register),
                                getString(R.string.title_bill_table),
                                getString(R.string.title_export_bill),
                                getString(R.string.title_qr_reader),
                        }),
                this);
    }

	public void onStart(){
    	super.onStart();
    	
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());     
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
      
        return true;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        getFragmentManager().beginTransaction()
	        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
	        .commit();
        //Updates the fragment view with the chosen fragment when a given 
        //dropdown item is selected.
        switch (position) {
        	case 0:
    			getFragmentManager().beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        		break;
           	case 1:
        	   	getFragmentManager().beginTransaction()
                .replace(R.id.container, new RegisterFragment())
                .commit();
        	   	break;
           	case 2:
           		getFragmentManager().beginTransaction()
                .replace(R.id.container, new BillTableFragment())
                .commit();
           		break;
           	case 3:
           		getFragmentManager().beginTransaction()
           		.replace(R.id.container, new ExportBill())
           		.commit();
           		break;
           	case 4:
           		getFragmentManager().beginTransaction()
                .replace(R.id.container, new QRCameraFragment())
                .commit();
           		
           		break;
        }
         return true;
    }
    
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;                       
            }
        }, 2000);
    } 
    
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            TextView title = (TextView) rootView.findViewById(R.id.textView1);
        	Typeface font = Typeface.createFromAsset(rootView.getContext().getAssets(), "Woodstamp.otf");
        	title.setTypeface(font);
        	
            Button btn1 = (Button) rootView.findViewById(R.id.button1);
            btn1.setOnClickListener(new View.OnClickListener() {
            	@Override
            	public void onClick(View v) {
                	actionBar.setSelectedNavigationItem(1);
                }
            });
            Button btn2 = (Button) rootView.findViewById(R.id.button2);
            btn2.setOnClickListener(new View.OnClickListener() {
            	@Override
            	public void onClick(View v) {
            		actionBar.setSelectedNavigationItem(2);
            	}
            });
                    
            Button btn3 = (Button) rootView.findViewById(R.id.button3);
            btn3.setOnClickListener(new View.OnClickListener() {
            	@Override
            	public void onClick(View v) {
            		actionBar.setSelectedNavigationItem(3);
            	}
            });
                    
            Button btn4 = (Button) rootView.findViewById(R.id.button4);
            btn4.setOnClickListener(new View.OnClickListener() {
            	@Override
            	public void onClick(View v) {
            		actionBar.setSelectedNavigationItem(4);
            	}
            });
            
            return rootView;
        }
    }
}
