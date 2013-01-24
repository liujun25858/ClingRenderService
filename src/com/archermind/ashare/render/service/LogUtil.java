package com.archermind.ashare.render.service;

import android.util.Log;


public class LogUtil {
	private final static boolean DEBUG =true;
	private final static String TAG = "RenderService";

	private final static int DEBUG_LEVEL_DEBUG =2;
	private final static int DEBUG_LEVEL_VERBOSE = 1;
	private final static int DEBUG_LEVEL_ERROR = 3;

	private final static int CURRENT_DEBUG_LEVEL =  DEBUG_LEVEL_VERBOSE;

	public static void logd(Object object,String msg,boolean debug) {
		log(object,msg,debug,DEBUG_LEVEL_DEBUG);
	}

	public static void logv(Object object,String msg,boolean debug) {
		log(object,msg,debug,DEBUG_LEVEL_VERBOSE);
	}

	public static void loge(Object object,String msg,boolean debug) {
		log(object,msg,debug,DEBUG_LEVEL_ERROR);
	}

	public static void log(Object object, String msg, boolean debug, int level) {
		if (level < CURRENT_DEBUG_LEVEL) {
			return;
		} else {
			if (DEBUG && debug) {
				String name = object.getClass().getSimpleName();
				switch (level) {
				case DEBUG_LEVEL_VERBOSE:
					Log.v(TAG, name + ": " + msg);
					break;
				case DEBUG_LEVEL_DEBUG:
					Log.d(TAG, name + ": " + msg);
					break;
				case DEBUG_LEVEL_ERROR:
					Log.e(TAG, name + ": " + msg);
					break;
				default:
					break;
				}
			}
		}
	}
}
