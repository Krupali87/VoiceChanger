package com.app.voicechangereffect.custUi.constatnt;

import android.util.Log;
import android.view.View;
import com.app.voicechangereffect.tapListener.lpCustomTapListener;
import kotlin.jvm.functions.Function1;

public final class lpTapClick {
    public static void lptap(View view, final Function1 function1) {
        view.setOnClickListener(new lpCustomTapListener() { // from class: com.app.voicechangereffect.custUi.constatnt.lpTapClick.1
            @Override // com.app.voicechangereffect.tapListener.lpCustomTapListener
            public void onTap(View view2) {
                Log.d("checktap", "ontap clicked");
           function1.invoke(view2);
            }
        });
    }
}
