package com.example.clontarfdeals;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textview = (TextView)findViewById(R.id.textview1);
		
		try {
	        String APP_ID = "500637120015885";
	        String APP_SECRET = "1842cdc29962358a37b8f281b93b2f4c";
	        String OWNER_OF_FEED = "ClontarfDeals";

	        HttpClient client = new DefaultHttpClient();
	        HttpGet get = new HttpGet(
	                "https://graph.facebook.com/oauth/access_token?client_id="+ 
	                APP_ID + 
	                "&client_secret="+APP_SECRET+"&grant_type=client_credentials");

	        ResponseHandler<String> responseHandler = new BasicResponseHandler();

	        String access_token = client.execute(get, responseHandler);
	        // access_token contains sthing like "access_token=XXXXXXXXXX|YYYYYY" , 
	        //need to replace pipe (this is ugly!)
	        String uri = "https://graph.facebook.com/" + OWNER_OF_FEED + "/feed?"
	                + access_token.replace("|", "%7C");

	        get = new HttpGet(uri);
	        String responseBody = client.execute(get, responseHandler);

	        // responseBody contains JSON-encoded feed

	        textview.setText(responseBody);

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
	}

	

}
