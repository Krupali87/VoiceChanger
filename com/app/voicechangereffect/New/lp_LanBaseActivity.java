package com.app.voicechangereffect.New;

import android.app.Application;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.app.voicechangereffect.allBaseAct.lpViewModelFactory;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;
import com.app.voicechangereffect.lpMainApplication;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class lp_LanBaseActivity extends AppCompatActivity {
    lp_LanBaseActivity baseActivity;
    @Inject
    public lpViewModelFactory factory;
    @Inject
    public lpInterfaceDataManager lpInterfaceDataManager;
    @Inject
    public lpSchedularProvider schedulerProvider;

    public final lpViewModelFactory getFactory() {
        lpViewModelFactory lpviewmodelfactory = this.factory;
        if (lpviewmodelfactory != null) {
            return lpviewmodelfactory;
        }
        return null;
    }

    public final void setFactory(lpViewModelFactory lpviewmodelfactory) {
        this.factory = lpviewmodelfactory;
    }

    public final lpInterfaceDataManager getDataManager() {
        return this.lpInterfaceDataManager;
    }

    public final void setDataManager(lpInterfaceDataManager lpinterfacedatamanager) {
        Intrinsics.checkNotNullParameter(lpinterfacedatamanager, "<set-?>");
        this.lpInterfaceDataManager = lpinterfacedatamanager;
    }

    public final lpSchedularProvider getSchedulerProvider() {
        lpSchedularProvider lpschedularprovider = this.schedulerProvider;
        if (lpschedularprovider != null) {
            return lpschedularprovider;
        }
        return null;
    }

    public final void setSchedulerProvider(lpSchedularProvider lpschedularprovider) {
        Intrinsics.checkNotNullParameter(lpschedularprovider, "<set-?>");
        this.schedulerProvider = lpschedularprovider;
    }

    public final void inject(lpViewModelFactory lpviewmodelfactory, lpInterfaceDataManager lpinterfacedatamanager, lpSchedularProvider lpschedularprovider) {
        setFactory(lpviewmodelfactory);
        setDataManager(lpinterfacedatamanager);
        setSchedulerProvider(lpschedularprovider);
    }

    @Override
    public void onCreate(Bundle bundle) {
        Application application = getApplication();
        if (application instanceof lpMainApplication) {
            lpMainApplication lpmainapplication = (lpMainApplication) application;
        }
        this.baseActivity = this;
        lpSetLanguage.lpsetLocale(this);
        super.onCreate(bundle);
    }
}
