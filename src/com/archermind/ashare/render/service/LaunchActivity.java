package com.archermind.ashare.render.service;

import com.archermind.ashare.render.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity);
		Button btn = (Button) findViewById(R.id.btn_start);
		btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		startService(new Intent(getApplicationContext(), AShareRenderService.class));
		finish();
	}

}
