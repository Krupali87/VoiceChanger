package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.adapters.lpCreationPagerAdapter;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allBaseAct.lpBasePopupMenu;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.databinding.ActivityCreationBinding;
import com.app.voicechangereffect.viewModel.lpCreationStudioViewModelLp;
import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public final class lpCreationActivity extends lpBaseActivity<lpCreationStudioViewModelLp, ActivityCreationBinding> {
    public static File lpfromFile;
    public static ImageView lpimgSort;
    public static File lptoFile;
    public ImageView lpivCreated;
    public ImageView lpivFileName;
    public static final Companion lpCOMPANION = new Companion(null);
    public static MutableLiveData<Integer> lpdateSort = new MutableLiveData<>(1);
    public static MutableLiveData<Integer> lpnameSort = new MutableLiveData<>(0);

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public int getContent() {
        return R.layout.activity_creation;
    }

    @Override
    public Class<lpCreationStudioViewModelLp> createViewModel() {
        return lpCreationStudioViewModelLp.class;
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
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("VoiceChanger", "CreationAct_onDestroy");
        lpdateSort.postValue(1);
        lpnameSort.postValue(0);
        lpfromFile = null;
        lptoFile = null;

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

        public File getFrom() {
            return lpCreationActivity.lpfromFile;
        }

        public void setFrom(File file) {
            lpCreationActivity.lpfromFile = file;
        }

        public File getTo() {
            return lpCreationActivity.lptoFile;
        }

        public void setTo(File file) {
            lpCreationActivity.lptoFile = file;
        }

        public MutableLiveData<Integer> getLiveSortCreateStudio() {
            return lpCreationActivity.lpdateSort;
        }

        public MutableLiveData<Integer> getLiveSortNameStudio() {
            return lpCreationActivity.lpnameSort;
        }
    }

    public ImageView getLpivCreated() {
        return this.lpivCreated;
    }

    public void setLpivCreated(ImageView imageView) {
        this.lpivCreated = imageView;
    }

    public ImageView getLpivFileName() {
        return this.lpivFileName;
    }

    public void setLpivFileName(ImageView imageView) {
        this.lpivFileName = imageView;
    }

    @Override
    public void lpmainView() {
        lpimgSort = getBindingData().toolbar.ivSort;
        getBindingData().toolbar.tvTitle.setText(getString(R.string.my_voice));
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
        super.onStop();
        
    }

    @Override
    public void lpinitViews() {

        Log.e("VoiceChanger", "CreationAct_onCreate");
        lpTapClick.lptap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            lpCreationActivity.this.onBackPressed();
            return null;
        });


        lpCreationPagerAdapter lpcreationpageradapter = new lpCreationPagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = getBindingData().viewPager;
        viewPager.setAdapter(lpcreationpageradapter);
        final lpBasePopupMenu lpbasepopupmenu = new lpBasePopupMenu(this, R.layout.layout_popup_menu_sort, new lpBasePopupMenu.PopupMenuCustomOnClickListener() { // from class: com.app.voicechangereffect.activity.lpCreationActivity.3
            @Override
            public void initView(View view) {
                lpCreationActivity.this.setLpivCreated(view.findViewById(R.id.iv_created));
                lpCreationActivity.this.setLpivFileName(view.findViewById(R.id.iv_file_name));
            }

            @Override
            public void onClick(int i, View view) {
                Integer value;
                Integer value2;
                if (i != R.id.ll_created) {
                    if (i == R.id.ll_file_name && viewPager.getCurrentItem() == 0) {
                        lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().postValue(0);
                        Integer value3 = lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().getValue();
                        Log.e("fff----", "onClick: val3 :: " + value3);
                        if ((value3 != null && value3.intValue() == 1) || ((value2 = lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().getValue()) != null && value2.intValue() == 0)) {
                            lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().postValue(2);
                            ImageView lpivFileName = lpCreationActivity.this.getLpivFileName();
                            if (lpivFileName != null) {
                                lpivFileName.setImageResource(R.drawable.ic_menu_down);
                                return;
                            }
                            return;
                        }
                        lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().postValue(1);
                        ImageView lpivFileName2 = lpCreationActivity.this.getLpivFileName();
                        if (lpivFileName2 != null) {
                            lpivFileName2.setImageResource(R.drawable.ic_menu_up);
                        }
                    }
                } else if (viewPager.getCurrentItem() == 0) {
                    lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().postValue(0);
                    Integer value4 = lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().getValue();
                    if ((value4 != null && value4.intValue() == 1) || ((value = lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().getValue()) != null && value.intValue() == 0)) {
                        lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().postValue(2);
                        ImageView lpivCreated = lpCreationActivity.this.getLpivCreated();
                        if (lpivCreated != null) {
                            lpivCreated.setImageResource(R.drawable.ic_menu_down);
                            return;
                        }
                        return;
                    }
                    lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().postValue(1);
                    ImageView lpivCreated2 = lpCreationActivity.this.getLpivCreated();
                    if (lpivCreated2 != null) {
                        lpivCreated2.setImageResource(R.drawable.ic_menu_up);
                    }
                }
            }
        });
        lpTapClick.lptap(getBindingData().toolbar.ivSort, (Function1<View, Unit>) view -> {
            lpbasepopupmenu.showRight(lpCreationActivity.this.getBindingData().toolbar.ivSort);
            return null;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("VoiceChanger", "CreationAct_onBack");
        finish();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        if (i == 1112 && i2 == -1) {
            File file = lpfromFile;
            if (file != null) {
                file.renameTo(lptoFile);
            }
            localBroadcastManager.sendBroadcast(new Intent("rename_file"));
        }
        if (i == 1111 && i2 == -1) {
            localBroadcastManager.sendBroadcast(new Intent("delete_file"));
        }
    }

}
