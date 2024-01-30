package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.databinding.ActivityMainBinding;
import com.app.voicechangereffect.lpBroadcastReceiver;
import com.app.voicechangereffect.viewModel.lpMainActViewModelLp;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;


public final class lpMainActivityLp extends lpBaseActivity<lpMainActViewModelLp, ActivityMainBinding> {
    private AlertDialog lpalertDialog;
    private Dialog lpdialog;
    public boolean lpisResumeApp;
    Dialog lppermission_dialog;
    Dialog lpsettingDialog;
    String[] lpstrArr;
    String[] lpstrArr33;
    String lpstrpos;
    public int lpMY_REQUEST_CODE = 111;
    boolean lpifPer = false;
    private int lprequestCode = -1;
    public boolean lpaBoolean = false;
    public ActivityResultLauncher<Intent> lpresultHandler;


    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public Class<lpMainActViewModelLp> createViewModel() {
        return lpMainActViewModelLp.class;
    }

    @Override
    public int getContent() {
        this.lpstrArr = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
        this.lpstrArr33 = new String[]{"android.permission.RECORD_AUDIO", "android.permission.READ_MEDIA_AUDIO"};
        lpBroadcastReceiver lpbroadcastreceiver = new lpBroadcastReceiver();
        registerReceiver(lpbroadcastreceiver, lpbroadcastreceiver.lpgetDataFilter());
        lppermissionCheck();
        lpregisterForActivityResult();
        Log.e("VoiceChanger", "MainAct_onCreate");

        return R.layout.activity_main;
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void lpmainView() {
        AlertDialog create = new AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        this.lpalertDialog = create;
        create.setTitle(getString(R.string.Grant_Permission));
        AlertDialog alertDialog = this.lpalertDialog;
        AlertDialog alertDialog2 = null;
        if (alertDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
            alertDialog = null;
        }
        alertDialog.setCancelable(false);
        AlertDialog alertDialog3 = this.lpalertDialog;
        if (alertDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
            alertDialog3 = null;
        }
        alertDialog3.setMessage(getString(R.string.Please_grant_all_permissions));
        AlertDialog alertDialog4 = this.lpalertDialog;
        if (alertDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
        } else {
            alertDialog2 = alertDialog4;
        }
        alertDialog2.setButton(-1, getString(R.string.Go_to_setting), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                lpAppConstant.lpAPP_CONSTANT.setCheckResumePermissionMain(true);
                AlertDialog alertDialog5 = lpMainActivityLp.this.lpalertDialog;
                if (alertDialog5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
                    alertDialog5 = null;
                }
                alertDialog5.dismiss();
                lpMainActivityLp.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 1112);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", lpMainActivityLp.this.getApplicationContext().getPackageName(), null));
                lpMainActivityLp.this.startActivity(intent);
            }
        });
    }

    @Override
    public void lpinitViews() {
        lpmainEvents();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
    super.onDestroy();
    }

    private void lpmainEvents() {
        lpTapClick.lptap(getBindingData().llRecord, (Function1<View, Unit>) view -> {
            lpMainActivityLp.this.lpstrpos = "record";
            Log.e("tap--", "invoke: ");
            lpMainActivityLp.this.nextActivity(lpRecordingActivity.class, null);
            return null;
        });
        lpTapClick.lptap(getBindingData().llOpenFile, (Function1<View, Unit>) view -> {
            lpMainActivityLp.this.lpstrpos = "openfile";
            lpMainActivityLp.this.nextActivity(lpOpenFileActivity.class, null);
            return null;
        });
        lpTapClick.lptap(getBindingData().llTextAudio, (Function1<View, Unit>) view -> {
            lpMainActivityLp.this.lpstrpos = "textaudio";
            lpMainActivityLp.this.nextActivity(lpTxtToAudioActivity.class, null);
            return null;
        });


        lpTapClick.lptap(getBindingData().llMyVoice, (Function1<View, Unit>) view -> {
            lpMainActivityLp.this.lpstrpos = "myvoice";
            lpMainActivityLp.this.nextActivity(lpCreationActivity.class, null);
            return null;
        });


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
    public void onStop() {
super.onStop();
        Log.e("VoiceChanger", "MainAct_onStop");
    }

    private void vkcopySimpleToDevice(int i, String str) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = getResources().openRawResource(i);
            try {
                Intrinsics.checkNotNullExpressionValue(inputStream, "resources.openRawResource(id)");
                fileOutputStream = new FileOutputStream(str);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } finally {
            try {
            } catch (Exception e) {
            }
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
      finishAffinity();
    }



    public void lppermissiondialog() {
        Log.e("vvccc", "permissiondialog: ");
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_permission, (ViewGroup) null);
        Dialog dialog = new Dialog(this);
        this.lppermission_dialog = dialog;
        dialog.setContentView(inflate);
        this.lppermission_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.lppermission_dialog.getWindow().addFlags(67108864);
        this.lppermission_dialog.getWindow().setLayout(-1, -2);
        this.lppermission_dialog.setCancelable(false);
        this.lppermission_dialog.setCanceledOnTouchOutside(false);

        this.lppermission_dialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            lpMainActivityLp.this.lppermission_dialog.dismiss();
            if (lpMainActivityLp.this.lpAlreadyGranted()) {
                return;
            }
            Log.e("zzzzz", "onActivityResult: 222");
            lpMainActivityLp.this.lprequestPermission();
        });

        this.lppermission_dialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            lpMainActivityLp.this.lppermission_dialog.dismiss();
            lpMainActivityLp.this.finishAffinity();
        });
        this.lppermission_dialog.show();
    }

    private void openSettingsDialog() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_permission, (ViewGroup) null);
        Dialog dialog = new Dialog(this);
        this.lpsettingDialog = dialog;
        dialog.setContentView(inflate);
        this.lpsettingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.lpsettingDialog.getWindow().addFlags(67108864);
        this.lpsettingDialog.getWindow().setLayout(-1, -2);
        this.lpsettingDialog.setCancelable(false);
        this.lpsettingDialog.setCanceledOnTouchOutside(false);
        this.lpsettingDialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            lpMainActivityLp.this.lpsettingDialog.dismiss();
            lpMainActivityLp.this.finishAffinity();
        });

        this.lpsettingDialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            lpMainActivityLp.this.lpsettingDialog.dismiss();
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", lpMainActivityLp.this.getPackageName(), null));
            lpMainActivityLp.this.startActivityForResultt(intent, 100);
        });
        this.lpsettingDialog.show();
    }

    private void lpregisterForActivityResult() {
        lpresultHandler = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.app.voicechangereffect.activity.lpMainActivityLp.21
            @Override
            public void onActivityResult(Object obj) {
                onActivityResult((ActivityResult) obj);
            }

            public void onActivityResult(ActivityResult activityResult) {
                lpMainActivityLp lpmainactivitylp = lpMainActivityLp.this;
                lpmainactivitylp.onActivityResult(lpmainactivitylp.lprequestCode, activityResult.getResultCode(), activityResult.getData());
                lpMainActivityLp.this.lprequestCode = -1;
            }
        });
    }

    public boolean lpAlreadyGranted() {
        return Build.VERSION.SDK_INT > 32 ? ContextCompat.checkSelfPermission(this, "android.permission.READ_MEDIA_AUDIO") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0 : ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0;
    }

    public void lprequestPermission() {
        if (Build.VERSION.SDK_INT > 32) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_MEDIA_AUDIO")) {
                ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO");
            }
            ActivityCompat.requestPermissions(this, this.lpstrArr33, 1);
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE");
            ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO");
        }
        ActivityCompat.requestPermissions(this, this.lpstrArr, 1);
        Log.e("per", "requestPermission: ");
    }

    public void startActivityForResultt(Intent intent, int i) {
        this.lprequestCode = i;
        ActivityResultLauncher<Intent> activityResultLauncher = this.lpresultHandler;
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(intent);
        }
    }

    private void lprequestForSpecificPermission() {
        Log.e("qqaa", "requestForSpecificPermission: qqqq");
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
    }

    public void lpsendnotification() {
        Log.e("bbb", "sendnotification: ");
        if (Build.VERSION.SDK_INT > 32) {
            Log.e("bbb", "sendnotification: 1111");
            if (lpcheckIfAlreadyhavePermission()) {
                return;
            }
            Log.e("bbb", "sendnotification: 2222");
            lprequestForSpecificPermission();
        }
    }

    private boolean lpcheckIfAlreadyhavePermission() {
        Log.e("nnnnn", "checkIfAlreadyhavePermission: ");
        return ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0;
    }

    private void lppermissionCheck() {
        if (lpAlreadyGranted()) {
            return;
        }
        Log.e("zzzzz", "onActivityResult: 333");
        lprequestPermission();
    }

    private void permissionNotAllowed() {
        final Dialog dialog = new Dialog(this);
        this.lpdialog = dialog;
        dialog.setContentView(R.layout.dialog_permission);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.lpdialog.setCanceledOnTouchOutside(false);
        this.lpdialog.setCancelable(false);
        this.lpdialog.getWindow().setLayout(-1, -2);
        if (!this.lpdialog.isShowing()) {
            this.lpdialog.show();
            Log.e("per", "permissionNotAllowed: ");
        }
        this.lpdialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            lpMainActivityLp.this.lpdialog.dismiss();
            if (ActivityCompat.shouldShowRequestPermissionRationale(lpMainActivityLp.this, "android.permission.WRITE_CALENDAR")) {
                ActivityCompat.requestPermissions(lpMainActivityLp.this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("app_package", lpMainActivityLp.this.getPackageName());
            intent.putExtra("app_uid", lpMainActivityLp.this.getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", lpMainActivityLp.this.getPackageName());
            lpMainActivityLp.this.lpaBoolean = true;
            lpMainActivityLp.this.startActivity(intent);
        });

        this.lpdialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            dialog.dismiss();
            lpMainActivityLp.this.finishAffinity();
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            if (iArr.length > 0) {
                int length = iArr.length;
                for (int i2 = 0; i2 < length && iArr[i2] == 0; i2++) {
                }
            }
            if (!this.lpifPer) {
                this.lpifPer = true;
                lpsendnotification();
            }
            if (Build.VERSION.SDK_INT < 32) {
                lppermissionCheck();
            }
        }
        if (i == 101) {
            if (iArr[0] == 0) {
                Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
                Log.e("VoiceChanger", "Permission_Granted");
                lppermissionCheck();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.POST_NOTIFICATIONS")) {
                lprequestForSpecificPermission();
            } else {
                permissionNotAllowed();
            }
        }
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == this.lpMY_REQUEST_CODE && i2 != -1) {
            Log.e("TAG", "onActivityResult: " + i2);
        }
        if (i == 100) {
            if (lpAlreadyGranted()) {
                Log.e("VoiceChanger", "Permission_Granted");
                Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
                lpsendnotification();
                if (Build.VERSION.SDK_INT < 32) {
                    lppermissionCheck();
                    return;
                }
                return;
            }
            lprequestPermission();
        }
    }
}
