package com.example.FareJudgeApp;

import com.example.farejudgeshelakatongo.R;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

public class Search extends ListActivity {

	


			
				private DatabaseHelper dbHelper;

				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.search_screen);
					dbHelper = new DatabaseHelper(this);
					Cursor results = dbHelper.getAllRecords();

					String[] columnNames ={DatabaseHelper.NAME_COL};

					int[] displayNames={R.id.Textid};
					
					@SuppressWarnings("deprecation")
					final SimpleCursorAdapter records = new SimpleCursorAdapter(this,
							R.layout.search_record, results, columnNames, displayNames);
					setListAdapter(records);
					// displayData();

					records.setFilterQueryProvider(new FilterQueryProvider() {

						public Cursor runQuery(CharSequence constraint) {
							Log.d("xxxx", "runQuery constraint:" + constraint);
							// uri, projection, and sortOrder might be the same as previous
							// but you might want a new selection, based on your filter
							// content (constraint)
							Cursor cur = dbHelper.getFilteredRecords(constraint.toString());
							return cur; // now your adapter will have the new filtered
										// content
						}
					});

					EditText txtInput = (EditText) findViewById(R.id.editTextFilter);
					txtInput.addTextChangedListener(new TextWatcher() {

						public void afterTextChanged(Editable s) {
							// TODO Auto-generated method stub
							records.getFilter().filter(s);
						}

						public void beforeTextChanged(CharSequence s, int start, int count,
								int after) {
							// TODO Auto-generated method stub

						}

						public void onTextChanged(CharSequence s, int start, int before,
								int count) {
							// TODO Auto-generated method stub
							records.getFilter().filter(s.toString());

							records.notifyDataSetChanged();
						}
					});
				}

				

				
				

			}


