package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Observer;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allDialogs.lpNotRecordedDialogLp;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpMobileState;
import com.app.voicechangereffect.custUi.lpRecordAudioType;
import com.app.voicechangereffect.custUi.lpSetLanguage;
import com.app.voicechangereffect.databinding.ActivityRecordingBinding;
import com.app.voicechangereffect.getApiData.allModel.lpRecordingModel;
import com.app.voicechangereffect.recordingServices.lpServiceRecordingVoice;
import com.app.voicechangereffect.viewModel.lpRecordingViewModel;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@SuppressLint("WrongConstant")

public final class lpRecordingActivity extends lpBaseActivity<lpRecordingViewModel, ActivityRecordingBinding> {

    public Observable.OnPropertyChangedCallback lpcallback;
    private boolean lpisRecording;
    private int lpplayerSecondsElapsed;
    private lpRecordingActivity lprecordingActivity;
    private Timer lptimer;
    private int lprecorderSecondsElapsed = -1;
    public boolean lpisFirstCallBack = true;
    public boolean lpisFirst = true;
    public lpRecordAudioType lpstateAudio = lpRecordAudioType.lpSTATE_PREPARE;

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    static int access$308(lpRecordingActivity lprecordingactivity) {
        int i = lprecordingactivity.lpplayerSecondsElapsed;
        lprecordingactivity.lpplayerSecondsElapsed = i + 1;
        return i;
    }

    static int access$408(lpRecordingActivity lprecordingactivity) {
        int i = lprecordingactivity.lprecorderSecondsElapsed;
        lprecordingactivity.lprecorderSecondsElapsed = i + 1;
        return i;
    }

    @Override
    public Class<lpRecordingViewModel> createViewModel() {
        return lpRecordingViewModel.class;
    }

    @Override
    public int getContent() {
        Log.e("VoiceChanger", "RecordingAct_onCreate");
        this.lprecordingActivity = this;
        return R.layout.activity_recording;
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
        lpSetLanguage.lpsetLocale(this);
        getBindingData().toolbar.tvTitle.setText(R.string.record_voice);
        lpshowHideExRecord();
        lpgetMViewModel().connectService(lpServiceRecordingVoice.makeIntent(this, true));
        this.lpisFirst = true;
    }

