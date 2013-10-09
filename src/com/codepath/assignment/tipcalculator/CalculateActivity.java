package com.codepath.assignment.tipcalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends Activity {
	
	private double amount;
	private EditText etAmount;
	private EditText etOtherPct;
	private TextView tvTipAmt;
	private double tipPercent;
	private Button btTen;
	private Button btFifteen;
	private Button btTwenty;
	private Button btOther;
	private List<Button> pctList;
	//private SeekBar skBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		btTen = (Button) findViewById(R.id.btTen);
		btFifteen = (Button) findViewById(R.id.btFifteen);
		btTwenty = (Button) findViewById(R.id.btTwenty);
		btOther = (Button) findViewById(R.id.btOther);
		etAmount = (EditText) findViewById(R.id.etAmountValue);
		etOtherPct = (EditText) findViewById(R.id.etOtherPct);
		tvTipAmt = (TextView) findViewById(R.id.tvTipValue);
		etAmount.setRawInputType(InputType.TYPE_CLASS_NUMBER);
		pctList = new ArrayList<Button>();
		pctList.add(btTen);
		pctList.add(btFifteen);
		pctList.add(btTwenty);
		pctList.add(btOther);
		/*skBar = (SeekBar) findViewById(R.id.seekBar1);
		skBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			   
			   @Override
			   public void onStopTrackingTouch(SeekBar seekBar) {
			   }
			   
			   @Override
			   public void onStartTrackingTouch(SeekBar seekBar) {
			    // TODO Auto-generated method stub
			   }
			   
			   @Override
			   public void onProgressChanged(SeekBar seekBar, int progress,
			     boolean fromUser) {
			   try {
					Double amountValue = Double.parseDouble(etAmount.getText().toString());
					DecimalFormat df = new DecimalFormat("$#.00");
					amount = amountValue.doubleValue();
					double tip = amount * progress/100;
					etOtherPct.setText(progress+"%");
					tvTipAmt.setText(df.format(tip));
				} catch (NumberFormatException e){
					Toast.makeText(getBaseContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
				}
			   }
			  });*/
		tipPercent=0;
		etAmount.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String totalAmount=etAmount.getText().toString();
				if ( totalAmount != null && !totalAmount.isEmpty() && tipPercent!=0){
					try {
						amount=Double.parseDouble(totalAmount);
						DecimalFormat df = new DecimalFormat("$#.00");
						tvTipAmt.setText(df.format(amount * tipPercent));
					} catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
					}
				} else {
					tvTipAmt.setText("");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		etOtherPct.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String totalAmount=etAmount.getText().toString();
				if ( totalAmount != null && !totalAmount.isEmpty()){
					try {
						Double otherPct = Double.parseDouble(etOtherPct.getText().toString());
						tipPercent = otherPct.doubleValue()/100;
						amount=Double.parseDouble(totalAmount);
						DecimalFormat df = new DecimalFormat("$#.00");
						tvTipAmt.setText(df.format(amount * tipPercent));
						for (Button currBt : pctList){
							currBt.setTypeface(null,  Typeface.NORMAL);
						}
						btOther.setTypeface(null, Typeface.BOLD);
					} catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
					}
				} else {
					tvTipAmt.setText("");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	private OnSeekBarChangeListener OnSeekBarChangeListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculate, menu);
		return true;
	}

	public void onTen(View view){
		this.changeButtonDisplay((Button)findViewById(view.getId()));
		tipPercent=0.10;
		this.calculate();
	}
	
	public void onFifteen(View view){
		this.changeButtonDisplay((Button)findViewById(view.getId()));
		tipPercent=0.15;
		this.calculate();
	}
	
	public void onTwenty(View view){
		this.changeButtonDisplay((Button)findViewById(view.getId()));
		tipPercent=0.2;
		this.calculate();
	}
	
	public void onOther(View view) {
		try {
			this.changeButtonDisplay((Button)findViewById(view.getId()));
			Double otherPct = Double.parseDouble(etOtherPct.getText().toString());
			tipPercent = otherPct.doubleValue()/100;
			this.calculate();
		} catch(NumberFormatException e) {
			tipPercent=0;
			Toast.makeText(this, "Please enter a valid tip percent", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void changeButtonDisplay(Button bt){
		for (Button currBt : pctList){
			if (currBt == bt) {
				currBt.setTypeface(null,  Typeface.BOLD);
			} else {
				currBt.setTypeface(null, Typeface.NORMAL);
			}
		}
	}
	
	public void showSoftKeyboard(View view){
		if(view.requestFocus()){
	        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	        imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
	    }
	}
	
	private void calculate(){
		try {
			Double amountValue = Double.parseDouble(etAmount.getText().toString());
			DecimalFormat df = new DecimalFormat("$#.00");
			amount = amountValue.doubleValue();
			double tip = amount * tipPercent;
			tvTipAmt.setText(df.format(tip));
		} catch (NumberFormatException e){
			Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
		}
	}

}
