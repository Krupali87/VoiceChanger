package com.app.voicechangereffect.langClass;

import android.content.Context;
import android.content.SharedPreferences;

public class lpPrefManager {
    private static final String lpAPP_LANGUAGE = "APP_LANGUAGE";
    private static final String lpIS_FIRST_INSTALL_APP = "IS_FIRST_INSTALL_APP";
    private static lpPrefManager lpinstance;
    private final SharedPreferences.Editor lpeditor;
    private final SharedPreferences lppref;

    private lpPrefManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("blood_pressure", 0);
        this.lppref = sharedPreferences;
        this.lpeditor = sharedPreferences.edit();
    }

    public static lpPrefManager getInstance(Context context) {
        if (lpinstance == null) {
            lpinstance = new lpPrefManager(context);
        }
        return lpinstance;
    }

    public String getDefaultLanguage() {
        return this.lppref.getString(lpAPP_LANGUAGE, "en");
    }

    public void lpsetAppLanguage(String str) {
        this.lpeditor.putString(lpAPP_LANGUAGE, str);
        this.lpeditor.commit();
    }

    public boolean lpisFirstInstallApp() {
        return this.lppref.getBoolean(lpIS_FIRST_INSTALL_APP, true);
    }

    public void lpsetFirstInstallApp(boolean z) {
        this.lpeditor.putBoolean(lpIS_FIRST_INSTALL_APP, z);
        this.lpeditor.commit();
    }
}
