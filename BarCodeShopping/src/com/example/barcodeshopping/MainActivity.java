package com.example.barcodeshopping;
import com.example.parser.HandleJSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
//import com.example.parser;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    
	private EditText location,country,temperature,humidity,pressure;
	private HandleJSON obj;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        country = (EditText)findViewById(R.id.editText1);
        scanBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v){
    	//respond to clicks
    	if(v.getId()==R.id.scan_button){
    		//scan
    			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    			scanIntegrator.initiateScan();
    		}
    	}
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	//retrieve scan result
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	if (scanningResult != null) {
    		//we have a result
    		String scanContent = scanningResult.getContents();
    		String scanFormat = scanningResult.getFormatName();
    		formatTxt.setText("FORMAT: " + scanFormat);
    		contentTxt.setText("CONTENT: " + scanContent);
    		String pid = "1";
    		String finalUrl = "http://www.numpun.lnw.mn/shona/API/productdetail.php?ID="+pid;
    	    country.setText(finalUrl);
    	    obj = new HandleJSON(finalUrl);
    	    obj.fetchJSON();

    	    while(obj.parsingComplete);
    	     country.setText(obj.getCountry());
    	}
    	else{
    	    Toast toast = Toast.makeText(getApplicationContext(), 
    	        "No scan data received!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
    }
}
