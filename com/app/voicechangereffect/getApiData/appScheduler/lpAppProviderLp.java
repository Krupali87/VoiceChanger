package com.app.voicechangereffect.getApiData.appScheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class lpAppProviderLp implements lpSchedularProvider {
    private final io.reactivex.Scheduler lpscheduler2 = Schedulers.computation();
    private final io.reactivex.Scheduler lpscheduler = Schedulers.io();
    private final io.reactivex.Scheduler lpscheduler1 = AndroidSchedulers.mainThread();
    @Override
    public io.reactivex.Scheduler getLpscheduler2() {
        return this.lpscheduler2;
    }

    @Override
    public io.reactivex.Scheduler getIo() {
        return (io.reactivex.Scheduler) this.lpscheduler;
    }

    @Override
    public Scheduler getUi() {
        return this.lpscheduler1;
    }
}
