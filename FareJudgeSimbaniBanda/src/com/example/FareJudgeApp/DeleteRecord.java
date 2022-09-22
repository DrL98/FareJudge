package com.example.FareJudgeApp;

/* 
 * program ListPersistFiltering.java
 * Author : c njilika
 * Student no :
 * Purpose  : To display summary enter people`s details
 * 
 * Modification history
 * ********************************************************************************************
 * Date Modified                                                  Purpose /Comments
 * ********************************************************************************************
 * 15/11/13                                                       Initial creation
 * 17/11/13    
 * 18/11/13                                                       search records,sources                                                         
 */

import com.example.farejudgeshelakatongo.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

	public class DeleteRecord extends ListActivity {
		private DatabaseHelper dbHelper;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.delete_screen);
			dbHelper = new DatabaseHelper(this);
			displayData();
			
			
			
			
			
			
			
		}
		private void displayData() {
			// TODO Auto-generated method stub
			Cursor results = dbHelper.getAllRecords();
			
			String[] columnNames ={DatabaseHelper.numberid_COL, DatabaseHelper.NAME_COL, DatabaseHelper.establishmenttype_COL, DatabaseHelper.typeoffoodserved_COL,
					DatabaseHelper.location_COL, DatabaseHelper.phonenumber_COL};
			int[] displayNames={R.id.textView14, R.id.textView2, R.id.textView3,R.id.textView4, R.id.textView5, R.id.textView6};
		
			@SuppressWarnings("deprecation")
			SimpleCursorAdapter records = new SimpleCursorAdapter(this, R.layout.delete_record_row, 
					results, columnNames, displayNames);
			setListAdapter(records);
		
		
		}
		

			
		}	
	
	
	
		
