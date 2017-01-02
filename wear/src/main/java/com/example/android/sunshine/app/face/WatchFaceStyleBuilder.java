package com.example.android.sunshine.app.face;

import android.app.Service;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.Gravity;

//  WatchFaceStyleBuilder sets the miscellaneous watch face style options for
//  Sunshine Watch Face

public class WatchFaceStyleBuilder extends WatchFaceStyle.Builder {
    public WatchFaceStyleBuilder(Service service) {
        super(service);
    }

    public WatchFaceStyle build() {
        super.setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT);
        super.setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE);
        super.setHotwordIndicatorGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        super.setShowSystemUiTime(false);
        return super.build();
    }
}
