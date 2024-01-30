package com.app.voicechangereffect.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.activity.lpChangeEffectActivity;
import com.app.voicechangereffect.adapters.lpItemEffectAdapterLp;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.databinding.FragAllEffectsBinding;
import com.app.voicechangereffect.getApiData.allModel.lpEffectModel;
import com.app.voicechangereffect.viewModel.lpChangeEffectViewModel;
import java.util.List;


import kotlin.jvm.internal.Intrinsics;



public final class fragAllEffects extends lpBaseFragment<lpChangeEffectViewModel, FragAllEffectsBinding> {
    public lpItemEffectAdapterLp lpItemEffectAdapter;
    private BroadcastReceiver lpreceiver = new BroadcastReceiver() { // from class: com.app.voicechangereffect.fragments.fragAllEffects.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            lpEffectModel lpeffectmodel;
            lpItemEffectAdapterLp lpitemeffectadapterlp;
            if (!Intrinsics.areEqual(intent == null ? null : intent.getAction(), "select_effect") || (lpeffectmodel = (lpEffectModel) intent.getParcelableExtra("effect_model")) == null || (lpitemeffectadapterlp = fragAllEffects.this.lpItemEffectAdapter) == null) {
                return;
            }
            lpitemeffectadapterlp.lpselectEffectItem(lpeffectmodel);
        }
    };

    @Override
    public void bindViewModels() {
    }

    @Override
    public int lpgetFragResourceLayout() {
        return R.layout.frag_all_effects;
    }

    @Override
    public Class<lpChangeEffectViewModel> createViewModel() {
        return lpChangeEffectViewModel.class;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void bindViews() {
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.lpreceiver, new IntentFilter("select_effect"));
        List<lpEffectModel> allVoiceEffects = getLpviewModel().getAllVoiceEffects(requireContext());
        RecyclerView recyclerView = getLpdataBinding().lprclEffect;
        recyclerView.setHasFixedSize(true);

        lpItemEffectAdapterLp lpitemeffectadapterlp = new lpItemEffectAdapterLp(requireContext(), allVoiceEffects, it -> {
            Intrinsics.checkNotNullParameter(it, "it");
            lpItemEffectAdapterLp lpitemeffectadapterlp2 = fragAllEffects.this.lpItemEffectAdapter;
            if (lpitemeffectadapterlp2 != null) {
                lpitemeffectadapterlp2.lpselectEffectItem(it);
            }
            Context context = fragAllEffects.this.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            Intrinsics.checkNotNull(it.getLpnameOrigin());
         ((lpChangeEffectActivity) fragAllEffects.this.requireActivity()).lpplayEffect(it.getLpid());
            lpChangeEffectActivity.lpCompanion.setEffectModelSelected(it);
            LocalBroadcastManager.getInstance(fragAllEffects.this.requireContext()).sendBroadcast(new Intent("select_effect").putExtra("effect_model", it));
            return null;
        });
        this.lpItemEffectAdapter = lpitemeffectadapterlp;
        recyclerView.setAdapter(lpitemeffectadapterlp);
    }

    @Override
    public void onResume() {
        lpItemEffectAdapterLp lpitemeffectadapterlp;
        super.onResume();
        lpEffectModel effectModelSelected = lpChangeEffectActivity.lpCompanion.getEffectModelSelected();
        if (effectModelSelected == null || (lpitemeffectadapterlp = this.lpItemEffectAdapter) == null) {
            return;
        }
        lpitemeffectadapterlp.lpselectEffectItem(effectModelSelected);
    }
}
