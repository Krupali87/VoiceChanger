package com.app.voicechangereffect.fragments;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.activity.lpChangeEffectActivity;
import com.app.voicechangereffect.activity.lpOpenFileActivity;
import com.app.voicechangereffect.adapters.lpAudioAdapterLp;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.databinding.FragAudioBinding;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import com.app.voicechangereffect.viewModel.lpDeviceMusicViewModelLp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class fragAudio extends lpBaseFragment<lpDeviceMusicViewModelLp, FragAudioBinding> {
    private lpAudioAdapterLp lpAudioAdapter;
    private fragAudio lpfragAudio;

    @Override
    public Class<lpDeviceMusicViewModelLp> createViewModel() {
        return lpDeviceMusicViewModelLp.class;
    }

    @Override
    public int lpgetFragResourceLayout() {
        this.lpfragAudio = this;
        return R.layout.frag_audio;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void bindViews() {
        getLpdataBinding().rclAudio.setLayoutManager(new LinearLayoutManager(getContext()));

        lpAudioAdapterLp lpaudioadapterlp = new lpAudioAdapterLp(requireContext(), new ArrayList(), (Function1) obj -> fragAudio.this.m415xaf290e35((lpAudioModel) obj));
        this.lpAudioAdapter = lpaudioadapterlp;
        getLpdataBinding().rclAudio.setAdapter(lpaudioadapterlp);
    }

    public Unit m415xaf290e35(lpAudioModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Bundle bundle = new Bundle();
        bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), it.getLppath());
        bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "AudioFragment");
       int dduretion= Integer.parseInt(it.getLpduration());

         bundle.putInt("duretion",dduretion);


        showActivity(lpChangeEffectActivity.class, bundle);
        return null;
    }

    @Override
    public void bindViewModels() {
        Objects.requireNonNull(getLpviewModel()).getAudioDataClass(requireContext(), getLpdataBinding().llLoading, getLpdataBinding().noData);

        lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().observe(requireActivity(), (Observer) obj -> fragAudio.this.lpm413xa82aa56d((Integer) obj));

        lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().observe(requireActivity(), (Observer) obj -> fragAudio.this.lpm414x997c34ee((Integer) obj));
    }

    public void lpm413xa82aa56d(Integer num) {
        if (num != null && num.intValue() == 1) {

            Objects.requireNonNull(this.lpfragAudio.getLpviewModel()).getMutableLiveData().observe(this.lpfragAudio.requireActivity(), (Observer) obj -> fragAudio.this.lpm411xc587866b((List) obj));
        } else if (num == null || num.intValue() != 2) {
        } else {

            Objects.requireNonNull(this.lpfragAudio.getLpviewModel()).getMutableLiveData().observe(this.lpfragAudio.requireActivity(), (Observer) obj -> fragAudio.this.lpm412xb6d915ec((List) obj));
        }
    }

    public void lpm411xc587866b(List list) {
        lpAudioAdapterLp lpaudioadapterlp = this.lpfragAudio.lpAudioAdapter;
        if (lpaudioadapterlp != null) {
            lpaudioadapterlp.lpsortListRefresh(list);
        }
    }

    public void lpm412xb6d915ec(List list) {
        lpAudioAdapterLp lpaudioadapterlp;
        if (list == null || (lpaudioadapterlp = this.lpfragAudio.lpAudioAdapter) == null) {
            return;
        }
        lpaudioadapterlp.oldDataSort(list);
    }

    public void lpm414x997c34ee(Integer num) {
        fragAudio this$0 = this.lpfragAudio;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (num != null && num.intValue() == 1) {

            Objects.requireNonNull(this.lpfragAudio.getLpviewModel()).getMutableLiveData().observe(this.lpfragAudio.requireActivity(), list -> {
                lpAudioAdapterLp lpaudioadapterlp;
                fragAudio this$02 = fragAudio.this.lpfragAudio;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                if (list == null || (lpaudioadapterlp = fragAudio.this.lpfragAudio.lpAudioAdapter) == null) {
                    return;
                }
                lpaudioadapterlp.lpsortByFileName(list);
            });
        } else if (num == null || num.intValue() != 2) {
        } else {

            this.lpfragAudio.getLpviewModel().getMutableLiveData().observe(this.lpfragAudio.requireActivity(), list -> {
                lpAudioAdapterLp lpaudioadapterlp;
                fragAudio this$02 = fragAudio.this.lpfragAudio;
                Intrinsics.checkNotNullParameter(this$02, "this$0");
                if (list == null || (lpaudioadapterlp = fragAudio.this.lpfragAudio.lpAudioAdapter) == null) {
                    return;
                }
                lpaudioadapterlp.lpsortOldByName(list);
            });
        }
    }
}
