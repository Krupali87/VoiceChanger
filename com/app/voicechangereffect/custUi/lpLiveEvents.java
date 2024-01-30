package com.app.voicechangereffect.custUi;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class lpLiveEvents<T> extends MutableLiveData<T> {
    public final AtomicBoolean lpatomicBoolean = new AtomicBoolean(false);

    @Override
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {

        super.observe(lifecycleOwner, (Observer) obj -> lpLiveEvents.this.lpm410xe56e57f1(observer, obj));
    }

    public void lpm410xe56e57f1(Observer observer, Object obj) {
        if (this.lpatomicBoolean.compareAndSet(true, false)) {
            observer.onChanged(obj);
        }
    }

    @Override
    public void setValue(T t) {
        this.lpatomicBoolean.set(true);
        super.setValue(t);
    }

    public void call() {
        setValue(null);
    }
}
