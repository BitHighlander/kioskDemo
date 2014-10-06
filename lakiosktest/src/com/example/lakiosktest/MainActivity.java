package com.example.lakiosktest;

//***************
//Resources
//***************

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

//***************
//	Things
//***************	
private Button button1;
private EditText input1;
private Button startBitcoinButton;
private TextView priceBitcoinView;
static final int BUYER_SELECTION_ACTIVITY = 0;
private static final int SELL_ACTIVITY = 0;
public static final int BUYER_INFORMATION_ACTIVITY = Menu.FIRST + 1;
public static final int BUY_ACTIVITY = Menu.FIRST + 2;
public static final int REDEEM_ACTIVITY = Menu.FIRST + 3;
private static final String TAG = "Main Activity";
//admin click
private int count = 0;
private long startMillis=0;
//***************

//***************
//  Tools
//***************

/*
 * Inventory
 * 
 * Admin Launch
 * 
 * Dialog
 * 
 * Navigation Calls
 */

//10 taps admin launch
//detect any touch event in the screen (instead of an specific view) 
@Override
public boolean onTouchEvent(MotionEvent event) {  

    int eventaction = event.getAction();
     if (eventaction == MotionEvent.ACTION_UP) {

     //get system current milliseconds
     long time= System.currentTimeMillis();


     //if it is the first time, or if it has been more than 3 seconds since the first tap ( so it is like a new try), we reset everything 
     if (startMillis==0 || (time-startMillis> 4000) ) {
         startMillis=time;
         count=1;
     }
     //it is not the first, and it has been  less than 3 seconds since the first
     else{ //  time-startMillis< 3000   
         count++;
     }

     if (count==10) {
    	 	// Start login
    	 startLoginActivity();
    	 
    	 Log.d(TAG, "Admin button pressed");
     }
     return true;    
    }
    return false;
  }

//Dialogs
private void openAlert() {
	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
     
	 alertDialogBuilder.setTitle(this.getTitle()+ " Hardware Failed To Start");
	 alertDialogBuilder.setMessage("Connected Hardware failed to innitialize, would you like to try again? Or you may exit and restart the application. If this fails please power cycle the bill accetpor");
	 // set positive button: Yes message
	 alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// go to a new activity of the app
//				Intent positveActivity = new Intent(getApplicationContext(), .class);
//	            startActivity(positveActivity);	
			}
		  });
	 // set negative button: No message
	 alertDialogBuilder.setNegativeButton("Return to app",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// cancel the alert box and put a Toast to the user
				dialog.cancel();
				Toast.makeText(getApplicationContext(), "You chose a negative answer", 
						Toast.LENGTH_LONG).show();
			}
		});
	 // set neutral button: Exit the app message
	 alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// exit the app and go to the HOME
				MainActivity.this.finish();
			}
		});
	 
	 AlertDialog alertDialog = alertDialogBuilder.create();
	 // show alert
	 alertDialog.show();
}

//Activitys
private void startLoginActivity() {
	Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
	startActivity(intent);
}

private void startBuyerInfoActivity() {
	Intent intent = new Intent(getApplicationContext(), BuyerInformationActivity.class);
	startActivityForResult(intent, BUYER_INFORMATION_ACTIVITY);
}


private void startBuyerSelectionActivity() {
	Intent intent = new Intent(getApplicationContext(), BuyerSelectionActivity.class);
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
       
//******** get screen deminsion and display accordingly

        
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        String toastMsg;
        switch(screenSize) {
        	case Configuration.SCREENLAYOUT_SIZE_XLARGE:
        		setContentView(R.layout.activity_main_xlarge);
        		Log.d(TAG, "Xtra Large screen");
        		toastMsg = "Xtra Large screen";
        		break;
        	case Configuration.SCREENLAYOUT_SIZE_LARGE:
        		setContentView(R.layout.activity_main_large);
        		Log.d(TAG, "Large screen");
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
            	setContentView(R.layout.activity_main_normal);
            	Log.d(TAG, "Normal screen");
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
            	setContentView(R.layout.activity_main_small);
            	Log.d(TAG, "Small screen");
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

//        
        
        
        
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
		priceBitcoinView = (TextView) findViewById(R.id.bitcoin_market_price);
		startBitcoinButton = (Button) findViewById(R.id.start_bitcoin_button);

		
		
//******* when first set		
        //input1 = (EditText) findViewById(R.id.editText1);
        //input1.setVisibility(View.INVISIBLE);
		priceBitcoinView.setText("Please Wait");
		
//****** Buttons		
		priceBitcoinView = (TextView) findViewById(R.id.bitcoin_market_price);    
		startBitcoinButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//Start Scanning Page
					Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_LONG).show();
					//startBuyerSelectionActivity();
					startBuyerSelectionActivity();
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
         
// 		priceBitcoinView = (TextView) findViewById(R.id.bitcoin_market_price);
// 		startBitcoinButton = (Button) findViewById(R.id.start_bitcoin_button);
//         
//         //input1 = (EditText) findViewById(R.id.editText1);
//         //input1.setVisibility(View.INVISIBLE);
// 		priceBitcoinView.setText("Please Wait");
// 		      
// 		priceBitcoinView = (TextView) findViewById(R.id.bitcoin_market_price);    
// 		      
// 	    startBitcoinButton.setOnClickListener(new OnClickListener() {
// 				@Override
// 				public void onClick(View v) {
// 					//Start Scanning Page
// 				
// 				}
// 		});
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
