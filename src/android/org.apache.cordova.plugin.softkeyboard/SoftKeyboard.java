package org.apache.cordova.plugin.softkeyboard;

import org.json.JSONArray;

import android.view.*;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class SoftKeyboard extends CordovaPlugin {

    public SoftKeyboard() {
    }

    public void showKeyBoard() {
		final View view = webView.getView();
        InputMethodManager mgr = (InputMethodManager) cordova.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);

        ((InputMethodManager) cordova.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    public void hideKeyBoard() {
        InputMethodManager mgr = (InputMethodManager) cordova.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(webView.getView().getWindowToken(), 0);
    }

    public boolean isKeyBoardShowing() {
		final View view = webView.getView();
    	int heightDiff = view.getRootView().getHeight() - view.getHeight();
    	return (100 < heightDiff); // if more than 100 pixels, its probably a keyboard...
    }

    @Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		if (action.equals("show")) {
            this.showKeyBoard();
            callbackContext.success("done");
            return true;
		}
        else if (action.equals("hide")) {
            this.hideKeyBoard();
            callbackContext.success();
            return true;
        }
        else if (action.equals("isShowing")) {
            callbackContext.success(Boolean.toString(this.isKeyBoardShowing()));
            return true;
        }
		else {
			return false;
		}
	}
}
