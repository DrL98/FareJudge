package com.example.FareJudgeApp;

import com.example.farejudgeshelakatongo.R;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;


public class ReviewList extends ListActivity {

	
					private DatabaseHelper dbHelper;
						@Override
						public void onCreate(Bundle savedInstanceState) {
							super.onCreate(savedInstanceState);
							setContentView(R.layout.review_screen);
							dbHelper = new DatabaseHelper(this);
							displayData();
							
						}
						private void displayData() {
							// TODO Auto-generated method stub
							Cursor results = dbHelper.getAllRecords1();
							
							String[] columnNames ={DatabaseHelper.NAME1_COL, DatabaseHelper.date_COL, DatabaseHelper.typeofmeal_COL,
									DatabaseHelper.overallrating_COL, DatabaseHelper.costofmeal_COL};
							
							int[] displayNames={R.id.textView14, R.id.textView2, R.id.textView3,R.id.textView4};
						
							SimpleCursorAdapter records = new SimpleCursorAdapter(this, R.layout.review_details, 
									results, columnNames, displayNames);
							setListAdapter(records);


				}
				}
						

					





