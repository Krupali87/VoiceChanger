package com.app.voicechangereffect.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.voicechangereffect.R;
import com.app.voicechangereffect.databinding.ItemCreationBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.app.voicechangereffect.allBaseAct.lpBaseAdapter;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;



public final class lpItemDowAudioAdapterLp extends lpBaseAdapter<ItemCreationBinding, lpAudioModel> {
    private final Context lpmCtx;
    private final List<lpAudioModel> lpmodelList;
    private final Function1 lponDeleteClick;
    private final Function1 lponItemClick;
    private final Function1 lponRenameClick;
    private final Function1 lponSetAsClick;
    private final Function1 lponShareClick;

    @Override
    public int getLayoutRes() {
        return R.layout.item_creation;
    }

    public List<lpAudioModel> getLpmodelList() {
        return this.lpmodelList;
    }

    public Function1 getLponItemClick() {
        return this.lponItemClick;
    }

    public Function1 getLponRenameClick() {
        return this.lponRenameClick;
    }

    public Function1 getLponSetAsClick() {
        return this.lponSetAsClick;
    }

    public Function1 getLponShareClick() {
        return this.lponShareClick;
    }

    public Function1 getLponDeleteClick() {
        return this.lponDeleteClick;
    }

    public lpItemDowAudioAdapterLp(Context context, List<lpAudioModel> list, Function1<? super lpAudioModel, Unit> function1, Function1<? super lpAudioModel, Unit> function12, Function1<? super lpAudioModel, Unit> function13, Function1<? super lpAudioModel, Unit> function14, Function1<? super lpAudioModel, Unit> function15) {
        super(list);
        this.lpmCtx = context;
        this.lpmodelList = list;
        this.lponItemClick = function1;
        this.lponRenameClick = function12;
        this.lponSetAsClick = function13;
        this.lponShareClick = function14;
        this.lponDeleteClick = function15;
    }

    @Override
    public RecyclerView.ViewHolder createVH(ItemCreationBinding itemCreationBinding) {
        return new AudioViewHolder(this, itemCreationBinding);
    }


    public final class AudioViewHolder extends lpBaseAdapter<ItemCreationBinding, lpAudioModel>.BaseVH<Object> {
        ItemCreationBinding lpbinding;
        final lpItemDowAudioAdapterLp lpitemDowAudioAdapter;

        public AudioViewHolder(lpItemDowAudioAdapterLp lpitemdowaudioadapterlp, ItemCreationBinding itemCreationBinding) {
            super(lpitemdowaudioadapterlp, itemCreationBinding);
            this.lpitemDowAudioAdapter = lpitemdowaudioadapterlp;
        }

        @Override
        public void onItemClickListener(lpAudioModel lpaudiomodel) {
            super.onItemClickListener(lpaudiomodel);
            this.lpitemDowAudioAdapter.getLponItemClick().invoke(lpaudiomodel);
        }

        public String lpconvertLongDataToDuration(long j) {
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

        @Override
        public void bind(final lpAudioModel lpaudiomodel) {
            super.bind(lpaudiomodel);
            ItemCreationBinding itemCreationBinding = this.lpbinding;
            if (itemCreationBinding != null) {
                if (lpaudiomodel != null) {
                    String str = lpaudiomodel.getLpfileName() + '.' + lpaudiomodel.getLptype();
                    itemCreationBinding.tvName.setText(str);
                    Log.d("sjsjsjsjsjsj", "tvName.setText(String) = " + str);
                }
                Log.d("sjsjsjsjsjsj", "audioModel.getFileName " + lpaudiomodel.getLpfileName());
            }
            getLpbinding().tvName.setText(lpaudiomodel.getLpfileName() + '.' + lpaudiomodel.getLptype());
            getLpbinding().tvDetail.setText(lpconvertLongDataToDuration(Long.parseLong(lpaudiomodel.getLpduration())) + " | " + lpItemDowAudioAdapterLp.this.lpconvertSize(new File(lpaudiomodel.getLppath()).length()));
            getLpbinding().ivMenu.setOnClickListener(view -> AudioViewHolder.this.lpm393x1fd76446(lpaudiomodel, view));
            Glide.with(lpItemDowAudioAdapterLp.this.lpmCtx).load(Integer.valueOf(lpAppDataException.getIconEffect(lpaudiomodel.getLpfileName())));
        }

        public void lpm393x1fd76446(final lpAudioModel lpaudiomodel, View view) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(lpItemDowAudioAdapterLp.this.lpmCtx, R.style.BottomSheetDialogTheme);
            bottomSheetDialog.setContentView(R.layout.dialog_menu);
            bottomSheetDialog.setCanceledOnTouchOutside(false);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            bottomSheetDialog.getWindow().setLayout(-1, -2);
            bottomSheetDialog.findViewById(R.id.imgClose).setOnClickListener(view2 -> bottomSheetDialog.dismiss());
            bottomSheetDialog.findViewById(R.id.setAsRing).setOnClickListener(view2 -> AudioViewHolder.this.lpm389xe0d695ca(lpaudiomodel, bottomSheetDialog, view2));
            bottomSheetDialog.findViewById(R.id.share).setOnClickListener(view2 -> AudioViewHolder.this.lpm390xb096c969(lpaudiomodel, bottomSheetDialog, view2));
            bottomSheetDialog.findViewById(R.id.delete).setOnClickListener(view2 -> AudioViewHolder.this.lpm391x8056fd08(lpaudiomodel, bottomSheetDialog, view2));
            bottomSheetDialog.findViewById(R.id.llyRename).setOnClickListener(view2 -> AudioViewHolder.this.lpm392x501730a7(lpaudiomodel, bottomSheetDialog, view2));
            if (bottomSheetDialog.isShowing()) {
                return;
            }
            bottomSheetDialog.show();
        }

