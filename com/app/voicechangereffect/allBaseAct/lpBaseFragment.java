package com.app.voicechangereffect.allBaseAct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;
import java.util.Objects;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;



public abstract class lpBaseFragment<VM extends lpBaseViewModel, DB extends ViewDataBinding> extends Fragment implements lpIFragmentCallback {
    private lpNavigators lpNavigators;
    public Activity lpactivity;
    public DB lpdataBinding;
    @Inject
    public lpViewModelFactory lpfactory;
    @Inject
    public lpInterfaceDataManager lpinterfaceDataManager;

    @Inject
    public lpSchedularProvider lpschedule;
    public VM lpviewModel;
  private final CompositeDisposable lpdisposable = new CompositeDisposable();

    private final Lazy lpmProgressDialog$delegate = LazyKt.lazy((Function0<Object>) () -> {
        Activity activity = lpBaseFragment.this.lpactivity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        return new ProgressDialog(activity);
    });

    public abstract void bindViewModels();

    public abstract void bindViews();

    public abstract Class<VM> createViewModel();

    public abstract int lpgetFragResourceLayout();

    public final void setDataManager(lpInterfaceDataManager lpinterfacedatamanager) {
        Intrinsics.checkNotNullParameter(lpinterfacedatamanager, "<set-?>");
        this.lpinterfaceDataManager = lpinterfacedatamanager;
    }

    public final lpViewModelFactory getLpfactory() {
        lpViewModelFactory lpviewmodelfactory = this.lpfactory;
        if (lpviewmodelfactory != null) {
            return lpviewmodelfactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("factory");
        return null;
    }

    public final void setLpfactory(lpViewModelFactory lpviewmodelfactory) {
        Intrinsics.checkNotNullParameter(lpviewmodelfactory, "<set-?>");
        this.lpfactory = lpviewmodelfactory;
    }

    public final void setLpschedule(lpSchedularProvider lpschedularprovider) {
        Intrinsics.checkNotNullParameter(lpschedularprovider, "<set-?>");
        this.lpschedule = lpschedularprovider;
    }

    public final VM getLpviewModel() {
        VM vm = this.lpviewModel;
        if (vm != null) {
            return vm;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setLpviewModel(VM vm) {
        Intrinsics.checkNotNullParameter(vm, "<set-?>");
        this.lpviewModel = vm;
    }

    public final DB getLpdataBinding() {
        DB db = this.lpdataBinding;
        if (db != null) {
            return db;
        }
        return null;
    }

    public final void setLpdataBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.lpdataBinding = db;
    }

    private final ProgressDialog getMProgressDialog() {
        return (ProgressDialog) this.lpmProgressDialog$delegate.getValue();
    }

    public final void inject(lpViewModelFactory factory, lpSchedularProvider schedule, lpInterfaceDataManager dataManager) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(schedule, "schedule");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        setLpfactory(factory);
        setLpschedule(schedule);
        setDataManager(dataManager);
    }

    @Override
    public Context getContext() {
        return this.lpactivity;
    }

    @Override
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof lpNavigators) {
            lpNavigators lpnavigators = (lpNavigators) context;
            lpnavigators.fragmentRequestInject(this);
            this.lpactivity = (Activity) context;
            this.lpNavigators = lpnavigators;
            ViewModel viewModel = new ViewModelProvider(this, getLpfactory()).get(createViewModel());
            Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, \u2026y).get(createViewModel())");
            setLpviewModel((VM) viewModel);
            getLpviewModel().setLpfragmentCallback(this);
            lpBaseViewModel lpviewModel = getLpviewModel();
            lpNavigators lpnavigators2 = this.lpNavigators;
            if (lpnavigators2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigators");
                lpnavigators2 = null;
            }
            lpviewModel.setLpnavigation(lpnavigators2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return onCreateViewInternal(inflater, viewGroup);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setupUI(view);
        lpNavigators lpnavigators = this.lpNavigators;
        if (lpnavigators == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigators");
            lpnavigators = null;
        }
        lpnavigators.onFragmentResumed(this);
        bindViews();
        bindViewModels();
    }

    @SuppressLint("ClickableViewAccessibility")
    private final void setupUI(View view) {
        if (!(view instanceof EditText)) {

            view.setOnTouchListener((view2, motionEvent) -> {
                Activity this$0 = lpBaseFragment.this.lpactivity;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                lpBaseFragment.this.lphideSoftKeyboard();
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (i < childCount) {
                int i2 = i + 1;
                View innerView = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(innerView, "innerView");
                setupUI(innerView);
                i = i2;
            }
        }
    }

    public final void lphideSoftKeyboard() {
        Activity activity = this.lpactivity;
        Activity activity2 = null;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            Activity activity3 = this.lpactivity;
            if (activity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
            } else {
                activity2 = activity3;
            }
            Object systemService = activity2.getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private final View onCreateViewInternal(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (lpgetFragResourceLayout() <= 0) {
            return null;
        }
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, lpgetFragResourceLayout(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               \u2026      false\n            )");
        setLpdataBinding((DB) inflate);
        getLpdataBinding().setVariable(2, getLpviewModel());
        return getLpdataBinding().getRoot();
    }

    public void showLoading() {
        if (getMProgressDialog().isShowing() || !getUserVisibleHint()) {
            return;
        }
        getMProgressDialog().show();
    }

    public void hideLoading() {
        if (!getMProgressDialog().isShowing() || isDetached()) {
            return;
        }
        getMProgressDialog().dismiss();
    }

    @Override
    public void showActivity(Class<?> act, Bundle bundle) {
        Intrinsics.checkNotNullParameter(act, "act");
        Activity activity = this.lpactivity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        Intent intent = new Intent(activity, act);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public static final class ProgressDialog extends Dialog {
        @SuppressLint("ResourceType")
        public ProgressDialog(Context context) {
            super(context);
            Window window;
            Intrinsics.checkNotNullParameter(context, "context");
            setCancelable(false);
            requestWindowFeature(1);
            setContentView(R.layout.progress_circle);
            if (getWindow() == null || (window = getWindow()) == null) {
                return;
            }
            window.setBackgroundDrawableResource(17170445);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLpviewModel().onDestroyView();
        this.lpdisposable.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
