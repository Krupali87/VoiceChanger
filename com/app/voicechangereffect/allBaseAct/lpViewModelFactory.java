package com.app.voicechangereffect.allBaseAct;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.app.voicechangereffect.getApiData.appScheduler.lpSchedularProvider;
import com.app.voicechangereffect.getApiData.lpInterfaceDataManager;
import com.app.voicechangereffect.viewModel.lpChangeEffectViewModel;
import com.app.voicechangereffect.viewModel.lpCreationStudioViewModelLp;
import com.app.voicechangereffect.viewModel.lpCreationViewModelLp;
import com.app.voicechangereffect.viewModel.lpDeviceMusicViewModelLp;
import com.app.voicechangereffect.viewModel.lpMainActViewModelLp;
import com.app.voicechangereffect.viewModel.lpMusicPlayerViewModel;
import com.app.voicechangereffect.viewModel.lpOpenFileViewModelLp;
import com.app.voicechangereffect.viewModel.lpPermissionViewModel;
import com.app.voicechangereffect.viewModel.lpRecordingViewModel;
import com.app.voicechangereffect.viewModel.lpSaveViewModel;
import com.app.voicechangereffect.viewModel.lpSplashActViewModelLp;
import com.app.voicechangereffect.viewModel.lpTextToAudioViewModel;
import kotlin.jvm.internal.Intrinsics;

@Singleton
public final class lpViewModelFactory implements ViewModelProvider.Factory {
    private final Context lpcontext;
    private final lpInterfaceDataManager lpmLpInterfaceDataManager;
    private final lpSchedularProvider lpmScheduler;

    @Inject
    public lpViewModelFactory(Context context, lpInterfaceDataManager mDataManager, lpSchedularProvider mScheduler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mDataManager, "mDataManager");
        Intrinsics.checkNotNullParameter(mScheduler, "mScheduler");
        this.lpcontext = context;
        this.lpmLpInterfaceDataManager = mDataManager;
        this.lpmScheduler = mScheduler;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> cls) {
        lpBaseViewModel lppermissionviewmodel;
        if (cls.isAssignableFrom(lpSplashActViewModelLp.class)) {
            lppermissionviewmodel = new lpSplashActViewModelLp();
        } else if (cls.isAssignableFrom(lpMainActViewModelLp.class)) {
            lppermissionviewmodel = new lpMainActViewModelLp();
        } else if (cls.isAssignableFrom(lpTextToAudioViewModel.class)) {
            lppermissionviewmodel = new lpTextToAudioViewModel();
        } else if (cls.isAssignableFrom(lpRecordingViewModel.class)) {
            lppermissionviewmodel = new lpRecordingViewModel();
        } else if (cls.isAssignableFrom(lpMusicPlayerViewModel.class)) {
            lppermissionviewmodel = new lpMusicPlayerViewModel();
        } else if (cls.isAssignableFrom(lpSaveViewModel.class)) {
            lppermissionviewmodel = new lpSaveViewModel();
        } else if (cls.isAssignableFrom(lpOpenFileViewModelLp.class)) {
            lppermissionviewmodel = new lpOpenFileViewModelLp();
        } else if (cls.isAssignableFrom(lpDeviceMusicViewModelLp.class)) {
            lppermissionviewmodel = new lpDeviceMusicViewModelLp();
        } else if (cls.isAssignableFrom(lpCreationViewModelLp.class)) {
            lppermissionviewmodel = new lpCreationViewModelLp();
        } else if (cls.isAssignableFrom(lpCreationStudioViewModelLp.class)) {
            lppermissionviewmodel = new lpCreationStudioViewModelLp();
        } else if (cls.isAssignableFrom(lpChangeEffectViewModel.class)) {
            lppermissionviewmodel = new lpChangeEffectViewModel();
        } else if (cls.isAssignableFrom(lpPermissionViewModel.class)) {
            lppermissionviewmodel = new lpPermissionViewModel();
        } else {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown ViewModel class: ", cls.getName()));
        }
        if (lppermissionviewmodel instanceof lpBaseViewModel) {
            lppermissionviewmodel.lpinitData(this.lpcontext, this.lpmLpInterfaceDataManager, this.lpmScheduler);
        }
        return (T) lppermissionviewmodel;
    }
}
