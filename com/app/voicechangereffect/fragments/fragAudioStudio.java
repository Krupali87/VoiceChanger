package com.app.voicechangereffect.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.activity.lpCreationActivity;
import com.app.voicechangereffect.activity.lpMusicPlayerActivity;
import com.app.voicechangereffect.adapters.lpItemDowAudioAdapterLp;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allDialogs.lpDeleteDialogLp;
import com.app.voicechangereffect.allDialogs.lpRenameDialogLp;
import com.app.voicechangereffect.allDialogs.lpSetRingtoneDialog;
import com.app.voicechangereffect.custUi.constatnt.lpRingtonePermission;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpMobileState;
import com.app.voicechangereffect.databinding.FragmentStudioAudioBinding;
import com.app.voicechangereffect.getApiData.allModel.lpAudioModel;
import com.app.voicechangereffect.lpFilenameUtils;
import com.app.voicechangereffect.viewModel.lpCreationViewModelLp;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

public final class fragAudioStudio extends lpBaseFragment<lpCreationViewModelLp, FragmentStudioAudioBinding> {
    public lpItemDowAudioAdapterLp lpaudioAdapter;
    public lpAudioModel lpaudioModel;
    public lpDeleteDialogLp lpdeleteDialog;
    public File lpfileTo;
    public File lpfromPath;
    public lpRenameDialogLp lprenameDialog;
    private final BroadcastReceiver lpreceiver = new BroadcastReceiver() { // from class: com.app.voicechangereffect.fragments.fragAudioStudio.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            List<lpAudioModel> lpmodelList;
            if (intent.getAction().equals("delete_file")) {
                TypeIntrinsics.asMutableCollection(fragAudioStudio.this.lplist).remove(fragAudioStudio.this.lpaudioModel);
                Log.e("tt---", "bindViewModel: list:  " + fragAudioStudio.this.lplist.size());
              lpsetAdapter();
                Toast.makeText(fragAudioStudio.this.getContext(), fragAudioStudio.this.getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                lpItemDowAudioAdapterLp lpitemdowaudioadapterlp = fragAudioStudio.this.lpaudioAdapter;
                if (lpitemdowaudioadapterlp == null || (lpmodelList = lpitemdowaudioadapterlp.getLpmodelList()) == null || lpmodelList.size() != 0) {
                    return;
                }
              lplist.add(new lpAudioModel("", "Sample.mp3", "", 0L, "", ""));
              lpaudioAdapter.notifyDataSetChanged();
            } else if (intent.getAction().equals("rename_file")) {
                Iterator<lpAudioModel> it = fragAudioStudio.this.lplist.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    lpAudioModel next = it.next();
                    if (Intrinsics.areEqual(next, next)) {
                        next.setLpfileName(fragAudioStudio.this.lpnewFileName);
                        break;
                    }
                }
              lpsetAdapter();
                Toast.makeText(fragAudioStudio.this.getContext(), fragAudioStudio.this.getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
            }
        }
    };
    public List<lpAudioModel> lplist = new ArrayList();
    public String lpnewFileName = "";
    public ArrayList<Uri> lpuris = new ArrayList<>();

    @Override
    public int lpgetFragResourceLayout() {
        return R.layout.fragment_studio_audio;
    }

