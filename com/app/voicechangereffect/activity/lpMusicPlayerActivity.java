package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.databinding.ActivityMusicPlayerBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allBaseAct.lpNavigators;
import com.app.voicechangereffect.allDialogs.lpSetRingtoneDialog;
import com.app.voicechangereffect.custUi.constatnt.lpRingtonePermission;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpMobileState;
import com.app.voicechangereffect.lpFilenameUtils;
import com.app.voicechangereffect.viewModel.lpMusicPlayerViewModel;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;


public final class lpMusicPlayerActivity extends lpBaseActivity<lpMusicPlayerViewModel, ActivityMusicPlayerBinding> {

    private float lpcurVolume;
    private ExoPlayer lpplayer;
    private Context lppreviewActivity;
    private boolean lpisMuteAudio = true;
    private String lpstrPath = "";
    private String lpfileName = "";
    private String lpstrDuration = "";
    private Boolean flag_ic = false;

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> lpbasefragment) {
    }

    @Override
    public Class<lpMusicPlayerViewModel> createViewModel() {
        return lpMusicPlayerViewModel.class;
    }

    @Override
    public int getContent() {
        this.lppreviewActivity = this;
        return R.layout.activity_music_player;
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void lpmainView() {
        Log.e("VoiceChanger", "MusicPlayerAct_onCreate");

        lpTapClick.lptap(getBindingData().ivBack, (Function1<View, Unit>) view -> {
            lpMusicPlayerActivity.this.onBackPressed();
            return null;
        });
        Object systemService = getSystemService(Context.AUDIO_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        ((AudioManager) systemService).setStreamMute(3, false);
        try {
            this.lpfileName = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT())) : "";
            this.lpstrPath = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE())) : "";
            Log.d("lpMusicPlayerActivity", "Path received: " + lpstrPath);
            this.lpstrDuration = getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE()) != null ? String.valueOf(getIntent().getStringExtra(lpAppConstant.lpAPP_CONSTANT.getKEY_DURATION_VOICE())) : "";
            this.lpplayer = new SimpleExoPlayer.Builder(this).build();
            ProgressiveMediaSource createMediaSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name))), new DefaultExtractorsFactory()).createMediaSource(MediaItem.fromUri(Uri.parse(new File(this.lpstrPath).getPath())));
            PlayerView playerView = getBindingData().playerView;
            ExoPlayer exoPlayer = this.lpplayer;
            ExoPlayer exoPlayer2 = null;
            if (exoPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                exoPlayer = null;
            }
            playerView.setPlayer(exoPlayer);
            getBindingData().playerView.setKeepScreenOn(true);
            ExoPlayer exoPlayer3 = this.lpplayer;
            if (exoPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            } else {
                exoPlayer2 = exoPlayer3;
            }
            exoPlayer2.prepare(createMediaSource);
            if (this.lpplayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            }
            String str = this.lpfileName;
            String substring = str.substring(StringsKt.lastIndexOf((CharSequence) str, "/", 0, false) + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            ((TextView) getBindingData().playerView.findViewById(R.id.tv_name)).setText(substring);

            getBindingData().playerView.findViewById(R.id.rl_main).setOnClickListener(view -> {
                ((LinearLayout) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.main_LinearLayout)).setVisibility(View.GONE);
                Log.d("akakakakak", "checked it flag_ic if =");
            });

            getBindingData().playerView.findViewById(R.id.menu).setOnClickListener(view -> {
                if (lpMusicPlayerActivity.this.flag_ic.booleanValue()) {
                    lpMusicPlayerActivity.this.flag_ic = false;
                    ((LinearLayout) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.main_LinearLayout)).setVisibility(View.GONE);
                    Log.d("akakakakak", "checked it flag_ic if =");
                    return;
                }
                lpMusicPlayerActivity.this.flag_ic = true;
                ((LinearLayout) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.main_LinearLayout)).setVisibility(View.VISIBLE);
                Log.d("akakakakak", "checked it flag_ic else =");
            });
            new Handler().postDelayed(() -> ((TextView) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.tv_detail)).setText(((Object) ((TextView) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.exo_duration)).getText()) + " | " + ((Object) lpMusicPlayerActivity.this.lpbyteToMB(new File(lpMusicPlayerActivity.this.lpstrPath).length()))), 500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
    super.onStop();
    }


    @Override
    public void lpinitViews() {

        lpTapClick.lptap(getBindingData().playerView.findViewById(R.id.exo_volume), (Function1<View, Unit>) view -> {
            lpMusicPlayerActivity lpmusicplayeractivity = new lpMusicPlayerActivity();
            ExoPlayer exoPlayer = null;
            if (!lpMusicPlayerActivity.this.lpisMuteAudio) {
                lpMusicPlayerActivity.this.lpcurVolume = 1.0f;
                ExoPlayer exoPlayer2 = lpMusicPlayerActivity.this.lpplayer;
                if (exoPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                } else {
                    exoPlayer = exoPlayer2;
                }
                exoPlayer.setVolume(lpMusicPlayerActivity.this.lpcurVolume);
                ((ImageView) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.exo_volume)).setImageResource(R.drawable.ic_volume);
            } else {
                ExoPlayer exoPlayer3 = lpMusicPlayerActivity.this.lpplayer;
                if (exoPlayer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                    exoPlayer3 = null;
                }
                lpMusicPlayerActivity.this.lpcurVolume = exoPlayer3.getVolume();
                ((ImageView) lpMusicPlayerActivity.this.getBindingData().playerView.findViewById(R.id.exo_volume)).setImageResource(R.drawable.ic_mute);
                ExoPlayer exoPlayer4 = lpMusicPlayerActivity.this.lpplayer;
                if (exoPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
                } else {
                    exoPlayer = exoPlayer4;
                }
                exoPlayer.setVolume(0.0f);
            }
            lpMusicPlayerActivity.this.lpisMuteAudio = !lpmusicplayeractivity.lpisMuteAudio;
            return Unit.INSTANCE;
        });
        lpTapClick.lptap(getBindingData().llSetRingtone, (Function1<View, Unit>) view -> {
            if (lpRingtonePermission.lpcheckSystemWritePermission(lpMusicPlayerActivity.this)) {
                new lpSetRingtoneDialog(lpMusicPlayerActivity.this, true, new Function1<lpMobileState, Unit>() {
                    @Override
                    public Unit invoke(lpMobileState lpmobilestate) {
                        Log.e("VoiceChanger", "Music_Set_on_Click");
                        int[] iArr = new int[lpMobileState.values().length];
                        iArr[lpMobileState.lpSTATE_RINGTONE.ordinal()] = 1;
                        iArr[lpMobileState.lpSTATE_ALARM.ordinal()] = 2;
                        iArr[lpMobileState.lpSTATE_NOTIFICATION.ordinal()] = 3;
                        int i = iArr[lpmobilestate.ordinal()];
                        if (i == 1) {
                            lpMusicPlayerActivity.this.lpsettingsPhoneRing(lpMusicPlayerActivity.this, lpMusicPlayerActivity.this.lpstrPath, lpMobileState.lpSTATE_RINGTONE, true);
                            return null;
                        } else if (i == 2) {
                            lpMusicPlayerActivity.this.lpsettingsPhoneRing(lpMusicPlayerActivity.this, lpMusicPlayerActivity.this.lpstrPath, lpMobileState.lpSTATE_ALARM, true);
                            return null;
                        } else {
                            if (i == 3) {
                                lpMusicPlayerActivity.this.lpsettingsPhoneRing(lpMusicPlayerActivity.this, lpMusicPlayerActivity.this.lpstrPath, lpMobileState.lpSTATE_NOTIFICATION, true);
                            }
                            return null;
                        }
                    }
                }).show();
                return null;
            }
            lpAppConstant.lpAPP_CONSTANT.setCheckResumePermissionRingtone(true);
            lpRingtonePermission.lpopenAndroidPermissionsMenu(lpMusicPlayerActivity.this);
            return null;
        });

        lpTapClick.lptap(getBindingData().llReRecord, (Function1<View, Unit>) view -> {
            Log.e("VoiceChanger", "Music_Record_Click");
            DefaultImpls.lpshowDefaultAct(lpMusicPlayerActivity.this, lpRecordingActivity.class, null, 2, null);
            lpChangeEffectActivity.lpCompanion.setEffectModelSelected(null);
            lpMusicPlayerActivity.this.onBackPressed();
            return null;
        });
        lpTapClick.lptap(getBindingData().tvShare, (Function1<View, Unit>) view -> {
            Log.e("VoiceChanger", "Music_Share_Click");
            String str = lpMusicPlayerActivity.this.lpstrPath;
            lpMusicPlayerActivity lpmusicplayeractivity = lpMusicPlayerActivity.this;
            lpmusicplayeractivity.lpshareFileProject(lpmusicplayeractivity.lppreviewActivity, str);
            return Unit.INSTANCE;
        });
        lpTapClick.lptap(getBindingData().ivHome, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                lpChangeEffectActivity.lpCompanion.setEffectModelSelected(null);
                lpSplashActivity.IntentFromSetting = true;

                lpNavigators.DefaultImpls.lpshowDefaultAct(lpMusicPlayerActivity.this, lpMainActivityLp.class, null, 2, null);
                return null;
            }
        });
        Intrinsics.checkNotNullExpressionValue(lpFilenameUtils.getBaseName(this.lpstrPath), "getBaseName(path)");
    }

    public void lpshareFileProject(Context context, String str) {
        Uri uriForFile = FileProvider.getUriForFile(context, "com.app.voicechangereffect.provider", new File(str));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("audio/*");
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", context.getResources().getString(R.string.appShare) + "\n\nhttps://play.google.com/store/apps/details?id=com.app.voicechangereffect\n");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

    private final void pauseMusicPlayer() {
        Log.e("VoiceChanger", "Music_Pause");
        ExoPlayer exoPlayer = this.lpplayer;
        ExoPlayer exoPlayer2 = null;
        if (exoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            exoPlayer = null;
        }
        exoPlayer.setPlayWhenReady(false);
        ExoPlayer exoPlayer3 = this.lpplayer;
        if (exoPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
        } else {
            exoPlayer2 = exoPlayer3;
        }
        exoPlayer2.getPlaybackState();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
        super.onPause();
        pauseMusicPlayer();
        Log.e("VoiceChanger", "MusicPlayerAct_onPause");
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("VoiceChanger", "MusicPlayerAct_onDestroy");
        ExoPlayer exoPlayer = this.lpplayer;
        ExoPlayer exoPlayer2 = null;
        if (exoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
            exoPlayer = null;
        }
        exoPlayer.stop();
        ExoPlayer exoPlayer3 = this.lpplayer;
        if (exoPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exoPlayer");
        } else {
            exoPlayer2 = exoPlayer3;
        }
        exoPlayer2.release();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
    super.onBackPressed();
        Log.e("VoiceChanger", "MusicPlayerAct_onBack");
        finish();
    }

    public void lpsettingsPhoneRing(lpMusicPlayerActivity context, String str, lpMobileState lpmobilestate, boolean z) {
        int[] iArr = new int[lpMobileState.values().length];
        iArr[lpMobileState.lpSTATE_ALARM.ordinal()] = 1;
        iArr[lpMobileState.lpSTATE_NOTIFICATION.ordinal()] = 2;
        iArr[lpMobileState.lpSTATE_RINGTONE.ordinal()] = 3;
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(ring)");
            int i = iArr[lpmobilestate.ordinal()];
            if (i == 1) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 4, fromFile);
                if (z) {
                    Toast makeText = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText, "makeText(\n              \u2026ONG\n                    )");
                    makeText.show();
                }
            } else if (i == 2) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 2, fromFile);
                if (z) {
                    Toast makeText2 = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText2, "makeText(\n              \u2026ONG\n                    )");
                    makeText2.show();
                }
            } else if (i == 3) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 1, fromFile);
                if (z) {
                    Toast makeText3 = Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG);
                    Intrinsics.checkNotNullExpressionValue(makeText3, "makeText(\n              \u2026ONG\n                    )");
                    makeText3.show();
                }
            }
        } catch (Throwable th) {
            Log.e("setAsDefaultRingtone err", th.toString());
        }
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
