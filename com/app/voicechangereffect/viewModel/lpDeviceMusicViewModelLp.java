package com.app.voicechangereffect.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.MutableLiveData;
import com.airbnb.lottie.LottieAnimationView;
import com.app.voicechangereffect.activity.lpOpenFileActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseViewModel;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import com.app.voicechangereffect.lpFilenameUtils;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

public final class lpDeviceMusicViewModelLp extends lpBaseViewModel {
    public static List<lpAudioModel> lpAudioModelList = new ArrayList();
    private final MutableLiveData<List<lpAudioModel>> mutableLiveData = new MutableLiveData<>(new ArrayList());

    public MutableLiveData<List<lpAudioModel>> getMutableLiveData() {
        return this.mutableLiveData;
    }

    public void getAudioDataClass(Context context, LinearLayout linearLayout, LottieAnimationView lottieAnimationView) {
        new GetAudioData(context, linearLayout, lottieAnimationView).execute(new String[0]);
    }

    public class GetAudioData extends AsyncTask<String, String, String[]> {
        Context ctx;
        LinearLayout lly;
        LottieAnimationView noData;

        public GetAudioData(Context context, LinearLayout linearLayout, LottieAnimationView lottieAnimationView) {
            this.ctx = context;
            this.lly = linearLayout;
            this.noData = lottieAnimationView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            this.lly.setVisibility(View.VISIBLE);
            lpDeviceMusicViewModelLp.lpAudioModelList.clear();
        }

        @Override
        public String[] doInBackground(String... strArr) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                lpDeviceMusicViewModelLp.lpAudioModelList.addAll(lpDeviceMusicViewModelLp.this.loadAudio(this.ctx));
                Log.e("rrr----", "doInBackground: audioModelList ::  " + lpDeviceMusicViewModelLp.lpAudioModelList.size());
            } catch (Exception e) {
                Log.e("rrr----", "doInBackground: e ::  " + e.getMessage());
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        public void onPostExecute(String[] it) {
            super.onPostExecute(it);
            Intrinsics.checkNotNullParameter(it, "it");
            this.lly.setVisibility(View.GONE);
            if (lpDeviceMusicViewModelLp.lpAudioModelList.size() == 0) {
                this.noData.setVisibility(View.VISIBLE);
            } else {
                this.noData.setVisibility(View.GONE);
                if (lpDeviceMusicViewModelLp.lpAudioModelList.size() > 1) {
                    lpOpenFileActivity.lpimgSort.setVisibility(View.VISIBLE);
                } else {
                    lpOpenFileActivity.lpimgSort.setVisibility(View.INVISIBLE);
                }
            }
            lpDeviceMusicViewModelLp.this.getMutableLiveData().setValue(lpDeviceMusicViewModelLp.lpAudioModelList);
        }
    }

    public ArrayList<lpAudioModel> loadAudio(Context context) {
        ArrayList<lpAudioModel> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "duration", "date_added"}, "_data like ? OR _data like ? ", new String[]{"%.mp3%", "%.wav%"}, Intrinsics.stringPlus("datetaken", " DESC"));
        if (query != null && query.getCount() > 0) {
            while (query.moveToNext()) {
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex("_data"));
                @SuppressLint("Range") String string2 = query.getString(query.getColumnIndex("duration"));
                @SuppressLint("Range") long parseLong = Long.parseLong(query.getString(query.getColumnIndex("date_added")));
                if (Integer.parseInt(convertSize(new File(string).length())) >= 20) {
                    if (string2 == null) {
                        string2 = getBaseDuration(this.lpcontext, Uri.parse(string));
                    }
                    String str = string2;
                    File file = new File(string);
                    arrayList.add(new lpAudioModel(string, lpFilenameUtils.lpremoveExtension(file.getName()), str, parseLong, String.valueOf(file.length()), lpFilenameUtils.lpgetExtension(file.getName())));
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public String convertSize(long j) {
        return new DecimalFormat("0").format(j / 1024.0d);
    }

    public String getBaseDuration(Context context, Uri uri) {
        MediaPlayer create = MediaPlayer.create(context, uri);
        Intrinsics.checkNotNullExpressionValue(create, "create(context, Uri.parse(uriOfFile))");
        int duration = create.getDuration();
        create.release();
        return String.valueOf(duration);
    }
}
