package com.stoneageartisans.weightconverter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class WeightConverter extends Activity {

	// Constants
	private static final int milligrams = 0;
	private static final int centigrams = 1;
	private static final int decigrams = 2;
	private static final int grams = 3;
	private static final int dekagrams = 4;
	private static final int ounces = 5;
	private static final int hectograms = 6;
	private static final int pounds = 7;
	private static final int kilograms = 8;
	private static final int tons_us = 9;
	private static final int tons_metric = 10;
	
	// Variables
	private EditText edittext_input;
	private TextView textview_output;
	private int font_size;
	private int units_from;
	private int units_to;
	private Spinner spinner_units_from;
	private Spinner spinner_units_to;
	private String[] units;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.layout_main);
		initialize();

	}
	
	@Override
	public void onResume() {
		
	    super.onResume();
	    
	}
	
	@Override
	public void onPause() {
		
	    super.onPause();
	    
	}

	private void initialize() {
		
		units = this.getResources().getStringArray(R.array.units);
    	units_from = 0;
    	units_to = 0;

        // Calculate font size based on screen dimensions
        font_size = 20;

        // Set title font size
        ( (TextView) this.findViewById(R.id.title) ).setTextSize(font_size);

        edittext_input = (EditText) this.findViewById(R.id.input);
        edittext_input.setTextSize(font_size);

        ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units) {
    		public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ( (TextView) view ).setTextSize(font_size);
                return view;
    		}
    		public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
               ( (TextView) view ).setTextSize(font_size);
               return view;
    		}
    	};
    	array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    	spinner_units_from = (Spinner) this.findViewById(R.id.units_from);
    	spinner_units_from.setAdapter(array_adapter);

    	textview_output = (TextView) this.findViewById(R.id.output);
    	textview_output.setTextSize(font_size);

    	spinner_units_to = (Spinner) this.findViewById(R.id.units_to);
    	spinner_units_to.setAdapter(array_adapter);

    	edittext_input.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {
				convert_units();
				return false;
			}
        });

    	spinner_units_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_from = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

    	spinner_units_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_to = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
    	
    	Button convert = ( (Button) this.findViewById(R.id.convert) );
    	convert.setTextSize(font_size);
    	convert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				convert_units();				
			}    		
    	});

    }

    private void convert_units() {

    	String output = "";
    	try {
    		double input = Double.parseDouble( edittext_input.getText().toString() );
	    	switch(units_from) {
	    		case milligrams:  // from milligrams
	    			switch(units_to) {
	    				case milligrams:  // to milligrams
	    					output = String.valueOf(input);
	    					break;
	    				case centigrams:  // to centigrams
	    					output = String.valueOf(input / 10);
	    					break;
	    				case decigrams:  // to decigrams
	    					output = String.valueOf(input / 100);
	    					break;
	    				case grams:  // to grams
	    					output = String.valueOf(input / 1000);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input / 10000);
	    					break;
	    				case ounces:  // to ounces
	    					output = String.valueOf(input / 28349.523125);
	    					break;
	    				case hectograms:  // to hectograms
	    					output = String.valueOf(input / 100000);
	    					break;
	    				case pounds:  // to pounds
	    					output = String.valueOf(input / 453592.37);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 1000000);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 907184740);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 1000000000);
	    					break;
	    			}
	    			break;
	    		case centigrams:  // from centigrams
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 10);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input / 10);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input / 100);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input / 1000);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input / 2834.9523125);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input / 10000);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 45359.237);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 100000);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 90718474);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 100000000);
	    					break;
	    			}
	    			break;
	    		case decigrams:  // from fluid ounces
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 100);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input * 10);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input / 10);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input / 100);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input / 283.49523125);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input / 1000);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 4535.9237);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 10000);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 9071847.4);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 10000000);
	    					break;
	    			}
	    			break;
	    		case grams:  // from grams
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 1000);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input * 100);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input * 10);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input / 10);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input / 28.349523125);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input / 100);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 453.59237);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 1000);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 907184.74);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 1000000);
	    					break;
	    			}
	    			break;
	    		case dekagrams:  // from dekagrams
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 10000);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input * 1000);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input * 100);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input * 10);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input / 2.834952313);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input / 10);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 45.359237);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 100);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 90718.474);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 100000);
	    					break;
	    			}
	    			break;
	    		case ounces:  // from ounces
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 28349.523125);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input * 2834.9523125);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input * 283.49523125);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input * 28.349523125);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 2.834952313);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input / 3.527396195);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 16);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 35.27396195);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 32000);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 35273.96194958);
	    					break;
	    			}
	    			break;
	    		case hectograms:  // from hectograms
	    			switch(units_to) {
						case milligrams:  // to milligrams
							output = String.valueOf(input * 10000);
							break;
						case centigrams:  // to centigrams
							output = String.valueOf(input * 10000);
							break;
						case decigrams:  // to decigrams
							output = String.valueOf(input * 1000);
							break;
						case grams:  // to grams
	    					output = String.valueOf(input * 100);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 10);
	    					break;
						case ounces:  // ounces
							output = String.valueOf(input * 3.527396195);
							break;
						case hectograms:  // to hectograms
	    					output = String.valueOf(input);
	    					break;
						case pounds:  // to pounds
	    					output = String.valueOf(input / 4.5359237);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 10);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 9071.8474);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 10000);
	    					break;
	    			}
	    			break;
	    		case pounds:  // from pounds
	    			switch(units_to) {
	    				case milligrams:  // to milligrams
	    					output = String.valueOf(input * 453592.37);
	    					break;
	    				case centigrams:  // to centigrams
	    					output = String.valueOf(input * 45359.237);
	    					break;
	    				case decigrams:  // to decigrams
	    					output = String.valueOf(input * 4535.9237);
	    					break;
	    				case grams:  // to grams
	    					output = String.valueOf(input * 453.59237);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 45.359237);
	    					break;
	    				case ounces:  // to ounces
	    					output = String.valueOf(input * 16);
	    					break;
	    				case hectograms:  // to hectograms
	    					output = String.valueOf(input * 4.5359237);
	    					break;
	    				case pounds:  // to pounds
	    					output = String.valueOf(input);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input / 2.204622622);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 2000);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 2204.622621849);
	    					break;
	    			}
	    			break;
	    		case kilograms:  // from kilograms
	    			switch(units_to) {
		    			case milligrams:  // to milligrams
	    					output = String.valueOf(input * 1000000);
	    					break;
	    				case centigrams:  // to centigrams
	    					output = String.valueOf(input * 100000);
	    					break;
	    				case decigrams:  // to decigrams
	    					output = String.valueOf(input * 10000);
	    					break;
	    				case grams:  // to grams
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 100);
	    					break;
	    				case ounces:  // to ounces
	    					output = String.valueOf(input * 35.27396195);
	    					break;
	    				case hectograms:  // to hectograms
	    					output = String.valueOf(input * 10);
	    					break;
	    				case pounds:  // to pounds
	    					output = String.valueOf(input * 2.204622622);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input / 907.18474);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 1000);
	    					break;
	    			}
	    			break;
	    		case tons_us:  // from tons_us
	    			switch(units_to) {
		    			case milligrams:  // to milligrams
	    					output = String.valueOf(input * 907184740);
	    					break;
	    				case centigrams:  // to centigrams
	    					output = String.valueOf(input * 90718474);
	    					break;
	    				case decigrams:  // to decigrams
	    					output = String.valueOf(input * 9071847.4);
	    					break;
	    				case grams:  // to grams
	    					output = String.valueOf(input * 907184.74);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 90718.474);
	    					break;
	    				case ounces:  // to ounces
	    					output = String.valueOf(input * 32000);
	    					break;
	    				case hectograms:  // to hectograms
	    					output = String.valueOf(input * 9071.8474);
	    					break;
	    				case pounds:  // to pounds
	    					output = String.valueOf(input * 2000);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input * 907.18474);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input / 1.102311311);
	    					break;
	    			}
	    			break;
	    		case tons_metric:  // from tons_metric
	    			switch(units_to) {
		    			case milligrams:  // to milligrams
	    					output = String.valueOf(input * 1000000000);
	    					break;
	    				case centigrams:  // to centigrams
	    					output = String.valueOf(input * 100000000);
	    					break;
	    				case decigrams:  // to decigrams
	    					output = String.valueOf(input * 10000000);
	    					break;
	    				case grams:  // to grams
	    					output = String.valueOf(input * 1000000);
	    					break;
	    				case dekagrams:  // to dekagrams
	    					output = String.valueOf(input * 100000);
	    					break;
	    				case ounces:  // to ounces
	    					output = String.valueOf(input * 35273.96194958);
	    					break;
	    				case hectograms:  // to hectograms
	    					output = String.valueOf(input * 10000);
	    					break;
	    				case pounds:  // to pounds
	    					output = String.valueOf(input * 2204.622621849);
	    					break;
	    				case kilograms:  // to kilograms
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case tons_us:  // to tons (US)
	    					output = String.valueOf(input * 1.102311311);
	    					break;
	    				case tons_metric:  // to tons (Metric)
	    					output = String.valueOf(input);
	    					break;
	    			}
	    			break;
	    	}
    	} catch(NumberFormatException ex) {
    		output = "";
    	}
    	textview_output.setText(output);

    }

}
