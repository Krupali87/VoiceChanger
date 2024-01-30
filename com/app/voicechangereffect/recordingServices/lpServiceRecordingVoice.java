package com.app.voicechangereffect.recordingServices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.app.voicechangereffect.R;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.app.voicechangereffect.custUi.lpFileMethods;
import com.app.voicechangereffect.getApiData.allModel.lpRecordingModel;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class lpServiceRecordingVoice extends Service {
    private static final String lpextraAcvtivityStarter = "com.app.voicechangereffect.EXTRA_ACTIVITY_STARTER";
    private static final String lplogTag = "SCHEDULED_RECORDER_TAG";
    public static int lponCallsCreate;
    public static int lponCallsDestroy;
    public static int lponCallsStartCommands;
    private final String lpclassNameSimple = getClass().getSimpleName();
    private boolean lpisRecording = false;
    private boolean lpisResumeRecording = false;
    public long lpmElapsedMillis = 0;
    private String lpstrFileName = null;
    private String lpstrFilePath = null;
    public TimerTask lptimerTask = null;
    public MediaRecorder lpmediaRecorder = null;
    private final IBinder lpmyBinder = new LocalBinder();
    public OnRecordingStatusChangedListener lponRecordingStatusChangedListener = null;


    public interface OnRecordingStatusChangedListener {
        void onAmplitudeInfo(int i);

        void onPauseRecording();

        void onResumeRecording();

        void onSkipRecording();

        void onStartedRecording();

        void onStopRecording(lpRecordingModel lprecordingmodel);

        void onTimerChanged(int i);
    }

    static long cusMethod(lpServiceRecordingVoice lpservicerecordingvoice, long j) {
        long j2 = lpservicerecordingvoice.lpmElapsedMillis + j;
        lpservicerecordingvoice.lpmElapsedMillis = j2;
        return j2;
    }

    public static Intent makeIntent(Context context, boolean z) {
        Intent intent = new Intent(context.getApplicationContext(), lpServiceRecordingVoice.class);
        intent.putExtra(lpextraAcvtivityStarter, z);
        return intent;
    }


    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public lpServiceRecordingVoice getService() {
            return lpServiceRecordingVoice.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.lpmyBinder;
    }

    public void setLponRecordingStatusChangedListener(OnRecordingStatusChangedListener onRecordingStatusChangedListener) {
        this.lponRecordingStatusChangedListener = onRecordingStatusChangedListener;
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        lponCallsStartCommands++;
        intent.getBooleanExtra(lpextraAcvtivityStarter, false);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        lponCallsCreate++;
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        lponCallsDestroy++;
        super.onDestroy();
        if (this.lpmediaRecorder != null) {
            recordingStop();
        }
        if (this.lponRecordingStatusChangedListener != null) {
            this.lponRecordingStatusChangedListener = null;
        }
    }

    public void startRecording(int i) {
        try {
            startForeground(2, notificationCreate());
            setFileNamePath();
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.lpmediaRecorder = mediaRecorder;
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(this.lpstrFilePath);
            mediaRecorder.setMaxDuration(i);
            mediaRecorder.setAudioChannels(1);
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setAudioEncodingBitRate(DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND);

            mediaRecorder.setOnInfoListener((mediaRecorder2, i2, i3) -> {
                if (i2 == 800) {
                    lpServiceRecordingVoice.this.recordingStop();
                }
            });
            this.lpmediaRecorder.prepare();
            this.lpmediaRecorder.start();
            this.lpisRecording = true;
            this.lpisResumeRecording = true;
            timerStart();
        } catch (Exception e) {
            Log.e(lplogTag, this.lpclassNameSimple + " - startRecording(): prepare() failed" + e.toString());
        }
        OnRecordingStatusChangedListener onRecordingStatusChangedListener = this.lponRecordingStatusChangedListener;
        if (onRecordingStatusChangedListener != null) {
            onRecordingStatusChangedListener.onStartedRecording();
        }
    }

    private void setFileNamePath() {
        this.lpstrFileName = "Voice_effect_" + System.currentTimeMillis();
        this.lpstrFilePath = lpFileMethods.lpgetMainDirPath(this) + "/" + this.lpstrFileName + ".mp3";
    }

    private void timerStart() {
        Timer timer = new Timer();
        this.lpmElapsedMillis = 0L;
        TimerTask timerTask = new TimerTask() { // from class: com.app.voicechangereffect.recordingServices.lpServiceRecordingVoice.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (lpServiceRecordingVoice.this.lptimerTask == null) {
                    cancel();
                }
                lpServiceRecordingVoice.cusMethod(lpServiceRecordingVoice.this, 100L);
                if (lpServiceRecordingVoice.this.lponRecordingStatusChangedListener != null) {
                    lpServiceRecordingVoice.this.lponRecordingStatusChangedListener.onTimerChanged(((int) lpServiceRecordingVoice.this.lpmElapsedMillis) / 1000);
                }
                if (lpServiceRecordingVoice.this.lponRecordingStatusChangedListener == null || lpServiceRecordingVoice.this.lpmediaRecorder == null) {
                    return;
                }
                try {
                    lpServiceRecordingVoice.this.lponRecordingStatusChangedListener.onAmplitudeInfo(lpServiceRecordingVoice.this.lpmediaRecorder.getMaxAmplitude());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.lptimerTask = timerTask;
        timer.scheduleAtFixedRate(timerTask, 100L, 100L);
    }

    public void recordingStop() {
        try {
            this.lpmediaRecorder.stop();
            this.lpmediaRecorder.release();
            this.lpisRecording = false;
            this.lpisResumeRecording = false;
            this.lpmediaRecorder = null;
            TimerTask timerTask = this.lptimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.lptimerTask = null;
            }
            Log.e("rec---", "recordingStop: strFileName :  " + this.lpstrFileName);
            Log.e("rec---", "recordingStop: strFilePath :  " + this.lpstrFilePath);
            Log.e("rec---", "recordingStop: mElapsedMillis :  " + this.lpmElapsedMillis);
            lpRecordingModel lprecordingmodel = new lpRecordingModel(this.lpstrFileName, this.lpstrFilePath, this.lpmElapsedMillis, System.currentTimeMillis(), 0);
            OnRecordingStatusChangedListener onRecordingStatusChangedListener = this.lponRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener != null) {
                onRecordingStatusChangedListener.onStopRecording(lprecordingmodel);
            }
            if (this.lponRecordingStatusChangedListener == null) {
                stopSelf();
            }
            stopForeground(true);
        } catch (Exception e) {
            System.out.println("RecordingService.stopRecording e = " + e);
        }
    }

    public void fileRecordSkip() {
        try {
            this.lpmediaRecorder.stop();
            this.lpmediaRecorder.release();
            this.lpisRecording = false;
            this.lpisResumeRecording = false;
            this.lpmediaRecorder = null;
            this.lpmElapsedMillis = 0L;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener = this.lponRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener != null) {
                onRecordingStatusChangedListener.onSkipRecording();
            }
            TimerTask timerTask = this.lptimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.lptimerTask = null;
            }
            if (this.lpstrFilePath != null) {
                File file = new File(this.lpstrFilePath);
                if (file.exists()) {
                    file.delete();
                }
            }
            if (this.lponRecordingStatusChangedListener == null) {
                stopSelf();
            }
            stopForeground(true);
        } catch (Exception e) {
            System.out.println("RecordingService.skipFileRecord e = " + e);
        }
    }

    public void pauseRecording() {
        try {
            this.lpmediaRecorder.pause();
            this.lpisResumeRecording = false;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener = this.lponRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener != null) {
                onRecordingStatusChangedListener.onPauseRecording();
            }
            TimerTask timerTask = this.lptimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.lptimerTask = null;
            }
        } catch (Exception e) {
            System.out.println("RecordingService.pauseRecording e = " + e);
        }
    }

    public void resumeRecording() {
        try {
            this.lpmediaRecorder.resume();
            this.lpisResumeRecording = true;
            OnRecordingStatusChangedListener onRecordingStatusChangedListener = this.lponRecordingStatusChangedListener;
            if (onRecordingStatusChangedListener != null) {
                onRecordingStatusChangedListener.onResumeRecording();
            }
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() { // from class: com.app.voicechangereffect.recordingServices.lpServiceRecordingVoice.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (lpServiceRecordingVoice.this.lptimerTask == null) {
                        cancel();
                    }
                    lpServiceRecordingVoice.cusMethod(lpServiceRecordingVoice.this, 100L);
                    if (lpServiceRecordingVoice.this.lponRecordingStatusChangedListener != null) {
                        lpServiceRecordingVoice.this.lponRecordingStatusChangedListener.onTimerChanged(((int) lpServiceRecordingVoice.this.lpmElapsedMillis) / 1000);
                    }
                    if (lpServiceRecordingVoice.this.lponRecordingStatusChangedListener == null || lpServiceRecordingVoice.this.lpmediaRecorder == null) {
                        return;
                    }
                    try {
                        lpServiceRecordingVoice.this.lponRecordingStatusChangedListener.onAmplitudeInfo(lpServiceRecordingVoice.this.lpmediaRecorder.getMaxAmplitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            this.lptimerTask = timerTask;
            timer.scheduleAtFixedRate(timerTask, 100L, 100L);
        } catch (Exception e) {
            System.out.println("RecordingService.resumeRecording e = " + e);
        }
    }

    private Notification notificationCreate() {
        return new NotificationCompat.Builder(getApplicationContext(), Build.VERSION.SDK_INT >= 26 ? notiChannelCreate() : "").setSmallIcon(R.drawable.ic_mic_white_36dp).setContentTitle(getString(R.string.notification_recording)).setOngoing(true).build();
    }

    private String notiChannelCreate() {
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("recording_service", "Recording Service", NotificationManager.IMPORTANCE_LOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setLightColor(-16776961);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setLockscreenVisibility(0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(notificationChannel);
        }
        return "recording_service";
    }

    public boolean isLpisRecording() {
        return this.lpisRecording;
    }

    public boolean isLpisResumeRecording() {
        return this.lpisResumeRecording;
    }
}