    @Override
    public void lpinitViews() {
        lpTapClick.lptap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            lpRecordingActivity.this.onBackPressed();
            return null;
        });
        lpTapClick.lptap(getBindingData().ivReset, (Function1<View, Unit>) view -> {
            lpRecordingActivity lprecordingactivity = lpRecordingActivity.this;
            lprecordingactivity.lpshowDialogNotSaved(true, false, lprecordingactivity.lprecordingActivity.getResources().getString(R.string.audio_has_not_been_saved_reset), lpRecordingActivity.this.getResources().getString(R.string.reset));
            return null;
        });
        getBindingData().icStart.setOnClickListener(view -> lpRecordingActivity.this.m388x7bda13fa(view));
        lpTapClick.lptap(getBindingData().imgRecord, (Function1<View, Unit>) view -> {
            int[] iArr = new int[lpRecordAudioType.values().length];
            iArr[lpRecordAudioType.lpSTATE_PREPARE.ordinal()] = 1;
            iArr[lpRecordAudioType.lpSTATE_START.ordinal()] = 2;
            iArr[lpRecordAudioType.lpSTATE_PAUSE.ordinal()] = 3;
            iArr[lpRecordAudioType.lpSTATE_STOP.ordinal()] = 4;
            int i = iArr[lpRecordingActivity.this.lpstateAudio.ordinal()];
            if (i == 1) {
                Log.e("re---", "invoke: 1111");
                lpRecordingActivity.this.lpstartRecordAudio();
                lpRecordingActivity.this.getBindingData().rlyBottom.setVisibility(0);
                lpRecordingActivity.this.getBindingData().imgRecord.setVisibility(0);
                return null;
            } else if (i == 2) {
                Log.e("re---", "invoke: 222");
                lpRecordingActivity.this.lppauseRecordAudio();
                lpRecordingActivity.this.lpstartStopRecording();
                lpRecordingActivity.this.getBindingData().rlyBottom.setVisibility(0);
                lpRecordingActivity.this.getBindingData().imgRecord.setVisibility(0);
                return null;
            } else {
                Log.e("re---", "invoke: 3333");
                lpRecordingActivity.this.lpstartRecordAudio();
                lpRecordingActivity.this.getBindingData().rlyBottom.setVisibility(0);
                lpRecordingActivity.this.getBindingData().imgRecord.setVisibility(0);
                return null;
            }
        });
        lpTapClick.lptap(getBindingData().imgStop, (Function1<View, Unit>) view -> {
            lpRecordingActivity.this.lpisFirst = false;
            lpRecordingActivity.this.lpgetMViewModel().recStop();
            lpRecordingActivity.this.lpstopAnim();
            lpRecordingActivity.this.lpstopTimer();
            ObservableInt observableInt = lpRecordingActivity.this.lpgetMViewModel().getObservableInt();
            Observable.OnPropertyChangedCallback onPropertyChangedCallback = lpRecordingActivity.this.lpcallback;
            Intrinsics.checkNotNull(onPropertyChangedCallback);
            observableInt.removeOnPropertyChangedCallback(onPropertyChangedCallback);
            lpRecordingActivity.this.getWindow().clearFlags(128);
            Observable.OnPropertyChangedCallback onPropertyChangedCallback2 = lpRecordingActivity.this.lpcallback;
            if (onPropertyChangedCallback2 != null) {
                Log.d("checkactivity", "onPropertyChangedCallback2 wqewqweqweqwe");
                lpRecordingActivity.this.lpgetMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback2);
            } else {
                Log.d("checkactivity", "onPropertyChangedCallback2 elseeee ");
            }
            lpRecordingActivity.this.lpgetMViewModel().getRecording().observe(lpRecordingActivity.this.lprecordingActivity, new Observer<lpRecordingModel>() {
                @Override
                public void onChanged(lpRecordingModel lprecordingmodel) {
                    Log.d("checkactivity", "onChanged ");
                    if (lprecordingmodel != null) {
                        Log.d("checkactivity", "recording model is not null");
                        Bundle bundle = new Bundle();
                        bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), lprecordingmodel.getLppath());
                        bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "RecordActivity");
                        lpRecordingActivity.this.nextActivity(lpChangeEffectActivity.class, bundle);
                        lpRecordingActivity.this.getBindingData().icStart.setClickable(true);
                        lpRecordingActivity.this.getBindingData().txtStartRecord.setText("Start Record");
                        lpRecordingActivity.this.getBindingData().txtExtra.setVisibility(0);
                        lpRecordingActivity.this.getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
                        return;
                    }
                    Log.d("checkactivity", "recording model is  null");
                }
            });
            lpRecordingActivity.this.lpstateAudio = lpRecordAudioType.lpSTATE_PREPARE;
            lpRecordingActivity.this.lpshowHideExRecord();
            return null;
        });
    }

    public void m388x7bda13fa(View view) {
        getBindingData().txtStartRecord.setText("Recording...");
        lpstartRecordAudio();
        getBindingData().icStart.setClickable(false);
        getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
        getBindingData().rlyBottom.setVisibility(0);
        getBindingData().imgRecord.setVisibility(0);
        getBindingData().txtExtra.setVisibility(8);
        Log.e("VoiceChanger", "Click_on_Start");
    }

    public void lpstartRecordAudio() {
        Log.e("VoiceChanger", "startRecordAudio");
        Log.d("checkactivity", "startRecordAudio 121212121");
        Log.e("record---", "startRecordAudio: ");

        this.lpstateAudio = lpRecordAudioType.lpSTATE_START;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_pause_effect);
        getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
        lpstartStopRecording();
    }

    public void lppauseRecordAudio() {
        Log.e("VoiceChanger", "pauseRecordAudio ");
        Log.d("checkactivity", "pauseRecordAudio   122121");
        Log.e("record---", "pauseRecordAudio: ");
        this.lpstateAudio = lpRecordAudioType.lpSTATE_PAUSE;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_play);
    }

    private void lpstopRecordAudio() {
        Log.e("VoiceChanger", "stopRecordAudio");
        Log.e("record---", "stopRecordAudio: ");
        this.lpstateAudio = lpRecordAudioType.lpSTATE_STOP;
        getBindingData().imgRecord.setImageResource(R.drawable.ic_start_record);
        getBindingData().txtStartRecord.setText(R.string.start_record);
    }

    public void lpshowHideExRecord() {
        int[] iArr = new int[lpMobileState.values().length];
        iArr[lpMobileState.lpSTATE_RINGTONE.ordinal()] = 1;
        iArr[lpMobileState.lpSTATE_ALARM.ordinal()] = 2;
        iArr[lpMobileState.lpSTATE_NOTIFICATION.ordinal()] = 3;
        if (iArr[this.lpstateAudio.ordinal()] == 1) {
            this.lprecorderSecondsElapsed = 0;
            this.lpplayerSecondsElapsed = 0;
            getBindingData().imgRecord.setClickable(true);
            getBindingData().imgRecord.setImageResource(R.drawable.ic_start_record);
            getBindingData().rlyBottom.setVisibility(8);
            getBindingData().imgRecord.setVisibility(8);
            getBindingData().txtStartRecord.setText(R.string.start_record);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("VoiceChanger", "RecordingAct_onResume");
        this.lpcallback = new Observable.OnPropertyChangedCallback() { // from class: com.app.voicechangereffect.activity.lpRecordingActivity.6
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int i) {
                Intrinsics.checkNotNullParameter(sender, "sender");
                if (lpRecordingActivity.this.lpisFirstCallBack) {
                    lpRecordingActivity.this.lpisFirstCallBack = false;
                }
            }
        };
        ObservableInt observableInt = lpgetMViewModel().getObservableInt();
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.lpcallback;
        Objects.requireNonNull(onPropertyChangedCallback, "null cannot be cast to non-null type androidx.databinding.Observable.OnPropertyChangedCallback");
        observableInt.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("VoiceChanger", "RecordingAct_onPause");
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.lpcallback;
        if (onPropertyChangedCallback != null) {
            lpgetMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
    }

    public void lpshowDialogNotSaved(boolean z, final boolean z2, String str, String str2) {
        if (z) {
            Log.e("ww---", "showDialogNotSaved: 111");
            new lpNotRecordedDialogLp(str, str2, this, true, () -> {
                lpRecordingActivity.this.lpstateAudio = lpRecordAudioType.lpSTATE_PREPARE;
                lpRecordingActivity.this.lpresetFileRecord();
                lpRecordingActivity.this.lpshowHideExRecord();
                if (z2) {
                    lpRecordingActivity.this.finish();
                }
                return null;
            }).show();
            return;
        }
        lpstopRecordAudio();
        finish();
    }

    public void lpstartStopRecording() {
        this.lpisFirstCallBack = false;
        if (!lpgetMViewModel().getServiceRecording().get()) {
            this.lpisFirst = false;
            this.lpisRecording = true;
            lpgetMViewModel().recStart();
            getWindow().addFlags(128);
            lpstartAnim();
            lpstartTimer();
            return;
        }
        lppauseRecord();
    }

    private void lppauseRecord() {
        if (!lpgetMViewModel().getServiceRecordResume().get()) {
            lpgetMViewModel().recResume();
            lpstartAnim();
            lpstartTimer();
            if (this.lpisFirst) {
                this.lpisFirst = false;
                this.lpisFirstCallBack = true;
            }
            this.lpcallback = new Observable.OnPropertyChangedCallback() { // from class: com.app.voicechangereffect.activity.lpRecordingActivity.8
                @Override // androidx.databinding.Observable.OnPropertyChangedCallback
                public void onPropertyChanged(Observable sender, int i) {
                    Intrinsics.checkNotNullParameter(sender, "sender");
                    if (lpRecordingActivity.this.lpisFirstCallBack) {
                        lpRecordingActivity.this.lpisFirstCallBack = false;
                    }
                }
            };
            ObservableInt observableInt = lpgetMViewModel().getObservableInt();
            Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.lpcallback;
            Objects.requireNonNull(onPropertyChangedCallback, "null cannot be cast to non-null type androidx.databinding.Observable.OnPropertyChangedCallback");
            observableInt.addOnPropertyChangedCallback(onPropertyChangedCallback);
            return;
        }
        this.lpisFirst = false;
        lpgetMViewModel().recPause();
        lpstopAnim();
        lpstopTimer();
        Observable.OnPropertyChangedCallback onPropertyChangedCallback2 = this.lpcallback;
        if (onPropertyChangedCallback2 != null) {
            lpgetMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback2);
        }
        this.lpisRecording = false;
    }

    public void lpresetFileRecord() {
        Log.e("eee---", "resetFileRecord: ");
        this.lpisFirst = false;
        lpgetMViewModel().recSkipFile();
        lpstopAnim();
        lpstopTimer();
        getWindow().clearFlags(128);
        Observable.OnPropertyChangedCallback onPropertyChangedCallback = this.lpcallback;
        getBindingData().icStart.setImageResource(R.drawable.ic_start_record);
        getBindingData().txtExtra.setVisibility(0);
        getBindingData().icStart.setClickable(true);
        getBindingData().txtStartRecord.setText("Start Record");
        if (onPropertyChangedCallback != null) {
            lpgetMViewModel().getObservableInt().removeOnPropertyChangedCallback(onPropertyChangedCallback);
        }
        this.lprecorderSecondsElapsed = 0;
        this.lpplayerSecondsElapsed = 0;
        getBindingData().txtStartRecord.setText(R.string.start_record);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lpgetMViewModel().serviceStartStop(new Intent(this, lpServiceRecordingVoice.class));
        stopService(new Intent(this, lpServiceRecordingVoice.class));
        lpstopAnim();
        lpstopTimer();
    }

    @Override
    public void onBackPressed() {
        Log.e("VoiceChanger", "RecordingAct_onBack");
        if (getBindingData().icStart.isClickable()) {
            super.onBackPressed();
        } else {
            lpshowDialogNotSaved(getBindingData().ivReset.isShown(), true, getResources().getString(R.string.audio_has_not_been_saved), getResources().getString(R.string.exit));
        }
    }

    public void lpstartAnim() {
        Log.e("eee---", "startAnim: ");
        getBindingData().recordLottie.setVisibility(0);
    }

    public void lpstopAnim() {
        Log.e("eee---", "stopAnim: ");
        getBindingData().recordLottie.setVisibility(8);
    }

    private void lpstartTimer() {
        Log.e("eee---", "startTimer: ");
        lpstopTimer();
        Timer timer = new Timer();
        this.lptimer = timer;
        timer.scheduleAtFixedRate(new TimerTask() { // from class: com.app.voicechangereffect.activity.lpRecordingActivity.9
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                lpRecordingActivity.this.updateTimer();
            }
        }, 800L, 1000L);
    }

    public void lpstopTimer() {
        Log.e("eee---", "stopTimer: ");
        Timer timer = this.lptimer;
        if (timer != null) {
            timer.purge();
            this.lptimer.cancel();
            this.lptimer = null;
        }
    }

    public void updateTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textView;
                int i;
                if (!lpRecordingActivity.this.lpisRecording) {
                    lpRecordingActivity.access$308(lpRecordingActivity.this);
                    textView = lpRecordingActivity.this.getBindingData().txtStartRecord;
                    i = lpRecordingActivity.this.lpplayerSecondsElapsed;
                } else {
                    lpRecordingActivity.access$408(lpRecordingActivity.this);
                    textView = lpRecordingActivity.this.getBindingData().txtStartRecord;
                    i = lpRecordingActivity.this.lprecorderSecondsElapsed;
                }
                textView.setText(lpRecordingActivity.lpformatSeconds(i));
                lpRecordingActivity.this.lpplayerSecondsElapsed = i;
            }
        });
    }

    public static String lpformatSeconds(int i) {
        return lpgetTwoDecimalsValue(i / 3600) + ":" + lpgetTwoDecimalsValue(i / 60) + ":" + lpgetTwoDecimalsValue(i % 60);
    }

    private static String lpgetTwoDecimalsValue(int i) {
        StringBuilder sb;
        if (i < 0 || i > 9) {
            sb = new StringBuilder();
            sb.append(i);
            sb.append("");
        } else {
            sb = new StringBuilder();
            sb.append("0");
            sb.append(i);
        }
        return sb.toString();
    }
}
