package com.codepath.assignment.tipcalculator;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends Activity {
	
	private double amount;
	private TextView etAmount;
	private TextView etOtherPct;
	private TextView tvTipAmt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		etAmount = (TextView) findViewById(R.id.etAmountValue);
		etOtherPct = (TextView) findViewById(R.id.etOtherPct);
		tvTipAmt = (TextView) findViewById(R.id.tvTipValue);
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
	
	private void calculate(double percent){
		try {
			Double amountValue = Double.parseDouble(etAmount.getText().toString());
			amount = amountValue.doubleValue();
			double tip = amount * percent;
			tvTipAmt.setText(Double.valueOf(tip).toString());
		} catch (NumberFormatException e){
			Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
		}
	}

}
