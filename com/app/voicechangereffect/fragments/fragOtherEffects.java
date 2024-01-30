package com.app.voicechangereffect.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.activity.lpChangeEffectActivity;
import com.app.voicechangereffect.adapters.lpItemEffectAdapterLp;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.databinding.FragAllEffectsBinding;
import com.app.voicechangereffect.getApiData.allModel.lpEffectModel;
import com.app.voicechangereffect.viewModel.lpChangeEffectViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class fragOtherEffects extends lpBaseFragment<lpChangeEffectViewModel, FragAllEffectsBinding> {
    public lpItemEffectAdapterLp lpItemEffectAdapter;
    private BroadcastReceiver lpmodel = new BroadcastReceiver() { // from class: com.app.voicechangereffect.fragments.fragOtherEffects.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            lpEffectModel lpeffectmodel;
            lpItemEffectAdapterLp lpitemeffectadapterlp;
            if (!Intrinsics.areEqual(intent == null ? null : intent.getAction(), "select_effect") || (lpeffectmodel = intent.getParcelableExtra("effect_model")) == null || (lpitemeffectadapterlp = fragOtherEffects.this.lpItemEffectAdapter) == null) {
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
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.lpmodel, new IntentFilter("select_effect"));
        List<lpEffectModel> allOtherEffects = getLpviewModel().getAllOtherEffects(requireContext());
        getLpdataBinding().lprclEffect.setHasFixedSize(true);

        lpItemEffectAdapterLp lpitemeffectadapterlp = new lpItemEffectAdapterLp(requireContext(), allOtherEffects, lpeffectmodel -> {
            lpItemEffectAdapterLp lpitemeffectadapterlp2 = fragOtherEffects.this.lpItemEffectAdapter;
            if (lpitemeffectadapterlp2 != null) {
                lpitemeffectadapterlp2.lpselectEffectItem(lpeffectmodel);
            }
            fragOtherEffects.this.getContext();
            lpeffectmodel.getLpnameOrigin();
            ((lpChangeEffectActivity) fragOtherEffects.this.requireContext()).lpplayEffect(lpeffectmodel.getLpid());
            lpChangeEffectActivity.lpCompanion.setEffectModelSelected(lpeffectmodel);
            LocalBroadcastManager.getInstance(fragOtherEffects.this.requireContext()).sendBroadcast(new Intent("select_effect").putExtra("effect_model", lpeffectmodel));
            return null;
        });
        this.lpItemEffectAdapter = lpitemeffectadapterlp;
        getLpdataBinding().lprclEffect.setAdapter(lpitemeffectadapterlp);
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
