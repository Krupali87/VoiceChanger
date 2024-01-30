package com.app.voicechangereffect.custUi;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.app.voicechangereffect.R;
import java.io.File;


public class lpFileMethods {
    public static String lpgetMainDirPath(Context context) {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + context.getResources().getString(R.string.app_name), "VoiceEffectAudio");
            Log.e("xz---", "getMainDirPath: voiceEffectAudioFilePath ::  " + file);
            if (!file.exists()) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return context.getFilesDir().getAbsolutePath();
        }
    }

    public static File lpgetDirectory(Activity activity) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + activity.getResources().getString(R.string.app_name) + "/VoiceEffects");
        Log.e("xz---", "getMainDirPath: voiceEffectDirPath ::  " + file);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String milliSecFormat(long j) {
        String str;
        String str2;
        int i = (int) (j / 3600000);
        long j2 = j % 3600000;
        int i2 = ((int) j2) / 60000;
        int round = Math.round((float) ((j2 % 60000) / 1000));
        String str3 = i > 0 ? i + ":" : "";
        if (i2 < 10) {
            str ="0" + i2;
        } else {
            str = "" + i2;
        }
        if (round < 10) {
            str2 = "0" + round;
        } else {
            str2 = "" + round;
        }
        return str3 + str + ":" + str2;
    }
}
