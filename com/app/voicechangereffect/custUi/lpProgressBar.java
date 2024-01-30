package com.app.voicechangereffect.custUi;

import android.database.Observable;

import androidx.lifecycle.Observer;

import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;

import java.util.function.Consumer;
import java.util.function.Function;

import io.reactivex.Scheduler;
import io.reactivex.subjects.BehaviorSubject;


public final class lpProgressBar extends Observable<Boolean> {
    private final BehaviorSubject<Integer> lpIntegerBehaviorSubject;
    private Observable<Object> lpobjectObservable;

    public lpProgressBar(lpSchedularProvider lpschedularprovider) {
        BehaviorSubject<Integer> createDefault = BehaviorSubject.createDefault(0);
        this.lpIntegerBehaviorSubject = createDefault;
    }

    public static Boolean getProgresses(Integer num) {
        return Boolean.valueOf(num.intValue() > 0);
    }


    public void subscribeActual(Observer<? super Boolean> observer) {
        this.lpobjectObservable.unregisterObserver((Consumer) observer);
    }

    public void reset() {
        this.lpIntegerBehaviorSubject.onNext(0);
    }
}
