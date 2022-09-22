package com.example.FareJudgeApp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

	public class DatabaseHelper extends SQLiteOpenHelper  {
		
		private static final String DATABASE_NAME = "fare";
		
		private static final String TABLE_NAME = "details";
		public static final String numberid_COL = "numberid";
		public static final String NAME_COL = "name";
		public static final String establishmenttype_COL = "establishmenttype";
		public static final String typeoffoodserved_COL = "typeoffoodserved";
		public static final String location_COL = "location";
		public static final String phonenumber_COL = "phonenumber";
		
		
		private static final String TABLE_NAME1 = "details1";
		public static final String NAME1_COL = "name1";
		public static final String date_COL = "date";
		public static final String typeofmeal_COL = "typeofmeal";
		public static final String overallrating_COL = "overallrating";
		public static final String costofmeal_COL = "costofmeal";
		
		private SQLiteDatabase database;
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1); // Note - 1 is a version number
													// for the database

			// get a reference to our database which we'll use later to insert
			database = getWritableDatabase();
		}
		
		

		// close the database connection
		public void close() 
		{
		   if (database != null)database.close(); // close the database connection
		}
		@Override
		// Automatically called if the database needs creating
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE details (_id INTEGER PRIMARY KEY AUTOINCREMENT,numberid TEXT, name TEXT, establishmenttype TEXT, " +
					"typeoffoodserved TEXT, location TEXT, phonenumber TEXT);");
			db.execSQL("CREATE TABLE details1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, name1 TEXT, date TEXT," +
					" typeofmeal Text, overallrating TEXT, costofmeal TEXT);");
		}

		@Override
		// Automatically called if database version number changes
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// bit mean to lose old data but at least we'll warn this is happening
			android.util.Log.w(this.getClass().getName(), DATABASE_NAME
					+ " database upgrade to version " + newVersion
					+ " old data lost");
			db.execSQL("DROP TABLE IF EXISTS details");
			db.execSQL("DROP TABLE IF EXISTS details1");
			onCreate(db);
		}

		
		// the database. We could use raw SQL (execSQL) but it is better to
		// use the purpose built insert method.
		
		public long insertDetails(String numberid, String name, String establishmenttype,
				String typeoffoodserved, String location,
		String phonenumber) {
			ContentValues rowValues = new ContentValues();

			// Assemble row of data in the ContentValues object
			rowValues.put(numberid_COL, numberid);
			rowValues.put(NAME_COL, name);
			rowValues.put(establishmenttype_COL, establishmenttype);
			rowValues.put(typeoffoodserved_COL, typeoffoodserved);
			rowValues.put(location_COL, location);
			rowValues.put(phonenumber_COL, phonenumber);
			
			return database.insertOrThrow(TABLE_NAME, null, rowValues);

		}
		public long insertDetails1(String name1, String date,String typeofmeal, 
				String overallrating,String costofmeal) {
			ContentValues rowValues = new ContentValues();

			// Assemble row of data in the ContentValues object
			rowValues.put(NAME1_COL, name1);
			rowValues.put(date_COL, date);
			rowValues.put(typeofmeal_COL, typeofmeal);
			rowValues.put(overallrating_COL, overallrating);
			rowValues.put(costofmeal_COL, costofmeal);
			return database.insertOrThrow(TABLE_NAME1, null, rowValues);

		}

		// get the number of rows by doing a query - maybe a bit inefficient
		public long getNumberOfRecords() {
			Cursor c = database.query(TABLE_NAME, null, null, null, null, null, null);
			
			return c.getCount();
		}
		public long getNumberOfRecords1() {
			Cursor a = database.query(TABLE_NAME1, null, null, null, null, null, null);
			return a.getCount();
		}

		// get all the rows of the details table ordered by the name column
		public Cursor getAllRecords() {
			return database.query(TABLE_NAME, null, null, null, null, null,NAME_COL);
		}
		public Cursor getAllRecords1() {
			return database.query(TABLE_NAME1, null, null, null, null, null,NAME1_COL);
		}

		// get all the rows of the details table ordered by the name column
		public Cursor getFilteredRecords(String filter) {
			// Alternative demonstrating some of the other parameters
			return database.query(TABLE_NAME, new String[] { "_id",numberid_COL, NAME_COL,establishmenttype_COL,
					typeoffoodserved_COL,location_COL,
					phonenumber_COL }, "name like ?", new String[] { filter + "%" }, null, null, 
					NAME_COL);
			
		}
		
		public Cursor getFilteredRecords1(String filter1) {
			// Alternative demonstrating some of the other parameters
			return database.query(TABLE_NAME1, new String[] { "_id", NAME1_COL,date_COL,
			typeofmeal_COL,overallrating_COL, costofmeal_COL}, "name1 like ?", new String[] { filter1 + "%" },
			null, null, NAME1_COL);
			
		}

		// delete all the rows of the details table
		public void deleteAllRecords() {
			database.delete(TABLE_NAME, null, null);
		}
		
		public void deleteAllRecords1() {
			database.delete(TABLE_NAME1, null, null);
		}
		   
		
	}

