package com.codepath.assignment.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends Activity {
	
	private double amount;
	private EditText etAmount;
	private EditText etOtherPct;
	private TextView tvTipAmt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		etAmount = (EditText) findViewById(R.id.etAmountValue);
		etOtherPct = (EditText) findViewById(R.id.etOtherPct);
		tvTipAmt = (TextView) findViewById(R.id.tvTipValue);
		//InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    //mgr.showSoftInput(etAmount, InputMethodManager.SHOW_IMPLICIT);
	    //mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED,1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculate, menu);
		return true;
	}

	public void onTen(View view){
		this.calculate(0.1);
	}
	
	public void onFifteen(View view){
		this.calculate(0.15);
	}
	
	public void onTwenty(View view){
		this.calculate(0.2);
	}
	
	public void onOther(View view) {
		try {
			Double otherPct = Double.parseDouble(etOtherPct.getText().toString());
			this.calculate(otherPct.doubleValue()/100);
		} catch(NumberFormatException e) {
			Toast.makeText(this, "Please enter a valid tip percent", Toast.LENGTH_SHORT).show();
		}

	}
	
	public void showSoftKeyboard(View view){
		if(view.requestFocus()){
	        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	        imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
	    }
	}
	
	private void calculate(double percent){
		try {
			Double amountValue = Double.parseDouble(etAmount.getText().toString());
			DecimalFormat df = new DecimalFormat("$#.00");
			amount = amountValue.doubleValue();
			double tip = amount * percent;
			tvTipAmt.setText(df.format(tip));
		} catch (NumberFormatException e){
			Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
		}
	}

}
