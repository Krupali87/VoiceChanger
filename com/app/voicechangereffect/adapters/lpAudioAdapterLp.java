package com.app.voicechangereffect.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseAdapter;
import com.app.voicechangereffect.databinding.ItemAudioAdapterBinding;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import com.app.voicechangereffect.lpComparisons;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class lpAudioAdapterLp extends lpBaseAdapter<ItemAudioAdapterBinding, lpAudioModel> {
    private final List<lpAudioModel> lpaudioModelList;
    private final Function1 lpunitFunction1;

    @Override
    public int getLayoutRes() {
        return R.layout.item_audio_adapter;
    }

    public  Function1 getLpunitFunction1() {
        return this.lpunitFunction1;
    }

    public lpAudioAdapterLp(Context context, List<lpAudioModel> list, Function1<? super lpAudioModel, Unit> function1) {
        super(list);
        this.lpaudioModelList = list;
        this.lpunitFunction1 = function1;
    }

    @Override
    public RecyclerView.ViewHolder createVH(ItemAudioAdapterBinding itemAudioAdapterBinding) {
        return new AudioViewHolder(this, itemAudioAdapterBinding);
    }

    public final class AudioViewHolder extends lpBaseAdapter<ItemAudioAdapterBinding, lpAudioModel>.BaseVH<Object> {
        final lpAudioAdapterLp lpaudioAdapter;

        public AudioViewHolder(lpAudioAdapterLp lpaudioadapterlp, ItemAudioAdapterBinding itemAudioAdapterBinding) {
            super(lpaudioadapterlp, itemAudioAdapterBinding);
            this.lpaudioAdapter = lpaudioadapterlp;
        }

        @Override
        public void onItemClickListener(lpAudioModel lpaudiomodel) {
            super.onItemClickListener(lpaudiomodel);
            this.lpaudioAdapter.getLpunitFunction1().invoke(lpaudiomodel);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void bind(lpAudioModel lpaudiomodel) {
            super.bind(lpaudiomodel);
            getLpbinding().tvName.setText(lpaudiomodel.getLpfileName() + '.' + lpaudiomodel.getLptype());
            getLpbinding().tvDetail.setText(lpAudioAdapterLp.this.convertLongDataToDuration(Long.parseLong(lpaudiomodel.getLpduration())) + " | " + lpAudioAdapterLp.this.convertSize(new File(lpaudiomodel.getLppath()).length()));
        }
    }

    public String convertLongDataToDuration(long j) {
        String str;
        String str2;
        if (j > 3600000) {
            int i = (int) (j / 3600000);
            if (i < 10) {
                str = "0" + i + ":";
            } else {
                str = i + ":";
            }
        } else {
            str = "";
        }
        int i2 = ((int) (j / 60000)) % 60;
        if (i2 < 10) {
            str2 = str + "0" + i2 + ":";
        } else {
            str2 = str + i2 + ":";
        }
        int i3 = ((int) (j / 1000)) % 60;
        if (i3 < 10) {
            return str2 + "0" + i3;
        }
        return str2 + i3;
    }

    public String convertSize(long j) {
        double d = j / 1024.0d;
        if (d >= 1048576.0d) {
            return new DecimalFormat("0.00").format((d / 1024.0d) / 1024.0d) + " GB";
        }
        if (d >= 1024.0d) {
            return new DecimalFormat("0.00").format(d / 1024.0d) + " MB";
        }
        return new DecimalFormat("0").format(d) + " KB";
    }

    public void lpsortListRefresh(List<lpAudioModel> list) {
        this.lpaudioModelList.clear();
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(Long.valueOf(lpaudiomodel2.getLpdateCreate()), Long.valueOf(lpaudiomodel.getLpdateCreate())));
        }
        this.lpaudioModelList.addAll(list);
        notifyDataSetChanged();
    }

    public final void oldDataSort(List<lpAudioModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.lpaudioModelList.clear();
        if (newList.size() > 1) {
            CollectionsKt.sortWith(newList, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(Long.valueOf(lpaudiomodel.getLpdateCreate()), Long.valueOf(lpaudiomodel2.getLpdateCreate())));
        }
        this.lpaudioModelList.addAll(newList);
        notifyDataSetChanged();
    }

    public  void lpsortByFileName(List<lpAudioModel> list) {
        this.lpaudioModelList.clear();
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new lpComparisons());
        }
        this.lpaudioModelList.addAll(list);
        notifyDataSetChanged();
    }

    public  void lpsortOldByName(List<lpAudioModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.lpaudioModelList.clear();
        if (newList.size() > 1) {
            CollectionsKt.sortWith(newList, new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(((lpAudioModel) obj).getLpfileName().toLowerCase(), ((lpAudioModel) obj2).getLpfileName().toLowerCase());
                }
            });
        }
        this.lpaudioModelList.addAll(newList);
        notifyDataSetChanged();
    }
}
