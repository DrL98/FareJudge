package com.example.FareJudgeApp;

import com.example.farejudgeshelakatongo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.view.*;
import android.widget.*;



public class Review  extends Activity{

							
					private DatabaseHelper dbHelper;
					private String[] name1 = { "Wimpy", "Galito's", "Debonairs", "Mug & Bean"};
					


					@Override
					protected void onCreate(Bundle savedInstanceState) {
						super.onCreate(savedInstanceState);
						setContentView(R.layout.review_view);
						
							DatePicker dp = (DatePicker) this.findViewById(R.id.datePicker2);
										dp.init(1980, 5, 1, null); // set to 1st June 1980 - note months start
																	// at 0

										Button btnupdate = (Button) findViewById(R.id.button1);
										/* Handle the event generated when the user clicks a button */
										btnupdate.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												displayNextAlert(); // call method defined later in the program
											}
										});
										
										Spinner sp = (Spinner) findViewById(R.id.spinner1);  // get reference
										sp.setAdapter(new ArrayAdapter<String>(this, 
												R.layout.spinner_item_list1, name1));
										
										//Spinner sp1 = (Spinner) findViewById(R.id.spinner2);  // get reference
										//sp.setAdapter(new ArrayAdapter<String>(this, 
										//		R.layout.spinner_item_list2, typeofmeal));


										// Get ready to access the database later
										dbHelper = new DatabaseHelper(this);
									}

									/* Display the menu. Note the menu is defined in menu.xml */
									public boolean onCreateOptionsMenu(Menu menu) {
										super.onCreateOptionsMenu(menu);
										MenuInflater inflater = getMenuInflater();
										inflater.inflate(R.menu.main, menu);
										return true;
									}

									/* Process the user's selection from the menu */
									//public boolean onOptionsItemSelected(MenuItem item) {
									

									/*
									 * The following are "helper" methods. They are declared as private because
									 * they are only called from within the class.
									 */

									/* Utility method created to display a pop up "toast" alert */
									private void popupToast(String message) {
									Toast.makeText(this, message, Toast.LENGTH_LONG).show();
									}

									/*
									 * Method to display an Alert confirming the user's input. Makes sense to
									 * have this in a separate method as it is called when either the "Next"
									 * menu item or button is clicked.
									 */
									private void displayNextAlert() {
										// Get what the user entered
										
										//EditText establishment1Input = (EditText) findViewById(R.id.editText1);
										EditText name1Input = (EditText) findViewById(R.id.editText5);
										//EditText dateInput = (EditText) findViewById(R.id.editText1);
										EditText typeofmealInput = (EditText) findViewById(R.id.editText2);
										EditText overallratingInput = (EditText) findViewById(R.id.editText3);
										EditText costofmealInput = (EditText) findViewById(R.id.editText4);
										DatePicker dobInput = (DatePicker) findViewById(R.id.datePicker2);
										
										
										Spinner sp = (Spinner) findViewById(R.id.spinner1);  // get reference
										//Spinner sp1 = (Spinner) findViewById(R.id.spinner1);  // get reference

										
										// final so we can reference them in the anonymous inner class below
										//final String strestablishment1 = establishment1Input.getText().toString();
										//final String strname1 = name1Input.getText().toString();
										//final String strdate = dateInput.getText().toString();
										final String strtypeofmeal = typeofmealInput.getText().toString();
										final String stroverallrating = overallratingInput.getText().toString();
										final String strcostofmeal = costofmealInput.getText().toString();
										
									
										final String strdob = dobInput.getDayOfMonth() + "/"
												+ (dobInput.getMonth() + 1) + "/" + dobInput.getYear();
										int row = sp.getSelectedItemPosition();  // get index of selected item
										final String strname1 = name1[row];   // get text associated with selected item
										
										
										

										if (strname1.equals("")){
											popupToast("Establishment Name Missing.");
											
										}else if(strdob.equals("")){
											popupToast("Date of Review Missing.");
											
										}else if(strtypeofmeal.equals("")){
											popupToast("Type of Meal Missing.");
													
										}else if(stroverallrating.equals("")){
											popupToast("Over All Rating Missing.");
												
										}else if(strcostofmeal.equals("")){
											popupToast("Cost Of Meal  Missing.");
													
											
										}else{

										// Create and display the Alert dialog
										new AlertDialog.Builder(this)
												.setTitle("   Details entered")
												.setMessage(
														" Details entered:\n " +  strname1 
														+ "\n " + strdob
														+ "\n " + strtypeofmeal + "\n " + stroverallrating + "\n " + strcostofmeal	)
												.setNeutralButton("Back",
														new DialogInterface.OnClickListener() {
															public void onClick(DialogInterface dialog,	int which) {
																// do nothing - it will just close when clicked
															}
														})
												.setPositiveButton("Save",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(DialogInterface dialog,	int which) {
																// save the details entered if "Save" button clicked
																savedetails1( strname1, strdob,strtypeofmeal, stroverallrating, 
																		strcostofmeal
																		);
															}
														}).show();
									}
									}

									protected void savedetails1( String name1, String date,
											String typeofmeal, String overallrating, String costofmeal ) {
										try {
											
										// insertDetails() in the DataBaseHelper class does the real work	
										dbHelper.insertDetails1( name1, date, typeofmeal, overallrating, costofmeal);
										long recordCount = dbHelper.getNumberOfRecords1();
										// confirm details saved ...
										new AlertDialog.Builder(Review.this)
												.setTitle("   ** Details saved **\n" +
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
													"   Error saving to database");
											new AlertDialog.Builder(Review.this)
											.setTitle("Couldn't save details")
											.setNeutralButton("forward",
													new DialogInterface.OnClickListener() 
											{
												public void onClick(DialogInterface dialog,
														int which) {
													// do nothing
												}
											}).show();
							}
									}
			}

											
											
									

														


