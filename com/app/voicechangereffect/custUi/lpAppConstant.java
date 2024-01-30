package com.app.voicechangereffect.custUi;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.jvm.internal.Intrinsics;

public final class lpAppConstant {
    public static final lpAppConstant lpAPP_CONSTANT = new lpAppConstant();
    public static final String lpactionService = "action_service";
    public static final String lpactionStart = "action_start";
    public static final String lpactionStop = "action_stop";
    public static final String lpchannelId = "save_audio_service";
    public static final String skchannelName = "save audio service";
    private static final String skkeyDurationVoice = "key_duration_effect";
    private static final String skkeyPathVoice = "key_path_voice";
    private static final String skkeyPositionEffect = "key_position_effect";
    private static final String skkeyScreenIntoVoiceEffect = "key_screen_into_voice_effect";
    private static final String skkeySizeVoice = "key_size_effect";

    public String getKEY_DURATION_VOICE() {
        return skkeyDurationVoice;
    }

    public String getKEY_FILENAME_EFFECT() {
        return "key_filename_effect";
    }

    public String getKEY_PATH_VOICE() {
        return skkeyPathVoice;
    }

    public String getKEY_POSITION_EFFECT() {
        return skkeyPositionEffect;
    }

    public String getKEY_SCREEN_INTO_VOICE_EFFECTS() {
        return skkeyScreenIntoVoiceEffect;
    }

    public String getKEY_SIZE_VOICE() {
        return skkeySizeVoice;
    }

    public void setCheckResumePermissionMain(boolean z) {
    }

    public void setCheckResumePermissionRingtone(boolean z) {
    }


    public void setCheckResumeShareMyVoice(boolean z) {
    }

    private lpAppConstant() {
    }

    public String getVoiceEffect(Context context) {
        try {
            InputStream open = context.getAssets().open("effects.json");
            Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(\"effects.json\")");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            return new String(bArr, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
