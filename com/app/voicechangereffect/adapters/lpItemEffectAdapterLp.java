package com.app.voicechangereffect.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseAdapter;
import com.app.voicechangereffect.databinding.ItemEffectAdapterBinding;
import com.bumptech.glide.Glide;
import com.app.voicechangereffect.getApiData.allModel.lpEffectModel;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class lpItemEffectAdapterLp extends lpBaseAdapter<ItemEffectAdapterBinding, lpEffectModel> {
    private List<lpEffectModel> lpeffectModelLps;
    private Context lpmCtx;
    private Function1 lponClickItem;

    @Override
    public int getLayoutRes() {
        return R.layout.item_effect_adapter;
    }

    public final Function1 getLponClickItem() {
        return this.lponClickItem;
    }

    public lpItemEffectAdapterLp(Context context, List<lpEffectModel> list, Function1<? super lpEffectModel, Unit> function1) {
        super(list);
        this.lpmCtx = context;
        this.lpeffectModelLps = list;
        this.lponClickItem = function1;
    }

    @Override
    public RecyclerView.ViewHolder createVH(ItemEffectAdapterBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        return new EffectViewHolder(this, binding);
    }

    public final class EffectViewHolder extends lpBaseAdapter<ItemEffectAdapterBinding, lpEffectModel>.BaseVH<Object> {
        ItemEffectAdapterBinding lpbinding;
        final lpItemEffectAdapterLp lpeffectAdapter;

        public EffectViewHolder(lpItemEffectAdapterLp lpitemeffectadapterlp, ItemEffectAdapterBinding itemEffectAdapterBinding) {
            super(lpitemeffectadapterlp, itemEffectAdapterBinding);
            this.lpeffectAdapter = lpitemeffectadapterlp;
            this.lpbinding = itemEffectAdapterBinding;
        }

        @Override
        public void onItemClickListener(lpEffectModel lpeffectmodel) {
            super.onItemClickListener(lpeffectmodel);
            lpItemEffectAdapterLp.this.getLponClickItem().invoke(lpeffectmodel);
        }

        @Override
        public void bind(lpEffectModel lpeffectmodel) {
            super.bind(lpeffectmodel);
            ItemEffectAdapterBinding itemEffectAdapterBinding = this.lpbinding;
            if (itemEffectAdapterBinding != null) {
                itemEffectAdapterBinding.tvEffect.setText("" + lpeffectmodel.getLpname());
                this.lpbinding.tvEffect.setText(lpeffectmodel.getLpname());
                if (lpeffectmodel.isLpisActive()) {
                    this.lpbinding.tvEffect.setTextColor(lpItemEffectAdapterLp.this.lpmCtx.getResources().getColor(R.color.black));
                    Glide.with(lpItemEffectAdapterLp.this.lpmCtx).load(Integer.valueOf(lpeffectmodel.getLpiconUnSelected())).into(this.lpbinding.ivAvt);
                    return;
                }
                this.lpbinding.tvEffect.setTextColor(lpItemEffectAdapterLp.this.lpmCtx.getResources().getColor(R.color.black));
                Glide.with(lpItemEffectAdapterLp.this.lpmCtx).load(Integer.valueOf(lpeffectmodel.getLpiconSelected())).into(this.lpbinding.ivAvt);
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void lpselectEffectItem(lpEffectModel lpeffectmodel) {
        for (lpEffectModel lpeffectmodel2 : this.lpeffectModelLps) {
            lpeffectmodel2.setLpisActive(Intrinsics.areEqual(lpeffectmodel2.getLpname(), lpeffectmodel.getLpname()));
        }
        notifyDataSetChanged();
    }
}
