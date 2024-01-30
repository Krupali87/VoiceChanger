package com.app.voicechangereffect;

import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;


public final class lpComparisons<T> implements Comparator {
    @Override
    public int compare(Object obj, Object obj2) {
        String lowerCase = ((lpAudioModel) obj2).getLpfileName().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String lowerCase2 = ((lpAudioModel) obj).getLpfileName().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        return ComparisonsKt.compareValues(lowerCase, lowerCase2);
    }
}
