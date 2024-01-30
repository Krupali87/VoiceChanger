package com.app.voicechangereffect.adapters;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.voicechangereffect.fragments.fragAllEffects;
import com.app.voicechangereffect.fragments.fragOtherEffects;
import com.app.voicechangereffect.fragments.fragPeopleEffects;
import com.app.voicechangereffect.fragments.fragRobotEffects;
import com.app.voicechangereffect.fragments.fragScaryEffects;
import kotlin.jvm.internal.Intrinsics;

public final class lpEffectVoiceAdapter extends FragmentPagerAdapter {
    @Override
    public int getCount() {
        return 5;
    }

    public lpEffectVoiceAdapter(FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new fragAllEffects();
        }
        if (i == 1) {
            return new fragScaryEffects();
        }
        if (i == 2) {
            return new fragOtherEffects();
        }
        if (i == 3) {
            return new fragPeopleEffects();
        }
        if (i == 4) {
            return new fragRobotEffects();
        }
        return new fragAllEffects();
    }
}
