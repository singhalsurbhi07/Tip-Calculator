package com.example.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	SeekBar seekBar;
	TextView percentage;
	int no_splits=1;
	ImageButton imageButton1;
	ImageButton imageButton2;
	ImageButton imageButton3;
	ImageButton imageButton4;
	TextView splitMes;
	
	EditText amountText;

	Button add;
	Button sub;
	final int TOTAL_FIG=4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imageButton2 =(ImageButton)findViewById(R.id.ImageButton4);
		imageButton3 =(ImageButton)findViewById(R.id.imageButton2);
		imageButton4 =(ImageButton)findViewById(R.id.ImageButton3);
		amountText=(EditText) findViewById(R.id.editText1);
		
		add = (Button) findViewById(R.id.AddButton);
		sub = (Button) findViewById(R.id.SubButton);
		
		splitMes = (TextView) findViewById(R.id.textView7);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		percentage = (TextView) findViewById(R.id.textView2);
		
		// Initialize the textview with '0'
		percentage.setText(seekBar.getProgress() + "%" );
		seekBar.setOnSeekBarChangeListener(
				new OnSeekBarChangeListener() {
					int progress = 0;
					@Override
					public void onProgressChanged(SeekBar seekBar, 
							int progresValue, boolean fromUser) {
						progress = progresValue;
						calculate();
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// Do something here, 
						//if you want to do anything at the start of
						// touching the seekbar
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// Display the value in textview
						percentage.setText(progress + "%");
					}
				});
		
		amountText.addTextChangedListener(new TextWatcher() {
			

			   public void afterTextChanged(Editable s) {}

			   public void beforeTextChanged(CharSequence s, int start,
			     int count, int after) {
			   }

			   public void onTextChanged(CharSequence s, int start,
			     int before, int count) {
			      calculate();
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

	


	public void increase(View view) {
		no_splits += 1;
		switch(no_splits % TOTAL_FIG) {
			case 0:
				imageButton4.setImageResource(R.drawable.icon_small_person_coloured);
				break;
		
			case 1:
				imageButton2.setImageResource(R.drawable.icon_small_person);
				imageButton3.setImageResource(R.drawable.icon_small_person);
				imageButton4.setImageResource(R.drawable.icon_small_person);
				break;
		
			case 2:
				imageButton2.setImageResource(R.drawable.icon_small_person_coloured);
				break;
			case 3:
				imageButton3.setImageResource(R.drawable.icon_small_person_coloured);
				break;
			}
		showSplitText();
		calculate();
	}
	
	public void decrease (View view){
		if(no_splits>1){
			no_splits-=1;
			switch(no_splits % TOTAL_FIG) {
			case 0:
				imageButton4.setImageResource(R.drawable.icon_small_person_coloured);
				imageButton3.setImageResource(R.drawable.icon_small_person_coloured);
				imageButton2.setImageResource(R.drawable.icon_small_person_coloured);
				break;
		
			case 1:
				imageButton2.setImageResource(R.drawable.icon_small_person);
				break;
		
			case 2:
				imageButton3.setImageResource(R.drawable.icon_small_person);
				break;
			case 3:
				imageButton4.setImageResource(R.drawable.icon_small_person);
				break;
			}
			
		}
		showSplitText();
		calculate();
	}
	
	public void showSplitText(){
		if(no_splits==1){
			splitMes.setText("No split");
		}else{
			splitMes.setText("Split it into "+no_splits+" parts");
		}
		
	}
	
	public void calculate(){
		float amount;
		
		if(!(amountText.getText().toString().length()>0)){
			amount=0;
		}else{
		
			amount=Float.parseFloat(amountText.getText().toString());
		}
		int percentageAmount=seekBar.getProgress();
		float tip=(amount * percentageAmount) / 100;
		
		TextView tipAmount=(TextView) findViewById(R.id.textView5);
		tipAmount.setText(Float.toString(tip));
		
		TextView totalAmount=(TextView) findViewById(R.id.textView6);
		totalAmount.setText(Float.toString((amount + tip)/no_splits));
		
	}
}