    @Override
    public Class<lpCreationViewModelLp> createViewModel() {
        return lpCreationViewModelLp.class;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void bindViews() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("delete_file");
        intentFilter.addAction("rename_file");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.lpreceiver, intentFilter);
        RecyclerView recyclerView = getLpdataBinding().rclAudio;
        recyclerView.setHasFixedSize(true);
        final Context requireContext = requireContext();
        // from class: com.app.voicechangereffect.fragments.fragAudioStudio.4
// kotlin.jvm.functions.Function1
        // from class: com.app.voicechangereffect.fragments.fragAudioStudio.5
// kotlin.jvm.functions.Function1
        // from class: com.app.voicechangereffect.fragments.fragAudioStudio.6
// kotlin.jvm.functions.Function1
        lpaudioAdapter = new lpItemDowAudioAdapterLp(requireContext, new ArrayList(), lpaudiomodel -> {
            Bundle bundle = new Bundle();
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), lpaudiomodel.getLppath());
            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_FILENAME_EFFECT(), lpaudiomodel.getLpfileName());
          showActivity(lpMusicPlayerActivity.class, bundle);
            return null;
        }, lpaudiomodel -> {
          lpaudioModel = lpaudiomodel;
            FragmentActivity requireActivity = fragAudioStudio.this.requireActivity();
            String lpfileName = lpaudiomodel.getLpfileName();

            lprenameDialog = new lpRenameDialogLp(requireActivity, true, lpfileName, (Function2<String, String, Unit>) (str, str2) -> {
                boolean z;
                lpItemDowAudioAdapterLp lpitemdowaudioadapterlp2 = fragAudioStudio.this.lpaudioAdapter;
                String str3 = str + "_" + str2;
                List<lpAudioModel> lpmodelList = lpitemdowaudioadapterlp2 == null ? null : lpitemdowaudioadapterlp2.getLpmodelList();
                Intrinsics.checkNotNull(lpmodelList);
                Iterator<lpAudioModel> it = lpmodelList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (Intrinsics.areEqual(lpFilenameUtils.getBaseName(it.next().getLpfileName()), str3)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    Toast.makeText(fragAudioStudio.this.getContext(), fragAudioStudio.this.getContext().getString(R.string.this_audio_already_exists), Toast.LENGTH_SHORT).show();
                    return null;
                }
                lpAudioModel lpaudiomodel2 = lpaudiomodel;
              lpfromPath = new File(lpaudiomodel2.getLppath());
                File file = new File(lpaudiomodel2.getLppath());
                File file2 = new File(file.getAbsolutePath());
              lpfileTo = new File(file.getAbsolutePath().replace(file.getName(), str + "_" + str2 + ".mp3"));
                lpCreationActivity.lpCOMPANION.setFrom(fragAudioStudio.this.lpfromPath);
                lpCreationActivity.lpCOMPANION.setTo(fragAudioStudio.this.lpfileTo);
                File file3 = fragAudioStudio.this.lpfromPath;
                if (Build.VERSION.SDK_INT <= 29) {
                    file2.renameTo(fragAudioStudio.this.lpfileTo);
                    Toast.makeText(fragAudioStudio.this.getContext(), fragAudioStudio.this.getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                  lpsetAdapter();
                } else {
                    String valueOf = String.valueOf(file3 != null ? file3.getAbsolutePath() : null);
                    Log.e("rename---", "invoke: valueOf :: " + valueOf);
                    Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.getContentUri("external"), fragAudioStudio.this.getFilePathToMedia(valueOf, fragAudioStudio.this.getContext()));
                    Log.e("rename---", "invoke: withAppendedId :: " + withAppendedId);
                  lpuris.clear();
                  lpuris.add(withAppendedId);
                  lpreqRenameData(fragAudioStudio.this.lpuris);
                }
                lpRenameDialogLp lprenamedialoglp = fragAudioStudio.this.lprenameDialog;
                if (lprenamedialoglp != null) {
                    lprenamedialoglp.dismiss();
                }
                return null;
            });
          lprenameDialog.show();
            return null;
        }, lpaudiomodel -> {
            if (lpRingtonePermission.lpcheckSystemWritePermission(fragAudioStudio.this.requireContext())) {

                new lpSetRingtoneDialog(fragAudioStudio.this.requireActivity(), true, lpmobilestate -> {
                    int[] iArr = new int[lpMobileState.values().length];
                    iArr[lpMobileState.lpSTATE_RINGTONE.ordinal()] = 1;
                    iArr[lpMobileState.lpSTATE_ALARM.ordinal()] = 2;
                    iArr[lpMobileState.lpSTATE_NOTIFICATION.ordinal()] = 3;
                    int i = iArr[lpmobilestate.ordinal()];
                    if (i == 1) {
                        Context requireContext2 = fragAudioStudio.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                      settingsPhoneRing(requireContext2, lpaudiomodel.getLppath(), lpMobileState.lpSTATE_RINGTONE, true);
                        return null;
                    } else if (i == 2) {
                        Context requireContext3 = fragAudioStudio.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                      settingsPhoneRing(requireContext3, lpaudiomodel.getLppath(), lpMobileState.lpSTATE_ALARM, true);
                        return null;
                    } else {
                        if (i == 3) {
                            Context requireContext4 = fragAudioStudio.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                          settingsPhoneRing(requireContext4, lpaudiomodel.getLppath(), lpMobileState.lpSTATE_NOTIFICATION, true);
                        }
                        return null;
                    }
                }).show();
            } else {
                lpAppConstant.lpAPP_CONSTANT.setCheckResumePermissionRingtone(true);
                lpRingtonePermission.lpopenAndroidPermissionsMenu((Activity) requireContext);
            }
            return Unit.INSTANCE;
        }, it -> {
            Intrinsics.checkNotNullParameter(it, "it");
            lpAppConstant.lpAPP_CONSTANT.setCheckResumeShareMyVoice(true);
            Context requireContext2 = fragAudioStudio.this.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
          lpshareFileProject(requireContext2, it.getLppath());
            return null;
        }, lpaudiomodel -> {
          lpaudioModel = lpaudiomodel;
            final File file = new File(lpaudiomodel == null ? null : lpaudiomodel.getLppath());
            FragmentActivity requireActivity = fragAudioStudio.this.requireActivity();
            if (Build.VERSION.SDK_INT <= 29) {

                lpdeleteDialog = new lpDeleteDialogLp(requireActivity, true, () -> {
                    List<lpAudioModel> lpmodelList;
                    file.delete();
                    TypeIntrinsics.asMutableCollection(fragAudioStudio.this.lplist).remove(lpaudiomodel);
                    boolean z = false;
                    Toast.makeText(fragAudioStudio.this.getContext(), fragAudioStudio.this.getString(R.string.Successfully), Toast.LENGTH_SHORT).show();
                    lpItemDowAudioAdapterLp lpitemdowaudioadapterlp2 = fragAudioStudio.this.lpaudioAdapter;
                    if (lpitemdowaudioadapterlp2 != null && (lpmodelList = lpitemdowaudioadapterlp2.getLpmodelList()) != null && lpmodelList.size() == 0) {
                        z = true;
                    }
                    if (z) {
                      lplist.add(new lpAudioModel("", "Sample.mp3", "", 0L, "", ""));
                      lpaudioAdapter.notifyDataSetChanged();
                    }
                  lpsetAdapter();
                    return null;
                });
              lpdeleteDialog.show();
            } else {
                Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.getContentUri("external"), fragAudioStudio.this.getFilePathToMedia(file.getAbsolutePath().toString(), fragAudioStudio.this.getContext()));
              lpuris.clear();
              lpuris.add(withAppendedId);
                fragAudioStudio fragaudiostudio = fragAudioStudio.this;
                fragaudiostudio.lpreqDeleteData(fragaudiostudio.lpuris);
            }
            return null;
        });
        recyclerView.setAdapter(lpaudioAdapter);
    }

    public void lpshareFileProject(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        Uri uriForFile = FileProvider.getUriForFile(context, "com.app.voicechangereffect.provider", new File(path));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("audio/*");
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", context.getResources().getString(R.string.appShare) + "\n\nhttps://play.google.com/store/apps/details?id=com.app.voicechangereffect\n");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

    @Override
    public void bindViewModels() {
        lpsetAdapter();
    }

    public void lpsetAdapter() {
        final Context requireContext = requireContext();
        getLpviewModel().getAudioDataClass(requireContext, getLpdataBinding().llLoading, getLpdataBinding().noData);

        lpCreationActivity.lpCOMPANION.getLiveSortCreateStudio().observe(requireActivity(), num -> {
            if (num != null && num.intValue() == 1) {

                getLpviewModel().getListMutableLiveData().observe(fragAudioStudio.this.requireActivity(), list -> {
                    Log.e("ee----", "onChanged: audioModels 11  :: " + list.size());
                    lpItemDowAudioAdapterLp lpitemdowaudioadapterlp = fragAudioStudio.this.lpaudioAdapter;
                    if (lpitemdowaudioadapterlp != null) {
                        lpitemdowaudioadapterlp.lpsortLatestData(list);
                    }
                });
            } else if (num == null || num.intValue() != 2) {
            } else {

                getLpviewModel().getListMutableLiveData().observe(fragAudioStudio.this.requireActivity(), list -> {
                    Log.e("ee----", "onChanged: audioModels 22  :: " + list.size());
                    lpItemDowAudioAdapterLp lpitemdowaudioadapterlp = fragAudioStudio.this.lpaudioAdapter;
                    if (lpitemdowaudioadapterlp != null) {
                        lpitemdowaudioadapterlp.sortOldestData(list);
                    }
                });
            }
        });

        lpCreationActivity.lpCOMPANION.getLiveSortNameStudio().observe(requireActivity(), num -> {
            Context this$0 = requireContext;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (num != null && num.intValue() == 1) {

                getLpviewModel().getListMutableLiveData().observe(fragAudioStudio.this.requireActivity(), list -> {
                    lpItemDowAudioAdapterLp lpitemdowaudioadapterlp;
                    Context this$02 = requireContext;
                    Intrinsics.checkNotNullParameter(this$02, "this$0");
                    if (list == null || (lpitemdowaudioadapterlp = fragAudioStudio.this.lpaudioAdapter) == null) {
                        return;
                    }
                    lpitemdowaudioadapterlp.sortLatestDataByFileName(list);
                });
            } else if (num == null || num.intValue() != 2) {
            } else {

                getLpviewModel().getListMutableLiveData().observe(fragAudioStudio.this.requireActivity(), list -> {
                    lpItemDowAudioAdapterLp lpitemdowaudioadapterlp;
                    Context this$02 = requireContext;
                    Intrinsics.checkNotNullParameter(this$02, "this$0");
                    if (list == null || (lpitemdowaudioadapterlp = fragAudioStudio.this.lpaudioAdapter) == null) {
                        return;
                    }
                    lpitemdowaudioadapterlp.lpsortOldestByFileName(list);
                });
            }
        });
        Log.e("tt---", "setAdapter: " + this.lplist.size());
    }

    @SuppressLint("Range")
    public final long getFilePathToMedia(String str, Context context) {
        Cursor query = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, Intrinsics.stringPlus("_data", "=?"), new String[]{str}, null);
        long j = 0;
        if (query != null) {
            while (query.moveToNext()) {
                j = Long.parseLong(query.getString(query.getColumnIndex("_id")));
            }
        }
        return j;
    }

    public void lpreqRenameData(ArrayList<Uri> arrayList) {
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                requireActivity().startIntentSenderForResult(MediaStore.createWriteRequest(getContext().getContentResolver(), arrayList).getIntentSender(), 1112, null, 0, 0, 67108864);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }

    public void lpreqDeleteData(ArrayList<Uri> arrayList) {
        Log.e("del===", "reqDeleteData: uris : " + arrayList);
        try {
            requireActivity().startIntentSenderForResult((Build.VERSION.SDK_INT >= 30 ? MediaStore.createDeleteRequest(getContext().getContentResolver(), arrayList) : null).getIntentSender(), 1111, null, 0, 0, 67108864);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(this.lpreceiver);
    }

    public void settingsPhoneRing(Context context, String path, lpMobileState state, boolean z) {
        int[] iArr = new int[lpMobileState.values().length];
        iArr[lpMobileState.lpSTATE_ALARM.ordinal()] = 1;
        iArr[lpMobileState.lpSTATE_NOTIFICATION.ordinal()] = 2;
        iArr[lpMobileState.lpSTATE_RINGTONE.ordinal()] = 3;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(state, "state");
        try {
            Uri fromFile = Uri.fromFile(new File(path));
            int i = iArr[state.ordinal()];
            if (i == 1) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 4, fromFile);
                if (z) {
                    Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG).show();
                }
            } else if (i == 2) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 2, fromFile);
                if (z) {
                    Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG).show();
                }
            } else if (i == 3) {
                RingtoneManager.setActualDefaultRingtoneUri(context, 1, fromFile);
                if (z) {
                    Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Throwable th) {
            Log.e("setAsDefaultRingtone err", th.toString());
        }
    }
}
