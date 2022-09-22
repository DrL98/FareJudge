/*Program: DatabaseHelper.java
 * Author: Shela Katongo
 * Student No:
 * Purpose: To Create the web service" 
 * Modifications:
 * **********************************************************************************
 * Date Modified:                  Purpose/Comment                                  *
 * **********************************************************************************
 * 26/02/14                        created web service "main_activity"
 *
 * 08/04/14                        
 *                                                            
 */






package com.FareJudgeApp.android;

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

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shelakatonga.android.R;
 
public class MainActivity extends Activity implements OnClickListener {
 
    TextView tvIsConnected;
    EditText editText1, editText2,  editText3, editText4, editText5,  editText6;
    Button PostData;
 
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // get reference to the views
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        PostData = (Button) findViewById(R.id.button1);
 
        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }
 
        // add click listener to Button "POST"
        PostData.setOnClickListener(this);
 
    }
 
    public static String POST(String url, Client client){
        InputStream inputStream = null;
 String result = "";
        try {
 
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
 
            String json = "";
 
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("numberid", client.getnumberid());
            jsonObject.accumulate("name", client.getname());
            jsonObject.accumulate("establishmenttype", client.getestablishmenttype());
            jsonObject.accumulate("typeoffoodserved", client.gettypeoffoodserved());
            jsonObject.accumulate("location", client.getlocation());
            jsonObject.accumulate("phonenumber", client.getphonenumber());
 
 
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
 
            // ** Alternative way to convert Client object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(client); 
 
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
 
            // 6. set httpPost Entity
            httpPost.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
 
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
 
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        // 11. return result
        return result;
    }
 
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
    }
    @Override
    public void onClick(View view) {
 
        switch(view.getId()){
            case R.id.button1:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                // call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("");
            break;
        }
 
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
 
            client = new Client();
            client.setnumberid(editText1.getText().toString());
            client.setname(editText2.getText().toString());
            client.setestablishmenttype(editText3.getText().toString());
            client.settypeoffoodserved(editText4.getText().toString());
            client.setlocation(editText5.getText().toString());
            client.setphonenumber(editText6.getText().toString());
        return POST(urls[0],client);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
       }
    }
 
    private boolean validate(){
        if(editText1.getText().toString().trim().equals(""))
            return false;
        else if(editText2.getText().toString().trim().equals(""))
            return false;
        else if(editText3.getText().toString().trim().equals(""))
            return false;
        if(editText4.getText().toString().trim().equals(""))
            return false;
        else if(editText5.getText().toString().trim().equals(""))
            return false;
        else if(editText6.getText().toString().trim().equals(""))
            return false;
        else
            return true;    
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }   
}
