package com.app.voicechangereffect.tapListener;

import android.os.SystemClock;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class lpCustomTapListener implements View.OnClickListener {
    public static final Companion lpCompanion = new Companion(null);
    private static final long lpdeBounce = 300;
    private long lplastMillClick;
    private long lpnowTime;

    public abstract void onTap(View view);


    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override
    public void onClick(View view) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.lpnowTime = elapsedRealtime;
        if (elapsedRealtime - this.lplastMillClick > lpdeBounce) {
            onTap(view);
            this.lplastMillClick = this.lpnowTime;
        }

    }
}
