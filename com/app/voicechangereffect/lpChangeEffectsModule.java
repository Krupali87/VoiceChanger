package com.app.voicechangereffect;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.app.voicechangereffect.allBaseAct.lpBaseCallback;
import com.app.voicechangereffect.viewModel.lpModelEffects;
import com.un4seen.bass.BASS;
import java.io.File;
import java.util.ArrayList;

public class lpChangeEffectsModule {
    public static final String lplogTag = "VoiceChangerModule";
    private lpMediaPlayerLpDb lpMediaPlayerDb;
    private final Context lpcontext;
    private boolean lpisInitIs;
    private String lpstrChangeVoiceName;
    public ArrayList<lpModelEffects> lpModelEffects = new ArrayList<>();
    private String lpstrAudioPath = null;
    public File lpfileOutputDirectory = null;
    public Integer lpindexPLaying = null;

    public lpChangeEffectsModule(Context context) {
        this.lpcontext = context;
        setDBMedia(this.lpMediaPlayerDb);
        lponInitAudioDevice();
    }

    public Integer getLpindexPLaying() {
        return this.lpindexPLaying;
    }

    public void insertEffect(String str) {
        this.lpModelEffects.add(lpParsingJsonObjects.lpjsonToObjectEffects(str));
    }

    public lpMediaPlayerLpDb getDBMedia() {
        return this.lpMediaPlayerDb;
    }

    public void setDBMedia(lpMediaPlayerLpDb lpmediaplayerlpdb) {
        this.lpMediaPlayerDb = lpmediaplayerlpdb;
    }

    public void show(String str, int i) {
        Toast.makeText(this.lpcontext, str, i).show();
    }

    public void setPath(String str) {
        this.lpstrAudioPath = str;
    }

    public void setLpindexPLaying(Integer num) {
        if (num != null) {
            this.lpindexPLaying = num;
        }
    }

    public void saveTheEffects(int i, String str, lpBaseCallback lpbasecallback) {
        onSaveEffect(this.lpModelEffects.get(i), str, lpbasecallback);
    }

    public void createOutputDir(Activity activity) {
        this.lpfileOutputDirectory = getDirectory(activity);
    }

    public void createDBMedia() {
        onCreateDBMedia();
    }

