package com.example.FareJudgeApp;

import com.example.farejudgeshelakatongo.R;

import android.app.ListActivity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;



public class Listdetails extends ListActivity {

	
			private DatabaseHelper dbHelper;
			
					@Override
					public void onCreate(Bundle savedInstanceState) {
						super.onCreate(savedInstanceState);
						setContentView(R.layout.list_screen);
						dbHelper = new DatabaseHelper(this);
						displayData();
						
					}
					private void displayData() {
						// TODO Auto-generated method stub
						Cursor results = dbHelper.getAllRecords();
						
						String[] columnNames ={DatabaseHelper.numberid_COL, DatabaseHelper.NAME_COL, DatabaseHelper.establishmenttype_COL,
								DatabaseHelper.typeoffoodserved_COL,  DatabaseHelper.location_COL, DatabaseHelper.phonenumber_COL};
						
						int[] displayNames={R.id.textView14, R.id.textView2, R.id.textView3,R.id.textView4, R.id.textView5, R.id.textView6};
					
						SimpleCursorAdapter records = new SimpleCursorAdapter(this, R.layout.list_details, 
								results, columnNames, displayNames);
						setListAdapter(records);


			}
			}
					

				




