/**
 * Phonegap Torch Plugin
 * Copyright (c) Arne de Bree 2011
 *
 */
package com.plugin.torch;

import java.io.IOException;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;

/**
 * Plugin to turn on or off the Camera Flashlight of an Android device after the
 * capability is tested
 */
public class TorchPlugin extends CordovaPlugin {

	public static final String CMD_ON = "turnOn";
	public static final String CMD_OFF = "turnOff";
	public static final String CMD_TOGGLE = "toggle";
	public static final String CMD_IS_ON = "isOn";
	public static final String CMD_HAS_TORCH = "isCapable";

	// Create camera and parameter objects
	private Camera mCamera;
	private Camera.Parameters mParameters;
	private boolean mbTorchEnabled = false;

	/**
	 * Constructor
	 */
	public TorchPlugin() {
		Log.d("TorchPlugin", "Plugin created");
		mCamera = null;

	}

	/*
	 * Executes the request and returns PluginResult.
	 *
	 * @param action action to perform. Allowed values: turnOn, turnOff, toggle,
	 * isOn, isCapable
	 *
	 * @param data input data, currently not in use
	 *
	 * @param callbackId The callback id used when calling back into JavaScript.
	 *
	 * @return A PluginResult object with a status and message.
	 *
	 * @see com.phonegap.api.Plugin#execute(java.lang.String,
	 * org.json.JSONArray, java.lang.String)
	 */

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		Log.d("TorchPlugin", "Plugin Called " + action);

		JSONObject response = new JSONObject();

		if (action.equals(CMD_ON)) {
			this.toggleTorch(true);
			callbackContext.success();
		} else if (action.equals(CMD_OFF)) {
			this.toggleTorch(false);
			callbackContext.success();
			return true;
		} else if (action.equals(CMD_TOGGLE)) {
			response.put("isOn", this.toggleTorch());
			callbackContext.success(response);
			return true;
		} else if (action.equals(CMD_IS_ON)) {
			try {
				response.put("isOn", mbTorchEnabled);
				callbackContext.success(response);
				return true;
			} catch (JSONException jsonEx) {
				callbackContext.error("Could not check torch state.");
			}
		} else if (action.equals(CMD_HAS_TORCH)) {
			try {
				// FIX:MANTIS:3012
				// init camera if necessary (it should be)
				if (mCamera == null) {
					mCamera = Camera.open();
				}
				mParameters = mCamera.getParameters();
				response.put("capable", this.isCapable());

				// release camera after use
				mCamera.release();
				mCamera = null;

				callbackContext.success(response);
			} catch (JSONException jsonEx) {
				callbackContext.error("Could not check torch capability.");
			}
		} else {
			Log.d("TorchPlugin", "Invalid action : " + action + " passed");
		}

		return false;
	}

	/**
	 * Test if this device has a Flashlight we can use and put in Torch mode
	 *
	 * @return boolean
	 */
	protected boolean isCapable() {
		boolean result = false;

		List<String> flashModes = mParameters.getSupportedFlashModes();

		if (flashModes != null
				&& flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
			result = true;
		}

		return result;
	}

	/**
	 * True toggle function, turns the torch on when off and vise versa
	 *
	 */
	protected boolean toggleTorch() {
		return toggleTorch(!mbTorchEnabled);
	}

	/**
	 * Toggle the torch in the requested state
	 *
	 * @param state
	 *            The requested state
	 *
	 */
	protected boolean toggleTorch(boolean state) {
		Log.d("TorchPlugin", "toggle torch : " + state);

		if (state) {
			if (mCamera == null) {
				mCamera = Camera.open();
				//FIX:MANTIS:4112
				try {
					mCamera.setPreviewTexture(new SurfaceTexture(0));
				} catch (IOException e) {
					e.printStackTrace();
				}
				mParameters = mCamera.getParameters();
				if (this.isCapable()) {
					mParameters
							.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
					mCamera.setParameters(mParameters);
					// FIX:MANTIS:3008
					mCamera.startPreview();
					mbTorchEnabled = state;
					return true;
				}
			}
		} else {
			if (mCamera != null && this.isCapable()) {
				mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(mParameters);

				mCamera.release();
				mCamera = null;

			}
		}
		mbTorchEnabled = state;
		return false;
	}
}
