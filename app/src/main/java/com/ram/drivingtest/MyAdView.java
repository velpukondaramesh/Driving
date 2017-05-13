package com.ram.drivingtest;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Ramesh on 11-05-2017.
 */

public class MyAdView {
    public static void SetAD(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }
}
