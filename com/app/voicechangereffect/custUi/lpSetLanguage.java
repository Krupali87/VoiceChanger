package com.app.voicechangereffect.custUi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.app.voicechangereffect.lpMainApplication;
import java.util.Locale;
public class lpSetLanguage  {
    public static void lpsetLocale(Activity context) {
        String defaultLanguage = lpMainApplication.getPrefManager().getDefaultLanguage();
        if (defaultLanguage.length() != 0) {
            lpsetLanguagesString(context, defaultLanguage);
        }
    }

    public static void lpsetLanguagesString(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
