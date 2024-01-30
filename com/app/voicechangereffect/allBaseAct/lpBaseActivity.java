package com.app.voicechangereffect.allBaseAct;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;
import com.app.voicechangereffect.lpMainApplication;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

public abstract class lpBaseActivity<VM extends lpBaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity implements lpNavigators {
    lpBaseActivity lpbaseActivity;
    @Inject
    public lpViewModelFactory lpfactory;
    @Inject
    public lpInterfaceDataManager lpinterfaceDataManager;
    public DB lpmDataBinding;
    public VM lpmViewModel;
    @Inject
    public lpSchedularProvider lpschedulerProvider;

    public abstract Class<VM> createViewModel();

    public abstract int getContent();

    public abstract void lpinitViews();

    public abstract void lpmainView();

    public final lpViewModelFactory getLpfactory() {
        lpViewModelFactory lpviewmodelfactory = this.lpfactory;
        if (lpviewmodelfactory != null) {
            return lpviewmodelfactory;
        }
        return null;
    }



    public final void setLpfactory(lpViewModelFactory lpviewmodelfactory) {
        this.lpfactory = lpviewmodelfactory;
    }

    public final lpInterfaceDataManager getDataManager() {
        return this.lpinterfaceDataManager;
    }

    public final void setDataManager(lpInterfaceDataManager lpinterfacedatamanager) {
        Intrinsics.checkNotNullParameter(lpinterfacedatamanager, "<set-?>");
        this.lpinterfaceDataManager = lpinterfacedatamanager;
    }

    public final lpSchedularProvider getLpschedulerProvider() {
        lpSchedularProvider lpschedularprovider = this.lpschedulerProvider;
        if (lpschedularprovider != null) {
            return lpschedularprovider;
        }
        return null;
    }

    public final void setLpschedulerProvider(lpSchedularProvider lpschedularprovider) {
        Intrinsics.checkNotNullParameter(lpschedularprovider, "<set-?>");
        this.lpschedulerProvider = lpschedularprovider;
    }

    public final void inject(lpViewModelFactory lpviewmodelfactory, lpInterfaceDataManager lpinterfacedatamanager, lpSchedularProvider lpschedularprovider) {
        setLpfactory(lpviewmodelfactory);
        setDataManager(lpinterfacedatamanager);
        setLpschedulerProvider(lpschedularprovider);
    }

    public final VM lpgetMViewModel() {
        VM vm = this.lpmViewModel;
        if (vm != null) {
            return vm;
        }
        return null;
    }

    public final void lpsetMViewModel(VM vm) {
        Intrinsics.checkNotNullParameter(vm, "<set-?>");
        this.lpmViewModel = vm;
    }

    public final DB getBindingData() {
        DB db = this.lpmDataBinding;
        if (db != null) {
            return db;
        }
        return null;
    }


    public final void setBindingData(DB db) {
        this.lpmDataBinding = db;
    }

    public void onCreate(Bundle bundle) {
        try {
            Application application = getApplication();
            lpMainApplication lpmainapplication = application instanceof lpMainApplication ? (lpMainApplication) application : null;
            if (lpmainapplication != null) {
                lpmainapplication.requestInjectAct(this);
            }
            this.lpbaseActivity = this;
            lpSetLanguage.lpsetLocale(this);
            super.onCreate(bundle);

            setBindingData(DataBindingUtil.setContentView(this, getContent()));

            lpsetMViewModel(ViewModelProviders.of(this, getLpfactory()).get(createViewModel()));
            getBindingData().setVariable(2, lpgetMViewModel());
            lpmainView();
            lpinitViews();
        }
        catch (Exception e){
         throw e;
        }

    }

    @Override
    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void fragmentRequestInject(lpBaseFragment<?, ?> lpbasefragment) {
        lpbasefragment.inject(getLpfactory(), getLpschedulerProvider(), getDataManager());
    }
}
