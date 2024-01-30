package com.app.voicechangereffect.allDialogs;

import android.app.Activity;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseDialog;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.databinding.PremissionRingtoneDialogBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public final class lpRingtonesPermissionDialogLp extends lpBaseDialog<PremissionRingtoneDialogBinding> {
    private Activity lpacts;
    private final Function0 lpallowClick;

    @Override
    public void lpinitViews() {
    }

    @Override
    public int getDialogView() {
        return R.layout.premission_ringtone_dialog;
    }

    public Activity getLpacts() {
        return this.lpacts;
    }

    public void setLpacts(Activity activity) {
        this.lpacts = activity;
    }

    public Function0 getLpallowClick() {
        return this.lpallowClick;
    }

    public lpRingtonesPermissionDialogLp(Activity activity, boolean z, Function0<Unit> function0) {
        super(activity, z);
        this.lpacts = activity;
        this.lpallowClick = function0;
    }

    @Override
    public void lpsetLanguage() {
        lpSetLanguage.lpsetLocale(this.lpacts);
    }

    @Override
    public void lpbindId() {

        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpRingtonesPermissionDialogLp.this.lpm403x938269e9(obj));

        lpTapClick.lptap(getLpdataBinding().tvAllow, obj -> lpRingtonesPermissionDialogLp.this.lpm404xd923ac88(obj));
    }

    public Object lpm403x938269e9(Object obj) {
        dismiss();
        return null;
    }

    public Object lpm404xd923ac88(Object obj) {
        getLpallowClick().invoke();
        dismiss();
        return null;
    }
}
