package com.app.voicechangereffect.viewModel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import com.app.voicechangereffect.allBaseAct.lpBaseViewModel;
import com.app.voicechangereffect.custUi.lpLiveEvents;
import com.app.voicechangereffect.getApiData.allModel.lpRecordingModel;
import com.app.voicechangereffect.recordingServices.lpServiceRecordingVoice;
import kotlin.jvm.internal.Intrinsics;

public final class lpRecordingViewModel extends lpBaseViewModel {
    public lpServiceRecordingVoice recordingVoice;
    public final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    public final lpLiveEvents<lpRecordingModel> recordingModelLpLiveEvents = new lpLiveEvents<>();
    public final lpServiceRecordingVoice.OnRecordingStatusChangedListener onScheduledRecordingListener = new lpServiceRecordingVoice.OnRecordingStatusChangedListener() { // from class: com.app.voicechangereffect.viewModel.lpRecordingViewModel.1
        @Override
        public void onAmplitudeInfo(int i) {
            Log.d("checkactivity", "onAmplitudeInfo 122121 ");
            lpRecordingViewModel.this.mutableLiveData.postValue(Integer.valueOf(i));
        }

        @Override
        public void onPauseRecording() {
            Log.d("checkactivity", "onPauseRecording 122121 ");
            lpRecordingViewModel.this.getServiceRecordResume().set(false);
            lpRecordingViewModel.this.getServiceRecordResume().set(false);
        }

        @Override
        public void onResumeRecording() {
            Log.d("checkactivity", "onResumeRecording 122121 ");
            lpRecordingViewModel.this.getServiceRecordResume().set(true);
        }

        @Override
        public void onSkipRecording() {
            Log.d("checkactivity", "onSkipRecording 122121 ");
            lpRecordingViewModel.this.getServiceRecordResume().set(false);
            lpRecordingViewModel.this.getServiceRecording().set(false);
            lpRecordingViewModel.this.getObservableInt().set(0);
        }

        @Override
        public void onStartedRecording() {
            Log.d("checkactivity", "onStartedRecording 122121 ");
            lpRecordingViewModel.this.getServiceRecording().set(true);
            lpRecordingViewModel.this.getServiceRecordResume().set(true);
        }

        @Override
        public void onStopRecording(lpRecordingModel lprecordingmodel) {
            Log.d("checkactivity", "onStopRecording 122121 ");
            lpRecordingViewModel.this.getServiceRecordResume().set(false);
            lpRecordingViewModel.this.getServiceRecording().set(false);
            lpRecordingViewModel.this.getObservableInt().set(0);
            lpRecordingViewModel.this.recordingModelLpLiveEvents.postValue(lprecordingmodel);
        }

        @Override
        public void onTimerChanged(int i) {
            Log.d("checkactivity", "onTimerChanged 122121 ");
            lpRecordingViewModel.this.getObservableInt().set(i);
        }
    };
    private final ObservableInt observableInt = new ObservableInt(0);
    private final ObservableBoolean observableBoolean = new ObservableBoolean(false);
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("checkactivity", "onServiceConnected 122121 ");
            lpRecordingViewModel.this.recordingVoice = ((lpServiceRecordingVoice.LocalBinder) iBinder).getService();
            lpRecordingViewModel.this.getObservableBoolean().set(true);
            lpRecordingViewModel.this.recordingVoice.setLponRecordingStatusChangedListener(lpRecordingViewModel.this.onScheduledRecordingListener);
            lpRecordingViewModel.this.getServiceRecording().set(lpRecordingViewModel.this.recordingVoice.isLpisRecording());
            lpRecordingViewModel.this.getServiceRecordResume().set(lpRecordingViewModel.this.recordingVoice.isLpisResumeRecording());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("checkactivity", "onServiceDisconnected 122121 ");
            if (lpRecordingViewModel.this.recordingVoice != null) {
                lpServiceRecordingVoice lpservicerecordingvoice = lpRecordingViewModel.this.recordingVoice;
                Intrinsics.checkNotNull(lpservicerecordingvoice);
                lpservicerecordingvoice.setLponRecordingStatusChangedListener(null);
                lpRecordingViewModel.this.recordingVoice = null;
            }
            lpRecordingViewModel.this.getObservableBoolean().set(false);
        }
    };
    private final ObservableBoolean serviceRecordResume = new ObservableBoolean(false);
    private final ObservableBoolean serviceRecording = new ObservableBoolean(false);
    private final lpLiveEvents<Integer> toastMsg = new lpLiveEvents<>();

    public final ObservableBoolean getObservableBoolean() {
        return this.observableBoolean;
    }

    public final ObservableBoolean getServiceRecording() {
        return this.serviceRecording;
    }

    public final ObservableBoolean getServiceRecordResume() {
        return this.serviceRecordResume;
    }

    public final ObservableInt getObservableInt() {
        return this.observableInt;
    }

    public final void connectService(Intent intent) {
        try {
            getLpcontext().startService(intent);
            getLpcontext().bindService(intent, this.connection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            System.out.println(Intrinsics.stringPlus("RecordViewModel.connectService e = ", e));
        }
    }

    public final void serviceStartStop(Intent intent) {
        try {
            if (this.observableBoolean.get()) {
                getLpcontext().unbindService(this.connection);
                if (!this.serviceRecording.get()) {
                    getLpcontext().stopService(intent);
                }
                lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
                if (lpservicerecordingvoice != null) {
                    lpservicerecordingvoice.setLponRecordingStatusChangedListener(null);
                }
                this.recordingVoice = null;
                this.observableBoolean.set(false);
            }
        } catch (Exception e) {
            System.out.println(Intrinsics.stringPlus("RecordViewModel.disconnectAndStopService e = ", e));
        }
    }

    public void recStart() {
        Log.e("cc----", "recStart: ");
        Log.d("checkactivity", "recStart 121121 ");
        lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
        if (lpservicerecordingvoice != null) {
            Log.d("checkactivity", "serviceRecordingVoice qwwqwqw ");
            lpservicerecordingvoice.startRecording(0);
        }
        this.serviceRecording.set(true);
        this.serviceRecordResume.set(true);
    }

    public void recStop() {
        Log.e("cc----", "recStop: ");
        lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
        if (lpservicerecordingvoice != null) {
            lpservicerecordingvoice.recordingStop();
        }
    }

    public void recPause() {
        Log.e("cc----", "recPause: ");
        lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
        if (lpservicerecordingvoice != null) {
            lpservicerecordingvoice.pauseRecording();
        }
    }

    public void recResume() {
        Log.e("cc----", "recResume: ");
        lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
        if (lpservicerecordingvoice != null) {
            lpservicerecordingvoice.resumeRecording();
        }
    }

    public void recSkipFile() {
        Log.e("cc----", "recSkipFile: ");
        lpServiceRecordingVoice lpservicerecordingvoice = this.recordingVoice;
        if (lpservicerecordingvoice != null) {
            lpservicerecordingvoice.fileRecordSkip();
        }
    }

    public lpLiveEvents<lpRecordingModel> getRecording() {
        return this.recordingModelLpLiveEvents;
    }
}
