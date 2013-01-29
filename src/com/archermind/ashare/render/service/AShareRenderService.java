package com.archermind.ashare.render.service;

import org.teleal.cling.android.AndroidUpnpServiceImpl;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.registry.Registry;
import org.teleal.cling.registry.RegistryListener;

import com.archermind.ashare.player.service.IPlayService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class AShareRenderService extends AndroidUpnpServiceImpl implements PlayServiceGetter{
	private final static boolean DEBUG = true;

	/*
	 * Set retry count and timeout about connecting to palyservice.
	 */
	private final int RETRY = 4;
	private final int RETRY_TIME_OUT = 500;
	private IPlayService mPlayService;
	private ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			mPlayService = null;
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			mPlayService = IPlayService.Stub.asInterface(service);
		}
	};
	private RegistryListener mRegistryListener = new RegistryListener() {

		public void remoteDeviceUpdated(Registry arg0, RemoteDevice arg1) {
			LogUtil.logv(this, "remoteDeviceUpdated: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);			
		}

		public void remoteDeviceRemoved(Registry arg0, RemoteDevice arg1) {
			LogUtil.logv(this, "remoteDeviceRemoved: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);			
		}

		public void remoteDeviceDiscoveryStarted(Registry arg0, RemoteDevice arg1) {
			LogUtil.logv(this, "remoteDeviceDiscoveryStarted: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);
		}

		public void remoteDeviceDiscoveryFailed(Registry arg0, RemoteDevice arg1,
				Exception arg2) {
			LogUtil.logv(this, "remoteDeviceDiscoveryFailed: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);			
		}

		public void remoteDeviceAdded(Registry arg0, RemoteDevice arg1) {
			LogUtil.logv(this, "remoteDeviceAdded: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);			
		}

		public void localDeviceRemoved(Registry arg0, LocalDevice arg1) {
			LogUtil.logv(this, "localDeviceRemoved: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);						
		}

		public void localDeviceAdded(Registry arg0, LocalDevice arg1) {
			LogUtil.logv(this, "localDeviceAdded: dev->"+arg1.getDetails().getFriendlyName(), DEBUG);			
		}

		public void beforeShutdown(Registry arg0) {
			// TODO Auto-generated method stub
		}

		public void afterShutdown() {
			// TODO Auto-generated method stub
		}
	};

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
		super.onCreate();
		LogUtil.logv(this, "onCreate", DEBUG);
		AShareMediaRender render = new AShareMediaRender(
				AShareRenderService.this, AShareRenderService.this);
		upnpService.getRegistry().addListener(mRegistryListener);
		upnpService.getRegistry().addDevice(render.getDevice());
	}

	public IPlayService getPlayService() {
		if (mPlayService == null) {
			LogUtil.logd(this,"PlayService is null, try "+RETRY+" times to get PlayService",DEBUG);
			bindService(new Intent("com.archermind.ashare.bindservice"),
					mServiceConnection, Context.BIND_AUTO_CREATE);
			int count = RETRY;
			while (count != 0) {
				if (mPlayService != null) {
					break;
				}
				try {
					Thread.sleep(RETRY_TIME_OUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count--;
			}
		}
		LogUtil.logd(this, "Get PlayService "+mPlayService, DEBUG);
		return mPlayService;
	}

}