        public void lpm389xe0d695ca(lpAudioModel lpaudiomodel, BottomSheetDialog bottomSheetDialog, View view) {
            lpItemDowAudioAdapterLp.this.getLponSetAsClick().invoke(lpaudiomodel);
            bottomSheetDialog.dismiss();
        }

        public void lpm390xb096c969(lpAudioModel lpaudiomodel, BottomSheetDialog bottomSheetDialog, View view) {
            lpItemDowAudioAdapterLp.this.getLponShareClick().invoke(lpaudiomodel);
            bottomSheetDialog.dismiss();
        }

        public void lpm391x8056fd08(lpAudioModel lpaudiomodel, BottomSheetDialog bottomSheetDialog, View view) {
            lpItemDowAudioAdapterLp.this.getLponDeleteClick().invoke(lpaudiomodel);
            bottomSheetDialog.dismiss();
        }

        public void lpm392x501730a7(lpAudioModel lpaudiomodel, BottomSheetDialog bottomSheetDialog, View view) {
            lpItemDowAudioAdapterLp.this.getLponRenameClick().invoke(lpaudiomodel);
            bottomSheetDialog.dismiss();
        }
    }

    public String lpconvertLongDataToDuration(long j) {
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

    public String lpconvertSize(long j) {
        double d = j / 1024.0d;
        if (d >= 1048576.0d) {
            return new DecimalFormat("0.00").format((d / 1024.0d) / 1024.0d) + " GB";
        }
        if (d >= 1024.0d) {
            return new DecimalFormat("0.00").format(d / 1024.0d) + " MB";
        }
        return new DecimalFormat("0").format(d) + " KB";
    }

    public void lpsortLatestData(List<lpAudioModel> list) {
        this.lpmodelList.clear();
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(Long.valueOf(lpaudiomodel2.getLpdateCreate()), Long.valueOf(lpaudiomodel.getLpdateCreate())));
        }
        this.lpmodelList.addAll(list);
        notifyDataSetChanged();
    }

    public void sortOldestData(List<lpAudioModel> list) {
        this.lpmodelList.clear();
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(Long.valueOf(lpaudiomodel.getLpdateCreate()), Long.valueOf(lpaudiomodel2.getLpdateCreate())));
        }
        this.lpmodelList.addAll(list);
        notifyDataSetChanged();
    }

    public void sortLatestDataByFileName(List<lpAudioModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.lpmodelList.clear();
        if (newList.size() > 1) {
            CollectionsKt.sortWith(newList, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(lpaudiomodel2.getLpfileName().toLowerCase(), lpaudiomodel.getLpfileName().toLowerCase()));
        }
        this.lpmodelList.addAll(newList);
        notifyDataSetChanged();
    }

    public void lpsortOldestByFileName(List<lpAudioModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.lpmodelList.clear();
        if (newList.size() > 1) {
            CollectionsKt.sortWith(newList, (lpaudiomodel, lpaudiomodel2) -> ComparisonsKt.compareValues(lpaudiomodel.getLpfileName().toLowerCase(), lpaudiomodel2.getLpfileName().toLowerCase()));
        }
        this.lpmodelList.addAll(newList);
        notifyDataSetChanged();
    }
}
