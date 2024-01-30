package com.app.voicechangereffect.getApiData.localData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.inject.Inject;


public final class lpHelpPrefClass implements lpHelperPreference {
    public static final String lpguideKey = "guide";
    private SharedPreferences lppreferences;

    private EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject 
    public lpHelpPrefClass(Context context, String str) {
        try {
            try {
                this.lppreferences = EncryptedSharedPreferences.create(str, MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            } catch (IOException e) {
                Log.e("eee--", "PreferencesHelper: e ::  " + e.getMessage());
                throw new RuntimeException(e);
            } catch (GeneralSecurityException e2) {
                throw new RuntimeException(e2);
            }
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override
    public String getToken() {
        return this.lppreferences.getString("token", "");
    }

    @Override
    public void setToken(String str) {
        SharedPreferences.Editor edit = this.lppreferences.edit();
        edit.putString("token", str);
        edit.apply();
    }

    @Override
    public boolean getGuide() {
        return this.lppreferences.getBoolean(lpguideKey, false);
    }

    @Override
    public void setGuide(boolean z) {
        SharedPreferences.Editor edit = this.lppreferences.edit();
        edit.putBoolean(lpguideKey, z);
        edit.apply();
    }
}
