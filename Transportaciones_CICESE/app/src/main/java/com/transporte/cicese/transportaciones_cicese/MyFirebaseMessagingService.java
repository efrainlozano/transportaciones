package com.transporte.cicese.transportaciones_cicese;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by blanca on 31/10/2017.
 */

public class MyFirebaseMessagingService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
