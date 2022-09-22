package com.example.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

 

public class MainActivity extends Activity {
	
        // Progress Dialog
    private ProgressDialog pDialog;
 
// Create the object of JsonParser class
    JSONParser jParser = new JSONParser();

    EditText inputnumberid;
    EditText inputestablishment;
    EditText inputestablishmenttype;
    EditText inputservedfood;
    EditText inputlocationarea;
    EditText inputemail; 

    // url to create send data. This contains the ip address of my machine on which the local server is running. You will write the IP address of your machine

      private static String url_create_enterfarejudge = "http://localhost/jason/connect.php/enterfarejudge.php";

     // JSON Node names

    private static final String TAG_SUCCESS = "success";
		        
       @Override
          public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);   

		   // Edit Text
             inputnumberid = (EditText) findViewById(R.id.editText1);
             inputestablishment = (EditText) findViewById(R.id.editText2);
             inputestablishmenttype = (EditText) findViewById(R.id.editText3);
             inputservedfood = (EditText) findViewById(R.id.editText4);
             inputlocationarea = (EditText) findViewById(R.id.editText5);
             inputemail = (EditText) findViewById(R.id.editText6);  

	   // Create button
       Button btnCreateenterfarejudge = (Button) findViewById(R.id.button1);     

		      // button click event
		       btnCreateenterfarejudge.setOnClickListener(new View.OnClickListener() {

	/*on button click you will call the execute() method with the object of CreateNewId class and onPreExecute() will be called where we start the progress dialogue. After the execution of
 onPreExecute(), doInBackGround method will be called automatically which sends the data to the JsonParser class. In JsonParser class I have created the Http Client to send the   data to the server.     */

	 @Override
	 public void onClick(View view) {

		       // creating new product in background thread
		 new CreateNewenterfarejudge().execute();
		                   }
		               });
		           }     

		           /**
		            * Background Async Task to Create new product
		            * */
		           class CreateNewenterfarejudge extends AsyncTask<String, String, String> {
		               /**
		                * Before starting background thread Show Progress Dialog
		                * */
		               @SuppressWarnings("unused")
		                     @Override
		               protected void onPreExecute() {
		                   super.onPreExecute();
		                   pDialog = new ProgressDialog(MainActivity.this);
		                   pDialog.setMessage("Creating Data..");

		                   pDialog.setIndeterminate(false);

		                   pDialog.setCancelable(true);
		                   pDialog.show();
		               }      
		               /**
		                * Creating product
		                * */
		               protected String doInBackground(String... args) {
		                   String numberid = inputnumberid.getText().toString();
		                   String establishment = inputestablishment.getText().toString();
		                   String establishmenttype = inputestablishmenttype.getText().toString();
		                   String servedfood = inputservedfood.getText().toString();
		                   String locationarea = inputlocationarea.getText().toString();
		                   String email = inputemail.getText().toString(); 

		                   // Building Parameters

		                   List<NameValuePair> params = new ArrayList<NameValuePair>();
		                   params.add(new BasicNameValuePair("numberid", numberid));
		                   params.add(new BasicNameValuePair("establishment", establishment));
		                   params.add(new BasicNameValuePair("establishmenttype", establishmenttype));
		                   params.add(new BasicNameValuePair("servedfood", servedfood));
		                   params.add(new BasicNameValuePair("locationarea", locationarea));
		                   params.add(new BasicNameValuePair("email", email));
		        
		                   // getting JSON Object
		                   // Note that create product url accepts POST method
		                   JSONObject json = jParser.makeHttpRequest(url_create_enterfarejudge,
		                           "POST", params);                 

		                   // check log cat fro response

		                   Log.d("Create Response", json.toString());
		                   
		                   // check for success tag
		                   try {
		                       int success = json.getInt(TAG_SUCCESS);	        
		                       if (success == 1) {
		                           // successfully created product
		                          // Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
		                           //startActivity(i);
		                             finish();

		                           // closing this screen	                         
		                       } else {
		                           // failed to create product
		                       }
		                   } catch (JSONException e) {
		                       e.printStackTrace();
		                   }   
		                   return null;
		               }     
		               /**
		                * After completing background task Dismiss the progress dialog
		                * **/
		               protected void onPostExecute(String file_url) {
		                   // dismiss the dialog once done
		                   pDialog.dismiss();
		               }        
		           }
		       }
