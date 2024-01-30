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
import com.app.voicechangereffect.databinding.AudioNotSaveDialogBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public final class lpNotRecordedDialogLp extends lpBaseDialog<AudioNotSaveDialogBinding> {
    private final Activity lpact;
    private final Function0 lpfunction0;
    private String lpstrContent;
    private String lpstrPos;

    @Override
    public int getDialogView() {
        return R.layout.audio_not_save_dialog;
    }

    public Function0 getLpfunction0() {
        return this.lpfunction0;
    }

    public lpNotRecordedDialogLp(String str, String str2, Activity activity, boolean z, Function0<Unit> function0) {
        super(activity, z);
        this.lpstrContent = str;
        this.lpstrPos = str2;
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
        getLpdataBinding().tvContent.setText(this.lpstrContent);
        getLpdataBinding().tvExit.setText(this.lpstrPos);
    }

    @Override
    public void lpbindId() {
        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpNotRecordedDialogLp.this.lpm398xb53a7f8c(obj));

        lpTapClick.lptap(getLpdataBinding().tvExit, obj -> lpNotRecordedDialogLp.this.lpm399xce3bd12b(obj));

    }

    public Object lpm398xb53a7f8c(Object obj) {
        dismiss();
        return null;
    }

    public Object lpm399xce3bd12b(Object obj) {
        getLpfunction0().invoke();
        dismiss();
        return null;
    }
}
