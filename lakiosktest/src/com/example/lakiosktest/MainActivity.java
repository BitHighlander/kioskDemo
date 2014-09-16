package com.example.lakiosktest;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

private Button button1;
private EditText input1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.onResume();
        View mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(
      	        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
      	      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
      	      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
      	      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
      	      | View.SYSTEM_UI_FLAG_FULLSCREEN
      	      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        
        input1 = (EditText) findViewById(R.id.editText1);
        input1.setVisibility(View.INVISIBLE);
        
        Button button1 = (Button) findViewById(R.id.button1); 
        
      
        button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				input1.setVisibility(View.VISIBLE);
			
			}
		});
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
