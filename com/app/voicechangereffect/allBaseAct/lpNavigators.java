package com.app.voicechangereffect.allBaseAct;

import android.os.Bundle;
import kotlin.reflect.KClass;

/* loaded from: classes3.dex */
public interface lpNavigators {
    void fragmentRequestInject(lpBaseFragment<?, ?> lpbasefragment);

    void navigate(int i, Bundle bundle, boolean z);

    void navigateUp();

    /* JADX WARN: Multi-variable type inference failed */
    // com.app.voicechangereffect.Ad.skAppOpenOne, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    void onCreate(Bundle bundle);

    void nextActivity(Class<?> cls, Bundle bundle);

    void onFragmentResumed(lpBaseFragment<?, ?> lpbasefragment);

    void switchFragment(KClass<?> kClass, Bundle bundle, boolean z);

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void lpshowDefaultAct(lpNavigators lpnavigators, Class cls, Bundle bundle, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    bundle = null;
                }
                lpnavigators.nextActivity(cls, bundle);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showActivity");
        }
    }
}
