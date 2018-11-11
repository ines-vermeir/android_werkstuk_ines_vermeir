package vermeir.ines.ehb.be.androidwerkstuk;

import android.app.Activity;
import android.content.Intent;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Ines on 11/11/2018.
 */

public class QRCodeUtil {



        /**
         * Starts the QR code scanning.
         * @param activity the activity that starts the scanning
         */
        public static void startQRScan(Activity activity){
            IntentIntegrator qrScan = new IntentIntegrator(activity);
            qrScan.initiateScan();
        }

        /**
         * Must be called in OnActivityResult of the calling activity. Returns the result of the scan
         * @param activity the activity that starts the scanning
         * @param requestCode the request code
         * @param resultCode the result code
         * @param data the data received
         * @return returns the String of the QR code. If no result found, returns null.
         */
        public static String onScanResult(Activity activity, int requestCode, int resultCode, Intent data){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                return result.getContents();
            }
            return null;
        }



}
