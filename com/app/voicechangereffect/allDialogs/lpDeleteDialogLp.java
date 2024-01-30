package com.app.voicechangereffect.allDialogs;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseDialog;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.databinding.DeleteDialogBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class lpDeleteDialogLp extends lpBaseDialog<DeleteDialogBinding> {
    private Activity lpact;
    private final Function0 lpfunction0;

    @Override
    public int getDialogView() {
        return R.layout.delete_dialog;
    }

    public Activity getLpact() {
        return this.lpact;
    }

    public void setLpact(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.lpact = activity;
    }

    public Function0 getLpfunction0() {
        return this.lpfunction0;
    }

    public lpDeleteDialogLp(Activity activity, boolean z, Function0<Unit> function0) {
        super(activity, z);
        this.lpact = activity;
        this.lpfunction0 = function0;
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
        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpDeleteDialogLp.this.lpm394x2e670672(obj));
        lpTapClick.lptap(getLpdataBinding().tvDelete, obj -> lpDeleteDialogLp.this.lpm395xf572ed73(obj));

    }

    public Object lpm394x2e670672(Object obj) {
        dismiss();
        return null;
    }

    public Object lpm395xf572ed73(Object obj) {
        getLpfunction0().invoke();
        dismiss();
        return null;
    }
}
