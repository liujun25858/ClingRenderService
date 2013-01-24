package com.archermind.ashare.render.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AShareRenderService extends Service {
	private final static boolean DEBUG = true;
	@Override
	public IBinder onBind(Intent intent) {
		/*
		 * No one need to use our service handler.
		 * So just return null;
		 */
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LogUtil.logv(this, "Create Service", DEBUG);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
