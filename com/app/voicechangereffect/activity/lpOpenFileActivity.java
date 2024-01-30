package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.MutableLiveData;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.databinding.ActivityOpenFileBinding;
import com.app.voicechangereffect.adapters.lpDeviceVideoPager;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allBaseAct.lpBasePopupMenu;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.viewModel.lpOpenFileViewModelLp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;


public final class lpOpenFileActivity extends lpBaseActivity<lpOpenFileViewModelLp, ActivityOpenFileBinding> {
    public static ImageView lpimgSort;

    private ImageView lpivCreated;
    private ImageView lpivFileName;
    public static final Companion lpCompanion = new Companion(null);
    public static MutableLiveData<Integer> lpsortingByCreateDate = new MutableLiveData<>(1);
    public static MutableLiveData<Integer> lpsortingByName = new MutableLiveData<>(0);

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public int getContent() {
        return R.layout.activity_open_file;
    }

    @Override
    public Class<lpOpenFileViewModelLp> createViewModel() {
        return lpOpenFileViewModelLp.class;
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }


    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public  MutableLiveData<Integer> getLiveSortCreateAudio() {
            return lpOpenFileActivity.lpsortingByCreateDate;
        }

        public MutableLiveData<Integer> getLiveSortNameAudio() {
            return lpOpenFileActivity.lpsortingByName;
        }
    }

    public  ImageView getLpivCreated() {
        return this.lpivCreated;
    }

    public  void setLpivCreated(ImageView imageView) {
        this.lpivCreated = imageView;
    }

    public  ImageView getLpivFileName() {
        return this.lpivFileName;
    }

    public  void setLpivFileName(ImageView imageView) {
        this.lpivFileName = imageView;
    }

    @Override
    public void lpmainView() {
        Log.e("VoiceChanger", "OpenFileAct_onCreate");
        getSharedPreferences("MY_PRE", 0);
        lpimgSort = getBindingData().toolbar.ivSort;
        getBindingData().toolbar.tvTitle.setText(getString(R.string.change_voice));
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
    super.onStop();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("VoiceChanger", "OpenFileAct_onDestroy");
        lpsortingByCreateDate.postValue(1);
        lpsortingByName.postValue(0);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
    super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
    super.onPause();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
    super.onActivityResult(i, i2, intent);
    }

    @Override
    public void lpinitViews() {

        lpTapClick.lptap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            lpOpenFileActivity.this.onBackPressed();
            return null;
        });
        getBindingData().viewPager.setAdapter(new lpDeviceVideoPager(getSupportFragmentManager()));
        final lpBasePopupMenu lpbasepopupmenu = new lpBasePopupMenu(this, R.layout.layout_popup_menu_sort, new lpBasePopupMenu.PopupMenuCustomOnClickListener() {
            @Override
            public void initView(View view) {
                lpOpenFileActivity.this.setLpivCreated(view.findViewById(R.id.iv_created));
                lpOpenFileActivity.this.setLpivFileName(view.findViewById(R.id.iv_file_name));
            }

            @Override
            public void onClick(int i, View view) {
                Integer value;
                Integer value2;
                Log.e("VoiceChanger", "Click_on_Sort");
                if (i == R.id.ll_created) {
                    lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().postValue(0);
                    Integer value3 = lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().getValue();
                    if ((value3 != null && value3.intValue() == 1) || ((value2 = lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().getValue()) != null && value2.intValue() == 0)) {
                        lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().postValue(2);
                        ImageView lpivCreated = lpOpenFileActivity.this.getLpivCreated();
                        if (lpivCreated != null) {
                            lpivCreated.setImageResource(R.drawable.ic_menu_down);
                            return;
                        }
                        return;
                    }
                    lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().postValue(1);
                    ImageView lpivCreated2 = lpOpenFileActivity.this.getLpivCreated();
                    if (lpivCreated2 != null) {
                        lpivCreated2.setImageResource(R.drawable.ic_menu_up);
                    }
                } else if (i == R.id.ll_file_name) {
                    lpOpenFileActivity.lpCompanion.getLiveSortCreateAudio().postValue(0);
                    Integer value4 = lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().getValue();
                    if ((value4 != null && value4.intValue() == 1) || ((value = lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().getValue()) != null && value.intValue() == 0)) {
                        lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().postValue(2);
                        ImageView lpivFileName = lpOpenFileActivity.this.getLpivFileName();
                        if (lpivFileName != null) {
                            lpivFileName.setImageResource(R.drawable.ic_menu_down);
                            return;
                        }
                        return;
                    }
                    lpOpenFileActivity.lpCompanion.getLiveSortNameAudio().postValue(1);
                    ImageView lpivFileName2 = lpOpenFileActivity.this.getLpivFileName();
                    if (lpivFileName2 != null) {
                        lpivFileName2.setImageResource(R.drawable.ic_menu_up);
                    }
                }
            }
        });
        lpTapClick.lptap(getBindingData().toolbar.ivSort, (Function1<View, Unit>) view -> {
            lpBasePopupMenu lpbasepopupmenu2 = lpbasepopupmenu;
            ImageView imageView = lpOpenFileActivity.this.getBindingData().toolbar.ivSort;
            Intrinsics.checkNotNullExpressionValue(imageView, "mDataBinding.toolbar.iv_sort");
            lpbasepopupmenu2.showRight(imageView);
            return null;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("VoiceChanger", "OpenFileAct_onBack");
        finish();
    }
}
