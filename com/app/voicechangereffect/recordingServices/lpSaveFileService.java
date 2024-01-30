package com.app.voicechangereffect.recordingServices;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.custUi.lpAppConstant;
import kotlin.jvm.internal.Intrinsics;


public final class lpSaveFileService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        String stringExtra = intent == null ? null : intent.getStringExtra(lpAppConstant.lpactionService);
        if (stringExtra == null) {
            return Service.START_NOT_STICKY;
        }
        lphandleActions(stringExtra);
        return Service.START_NOT_STICKY;
    }

    private final void lpnotificationSend() {
        Notification build = new NotificationCompat.Builder(this, lpAppConstant.lpchannelId).setSmallIcon(R.drawable.set_as_ring).setContentTitle(getString(R.string.saving_audio)).setAutoCancel(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(this, Constants.\u2026rue)\n            .build()");
        startForeground(101, build);
    }

    private final void lphandleActions(String str) {
        if (Intrinsics.areEqual(str, lpAppConstant.lpactionStart)) {
            lpnotificationSend();
        } else if (Intrinsics.areEqual(str, lpAppConstant.lpactionStop)) {
            stopSelf();
        }
    }
}
