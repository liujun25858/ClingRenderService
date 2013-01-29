package com.archermind.ashare.render.service;

import org.teleal.cling.binding.annotations.UpnpAction;
import org.teleal.cling.binding.annotations.UpnpInputArgument;
import org.teleal.cling.binding.annotations.UpnpOutputArgument;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.avtransport.AVTransportException;
import org.teleal.cling.support.avtransport.AbstractAVTransportService;
import org.teleal.cling.support.model.DeviceCapabilities;
import org.teleal.cling.support.model.MediaInfo;
import org.teleal.cling.support.model.PositionInfo;
import org.teleal.cling.support.model.TransportInfo;
import org.teleal.cling.support.model.TransportSettings;

import android.os.RemoteException;

public class AShareAVTransportService extends AbstractAVTransportService {
	private final static boolean DEBUG = true;
	private PlayServiceGetter mGetter;
	public AShareAVTransportService(PlayServiceGetter getter) {
		LogUtil.logv(this, "Creater", DEBUG);
		mGetter = getter;
	}
	@Override
	@UpnpAction(out = @UpnpOutputArgument(name = "Actions"))
	public String getCurrentTransportActions(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "PlayMedia", stateVariable = "PossiblePlaybackStorageMedia", getterName = "getPlayMediaString"),
			@UpnpOutputArgument(name = "RecMedia", stateVariable = "PossibleRecordStorageMedia", getterName = "getRecMediaString"),
			@UpnpOutputArgument(name = "RecQualityModes", stateVariable = "PossibleRecordQualityModes", getterName = "getRecQualityModesString") })
	public DeviceCapabilities getDeviceCapabilities(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "NrTracks", stateVariable = "NumberOfTracks", getterName = "getNumberOfTracks"),
			@UpnpOutputArgument(name = "MediaDuration", stateVariable = "CurrentMediaDuration", getterName = "getMediaDuration"),
			@UpnpOutputArgument(name = "CurrentURI", stateVariable = "AVTransportURI", getterName = "getCurrentURI"),
			@UpnpOutputArgument(name = "CurrentURIMetaData", stateVariable = "AVTransportURIMetaData", getterName = "getCurrentURIMetaData"),
			@UpnpOutputArgument(name = "NextURI", stateVariable = "NextAVTransportURI", getterName = "getNextURI"),
			@UpnpOutputArgument(name = "NextURIMetaData", stateVariable = "NextAVTransportURIMetaData", getterName = "getNextURIMetaData"),
			@UpnpOutputArgument(name = "PlayMedium", stateVariable = "PlaybackStorageMedium", getterName = "getPlayMedium"),
			@UpnpOutputArgument(name = "RecordMedium", stateVariable = "RecordStorageMedium", getterName = "getRecordMedium"),
			@UpnpOutputArgument(name = "WriteStatus", stateVariable = "RecordMediumWriteStatus", getterName = "getWriteStatus") })
	public MediaInfo getMediaInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "Track", stateVariable = "CurrentTrack", getterName = "getTrack"),
			@UpnpOutputArgument(name = "TrackDuration", stateVariable = "CurrentTrackDuration", getterName = "getTrackDuration"),
			@UpnpOutputArgument(name = "TrackMetaData", stateVariable = "CurrentTrackMetaData", getterName = "getTrackMetaData"),
			@UpnpOutputArgument(name = "TrackURI", stateVariable = "CurrentTrackURI", getterName = "getTrackURI"),
			@UpnpOutputArgument(name = "RelTime", stateVariable = "RelativeTimePosition", getterName = "getRelTime"),
			@UpnpOutputArgument(name = "AbsTime", stateVariable = "AbsoluteTimePosition", getterName = "getAbsTime"),
			@UpnpOutputArgument(name = "RelCount", stateVariable = "RelativeCounterPosition", getterName = "getRelCount"),
			@UpnpOutputArgument(name = "AbsCount", stateVariable = "AbsoluteCounterPosition", getterName = "getAbsCount") })
	public PositionInfo getPositionInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "CurrentTransportState", stateVariable = "TransportState", getterName = "getCurrentTransportState"),
			@UpnpOutputArgument(name = "CurrentTransportStatus", stateVariable = "TransportStatus", getterName = "getCurrentTransportStatus"),
			@UpnpOutputArgument(name = "CurrentSpeed", stateVariable = "TransportPlaySpeed", getterName = "getCurrentSpeed") })
	public TransportInfo getTransportInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "PlayMode", stateVariable = "CurrentPlayMode", getterName = "getPlayMode"),
			@UpnpOutputArgument(name = "RecQualityMode", stateVariable = "CurrentRecordQualityMode", getterName = "getRecQualityMode") })
	public TransportSettings getTransportSettings(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpnpAction
	public void next(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void pause(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void play(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "Speed", stateVariable = "TransportPlaySpeed") String arg1)
			throws AVTransportException {
		if (mGetter.getPlayService() != null){
			try {
				mGetter.getPlayService().IPlay("Media:Video");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@UpnpAction
	public void previous(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void record(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void seek(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "Unit", stateVariable = "A_ARG_TYPE_SeekMode") String arg1,
			@UpnpInputArgument(name = "Target", stateVariable = "A_ARG_TYPE_SeekTarget") String arg2)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void setAVTransportURI(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "CurrentURI", stateVariable = "AVTransportURI") String arg1,
			@UpnpInputArgument(name = "CurrentURIMetaData", stateVariable = "AVTransportURIMetaData") String arg2)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void setNextAVTransportURI(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "NextURI", stateVariable = "AVTransportURI") String arg1,
			@UpnpInputArgument(name = "NextURIMetaData", stateVariable = "AVTransportURIMetaData") String arg2)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void setPlayMode(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "NewPlayMode", stateVariable = "CurrentPlayMode") String arg1)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void setRecordQualityMode(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0,
			@UpnpInputArgument(name = "NewRecordQualityMode", stateVariable = "CurrentRecordQualityMode") String arg1)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public void stop(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes arg0)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

}
