package com.app.voicechangereffect.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.app.voicechangereffect.databinding.ActivityPermissionBinding;
import com.app.voicechangereffect.viewModel.lpPermissionViewModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;


public class lpPermissionActivity extends lpBaseActivity<lpPermissionViewModel, ActivityPermissionBinding> {
    private Dialog lpdialog;
    Dialog lppermission_dialog;
    TextView lppermssion_btn;
    Dialog lpsettingDialog;
    String[] lpstrArr;
    String[] lpstrArr33;
    boolean lpifPer = false;
    private int lprequestCode = -1;
    private ActivityResultLauncher<Intent> lpresultHandler = null;
    public boolean lpaBoolean = false;

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> lpbasefragment) {
    }

    @Override
    public void switchFragment(KClass<?> kClass, Bundle bundle, boolean z) {
    }

    @Override
    public int getContent() {
        return R.layout.activity_permission;
    }

    private void lppermissionCheck() {
        if (lpAlreadyGranted()) {
            lpnextAct();
        } else {
            lprequestPermission();
        }
    }

    public void lpnextAct() {
        startActivity(new Intent(this, lpMainActivityLp.class));
    }

    public boolean lpAlreadyGranted() {
        return Build.VERSION.SDK_INT > 32 ? ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0 : ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0;
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
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0) {
                int length = iArr.length;
                for (int i2 = 0; i2 < length && iArr[i2] == 0; i2++) {
                }
            }
            if (!this.lpifPer) {
                this.lpifPer = true;
                Log.e("VoiceChanger", "Permission_Granted");
                Toast.makeText(this, getString(R.string.grantPermission), Toast.LENGTH_SHORT).show();
                lpsendnotification();
            }
            if (Build.VERSION.SDK_INT < 32) {
                lppermissionCheck();
            }
        }
        if (i == 101) {
            if (iArr[0] == 0) {
                lppermissionCheck();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.POST_NOTIFICATIONS")) {
                lprequestForSpecificPermission();
            } else {
                lppermissionNotAllowed();
            }
        }
    }

    public void lppermissionDialogShow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_permission, (ViewGroup) null);
        Dialog dialog = new Dialog(this);
        this.lppermission_dialog = dialog;
        dialog.setContentView(inflate);
        this.lppermission_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.lppermission_dialog.getWindow().addFlags(67108864);
        this.lppermission_dialog.getWindow().setLayout(-1, -2);
        this.lppermission_dialog.setCancelable(false);
        this.lppermission_dialog.setCanceledOnTouchOutside(false);
        this.lppermission_dialog.findViewById(R.id.cancle_btn).setOnClickListener(view -> {
            lpPermissionActivity.this.lppermission_dialog.dismiss();
            lpPermissionActivity.this.lpnextAct();
        });
        this.lppermission_dialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            Log.e("rrr", "onClick: 3333");
            lpPermissionActivity.this.lppermission_dialog.dismiss();
            if (lpPermissionActivity.this.lpAlreadyGranted()) {
                return;
            }
            lpPermissionActivity.this.lprequestPermission();
        });
        Log.e("VoiceChanger", "Permission_Dialog_Show");
        this.lppermission_dialog.show();
    }

    private void lpopenSettingsDialog() {
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
            lpPermissionActivity.this.lpsettingDialog.dismiss();
            lpPermissionActivity.this.lpnextAct();
        });
        this.lpsettingDialog.findViewById(R.id.permission_btn).setOnClickListener(view -> {
            Log.e("rrr", "onClick: 2222");
            lpPermissionActivity.this.lpsettingDialog.dismiss();
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", lpPermissionActivity.this.getPackageName(), null));
            lpPermissionActivity.this.lpstartActivityForResults(intent, 100);
        });
        Log.e("VoiceChanger", "Setting_Dialog_Show");
        this.lpsettingDialog.show();
    }

    private void lpregisterForActivityResult() {
        this.lpresultHandler = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.app.voicechangereffect.activity.lpPermissionActivity.5
            @Override
            public void onActivityResult(Object obj) {
                onActivityResult((ActivityResult) obj);
            }

            public void onActivityResult(ActivityResult activityResult) {
                lpPermissionActivity lppermissionactivity = lpPermissionActivity.this;
                lppermissionactivity.onActivityResult(lppermissionactivity.lprequestCode, activityResult.getResultCode(), activityResult.getData());
                lpPermissionActivity.this.lprequestCode = -1;
            }
        });
    }

    public void lpstartActivityForResults(Intent intent, int i) {
        this.lprequestCode = i;
        ActivityResultLauncher<Intent> activityResultLauncher = this.lpresultHandler;
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(intent);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
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

    private void lprequestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
    }

    public void lpsendnotification() {
        if (Build.VERSION.SDK_INT <= 32 || lpcheckIfAlreadyHavePermission()) {
            return;
        }
        lprequestForSpecificPermission();
    }

    private boolean lpcheckIfAlreadyHavePermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0;
    }

    private void lppermissionNotAllowed() {
        Dialog dialog = new Dialog(this);
        this.lpdialog = dialog;
        dialog.setContentView(R.layout.dialog_permission);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.lpdialog.setCanceledOnTouchOutside(false);
        this.lpdialog.setCancelable(false);
        this.lpdialog.getWindow().setLayout(-1, -2);
        if (!this.lpdialog.isShowing()) {
            this.lpdialog.show();
        }

        ((TextView) this.lpdialog.findViewById(R.id.permission_btn)).setOnClickListener(view -> {
            Log.e("rrr", "onClick: 1111");
            lpPermissionActivity.this.lpdialog.dismiss();
            if (ActivityCompat.shouldShowRequestPermissionRationale(lpPermissionActivity.this, "android.permission.WRITE_CALENDAR")) {
                ActivityCompat.requestPermissions(lpPermissionActivity.this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("app_package", lpPermissionActivity.this.getPackageName());
            intent.putExtra("app_uid", lpPermissionActivity.this.getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", lpPermissionActivity.this.getPackageName());
            lpPermissionActivity.this.lpaBoolean = true;
            lpPermissionActivity.this.startActivity(intent);
        });

        ((TextView) this.lpdialog.findViewById(R.id.cancle_btn)).setOnClickListener(view -> lpPermissionActivity.this.lpnextAct());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("VoiceChanger", "PermissionAct_onResume");
        if (this.lpaBoolean) {
            this.lpaBoolean = false;
            lpsendnotification();
        }
        if (lpAlreadyGranted()) {
            Dialog dialog = this.lpdialog;
            if (dialog != null && dialog.isShowing()) {
                this.lpdialog.dismiss();
            } else {
                Dialog dialog2 = this.lppermission_dialog;
                if (dialog2 != null && dialog2.isShowing()) {
                    this.lppermission_dialog.dismiss();
                } else {
                    Dialog dialog3 = this.lpsettingDialog;
                    if (dialog3 != null && dialog3.isShowing()) {
                        this.lpsettingDialog.dismiss();
                    }
                }
            }
            lpnextAct();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("VoiceChanger", "PermissionAct_onBack");
        finishAffinity();
    }

    @Override
    public void lpinitViews() {
        this.lppermssion_btn = findViewById(R.id.txtAllow);
        this.lpstrArr = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
        this.lpstrArr33 = new String[]{"android.permission.RECORD_AUDIO", "android.permission.READ_MEDIA_AUDIO"};
        lpregisterForActivityResult();
        this.lppermssion_btn.setOnClickListener(view -> lpPermissionActivity.this.m387xdd7797e5(view));
        this.lpifPer = false;
    }

    public void m387xdd7797e5(View view) {
        this.lppermssion_btn.setEnabled(false);

        new Handler().postDelayed(() -> lpPermissionActivity.this.lpm386xdca91964(), 1000L);
        lppermissionCheck();
    }

    public void lpm386xdca91964() {
        this.lppermssion_btn.setEnabled(true);
    }

    @Override
    public Class<lpPermissionViewModel> createViewModel() {
        return lpPermissionViewModel.class;
    }

    @Override
    public void lpmainView() {

                lpPermissionActivity.this.getBindingData().mainLayout.setVisibility(View.VISIBLE);
                lpPermissionActivity.this.getBindingData().mainLayout.setVisibility(View.GONE);


        Log.e("VoiceChanger", "PermissionAct_onCreate");
        if (lpAlreadyGranted()) {
            lpnextAct();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void lpcopySimpleToDevice(int i, String str) throws IOException {
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
}
