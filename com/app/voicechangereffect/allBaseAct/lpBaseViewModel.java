package com.app.voicechangereffect.allBaseAct;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.app.voicechangereffect.custUi.lpProgressBar;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.jvm.internal.Intrinsics;


public abstract class lpBaseViewModel extends ViewModel {
    public Scheduler lpcomputation;
    public Context lpcontext;
    public Scheduler lpf5io;
    public lpIFragmentCallback lpfragmentCallback;
    public lpInterfaceDataManager lpinterfaceDataManager;
   private final CompositeDisposable lpmDisposable = new CompositeDisposable();
    public lpNavigators lpnavigation;
    public lpProgressBar lpprogressBar;
    public lpSchedularProvider lpscheduler;
    public Scheduler lpui;

    public final void setLpfragmentCallback(lpIFragmentCallback lpifragmentcallback) {
        Intrinsics.checkNotNullParameter(lpifragmentcallback, "<set-?>");
        this.lpfragmentCallback = lpifragmentcallback;
    }

    public final Context getLpcontext() {
        Context context = this.lpcontext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final void setLpcontext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.lpcontext = context;
    }

    public final void setLpnavigation(lpNavigators lpnavigators) {
        Intrinsics.checkNotNullParameter(lpnavigators, "<set-?>");
        this.lpnavigation = lpnavigators;
    }

    public final void lpsetDataManager(lpInterfaceDataManager lpinterfacedatamanager) {
        Intrinsics.checkNotNullParameter(lpinterfacedatamanager, "<set-?>");
        this.lpinterfaceDataManager = lpinterfacedatamanager;
    }

    public final void setLpscheduler(lpSchedularProvider lpschedularprovider) {
        Intrinsics.checkNotNullParameter(lpschedularprovider, "<set-?>");
        this.lpscheduler = lpschedularprovider;
    }

    public final Scheduler lpgetIo() {
        Scheduler scheduler = this.lpf5io;
        if (scheduler != null) {
            return scheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("io");
        return null;
    }

    public final void lpsetIo(Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(scheduler, "<set-?>");
        this.lpf5io = scheduler;
    }

    public final Scheduler lpgetSkui() {
        Scheduler scheduler = this.lpui;
        if (scheduler != null) {
            return scheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ui");
        return null;
    }

    public final void setLpui(Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(scheduler, "<set-?>");
        this.lpui = scheduler;
    }

    public final void setLpcomputation(Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(scheduler, "<set-?>");
        this.lpcomputation = scheduler;
    }

    public final lpProgressBar getLpprogressBar() {
        lpProgressBar lpprogressbar = this.lpprogressBar;
        if (lpprogressbar != null) {
            return lpprogressbar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        return null;
    }

    public final void setLpprogressBar(lpProgressBar lpprogressbar) {
        Intrinsics.checkNotNullParameter(lpprogressbar, "<set-?>");
        this.lpprogressBar = lpprogressbar;
    }

    public final void lpinitData(Context context, lpInterfaceDataManager dataManager, lpSchedularProvider scheduler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        setLpcontext(context);
        lpsetDataManager(dataManager);
        setLpscheduler(scheduler);
        lpsetIo(scheduler.getIo());
        setLpui(scheduler.getUi());
        setLpcomputation(scheduler.getLpscheduler2());
        setLpprogressBar(new lpProgressBar(scheduler));
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    public final void onDestroyView() {
        getLpprogressBar().reset();
     this.lpmDisposable.clear();
    }
}
