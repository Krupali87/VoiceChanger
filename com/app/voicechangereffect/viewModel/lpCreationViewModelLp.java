package com.app.voicechangereffect.viewModel;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.MutableLiveData;
import com.airbnb.lottie.LottieAnimationView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.activity.lpCreationActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseViewModel;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import com.app.voicechangereffect.lpFilenameUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;


public final class lpCreationViewModelLp extends lpBaseViewModel {
    int label;
    public List<lpAudioModel> arrayList = new ArrayList();
    private MutableLiveData<List<lpAudioModel>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<lpAudioModel>> getListMutableLiveData() {
        return this.listMutableLiveData;
    }

    public void getAudioDataClass(Context context, LinearLayout linearLayout, LottieAnimationView lottieAnimationView) {
        new GetAudioData(context, linearLayout, lottieAnimationView).execute(new Object[0]);
    }

    public class GetAudioData extends AsyncTask {
        Context ctx;
        LinearLayout layout;
        LottieAnimationView noData;

        public GetAudioData(Context context, LinearLayout linearLayout, LottieAnimationView lottieAnimationView) {
            this.ctx = context;
            this.layout = linearLayout;
            this.noData = lottieAnimationView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpCreationViewModelLp.this.label == 0) {
                this.layout.setVisibility(View.VISIBLE);
                lpCreationViewModelLp.this.arrayList.clear();
            }
        }

        @Override
        protected Object doInBackground(Object[] objArr) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpCreationViewModelLp.this.label == 0) {
                try {
                    File[] listFiles = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + this.ctx.getResources().getString(R.string.app_name) + "/VoiceEffects").listFiles();
                    if (listFiles.length != 0) {
                        int length = listFiles.length;
                        int i = 0;
                        while (i < length) {
                            File file = listFiles[i];
                            i++;
                            String absolutePath = file.getAbsolutePath();
                            String lpremoveExtension = lpFilenameUtils.lpremoveExtension(file.getName());
                            Uri parse = Uri.parse(absolutePath);
                            lpCreationViewModelLp lpcreationviewmodellp = lpCreationViewModelLp.this;
                            lpcreationviewmodellp.arrayList.add(new lpAudioModel(absolutePath, lpremoveExtension, lpcreationviewmodellp.getBaseDuration(lpcreationviewmodellp.getLpcontext(), parse), file.lastModified(), String.valueOf(file.length()), lpFilenameUtils.lpgetExtension(file.getName())));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
            return objArr;
        }

        @Override
        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            this.layout.setVisibility(View.GONE);
            if (lpCreationViewModelLp.this.arrayList.size() == 0) {
                this.noData.setVisibility(View.VISIBLE);
            } else {
                this.noData.setVisibility(View.GONE);
                if (lpCreationViewModelLp.this.arrayList.size() > 1) {
                    lpCreationActivity.lpimgSort.setVisibility(View.VISIBLE);
                } else {
                    lpCreationActivity.lpimgSort.setVisibility(View.GONE);
                }
            }
            lpCreationViewModelLp.this.getListMutableLiveData().setValue(lpCreationViewModelLp.this.arrayList);
        }
    }

    public String getBaseDuration(Context context, Uri uri) {
        MediaPlayer create = MediaPlayer.create(context, uri);
        String valueOf = String.valueOf(create.getDuration());
        create.release();
        return valueOf;
    }
}
