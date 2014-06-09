package com.upb.taxbilling.view.billtable.events;

import android.view.View;
import android.view.View.OnClickListener;

import com.upb.taxbilling.R;
import com.upb.taxbilling.view.billtable.BillRow;
import com.upb.taxbilling.view.billtable.BillTableFragment;

public class RowClickedListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		BillRow row = (BillRow) v.getParent();
		highLightRow(row);
	}

	/**
	 * Highlights the given row and the table's rows' backgrounds are updated. 
	 * @param row to be highlighted
	 */
	private void highLightRow(BillRow row) {
		row.updateHighlight();
		//BillTableFragment table = (BillTableFragment) findViewById(R.id.ContentTable);
		//table.updateRowsBackground();
	}
}
