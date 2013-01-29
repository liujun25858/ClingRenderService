package com.archermind.ashare.render.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.teleal.cling.binding.annotations.UpnpAction;
import org.teleal.cling.binding.annotations.UpnpInputArgument;
import org.teleal.cling.binding.annotations.UpnpOutputArgument;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.model.types.UnsignedIntegerTwoBytes;
import org.teleal.cling.support.renderingcontrol.AbstractAudioRenderingControl;
import org.teleal.cling.support.renderingcontrol.RenderingControlException;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

public class AShareAudioRenderingControl extends AbstractAudioRenderingControl{
    private Context mCtx;
    private AudioManager mAudioManager;
    public AShareAudioRenderingControl(Context ctx) {
        if(ctx == null) {
            throw new IllegalArgumentException();
        }
        mCtx = ctx;
        mAudioManager = (AudioManager)mCtx.getSystemService(Context.AUDIO_SERVICE);
    }
    @Override
    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentMute", stateVariable = "Mute"))
    public boolean getMute(
            @UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceID,
            @UpnpInputArgument(name = "Channel") String channel)
            throws RenderingControlException {
        boolean isMute = false;
        try {
            Method method = AudioManager.class.getMethod("isStreamMute", int.class);
            isMute = ((Boolean)method.invoke(mAudioManager, AudioManager.STREAM_MUSIC)).booleanValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.v(AShareAudioRenderingControl.class.getSimpleName(),
                "getMute() -----> isMute:" + isMute);
        return isMute;
    }

    @Override
    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentVolume", stateVariable = "Volume"))
    public UnsignedIntegerTwoBytes getVolume(
            @UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceID,
            @UpnpInputArgument(name = "Channel") String channel)
            throws RenderingControlException {
        int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.v(AShareAudioRenderingControl.class.getSimpleName(), "getVolume() -----> " + volume);
        return new UnsignedIntegerTwoBytes(volume);
    }

    @Override
    @UpnpAction
    public void setMute(
            @UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceID,
            @UpnpInputArgument(name = "Channel") String channel,
            @UpnpInputArgument(name = "DesiredMute", stateVariable = "Mute") boolean desiredMute)
            throws RenderingControlException {
        Log.v(AShareAudioRenderingControl.class.getSimpleName(),
                "setMute() -----> desiredMute:" + desiredMute);
        mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, desiredMute);
    }

    @Override
    @UpnpAction
    public void setVolume(
            @UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceID,
            @UpnpInputArgument(name = "Channel") String channel,
            @UpnpInputArgument(name = "DesiredVolume", stateVariable = "Volume") UnsignedIntegerTwoBytes desiredVolume)
            throws RenderingControlException {
        int volume = desiredVolume.getValue().intValue();
        Log.v(AShareAudioRenderingControl.class.getSimpleName(),
                "setVolume() -----> desiredVolume" + volume);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

}
