package com.example.lakiosktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BuyerInformationActivity extends Activity {

//***************
//	Things
//***************	
private Button button1;
private EditText input1;
private Button abortButton;
private Button buyButton;
private Button sellButton;
private Button nextButton;
private TextView priceBitcoinView;
static final int BUYER_SELECTION_ACTIVITY = 0;
private static final int SELL_ACTIVITY = 0;
public static final int BUYER_INFORMATION_ACTIVITY = Menu.FIRST + 1;
public static final int BUY_ACTIVITY = Menu.FIRST + 2;
public static final int REDEEM_ACTIVITY = Menu.FIRST + 3;
//***************

//***************
//  Tools
//***************

/*
 * Inventory
 * 
 * Navigation Calls
 */

private void startLoginActivity() {
	Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
	startActivity(intent);
}

private void startBuyerInfoActivity() {
	Intent intent = new Intent(getApplicationContext(), BuyerInformationActivity.class);
	startActivityForResult(intent, BUYER_INFORMATION_ACTIVITY);
}


private void startBuyerSelectionActivity() {
	Intent intent = new Intent(getApplicationContext(), BuyerInformationActivity.class);
	startActivityForResult(intent, BUYER_SELECTION_ACTIVITY);
}

private void startFundingActivity() {
	Intent intent = new Intent(getApplicationContext(), BuyScreenActivity.class);
	startActivityForResult(intent, BUY_ACTIVITY);
}

private void startSellingActivity() {
	Intent intent = new Intent(getApplicationContext(), SellScreenActivity.class);
	startActivityForResult(intent, SELL_ACTIVITY);
}

private void startRedeemActivity() {
	Intent intent = new Intent(getApplicationContext(), Redemption_Activity.class);
	startActivityForResult(intent, REDEEM_ACTIVITY);
}

private void startSessionsActivity() {
	Intent intent = new Intent(getApplicationContext(), SessionsViewActivity.class);
	startActivity(intent);
}
//***************





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_information);

        //Kiosk Mode
        View mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(
      	        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
      	      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
      	      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
      	      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
      	      | View.SYSTEM_UI_FLAG_FULLSCREEN
      	      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        
        //******* Things        
  		
  		abortButton = (Button) findViewById(R.id.abort);
  		nextButton = (Button) findViewById(R.id.next_button);

  		
  		
  //******* when first set		
          //input1 = (EditText) findViewById(R.id.editText1);
          //input1.setVisibility(View.INVISIBLE);
  		
  		
  //****** Buttons		
  		abortButton.setOnClickListener(new OnClickListener() {
  				@Override
  				public void onClick(View v) {
  					//Start Scanning Page
  					Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_LONG).show();
  					//startBuyerSelectionActivity();
  					finish();
  					
  				}
  		});
  		nextButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//Start Scanning Page
					Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_LONG).show();
					//startBuyerSelectionActivity();
					startRedeemActivity();
					
				}
		});

        

    }


    public void onResume(){
    	 super.onResume();
         View mDecorView = getWindow().getDecorView();
         mDecorView.setSystemUiVisibility(
       	        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
       	      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
       	      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
       	      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
       	      | View.SYSTEM_UI_FLAG_FULLSCREEN
       	      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
         

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
}
