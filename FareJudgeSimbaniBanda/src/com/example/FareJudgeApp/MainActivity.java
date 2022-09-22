package com.example.FareJudgeApp;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.example.farejudgeshelakatongo.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class MainActivity extends Activity {
	
	private DatabaseHelper dbHelper;

	private String[] establishmenttype = { "Bar", "Cafe", "Pub","Resturant"};
	   


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
			        
			        
			        
			        Button btnNext = (Button) findViewById(R.id.button1);
					// Handle the event generated when the user clicks a button
					btnNext.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							displayNextAlert(); // call method defined later in the program
							 // get reference to the views
					       
						}
					});
					
					Spinner sp = (Spinner) findViewById(R.id.spinner11);  // get reference
					sp.setAdapter(new ArrayAdapter<String>(this, 
							R.layout.spinner_item_list, establishmenttype));


					// Get ready to access the database later
					dbHelper = new DatabaseHelper(this);
			    }
				//Display the menu
				public boolean onCreateOptionsMenu(Menu menu) {
					super.onCreateOptionsMenu(menu);
					MenuInflater inflater = getMenuInflater();
					inflater.inflate(R.menu.main, menu);
					return true;
				}
				
				
				 //The following are "helper" methods. 
				//They are declared as private because
				 //they can only be accessed within the class.
				//Utility method created to display a popup "toast" alert 
				private void popupToast(String message) {
					Toast.makeText(this, message, Toast.LENGTH_LONG).show();
				}
				
				 // Method to display an Alert confirming the user's input.
				private void displayNextAlert() {
					
					// Get what the user entered
					EditText numberidInput = (EditText) findViewById(R.id.editText1);
					EditText nameInput = (EditText) findViewById(R.id.editText2);
				//	EditText establishmenttypeInput = (EditText) findViewById(R.id.spinner1);
					EditText typeoffoodservedInput = (EditText) findViewById(R.id.editText3);
					EditText locationInput = (EditText) findViewById(R.id.editText4);
					EditText phonenumberInput = (EditText) findViewById(R.id.editText5);
					Spinner sp = (Spinner) findViewById(R.id.spinner11);  // get reference


					
					// final so we can reference them in the anonymous inner class below

					final String strnumberid = numberidInput.getText().toString();
					final String strname = nameInput.getText().toString();
					//final String strestablishmenttype = establishmenttypeInput.getText().toString();
					final String strtypeoffoodserved = typeoffoodservedInput.getText().toString();
					final String strlocation = locationInput.getText().toString();
					final String strphonenumber = phonenumberInput.getText().toString();
					
					int row = sp.getSelectedItemPosition();  // get index of selected item
					final String strestablishmenttype = establishmenttype[row];   // get text associated with selected item
					
					  
					//code for validating input
					//if the field is left blank then a popup toast alert is displayed
					if (strnumberid.equals("")){
						popupToast("Numberid Field is Empty.");
								
					}else if(strname.equals("")){
						popupToast("Name Field is Empty.");
							
					}else if(strestablishmenttype.equals("")){
					popupToast("Eastablishment Type Field is Empty.");
					
					}else if(strtypeoffoodserved.equals("")){
						popupToast("Type of Food served Field is Empty.");
						
					}else if(strlocation.equals("")){
						popupToast("location Field is Empty.");
						
					}else if(strphonenumber.equals("")){
						popupToast("Phone Number Comments Field is Empty.");
						
											
					}else{


					// Create and display the Alert dialog
					new AlertDialog.Builder(this)
							.setTitle("Details entered")
							.setMessage(" Details entered:\n " + strnumberid + "\n " + strname 
									+ "\n " + strestablishmenttype + "\n " + strtypeoffoodserved 
									+ "\n " + strlocation + "\n " + strphonenumber )
							.setNeutralButton("Back",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,	int which) {
											// do nothing - it will just close when clicked
										}
									})
							.setPositiveButton("Save",new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,	int which) {
											// save the details entered if "Save" button clicked
											saveDetails(strnumberid, strname , strestablishmenttype , strtypeoffoodserved , strlocation ,
													strphonenumber );
										}
									}).show();
				}
				}
				protected void saveDetails(String numberid, String name, String establishmenttype, String typeoffoodserved,
						String location, String phonenumber) {
					try {
						
					// insertDetails() in the DataBaseHelper class does the real work	
				   
					dbHelper.insertDetails(numberid, name, establishmenttype, typeoffoodserved,location, phonenumber);
					
					long recordCount = dbHelper.getNumberOfRecords();
					// confirm details saved ...
					new AlertDialog.Builder(MainActivity.this)
							.setTitle("   *** Details saved ***\n" +
			                recordCount + " records have been stored")
							.setNeutralButton("Next",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											// do nothing
										}
									}).show();
					}
					catch (SQLiteException sqle) {
						// ... or display error message
						android.util.Log.w(this.getClass().getName(), 
								"Error saving to database");
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("Couldn't save details")
						.setNeutralButton("Next",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// do nothing
									}
								}).show();
					}

				}
				}

				