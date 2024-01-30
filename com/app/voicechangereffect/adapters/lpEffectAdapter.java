package com.app.voicechangereffect.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseAdapter;
import com.app.voicechangereffect.databinding.ItemTypeEffectAdapterBinding;
import com.app.voicechangereffect.getApiData.allModel.lpTypeEffectModel;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;


public final class lpEffectAdapter extends lpBaseAdapter<ItemTypeEffectAdapterBinding, lpTypeEffectModel> {
    private Function2 lpfunction2;
    private Context lpmContext;
    private List<lpTypeEffectModel> lptypeEffectModelListLp;

    @Override
    public int getLayoutRes() {
        return R.layout.item_type_effect_adapter;
    }

    public Function2 getLpfunction2() {
        return this.lpfunction2;
    }

    public lpEffectAdapter(Context context, List<lpTypeEffectModel> list, Function2<? super lpTypeEffectModel, ? super Integer, Unit> function2) {
        super(list);
        this.lpmContext = context;
        this.lptypeEffectModelListLp = list;
        this.lpfunction2 = function2;
    }

    @Override
    public RecyclerView.ViewHolder createVH(ItemTypeEffectAdapterBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        return new TypeEffectViewHolder(this, binding);
    }


    public final class TypeEffectViewHolder extends lpBaseAdapter<ItemTypeEffectAdapterBinding, lpTypeEffectModel>.BaseVH<Object> {
        final lpEffectAdapter lpeffectAdapter;

        public TypeEffectViewHolder(lpEffectAdapter this$0, ItemTypeEffectAdapterBinding binding) {
            super(this$0, binding);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.lpeffectAdapter = this$0;
        }

        @Override
        public void onItemClickListener(lpTypeEffectModel data) {
            Intrinsics.checkNotNullParameter(data, "data");
            super.onItemClickListener( data);
            this.lpeffectAdapter.selectItem(data);
            this.lpeffectAdapter.getLpfunction2().invoke(data, Integer.valueOf(getPosition()));
        }

        @Override
        public void bind(lpTypeEffectModel data) {
            Intrinsics.checkNotNullParameter(data, "data");
            super.bind(data);
            getLpbinding().txtEffectType.setText(data.getLptype());
            if (data.isLpisActive()) {
                getLpbinding().txtEffectType.setTextColor(lpEffectAdapter.this.lpmContext.getColor(R.color.white));
                getLpbinding().lnmain.setBackgroundResource(R.drawable.seletc_view);
                return;
            }
            getLpbinding().txtEffectType.setTextColor(lpEffectAdapter.this.lpmContext.getColor(R.color.black));
            getLpbinding().lnmain.setBackgroundResource(R.drawable.unseletc_view);
        }
    }

    public void selectItem(lpTypeEffectModel lptypeeffectmodel) {
        try {
            for (lpTypeEffectModel lptypeeffectmodel2 : this.lptypeEffectModelListLp) {
                lptypeeffectmodel2.setLpisActive(Intrinsics.areEqual(lptypeeffectmodel2, lptypeeffectmodel));
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void lpselItemPosition(int i) {
        try {
            lpTypeEffectModel lptypeeffectmodel = this.lptypeEffectModelListLp.get(i);
            for (lpTypeEffectModel lptypeeffectmodel2 : this.lptypeEffectModelListLp) {
                lptypeeffectmodel2.setLpisActive(Intrinsics.areEqual(lptypeeffectmodel2, lptypeeffectmodel));
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
