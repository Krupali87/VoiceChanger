package com.app.voicechangereffect.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.voicechangereffect.fragments.fragAudio;
import kotlin.jvm.internal.Intrinsics;


public final class lpDeviceVideoPager extends FragmentPagerAdapter {
    @Override
    public int getCount() {
        return 1;
    }

    public lpDeviceVideoPager(FragmentManager fm) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
    }

    @Override
    public Fragment getItem(int i) {
        return new fragAudio();
    }
}
