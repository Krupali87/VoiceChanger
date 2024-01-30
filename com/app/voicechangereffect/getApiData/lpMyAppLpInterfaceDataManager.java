package com.app.voicechangereffect.getApiData;

import com.app.voicechangereffect.getApiData.localData.lpHelperPreference;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.jvm.internal.Intrinsics;

@Singleton

public final class lpMyAppLpInterfaceDataManager implements lpInterfaceDataManager {
    private final lpHelperPreference lpmPreferencesHelper;

    @Inject
    public lpMyAppLpInterfaceDataManager(lpHelperPreference mPreferencesHelper) {
        Intrinsics.checkNotNullParameter(mPreferencesHelper, "mPreferencesHelper");
        this.lpmPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public String getToken() {
        return this.lpmPreferencesHelper.getToken();
    }

    @Override
    public void setToken(String str) {
        this.lpmPreferencesHelper.setToken(str);
    }

    @Override
    public boolean getGuide() {
        return this.lpmPreferencesHelper.getGuide();
    }

    @Override
    public void setGuide(boolean z) {
        this.lpmPreferencesHelper.setGuide(z);
    }
}
