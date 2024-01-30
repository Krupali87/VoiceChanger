package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.adapters.lpEffectAdapter;
import com.app.voicechangereffect.adapters.lpEffectVoiceAdapter;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allDialogs.lpDownloadDialog;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpFileMethods;
import com.app.voicechangereffect.databinding.ActivityEffectChangeBinding;
import com.app.voicechangereffect.getApiData.allModel.lpEffectModel;
import com.app.voicechangereffect.getApiData.allModel.lpTypeEffectModel;
import com.app.voicechangereffect.lpChangeEffectsModule;
import com.app.voicechangereffect.lpFilenameUtils;
import com.app.voicechangereffect.lpMediaPlayerLpDb;
import com.app.voicechangereffect.viewModel.lpChangeEffectViewModel;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class lpChangeEffectActivity extends lpBaseActivity<lpChangeEffectViewModel, ActivityEffectChangeBinding> implements LifecycleOwner {
    public static lpEffectModel lpeffectModel;
    private AudioManager lpaudioManager;
    public lpChangeEffectsModule lpchangeEffectsModule;
    private lpDownloadDialog lpdownloadDialog;
    private lpChangeEffectActivity lpeffectActivity;
    public lpEffectAdapter lpeffectAdapter;
    private boolean lpinitZView;
    private int lpintCurrPos;
    public boolean lpisMutes;
    public boolean lpisPlayAudio;
    public String lpkeyScreen = "";
    private List<String> lplistEffectName = new ArrayList();
    private Handler lpmHandler;
    private Runnable lpmRunnable;
    private SharedPreferences lppreferences;
    public static final Companion lpCompanion = new Companion(null);
    public static String lppath = "";
    public SeekBar seekBar;
   public lpMediaPlayerLpDb dBMedia;


    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public Class<lpChangeEffectViewModel> createViewModel() {
        return lpChangeEffectViewModel.class;
    }

    @Override
    public int getContent() {
        this.lpeffectActivity = this;
        return R.layout.activity_effect_change;
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    public lpDownloadDialog getLpdownloadDialog() {
        return this.lpdownloadDialog;
    }

    public void setLpdownloadDialog(lpDownloadDialog lpdownloaddialog) {
        this.lpdownloadDialog = lpdownloaddialog;
    }



    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final lpEffectModel getEffectModelSelected() {
            return lpChangeEffectActivity.lpeffectModel;
        }

        public final void setEffectModelSelected(lpEffectModel lpeffectmodel) {
            lpChangeEffectActivity.lpeffectModel = lpeffectmodel;
        }

        public final String getPath() {
            return lpChangeEffectActivity.lppath;
        }

        public final void setPath(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            lpChangeEffectActivity.lppath = str;
        }
    }

    @Override
    public void lpmainView() {
        Log.e("VoiceChanger", "ChangeEffectAct_onCreate");
        this.lppreferences = getSharedPreferences("MY_PRE", 0);
        this.lpinitZView = true;
        getBindingData().toolbar.ivDone.setVisibility(View.VISIBLE);
        getBindingData().toolbar.tvTitle.setText(getString(R.string.voice_effects));
        seekBar = getBindingData().playerView.seekbar;
        this.lpmHandler = new Handler();
        if (lpeffectModel == null) {
            String string = getString(R.string.normal);
            Log.e("eee---", "initView: string : " + string);
            lpeffectModel = new lpEffectModel(0, string, "normal", 0, 0, 0, true);
        }
        RecyclerView recyclerView = getBindingData().rvAudioEffect;
        recyclerView.setHasFixedSize(true);
        lpEffectAdapter lpeffectadapter = new lpEffectAdapter(this, new ArrayList(), new Function2<lpTypeEffectModel, Integer, Unit>() { // from class: com.app.voicechangereffect.activity.lpChangeEffectActivity.1
            @Override
            public Unit invoke(lpTypeEffectModel data, Integer num) {
                Intrinsics.checkNotNullParameter(data, "data");
               getBindingData().viewPager.setCurrentItem(num.intValue());
                return null;
            }
        });
        this.lpeffectAdapter = lpeffectadapter;
        recyclerView.setAdapter(lpeffectadapter);
        this.lpkeyScreen = String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS()));
        Log.e("eee----", "mainView: keyScreen :  " + this.lpkeyScreen);
        if (String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE())).length() > 0) {
            lppath = String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
            lpChangeEffectsModule lpchangeeffectsmodule = new lpChangeEffectsModule(this);
            this.lpchangeEffectsModule = lpchangeeffectsmodule;
            lpchangeeffectsmodule.createOutputDir(this);
            lpChangeEffectsModule lpchangeeffectsmodule2 = this.lpchangeEffectsModule;
            if (lpchangeeffectsmodule2 != null) {
                lpchangeeffectsmodule2.setPath(lppath);
            }
            lpChangeEffectsModule lpchangeeffectsmodule3 = this.lpchangeEffectsModule;
            if (lpchangeeffectsmodule3 != null) {
                lpchangeeffectsmodule3.createDBMedia();
            }
        }
        try {
            JSONArray jSONArray = new JSONArray(lpAppConstant.lpAPP_CONSTANT.getVoiceEffect(this));
            int length = jSONArray.length();
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                lpChangeEffectsModule lpchangeeffectsmodule4 = this.lpchangeEffectsModule;
                if (lpchangeeffectsmodule4 != null) {
                    lpchangeeffectsmodule4.insertEffect(jSONObject.toString());
                }
                List<String> list = this.lplistEffectName;
                String string2 = jSONObject.getString("name");
                Intrinsics.checkNotNullExpressionValue(string2, "jsonObj.getString(\"name\")");
                list.add(string2);
                i = i2;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lpplayEffect(0);
        Object systemService = getSystemService(Context.AUDIO_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.lpaudioManager = (AudioManager) systemService;
    }

    @Override
    public void lpinitViews() {
        lpMediaPlayerLpDb dBMedia;
        getBindingData().toolbar.ivDone.setOnClickListener(view -> {
            String str =lpkeyScreen;
            int hashCode = str.hashCode();
            if (hashCode != 1197439160) {
                Log.e("eee----", "1197439160: ");
                if (hashCode != 1345236640) {
                    if (hashCode == 1511250982 && str.equals("AudioFragment")) {
                        Log.e("eee----", "AudioFragment: ");
                        final Bundle bundle = new Bundle();
                       lpeffectActivity.setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, new Function1<String, Unit>() { // from class: com.app.voicechangereffect.activity.lpChangeEffectActivity.3.1
                            @Override
                            public Unit invoke(String str2) {
                                String lpnameOrigin;
                               lppauseAudio();
                                StringBuilder sb = new StringBuilder();
                                sb.append(str2);
                                sb.append('_');
                                lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                                sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                                String sb2 = sb.toString();
                                if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                                    Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                                    return null;
                                }
                                String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                                lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
                                Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                                Intrinsics.checkNotNull(lpindexPLaying);
                                bundle.putInt(key_position_effect, lpindexPLaying.intValue());
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                               nextActivity(lpSaveActivity.class, bundle);
                                return null;
                            }
                        }));
                        lpDownloadDialog lpdownloadDialog =getLpdownloadDialog();
                        if (lpdownloadDialog == null) {
                        }
                        lpdownloadDialog.show();
                    }
                } else if (str.equals("RecordActivity")) {
                    final Bundle bundle2 = new Bundle();
                   setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, str2 -> {
                       String lpnameOrigin;
                      lppauseAudio();
                       StringBuilder sb = new StringBuilder();
                       sb.append(str2);
                       sb.append('_');
                       lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                       sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                       String sb2 = sb.toString();
                       if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                           Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                           return null;
                       }
                       Bundle bundle3 = bundle2;
                       String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                       lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
                       Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                       Intrinsics.checkNotNull(lpindexPLaying);
                       bundle3.putInt(key_position_effect, lpindexPLaying.intValue());
                       bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                       bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                       bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
                       bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                      nextActivity(lpSaveActivity.class, bundle3);
                       return null;
                   }));
                    lpDownloadDialog lpdownloadDialog2 =getLpdownloadDialog();
                    if (lpdownloadDialog2 == null) {

                    }
                    lpdownloadDialog2.show();
                }
            } else if (str.equals("TextAudioActivity")) {
                final Bundle bundle3 = new Bundle();
                final lpChangeEffectActivity lpchangeeffectactivity = lpChangeEffectActivity.this;
                lpchangeeffectactivity.setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, str2 -> {
                    String lpnameOrigin;
                    Log.e("vv---", "invoke: str :: " + str2);
                    lpchangeeffectactivity.lppauseAudio();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append('_');
                    lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                    sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                    String sb2 = sb.toString();
                    if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                        lpChangeEffectActivity lpchangeeffectactivity2 = lpchangeeffectactivity;
                        Toast.makeText(lpchangeeffectactivity2, lpchangeeffectactivity2.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    Bundle bundle4 = bundle3;
                    String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                    lpChangeEffectsModule lpchangeeffectsmodule = lpchangeeffectactivity.lpchangeEffectsModule;
                    Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                    Intrinsics.checkNotNull(lpindexPLaying);
                    bundle4.putInt(key_position_effect, lpindexPLaying.intValue());
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), lpchangeeffectactivity.getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(), lpchangeeffectactivity.getBindingData().playerView.exoDuration.getText().toString());
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                    lpchangeeffectactivity.nextActivity(lpSaveActivity.class, bundle3);
                    return null;
                }));
                lpDownloadDialog lpdownloadDialog3 =getLpdownloadDialog();
                if (lpdownloadDialog3 == null) {
                }
                lpdownloadDialog3.show();
            }
            final Bundle bundle4 = new Bundle();
           setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, it -> {
               Intrinsics.checkNotNullParameter(it, "it");
              lppauseAudio();
               StringBuilder sb = new StringBuilder();
               sb.append(it);
               sb.append('_');
               sb.append((lpChangeEffectActivity.lpCompanion.getEffectModelSelected() == null || it == null) ? null : StringsKt.replace(it, " ", "", false));
               String sb2 = sb.toString();
               if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                   Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                   return null;
               }
               Bundle bundle5 = bundle4;
               String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
               lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
               Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
               Intrinsics.checkNotNull(lpindexPLaying);
               bundle5.putInt(key_position_effect, lpindexPLaying.intValue());
               bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
               bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
               bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
               bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
              nextActivity(lpSaveActivity.class, bundle4);
               return null;
           }));
        });

        lpTapClick.lptap(getBindingData().toolbar.ivDone, (Function1<View, Unit>) view -> {
            String str =lpkeyScreen;
            int hashCode = str.hashCode();
            if (hashCode != 1197439160) {
                Log.e("eee----", "1197439160: ");
                if (hashCode != 1345236640) {
                    if (hashCode == 1511250982 && str.equals("AudioFragment")) {
                        Log.e("eee----", "AudioFragment: ");
                        final Bundle bundle = new Bundle();
                       lpeffectActivity.setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, new Function1<String, Unit>() {
                            public Unit invoke(String str2) {
                                String lpnameOrigin;
                               lppauseAudio();
                                StringBuilder sb = new StringBuilder();
                                sb.append(str2);
                                sb.append('_');
                                lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                                sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                                String sb2 = sb.toString();
                                if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                                    Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                                    return null;
                                }
                                String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                                lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
                                Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                                Intrinsics.checkNotNull(lpindexPLaying);
                                bundle.putInt(key_position_effect, lpindexPLaying.intValue());
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
                                bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                               nextActivity(lpSaveActivity.class, bundle);
                                return null;
                            }
                        }));
                        lpDownloadDialog lpdownloadDialog =getLpdownloadDialog();
                        if (lpdownloadDialog == null) {
                            return null;
                        }
                        lpdownloadDialog.show();
                        return null;
                    }
                } else if (str.equals("RecordActivity")) {
                    final Bundle bundle2 = new Bundle();
                   setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, new Function1<String, Unit>() { // from class: com.app.voicechangereffect.activity.lpChangeEffectActivity.3.2
                        @Override
                        public Unit invoke(String str2) {
                            String lpnameOrigin;
                           lppauseAudio();
                            StringBuilder sb = new StringBuilder();
                            sb.append(str2);
                            sb.append('_');
                            lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                            sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                            String sb2 = sb.toString();
                            if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                                Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                                return null;
                            }
                            Bundle bundle3 = bundle2;
                            String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                            lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
                            Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                            Intrinsics.checkNotNull(lpindexPLaying);
                            bundle3.putInt(key_position_effect, lpindexPLaying.intValue());
                            bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                            bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                            bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
                            bundle2.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                           nextActivity(lpSaveActivity.class, bundle3);
                            return null;
                        }
                    }));
                    lpDownloadDialog lpdownloadDialog2 =getLpdownloadDialog();
                    if (lpdownloadDialog2 == null) {
                        return null;
                    }
                    lpdownloadDialog2.show();
                }
            } else if (str.equals("TextAudioActivity")) {
                final Bundle bundle3 = new Bundle();
                final lpChangeEffectActivity lpchangeeffectactivity = lpChangeEffectActivity.this;
                lpchangeeffectactivity.setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, str2 -> {
                    String lpnameOrigin;
                    Log.e("vv---", "invoke: str :: " + str2);
                    lpchangeeffectactivity.lppauseAudio();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append('_');
                    lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
                    sb.append((Object) ((effectModelSelected == null || (lpnameOrigin = effectModelSelected.getLpnameOrigin()) == null) ? null : StringsKt.replace(lpnameOrigin, " ", "", false)));
                    String sb2 = sb.toString();
                    if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                        lpChangeEffectActivity lpchangeeffectactivity2 = lpchangeeffectactivity;
                        Toast.makeText(lpchangeeffectactivity2, lpchangeeffectactivity2.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                        return null;
                    }
                    Bundle bundle4 = bundle3;
                    String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                    lpChangeEffectsModule lpchangeeffectsmodule = lpchangeeffectactivity.lpchangeEffectsModule;
                    Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                    Intrinsics.checkNotNull(lpindexPLaying);
                    bundle4.putInt(key_position_effect, lpindexPLaying.intValue());
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), lpchangeeffectactivity.getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(), lpchangeeffectactivity.getBindingData().playerView.exoDuration.getText().toString());
                    bundle3.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
                    lpchangeeffectactivity.nextActivity(lpSaveActivity.class, bundle3);
                    return null;
                }));
                lpDownloadDialog lpdownloadDialog3 =getLpdownloadDialog();
                if (lpdownloadDialog3 == null) {
                    return null;
                }
                lpdownloadDialog3.show();
            }
            final Bundle bundle4 = new Bundle();
            setLpdownloadDialog(new lpDownloadDialog(lpChangeEffectActivity.this, false, it -> {
                Intrinsics.checkNotNullParameter(it, "it");
               lppauseAudio();
                StringBuilder sb = new StringBuilder();
                sb.append(it);
                sb.append('_');
                sb.append((lpChangeEffectActivity.lpCompanion.getEffectModelSelected() == null || it == null) ? null : StringsKt.replace(it, " ", "", false));
                String sb2 = sb.toString();
                if (lpFilenameUtils.getBaseName(lpChangeEffectActivity.lpCompanion.getPath()).equals(sb2)) {
                    Toast.makeText(lpChangeEffectActivity.this.lpeffectActivity,lpeffectActivity.getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                    return null;
                }
                Bundle bundle5 = bundle4;
                String key_position_effect = lpAppConstant.lpAPP_CONSTANT.getKEY_POSITION_EFFECT();
                lpChangeEffectsModule lpchangeeffectsmodule =lpchangeEffectsModule;
                Integer lpindexPLaying = lpchangeeffectsmodule != null ? lpchangeeffectsmodule.getLpindexPLaying() : null;
                Intrinsics.checkNotNull(lpindexPLaying);
                bundle5.putInt(key_position_effect, lpindexPLaying.intValue());
                bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(),getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()));
                bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), sb2);
                bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE(),getBindingData().playerView.exoDuration.getText().toString());
                bundle4.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SIZE_VOICE(),lpbyteToMB(new File(lpChangeEffectActivity.lpCompanion.getPath()).length()));
               nextActivity(lpSaveActivity.class, bundle4);
                return null;
            }));
            return null;
        });
        lpTapClick.lptap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
           onBackPressed();
            return null;
        });

        lpgetMViewModel().getTypeEffects(this);
        lpgetMViewModel().getLiveType().observe(this, list -> {
            lpEffectAdapter lpeffectadapter;
            if (list == null || (lpeffectadapter =lpeffectActivity.lpeffectAdapter) == null) {
                return;
            }
            lpeffectadapter.addList(list);
        });
        FragmentManager supportFragmentManager = getFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        lpEffectVoiceAdapter lpeffectvoiceadapter = new lpEffectVoiceAdapter(getSupportFragmentManager());
        ViewPager viewPager = getBindingData().viewPager;
        viewPager.setAdapter(lpeffectvoiceadapter);
        getBindingData().viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
               getBindingData().rvAudioEffect.smoothScrollToPosition(i);
                lpEffectAdapter lpeffectadapter =lpeffectAdapter;
                if (lpeffectadapter != null) {
                    lpeffectadapter.lpselItemPosition(i);
                }
                PagerAdapter adapter =getBindingData().viewPager.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                if (i == 0) {
                   getBindingData().viewPager.setCurrentItem(0);
                } else if (i == 1) {
                   getBindingData().viewPager.setCurrentItem(1);
                } else if (i == 2) {
                   getBindingData().viewPager.setCurrentItem(2);
                } else if (i == 3) {
                   getBindingData().viewPager.setCurrentItem(3);
                } else if (i == 4) {
                   getBindingData().viewPager.setCurrentItem(4);
                }
            }
        });
        lpTapClick.lptap(getBindingData().playerView.exoVolume, (Function1<View, Unit>) view -> {
            lpChangeEffectActivity lpchangeeffectactivity = lpChangeEffectActivity.this;
            lpchangeeffectactivity.lpisMutes = !lpchangeeffectactivity.lpisMutes;
            if (lpChangeEffectActivity.this.lpisMutes) {
               getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_mute);
               lpmute();
                return null;
            }
           getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_volume);
           lpunMute();
            return null;
        });

        lpTapClick.lptap(getBindingData().playerView.exoPlay, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                lpChangeEffectActivity lpchangeeffectactivity = lpChangeEffectActivity.this;
                lpchangeeffectactivity.lpisPlayAudio = !lpchangeeffectactivity.lpisPlayAudio;
                if (lpChangeEffectActivity.this.lpisPlayAudio) {
                    lpChangeEffectActivity.this.lppauseAudio();
                   getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_play_media);
                    return null;
                }
                lpChangeEffectActivity.this.lpplayAudio();
               getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_pause_effect);
                return null;
            }
        });


        TextView textView = getBindingData().playerView.exoDuration;
        lpChangeEffectsModule lpchangeeffectsmodule = this.lpchangeEffectsModule;
        textView.setText(lpFileMethods.milliSecFormat(((lpchangeeffectsmodule == null || (dBMedia = lpchangeeffectsmodule.getDBMedia()) == null) ? null : Long.valueOf(dBMedia.lpgetSkintDuration())) * 1000));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                try {
                    lpMediaPlayerLpDb dBMedia2;
                    lpMediaPlayerLpDb dBMedia3;
                    Log.e("@@123","test for seekbar");
                    if (b) {
                        lpChangeEffectsModule changeEffectsModule2 = lpChangeEffectActivity.this.lpchangeEffectsModule;
                        if (changeEffectsModule2 != null && (dBMedia3 = changeEffectsModule2.getDBMedia()) != null) {
                            dBMedia3.lptoSeek(i);
                        }

                        seekBar.setProgress(i);
                    }

                    lpChangeEffectsModule changeEffectsModule3 = lpChangeEffectActivity.this.lpchangeEffectsModule;
                    Long valueOf = (changeEffectsModule3 == null || (dBMedia2 = changeEffectsModule3.getDBMedia()) == null) ? null : Long.valueOf(dBMedia2.lpgetSkintDuration());
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.longValue() == i) {
                        lpChangeEffectActivity.this.lpisPlayAudio = true;
                        lpChangeEffectActivity.this.getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_play_media);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void onRestart() {
        super.onRestart();
    }


    public void lpmute() {
        AudioManager audioManager = this.lpaudioManager;
        if (audioManager != null) {
            audioManager.setStreamMute(3, true);
        }
    }

    public final void lpunMute() {
        AudioManager audioManager = this.lpaudioManager;
        if (audioManager != null) {
            audioManager.setStreamMute(3, false);
        }
    }

    public final void lpplayAudio() {
        lpMediaPlayerLpDb dBMedia;
        lpMediaPlayerLpDb dBMedia2;
        lpChangeEffectsModule lpchangeeffectsmodule = this.lpchangeEffectsModule;
        if ((lpchangeeffectsmodule == null ? null : lpchangeeffectsmodule.getDBMedia()) != null) {
            lpChangeEffectsModule lpchangeeffectsmodule2 = this.lpchangeEffectsModule;
            if (lpchangeeffectsmodule2 != null && (dBMedia2 = lpchangeeffectsmodule2.getDBMedia()) != null) {
                dBMedia2.lpaudioStart();
            }
            lpChangeEffectsModule lpchangeeffectsmodule3 = this.lpchangeEffectsModule;
            if ((lpchangeeffectsmodule3 == null || (dBMedia = lpchangeeffectsmodule3.getDBMedia()) == null || dBMedia.lpgetSkintCurrPosition() != 0) ? false : true) {
                getBindingData().playerView.exoPosition1.setText("00:00");
            }
            lpupdateSeekbar();
        }
    }

    public  void lppauseAudio() {
        lpChangeEffectsModule lpchangeeffectsmodule;
        lpMediaPlayerLpDb dBMedia;
        lpChangeEffectsModule lpchangeeffectsmodule2 = this.lpchangeEffectsModule;
        if ((lpchangeeffectsmodule2 == null ? null : lpchangeeffectsmodule2.getDBMedia()) == null || (lpchangeeffectsmodule = this.lpchangeEffectsModule) == null || (dBMedia = lpchangeeffectsmodule.getDBMedia()) == null) {
            return;
        }
        dBMedia.lpaudioPause();
    }

    public  void lpupdateSeekbar() {

        lpMediaPlayerLpDb dBMedia;
        try {
            Log.e("@@123","seekbar not update");
            lpChangeEffectsModule changeEffectsModule = this.lpchangeEffectsModule;
            Integer valueOf = (changeEffectsModule == null || (dBMedia = changeEffectsModule.getDBMedia()) == null) ? null : Integer.valueOf(dBMedia.lpgetSkintCurrPosition());
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            this.lpintCurrPos = intValue;

            if (intValue != -1) {
               seekBar.setProgress(this.lpintCurrPos);
                getBindingData().playerView.exoPosition1.setText(lpFileMethods.milliSecFormat(this.lpintCurrPos * 1000));
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        lpChangeEffectActivity this$0 = lpChangeEffectActivity.this.lpeffectActivity;
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        lpChangeEffectActivity.this.lpeffectActivity.lpupdateSeekbar();
                    }
                };
                this.lpmRunnable = runnable;
                Handler handler = this.lpmHandler;
                if (handler != null) {
                   Intrinsics.checkNotNull(runnable);
                    handler.postDelayed(this.lpmRunnable, 500L);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lpplayEffect(int i) {

        this.lpisPlayAudio = false;
        try {
            Log.e("@@123","main not working");
            lpChangeEffectsModule lpchangeeffectsmodule2 = this.lpchangeEffectsModule;
            Integer valueOf = (lpchangeeffectsmodule2 == null || (dBMedia = lpchangeeffectsmodule2.getDBMedia()) == null) ? null : Integer.valueOf(dBMedia.lpgetSkintCurrPosition());
            Intrinsics.checkNotNull(valueOf);
            int duration = dBMedia.lpgetSkintDuration();
            seekBar.setMax(duration);
            lpChangeEffectsModule changeEffectsModule2 = this.lpchangeEffectsModule;
            if (changeEffectsModule2 != null) {
                changeEffectsModule2.effectPlay(i);
            }
           lpupdateSeekbar();
            getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_pause_effect);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, getString(R.string.error_file), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
        super.onStop();
        Log.e("VoiceChanger", "ChangeEffectAct_onStop");
        lppauseAudio();
        this.lpisPlayAudio = true;
        getBindingData().playerView.exoPlay.setImageResource(R.drawable.ic_play_media);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
        super.onResume();
        Log.e("VoiceChanger", "ChangeEffectAct_onResume");
        if (this.lpinitZView) {
            lpplayAudio();
            this.lpinitZView = false;
        }
        if (this.lpisMutes) {
            getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_mute);
            lpmute();
            return;
        }
        getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_volume);
        lpunMute();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
        super.onDestroy();
        lpMediaPlayerLpDb dBMedia;
        Log.e("VoiceChanger", "ChangeEffectAct_onDestroy");

        lpChangeEffectsModule lpchangeeffectsmodule = this.lpchangeEffectsModule;
        if (lpchangeeffectsmodule != null && (dBMedia = lpchangeeffectsmodule.getDBMedia()) != null) {
            dBMedia.lpaudioPause();
        }
        lppath = "";
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
        super.onPause();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        lpMediaPlayerLpDb dBMedia;

        Log.e("VoiceChanger", "ChangeEffectAct_onBack");
        itemReset();
        finish();
        try {
            lpChangeEffectsModule lpchangeeffectsmodule = this.lpchangeEffectsModule;
            if (lpchangeeffectsmodule != null && (dBMedia = lpchangeeffectsmodule.getDBMedia()) != null) {
                dBMedia.lpaudioPause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Intrinsics.checkNotNull(keyEvent);
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 24) {
            if (action != 0) {
                return true;
            }
            AudioManager audioManager = this.lpaudioManager;
            if (audioManager != null) {
                Integer.valueOf(audioManager.getStreamVolume(3));
            }
            AudioManager audioManager2 = this.lpaudioManager;
            if (audioManager2 != null) {
                audioManager2.adjustStreamVolume(3, 1, 5);
            }
            getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_volume);
            this.lpisMutes = false;
            return true;
        } else if (keyCode != 25) {
            return super.dispatchKeyEvent(keyEvent);
        } else {
            if (action != 0) {
                return true;
            }
            AudioManager audioManager3 = this.lpaudioManager;
            Integer valueOf = audioManager3 == null ? null : Integer.valueOf(audioManager3.getStreamVolume(3));
            AudioManager audioManager4 = this.lpaudioManager;
            if (audioManager4 != null) {
                audioManager4.adjustStreamVolume(3, -1, 5);
            }
            if (valueOf != null && valueOf.intValue() == 0) {
                getBindingData().playerView.exoVolume.setImageResource(R.drawable.ic_mute);
                this.lpisMutes = true;
            }
            return true;
        }
    }

    private void itemReset() {
        lpeffectModel = null;
        String string = getString(R.string.normal);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.normal)");
        lpeffectModel = new lpEffectModel(0, string, "normal", 0, 0, 0, true);
    }

    public String lpbyteToMB(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f = (float) j;
        if (f < 1048576.0f) {
            return Intrinsics.stringPlus(decimalFormat.format(f / 1024.0f), "KB");
        }
        if (f < 1.07374182E9f) {
            return Intrinsics.stringPlus(decimalFormat.format(f / 1048576.0f), "MB");
        }
        return f < 1.09951163E12f ? Intrinsics.stringPlus(decimalFormat.format(f / 1.07374182E9f), "GB") : "";
    }
}
