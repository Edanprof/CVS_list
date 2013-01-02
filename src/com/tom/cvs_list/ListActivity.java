package com.tom.cvs_list;

import java.io.InputStream;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tom.parser.Parser;
import com.tom.parser.ProductVO;

public class ListActivity extends Activity {

	LinearLayout index, bar;
	TextView tv_name, tv_price, category, categoryBtn;
	ScrollView scv;
	
	public final static int DIALOG_ID_NOTICE = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		
		InputStream ins = null;
		ProductVO[] list = null;
		category = (TextView) findViewById(R.id.category);
		categoryBtn = (TextView) findViewById(R.id.categoryBtn);
		scv = (ScrollView) findViewById(R.id.list);
		
		
		try {
			ins = getResources().openRawResource(R.raw.dataxml);
			Parser p = new Parser(ins);
			list = p.getProductList();
			ins.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		index = new LinearLayout(this);
		index.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		index.setOrientation(LinearLayout.VERTICAL);
		
		for (ProductVO pvo : list) {
			bar = new LinearLayout(getApplicationContext());
			bar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 60));
			bar.setOrientation(LinearLayout.HORIZONTAL);
			ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) bar.getLayoutParams();
			margin.bottomMargin = 1;
			bar.setBackgroundColor(Color.parseColor("#ffffff"));
			
			tv_name = new TextView(getApplicationContext());
			tv_name.setLayoutParams(new LinearLayout.LayoutParams(230, LayoutParams.MATCH_PARENT));
			tv_name.setGravity(Gravity.CENTER_VERTICAL);
			tv_name.setPadding(15, 0, 0, 0);
			tv_name.setTextColor(Color.parseColor("#3399ff"));
			tv_name.setTextSize(20);
			tv_name.setText(pvo.getName());
			bar.addView(tv_name);
			
			tv_price = new TextView(this);
			tv_price.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT));
			tv_price.setGravity(Gravity.CENTER);
			tv_price.setTextColor(Color.parseColor("#3399ff"));
			tv_price.setText(String.valueOf(pvo.getPrice()) + "원");
			bar.addView(tv_price);
			index.addView(bar);
		}
		scv.addView(index);
		
		
		categoryBtn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				showDialog(DIALOG_ID_NOTICE);
				return false;
			}
		});
	}

	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dlg = null;
		
		switch (id) {
		case DIALOG_ID_NOTICE:
			dlg = new Dialog(this);
			dlg.setTitle("Category");
			break;
		}
		return dlg;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;
	}
}
