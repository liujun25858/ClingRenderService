package com.archermind.ashare.render.service;

import java.io.IOException;
import java.net.URI;

import org.teleal.cling.binding.LocalServiceBinder;
import org.teleal.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.teleal.cling.model.DefaultServiceManager;
import org.teleal.cling.model.ServiceManager;
import org.teleal.cling.model.ValidationException;
import org.teleal.cling.model.meta.DeviceDetails;
import org.teleal.cling.model.meta.DeviceIdentity;
import org.teleal.cling.model.meta.Icon;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.LocalService;
import org.teleal.cling.model.meta.ManufacturerDetails;
import org.teleal.cling.model.meta.ModelDetails;
import org.teleal.cling.model.types.UDADeviceType;
import org.teleal.cling.model.types.UDN;

import com.archermind.ashare.render.R;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

public class AShareMediaRender {
    private final static String sManufactor = "Archermind Inc.";
    private final static String sManufactorURL = "http://www.archermind.com";
    private final static String sModelName = "AShare Media Renderer";
    private final static String sModelDescriptor = "AShare Media Renderer";
    private final static String sModelNO = "1";
    private final static String sModelURL = "http://www.archermind.com";

    private final static long LAST_CHANGE_FIRING_INTERVAL_MILLISECONDS = 500;

	private Context mContext;
	private PlayServiceGetter mGetter;

	private LocalDevice mDevice;
	private final LocalServiceBinder mBinder = new AnnotationLocalServiceBinder();
	private final ServiceManager<AShareAVTransportService> mAVTransportManager;
	private final ServiceManager<AShareConnectionManagerService> mConnectionManager;
	private final ServiceManager<AShareAudioRenderingControl> mRenderingControl;

	@SuppressWarnings("unchecked")
	public AShareMediaRender(Context context,PlayServiceGetter getter) {
		mContext = context;
		mGetter = getter;

		LocalService<AShareConnectionManagerService> avConnectionService =
				mBinder.read(AShareConnectionManagerService.class);
		mConnectionManager = new DefaultServiceManager<AShareConnectionManagerService>(avConnectionService) {

			@Override
			protected AShareConnectionManagerService createServiceInstance()
					throws Exception {
				return new AShareConnectionManagerService();
			}
		};
		avConnectionService.setManager(mConnectionManager);

		LocalService<AShareAVTransportService> avTransportService =
				mBinder.read(AShareAVTransportService.class);
		mAVTransportManager = new DefaultServiceManager<AShareAVTransportService>(avTransportService){

			@Override
			protected AShareAVTransportService createServiceInstance()
					throws Exception {
				return new AShareAVTransportService(mGetter);
			}
		};
		avTransportService.setManager(mAVTransportManager);

        LocalService<AShareAudioRenderingControl> renderingControlService =
                mBinder.read(AShareAudioRenderingControl.class);
        mRenderingControl = new DefaultServiceManager<AShareAudioRenderingControl>(renderingControlService) {
            @Override
            protected AShareAudioRenderingControl createServiceInstance() throws Exception {
                return new AShareAudioRenderingControl(mContext);
            }
        };
        renderingControlService.setManager(mRenderingControl);

        try {
            UDN udn = UDN.uniqueSystemIdentifier(sModelDescriptor);
            DeviceDetails devDetails = new DeviceDetails(
                    genFriendlyName(udn.toString()),
                    new ManufacturerDetails(sManufactor, sManufactorURL),
                    new ModelDetails(sModelName, sModelDescriptor, sModelNO, sModelURL)
                    );
            mDevice = new LocalDevice(
                    new DeviceIdentity(udn),
                    new UDADeviceType("MediaRenderer", 1),
                    devDetails,
                    new Icon[] { createDefaultIcon() },
                    new LocalService[] {
                        avTransportService,
                        avConnectionService,
                        renderingControlService,
                    });
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        runLastChangePushThread();
	}

	public LocalDevice getDevice() {
		return mDevice;
	}
    private void runLastChangePushThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        mAVTransportManager.getImplementation().fireLastChange();
                        mRenderingControl.getImplementation().fireLastChange();
                        Thread.sleep(LAST_CHANGE_FIRING_INTERVAL_MILLISECONDS);
                    }
                } catch (Exception e) {

                }
            }
        }.start();
    }

    private String genFriendlyName(String udn) {
        // Generate friendly name "Base Friendly Name( last 4 chars of UDN)"
        String baseFriendlyName = mContext.getResources().getString(R.string.base_friendly_name);
        Log.v(AShareMediaRender.class.getSimpleName(), "udn:" + udn);
        return baseFriendlyName + " ("  + udn.substring(udn.length() - 4) + ")";
    }

    private Icon createDefaultIcon() {
        mContext.getResources().openRawResource(R.raw.device_icon);
        Icon icon = null;
        try {
            icon = new Icon("image/png", 72, 72, 255, URI.create("device_icon.png"),
                mContext.getResources().openRawResource(R.raw.device_icon));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return icon;
    }
}