    public void effectPlay(int i) {
        Log.d(lplogTag, "audioPath: " + this.lpstrAudioPath);
        String str = this.lpstrAudioPath;
        if (str != null || !str.equals("")) {
            File file = new File(this.lpstrAudioPath);
            if (!file.exists() || !file.isFile()) {
                toastShow("File not found exception");
            }
        }
        try {
            setLpindexPLaying(Integer.valueOf(i));
            Log.e(lplogTag, "playEffect: " + this.lpModelEffects.get(i).getLpstrName());
            onEffectPlay(this.lpModelEffects.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onEffectPlay(lpModelEffects lpmodeleffects) {
        if (lpmodeleffects.isLpisPlayingBool()) {
            lpmodeleffects.setLpisPlayingBool(false);
            lpMediaPlayerLpDb lpmediaplayerlpdb = this.lpMediaPlayerDb;
            if (lpmediaplayerlpdb != null) {
                lpmediaplayerlpdb.lpaudioPause();
                return;
            }
            return;
        }
        onStateReset();
        lpmodeleffects.setLpisPlayingBool(true);
        lpMediaPlayerLpDb lpmediaplayerlpdb2 = this.lpMediaPlayerDb;
        if (lpmediaplayerlpdb2 != null) {
            lpmediaplayerlpdb2.setPathMix(lpmodeleffects.getLpstrPathMix());
            this.lpMediaPlayerDb.setLpisMixNeed(lpmodeleffects.isLpisMixBool());
            this.lpMediaPlayerDb.lpaudioPrepare();
            this.lpMediaPlayerDb.lpsetSkisThisReverse(lpmodeleffects.isLpisReverseBool());
            this.lpMediaPlayerDb.lpsetPitchAudio(lpmodeleffects.getLpintPitch());
            this.lpMediaPlayerDb.lpcompressorSet(lpmodeleffects.getLpfloatCompressor());
            this.lpMediaPlayerDb.lpsetRateAudio(lpmodeleffects.getLpfloatRate());
            this.lpMediaPlayerDb.lpsetEQ1Audio(lpmodeleffects.getLpfloatyEcho1());
            this.lpMediaPlayerDb.lpsetEQ2Audio(lpmodeleffects.getLpfloatEq2());
            this.lpMediaPlayerDb.lpsetEQ3Audio(lpmodeleffects.getLpfloatEq3());
            this.lpMediaPlayerDb.lpphrserSet(lpmodeleffects.getLpfloatPhaser());
            this.lpMediaPlayerDb.lpsetWahAuto(lpmodeleffects.getLpfloatAutoWah());
            this.lpMediaPlayerDb.lpsetReverbAudio(lpmodeleffects.getLpfloatReverb());
            this.lpMediaPlayerDb.lpsetEffectEcho4(lpmodeleffects.getLpfloatEcho4());
            this.lpMediaPlayerDb.lpsetEchoAudio(lpmodeleffects.getLpfloatEcho());
            this.lpMediaPlayerDb.lpsetFilterQuad(lpmodeleffects.getLpfloatFilter());
            this.lpMediaPlayerDb.lpsetEffectFlang(lpmodeleffects.getLpfloatFlange());
            this.lpMediaPlayerDb.lpchorusSet(lpmodeleffects.getLpfloatChorus());
            this.lpMediaPlayerDb.lpsetAmpli(lpmodeleffects.getLpfloatAmplify());
            this.lpMediaPlayerDb.lpdisortSet(lpmodeleffects.getLpfloatDistort());
            this.lpMediaPlayerDb.lprotateSet(lpmodeleffects.getLpfloatRotate());
            this.lpMediaPlayerDb.lpaudioStart();
        }
    }

    private void lponInitAudioDevice() {
        if (this.lpisInitIs) {
            return;
        }
        this.lpisInitIs = true;
        if (!BASS.BASS_Init(-1, 44100, 0)) {

            new Exception("VoiceChangerModule Can't initialize device").printStackTrace();

            this.lpisInitIs = false;
            return;
        }
        String str = this.lpcontext.getApplicationInfo().nativeLibraryDir;
        try {
            BASS.BASS_PluginLoad(str + "/libbass_fx.so", 0);
            BASS.BASS_PluginLoad(str + "/libbassenc.so", 0);
            BASS.BASS_PluginLoad(str + "/libbassmix.so", 0);
            BASS.BASS_PluginLoad(str + "/libbasswv.so", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSaveEffect(lpModelEffects lpmodeleffects, final String str, final lpBaseCallback lpbasecallback) {
        if (this.lpMediaPlayerDb != null) {
            this.lpstrChangeVoiceName = str + ".wav";

            startSaveEffect(lpmodeleffects, () -> {
                File file = new File(lpChangeEffectsModule.this.lpfileOutputDirectory, str);
                if (file.exists()) {
                    lpChangeEffectsModule.this.toastShow(String.format("Your voice path is %1$s", file.getAbsolutePath()));
                }
                lpbasecallback.onSuccess();
            });
        }
    }

    private void startSaveEffect(final lpModelEffects lpmodeleffects, final lpInterFaceCallBack lpinterfacecallback) {
        final File file = new File(this.lpfileOutputDirectory, this.lpstrChangeVoiceName);
        final lpMediaPlayerLpDb lpmediaplayerlpdb = new lpMediaPlayerLpDb(this.lpstrAudioPath);
        new lpDatabaseTa(new lpTaskListener() {
            @Override
            public void onPreExecuteTask() {
            }

            @Override
            public void onDoInBackgroundTask() {
                if (lpmediaplayerlpdb.lpinitSolveToMedia()) {
                    lpmediaplayerlpdb.lpsetSkisThisReverse(lpmodeleffects.isLpisReverseBool());
                    lpmediaplayerlpdb.lpsetPitchAudio(lpmodeleffects.getLpintPitch());
                    lpmediaplayerlpdb.lpcompressorSet(lpmodeleffects.getLpfloatCompressor());
                    lpmediaplayerlpdb.lpsetRateAudio(lpmodeleffects.getLpfloatRate());
                    lpmediaplayerlpdb.lpsetEQ1Audio(lpmodeleffects.getLpfloatyEcho1());
                    lpmediaplayerlpdb.lpsetEQ2Audio(lpmodeleffects.getLpfloatEq2());
                    lpmediaplayerlpdb.lpsetEQ3Audio(lpmodeleffects.getLpfloatEq3());
                    lpmediaplayerlpdb.lpphrserSet(lpmodeleffects.getLpfloatPhaser());
                    lpmediaplayerlpdb.lpsetWahAuto(lpmodeleffects.getLpfloatAutoWah());
                    lpmediaplayerlpdb.lpsetReverbAudio(lpmodeleffects.getLpfloatReverb());
                    lpmediaplayerlpdb.lpsetEffectEcho4(lpmodeleffects.getLpfloatEcho4());
                    lpmediaplayerlpdb.lpsetEchoAudio(lpmodeleffects.getLpfloatEcho());
                    lpmediaplayerlpdb.lpsetFilterQuad(lpmodeleffects.getLpfloatFilter());
                    lpmediaplayerlpdb.lpsetEffectFlang(lpmodeleffects.getLpfloatFlange());
                    lpmediaplayerlpdb.lpchorusSet(lpmodeleffects.getLpfloatChorus());
                    lpmediaplayerlpdb.lpsetAmpli(lpmodeleffects.getLpfloatAmplify());
                    lpmediaplayerlpdb.lpdisortSet(lpmodeleffects.getLpfloatDistort());
                    lpmediaplayerlpdb.lprotateSet(lpmodeleffects.getLpfloatRotate());
                    lpmediaplayerlpdb.lpsaveAsFile(file.getAbsolutePath());
                    lpmediaplayerlpdb.lpaudioRelease();
                }
            }

            @Override
            public void onPostExecuteTask() {
                lpInterFaceCallBack lpinterfacecallback2 = lpinterfacecallback;
                if (lpinterfacecallback2 != null) {
                    lpinterfacecallback2.onCallBackAction();
                }
            }
        }).execute(new Void[0]);
    }

    private void onCreateDBMedia() {
        String str = this.lpstrAudioPath;
        if (str != null || !str.equals("")) {
            lpMediaPlayerLpDb lpmediaplayerlpdb = new lpMediaPlayerLpDb(this.lpstrAudioPath);
            this.lpMediaPlayerDb = lpmediaplayerlpdb;
            lpmediaplayerlpdb.lpaudioPrepare();
            this.lpMediaPlayerDb.lpsetOnDBMediaListener(new lpDBMediaListener() { // from class: com.reactlibrary.lpChangeEffectsModule.3
                public void onMediaError() {
                }

                @Override
                public void onMediaCompleteListener() {
                    lpChangeEffectsModule.this.lpModelEffects.get(lpChangeEffectsModule.this.lpindexPLaying).setLpisPlayingBool(false);
                    lpChangeEffectsModule.this.setLpindexPLaying(null);
                }
            });
            return;
        }
        toastShow("Media file not found!");
    }

    public void toastShow(String str) {
        Toast makeText = Toast.makeText(this.lpcontext, str, Toast.LENGTH_SHORT);
        makeText.setGravity(80, 0, 0);
        makeText.show();
    }

    public void onStateReset() {
        ArrayList<lpModelEffects> arrayList = this.lpModelEffects;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.lpModelEffects.size(); i++) {
            if (this.lpModelEffects.get(i).isLpisPlayingBool()) {
                this.lpModelEffects.get(i).setLpisPlayingBool(false);
            }
        }
    }

    private File getDirectory(Activity activity) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + activity.getResources().getString(R.string.app_name) + "/VoiceEffects");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
