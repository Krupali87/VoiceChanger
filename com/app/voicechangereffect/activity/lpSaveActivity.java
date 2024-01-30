package com.app.voicechangereffect.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpFileMethods;
import com.app.voicechangereffect.databinding.ActivitySaveBinding;
import com.app.voicechangereffect.lpChangeEffectsModule;
import com.app.voicechangereffect.tapListener.lpCustomTapListener;
import com.app.voicechangereffect.viewModel.lpSaveViewModel;
import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class lpSaveActivity extends lpBaseActivity<lpSaveViewModel, ActivitySaveBinding> {

    private lpChangeEffectsModule lpmodule;
    private int lppos;
    public String lpstrDuration = "";
    public String lpstrFileName = "";
    public String lpstrSize = "";

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public int getContent() {
        return R.layout.activity_save;
    }

    @Override
    public Class<lpSaveViewModel> createViewModel() {
        return lpSaveViewModel.class;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("VoiceChanger", "SaveAct_onBack");
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void lpmainView() {
        Log.e("VoiceChanger", "SaveAct_onCreate");
        try {
            if (getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()) != null) {
                lpChangeEffectsModule lpchangeeffectsmodule = new lpChangeEffectsModule(this);
                this.lpmodule = lpchangeeffectsmodule;
                lpchangeeffectsmodule.createOutputDir(this);
                lpChangeEffectsModule lpchangeeffectsmodule2 = this.lpmodule;
                if (lpchangeeffectsmodule2 != null) {
                    lpchangeeffectsmodule2.setPath(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                }
                lpChangeEffectsModule lpchangeeffectsmodule3 = this.lpmodule;
                if (lpchangeeffectsmodule3 != null) {
                    lpchangeeffectsmodule3.createDBMedia();
                }
                try {
                    JSONArray jSONArray = new JSONArray(lpAppConstant.lpAPP_CONSTANT.getVoiceEffect(this));
                    int length = jSONArray.length();
                    int i = 0;
                    while (i < length) {
                        int i2 = i + 1;
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        lpChangeEffectsModule lpchangeeffectsmodule4 = this.lpmodule;
                        if (lpchangeeffectsmodule4 != null) {
                            lpchangeeffectsmodule4.insertEffect(jSONObject.toString());
                        }
                        i = i2;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.lppos = getIntent().getIntExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT(), 0) != -1 ? getIntent().getIntExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT(), 0) : 0;
            this.lpstrFileName = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT())) : "";
            this.lpstrDuration = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE())) : "";
            this.lpstrSize = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE())) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void lpinitViews() {
        lpChangeEffectsModule lpchangeeffectsmodule = this.lpmodule;
        Log.e("mk", "module1:-" + lpchangeeffectsmodule);
        if (lpchangeeffectsmodule != null) {
            lpchangeeffectsmodule.saveTheEffects(this.lppos, this.lpstrFileName, () -> {
                lpSaveActivity.this.getBindingData().preview.setVisibility(View.VISIBLE);
                lpSaveActivity.this.getBindingData().tvStatus.setText(lpSaveActivity.this.getString(R.string.successfully_saved_the_audio_file));
            });
        }
        getBindingData().backbtn.setOnClickListener(new lpCustomTapListener() {
            @Override
            public void onTap(View view) {
                lpSaveActivity.this.finish();
            }
        });
        lpTapClick.lptap(getBindingData().preview, (Function1<View, Unit>) view -> {
            Bundle bundle = new Bundle();
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), new File(lpFileMethods.lpgetDirectory(lpSaveActivity.this), Intrinsics.stringPlus(lpSaveActivity.this.lpstrFileName, ".wav")).getPath());
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), lpSaveActivity.this.lpstrFileName);
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(), lpSaveActivity.this.lpstrDuration);
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(), lpSaveActivity.this.lpstrSize);
            lpSaveActivity.this.nextActivity(lpMusicPlayerActivity.class, bundle);
            lpSaveActivity.this.finish();
            return null;
        });
    }
}
