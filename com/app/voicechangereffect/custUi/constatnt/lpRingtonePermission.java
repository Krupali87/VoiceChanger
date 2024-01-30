package com.app.voicechangereffect.custUi.constatnt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.app.voicechangereffect.allDialogs.lpRingtonesPermissionDialogLp;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class lpRingtonePermission {
    public static boolean lpcheckSystemWritePermission(Context context) {
        return Settings.System.canWrite(context);
    }

    public static void lpopenAndroidPermissionsMenu(final Activity activity) {

        new lpRingtonesPermissionDialogLp(activity, true, () -> {
            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
            intent.setData(Uri.parse("package:com.app.voicechangereffect"));
            activity.startActivity(intent);
            return null;
        }).show();
    }
}
