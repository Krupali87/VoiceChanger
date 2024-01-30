package com.app.voicechangereffect.allBaseAct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.app.voicechangereffect.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public abstract class lpBaseBottomSheetDialog<DB extends ViewDataBinding> extends BottomSheetDialogFragment {
    private boolean lpcanAble;
    public DB lpdataBinding;

    public abstract void bindView();

    public abstract int getContentView();

    public abstract void initView();

    @Override
    public int getTheme() {
        return R.style.BaseBottomSheetDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public lpBaseBottomSheetDialog(Activity activity, boolean z) {
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

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        setLpdataBinding(DataBindingUtil.inflate(LayoutInflater.from(getContext()), getContentView(), null, false));
        dialog.setContentView(getLpdataBinding().getRoot());
        setCancelable(this.lpcanAble);
        initView();
        bindView();
    }
}
