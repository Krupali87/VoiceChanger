package com.app.voicechangereffect.allBaseAct;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.app.voicechangereffect.R;


public abstract class lpBaseDialog<DB extends ViewDataBinding> extends Dialog {
    private boolean lpcanAble;
    public DB lpdataBinding;

    public abstract int getDialogView();

    public abstract void lpbindId();

    public abstract void lpinitViews();

    public abstract void lpsetLanguage();

    public lpBaseDialog(Activity activity, boolean z) {
        super(activity, R.style.BottomSheetDialogTheme);
        this.lpcanAble = z;
    }

    public final DB getLpdataBinding() {
        DB db = this.lpdataBinding;
        if (db != null) {
            return db;
        }
        return null;
    }

    public final void setLpdataBinding(DB db) {
        this.lpdataBinding = db;
    }


    @Override
    public void onCreate(Bundle bundle) {
        lpsetLanguage();
        super.onCreate(bundle);
        setLpdataBinding(DataBindingUtil.inflate(LayoutInflater.from(getContext()), getDialogView(), null, false));
        setContentView(getLpdataBinding().getRoot());
        Window window = getWindow();
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        setCancelable(this.lpcanAble);
        lpinitViews();
        lpbindId();
    }
}
