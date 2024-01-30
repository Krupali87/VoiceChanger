package com.app.voicechangereffect.allBaseAct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public abstract class lpBaseAdapter<DB extends ViewDataBinding, M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<M> listData;
    protected RecyclerView recyclerView;

    public abstract RecyclerView.ViewHolder createVH(DB db);

    public abstract int getLayoutRes();

    public final List<M> getListData() {
        return this.listData;
    }

    public lpBaseAdapter(List<M> listData) {
        Intrinsics.checkNotNullParameter(listData, "listData");
        this.listData = listData;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.recyclerView = recyclerView;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        setRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutRes(), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f\u2026layoutRes, parent, false)");
        return createVH((DB) inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((BaseVH) viewHolder).bind(this.listData.get(i));
    }

    @Override
    public int getItemCount() {
        return this.listData.size();
    }

    public final void addList(List<M> list) {
        this.listData.clear();
        this.listData.addAll(list);
        notifyDataSetChanged();
    }


    public abstract class BaseVH<T> extends RecyclerView.ViewHolder {
        private final DB lpbinding;
        final lpBaseAdapter<DB, M> lpthis$0;

        public void onItemClickListener(M m) {
        }

        public BaseVH(lpBaseAdapter this$0, DB binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.lpthis$0 = this$0;
            this.lpbinding = binding;
            View root = binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.root");
            lpTapClick.lptap(root, (Function1<View, Unit>) view -> {
                BaseVH baseVH = BaseVH.this;
                baseVH.onItemClickListener(lpBaseAdapter.this.getListData().get(BaseVH.this.getAdapterPosition()));
                return null;
            });
        }

        public final DB getLpbinding() {
            return this.lpbinding;
        }

        public void bind(M m) {
            this.lpbinding.setVariable(1, m);
            this.lpbinding.executePendingBindings();
        }
    }
}
