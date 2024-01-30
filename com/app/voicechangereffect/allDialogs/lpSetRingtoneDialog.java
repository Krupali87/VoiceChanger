package com.app.voicechangereffect.allDialogs;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseDialog;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpMobileState;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.databinding.RingSetDialogBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class lpSetRingtoneDialog extends lpBaseDialog<RingSetDialogBinding> {
    private Activity lpact;
    private lpMobileState lpmobileState;
    private final Function1 lponSetClick;

    @Override
    public int getDialogView() {
        return R.layout.ring_set_dialog;
    }

    public Activity getLpact() {
        return this.lpact;
    }

    public void setLpact(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.lpact = activity;
    }

    public Function1<lpMobileState, Unit> getLponSetClick() {
        return this.lponSetClick;
    }

    public lpSetRingtoneDialog(FragmentActivity activity, boolean z, Function1<? super lpMobileState, Unit> function1) {
        super(activity, z);
        this.lpmobileState = lpMobileState.lpSTATE_RINGTONE;
        this.lpact = activity;
        this.lponSetClick = function1;
    }

    public lpMobileState getMobileState() {
        return this.lpmobileState;
    }

    public void setLpmobileState(lpMobileState lpmobilestate) {
        Intrinsics.checkNotNullParameter(lpmobilestate, "<set-?>");
        this.lpmobileState = lpmobilestate;
    }

    @Override
    public void lpsetLanguage() {
        lpSetLanguage.lpsetLocale(this.lpact);
    }

    @Override
    public void lpinitViews() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
        if (attributes != null) {
            attributes.width = (int) (lpAppDataException.getWithMetrics(this.lpact) * 0.9d);
        }
    }

    @Override
    public void lpbindId() {

        lpTapClick.lptap(getLpdataBinding().tvCancel, (Function1<View, Unit>) view -> {
            lpSetRingtoneDialog.this.dismiss();
            return null;
        });

        lpTapClick.lptap(getLpdataBinding().rbRingtones, obj -> lpSetRingtoneDialog.this.lpm405x3b6404d(obj));

        lpTapClick.lptap(getLpdataBinding().rbAlarm, obj -> lpSetRingtoneDialog.this.lpm406x1cb791ec(obj));

        lpTapClick.lptap(getLpdataBinding().rbNotification, obj -> lpSetRingtoneDialog.this.lpm407x35b8e38b(obj));

        lpTapClick.lptap(getLpdataBinding().tvSet, obj -> lpSetRingtoneDialog.this.lpm408x4eba352a(obj));

        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpSetRingtoneDialog.this.lpm409x67bb86c9(obj));
    }

    public Object lpm405x3b6404d(Object obj) {
        setLpmobileState(lpMobileState.lpSTATE_RINGTONE);
        getLpdataBinding().rbRingtones.setChecked(true);
        getLpdataBinding().rbAlarm.setChecked(false);
        getLpdataBinding().rbNotification.setChecked(false);
        return null;
    }

    public Object lpm406x1cb791ec(Object obj) {
        setLpmobileState(lpMobileState.lpSTATE_ALARM);
        getLpdataBinding().rbRingtones.setChecked(false);
        getLpdataBinding().rbAlarm.setChecked(true);
        getLpdataBinding().rbNotification.setChecked(false);
        return null;
    }

    public Object lpm407x35b8e38b(Object obj) {
        setLpmobileState(lpMobileState.lpSTATE_NOTIFICATION);
        getLpdataBinding().rbRingtones.setChecked(false);
        getLpdataBinding().rbAlarm.setChecked(false);
        getLpdataBinding().rbNotification.setChecked(true);
        return null;
    }

    public Object lpm408x4eba352a(Object obj) {
        getLponSetClick().invoke(getMobileState());
        dismiss();
        return null;
    }

    public Object lpm409x67bb86c9(Object obj) {
        dismiss();
        return null;
    }
}
