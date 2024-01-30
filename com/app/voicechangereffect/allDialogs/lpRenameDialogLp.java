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
import com.app.voicechangereffect.databinding.RenameDialogBinding;
import com.app.voicechangereffect.lpFilenameUtils;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;


public final class lpRenameDialogLp extends lpBaseDialog<RenameDialogBinding> {
    private final Activity lpact;
    private final Function2 lpfunction2;
    private final String lpstrName;
    private String lpstrTypeEffect;

    @Override
    public int getDialogView() {
        return R.layout.rename_dialog;
    }

    public lpRenameDialogLp(Activity activity, boolean z, String str, Function2 function2) {
        super(activity, z);
        this.lpact = activity;
        this.lpstrName = str;
        this.lpfunction2 = function2;
        this.lpstrTypeEffect = "";
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
        String[] split = lpFilenameUtils.getBaseName(this.lpstrName).split("_");
        String str = split[0];
        this.lpstrTypeEffect = split[1];
        getLpdataBinding().input.setText(str);
    }

    @Override
    public void lpbindId() {

        lpTapClick.lptap(getLpdataBinding().tvCancel, obj -> lpRenameDialogLp.this.m400x5a76fdc5(obj));

        lpTapClick.lptap(getLpdataBinding().tvSet, obj -> lpRenameDialogLp.this.m401x2182e4c6(obj));

        lpTapClick.lptap(getLpdataBinding().ivDel, obj -> lpRenameDialogLp.this.m402xe88ecbc7(obj));
    }

    public Object m400x5a76fdc5(Object obj) {
        Log.e("rename---", "invoke: tvCancel :: ");
        dismiss();
        return Unit.INSTANCE;
    }

    public Object m401x2182e4c6(Object obj) {
        String obj2 = StringsKt.trim(getLpdataBinding().input.getText().toString()).toString();
        if (obj2.equals("")) {
            Activity activity = this.lpact;
            Toast.makeText(activity, activity.getResources().getText(R.string.please_enter_text), Toast.LENGTH_SHORT).show();
            return null;
        } else if (lpAppDataException.lpgetSpecialChar(obj2)) {
            Activity activity2 = this.lpact;
            Toast.makeText(activity2, activity2.getResources().getText(R.string.there_is_a_special_character), Toast.LENGTH_SHORT).show();
            return null;
        } else {
            this.lpfunction2.invoke(obj2, this.lpstrTypeEffect);
            return null;
        }
    }

    public Object m402xe88ecbc7(Object obj) {
        getLpdataBinding().input.setText("");
        return Unit.INSTANCE;
    }
}
