package com.carriername.plugin;

import android.content.Context;
import android.telephony.TelephonyManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class CarrierName extends CordovaPlugin {

    @Override
    public boolean execute(
            String action,
            JSONArray args,
            CallbackContext callbackContext) {

        if ("getCarrierName".equals(action)) {
            getCarrierName(callbackContext);
            return true;
        }

        return false;
    }

    private void getCarrierName(CallbackContext callbackContext) {

        try {

            Context context =
                    cordova.getActivity().getApplicationContext();

            TelephonyManager telephonyManager =
                    (TelephonyManager) context.getSystemService(
                            Context.TELEPHONY_SERVICE
                    );

            if (telephonyManager == null) {

                callbackContext.error(
                        "TelephonyManager unavailable"
                );

                return;
            }

            String carrierName =
                    telephonyManager.getNetworkOperatorName();

            if (carrierName == null) {
                carrierName = "";
            }

            callbackContext.success(carrierName);

        } catch (SecurityException ex) {

            callbackContext.error(
                    "Permission denied: " + ex.getMessage()
            );

        } catch (Exception ex) {

            callbackContext.error(
                    "Unable to get carrier name: " + ex.getMessage()
            );
        }
    }
}
