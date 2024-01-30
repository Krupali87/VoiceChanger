package com.app.voicechangereffect.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.voicechangereffect.fragments.fragAudioStudio;


public final class lpCreationPagerAdapter extends FragmentPagerAdapter {
    @Override
    public int getCount() {
        return 1;
    }

    public lpCreationPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        return new fragAudioStudio();
    }
}
