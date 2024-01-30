package com.app.voicechangereffect.allDialogs;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseDialog;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.databinding.DownloadDialogBinding;
import java.util.Calendar;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


public final class lpDownloadDialog extends lpBaseDialog<DownloadDialogBinding> {
    private final Activity lpact;
    String lpfileName;
    private final Function1 lpfunction1;

    @Override
    public int getDialogView() {
        return R.layout.download_dialog;
    }

    public lpDownloadDialog(Activity activity, boolean z, Function1<? super String, Unit> onOk) {
        super(activity, z);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(onOk, "onOk");
        this.lpact = activity;
        this.lpfunction1 = onOk;
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
        getLpdataBinding().input.setText(String.valueOf(Calendar.getInstance().getTime().getTime()));
        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpDownloadDialog.this.lpm396xc54083cf(obj));
        lpTapClick.lptap(getLpdataBinding().tvSet, obj -> lpDownloadDialog.this.m397xf8eeae90(obj));
    }

    public Object lpm396xc54083cf(Object obj) {
        dismiss();
        return null;
    }

    public Object m397xf8eeae90(Object obj) {
        String obj2 = StringsKt.trim(getLpdataBinding().input.getText().toString()).toString();
        this.lpfileName = obj2;
        if (Intrinsics.areEqual(obj2, "")) {
            Activity activity = this.lpact;
            Toast.makeText(activity, activity.getResources().getText(R.string.please_enter_text), Toast.LENGTH_SHORT).show();
            return null;
        } else if (lpAppDataException.lpgetSpecialChar(this.lpfileName)) {
            Activity activity2 = this.lpact;
            Toast.makeText(activity2, activity2.getResources().getText(R.string.there_is_a_special_character), Toast.LENGTH_SHORT).show();
            return null;
        } else {
            dismiss();
            Log.e("vv---", "bindId: fileName :: " + this.lpfileName);
            this.lpfunction1.invoke(this.lpfileName);
            dismiss();
            return null;
        }
    }
}
