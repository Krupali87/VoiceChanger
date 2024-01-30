package com.app.voicechangereffect;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpViewModelFactory;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.getApiData.appScheduler.lpAppProviderLp;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.localData.lpHelpPrefClass;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;
import com.app.voicechangereffect.getApiData.lpMyAppLpInterfaceDataManager;
import com.app.voicechangereffect.langClass.lpPrefManager;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class lpMainApplication extends Application {
    public static final Companion lpCompanion = new Companion(null);
    private static lpPrefManager lpPrefManager;
    public static lpMainApplication lpinstance;
    private lpInterfaceDataManager lpInterfaceDataManager;
    private lpSchedularProvider lpSchedularProvider;
    private lpViewModelFactory lpmodelFactory;

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public lpMainApplication lpgetInstance() {
            if (lpMainApplication.lpinstance == null) {
                lpMainApplication.lpinstance = new lpMainApplication();
            }
            lpMainApplication lpmainapplication = lpMainApplication.lpinstance;
            Objects.requireNonNull(lpmainapplication, "null cannot be cast to non-null type com.app.voicechangereffect.App");
            return lpmainapplication;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lpinstance = this;
        lpPrefManager = lpPrefManager.getInstance(this);
        lpHelpPrefClass lphelpprefclass = new lpHelpPrefClass(this, "VoiceEffect111");
        this.lpSchedularProvider = new lpAppProviderLp();
        this.lpInterfaceDataManager = new lpMyAppLpInterfaceDataManager(lphelpprefclass);
        Context applicationContext = getApplicationContext();
        lpInterfaceDataManager lpinterfacedatamanager = this.lpInterfaceDataManager;
        if (lpinterfacedatamanager == null) {
            lpinterfacedatamanager = null;
        }
        lpSchedularProvider lpschedularprovider = this.lpSchedularProvider;
        this.lpmodelFactory = new lpViewModelFactory(applicationContext, lpinterfacedatamanager, lpschedularprovider);
        lpchannelNotificationCreate();
    }

    public void requestInjectAct(lpBaseActivity<?, ?> activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        lpViewModelFactory lpviewmodelfactory = this.lpmodelFactory;
        if (lpviewmodelfactory == null) {
            lpviewmodelfactory = null;
        }
        lpInterfaceDataManager lpinterfacedatamanager = this.lpInterfaceDataManager;
        if (lpinterfacedatamanager == null) {
            lpinterfacedatamanager = null;
        }
        lpSchedularProvider lpschedularprovider = this.lpSchedularProvider;
        activity.inject(lpviewmodelfactory, lpinterfacedatamanager, lpschedularprovider);
    }

    private void lpchannelNotificationCreate() {
        if (Build.VERSION.SDK_INT > 26) {
            NotificationChannel notificationChannel = new NotificationChannel(lpAppConstant.lpchannelId, lpAppConstant.skchannelName, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public static lpPrefManager getPrefManager() {
        return lpPrefManager;
    }

    public static synchronized lpMainApplication getLpinstance() {
        lpMainApplication lpmainapplication;
        synchronized (lpMainApplication.class) {
            synchronized (lpMainApplication.class) {
                synchronized (lpMainApplication.class) {
                    lpmainapplication = lpinstance;
                }
                return lpmainapplication;
            }
        }
    }
}
