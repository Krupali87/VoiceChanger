package com.app.voicechangereffect.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import com.app.voicechangereffect.databinding.ActivitySplashBinding;
import com.google.firebase.FirebaseApp;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.viewModel.lpSplashActViewModelLp;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import com.app.voicechangereffect.R;

public final class lpSplashActivity extends lpBaseActivity<lpSplashActViewModelLp, ActivitySplashBinding> {
    public static boolean IntentFromSetting = false;
    public static boolean actPauseRes = false;
    public static boolean isNoNetwork = false;
    public static lpSplashActivity splashActivity;

    Dialog dialogNetwork;
    Handler handler;
    Handler hnd_HandlerMain9000;
    boolean isShowingDissmissed;
    Runnable mRunnable;

    Runnable run_RunnableMain9000;

    private int requestcode = -1;
    private ActivityResultLauncher<Intent> resultLauncher = null;
    private ConnectivityManager.NetworkCallback callback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            Log.e("network", "onAvailable: " + network);
            if (lpSplashActivity.this.isFinishing()) {
                return;
            }
            lpSplashActivity.this.runOnUiThread(() -> {
                lpSplashActivity.this.dismissDialog();
                lpSplashActivity.this.isShowingDissmissed = false;
            });
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
        }

        @Override
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Log.e("network", "onCapabilitiesChanged: " + networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED));
        }
    };

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public int getContent() {
        return R.layout.activity_splash;
    }

    @Override
    public void lpinitViews() {
        this.hnd_HandlerMain9000 = new Handler();
        new Handler().postDelayed(new AnonymousClass2(), 0L);

    }


    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override
        public void run() {
            FirebaseApp.initializeApp(lpSplashActivity.this);

            lpSplashActivity.this.run_RunnableMain9000 = () -> lpSplashActivity.this.startHomePage();
            lpSplashActivity.this.hnd_HandlerMain9000.postDelayed(lpSplashActivity.this.run_RunnableMain9000, 40000L);
            lpSplashActivity.this.startHomePage();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.hnd_HandlerMain9000;
        if (handler == null && this.run_RunnableMain9000 == null) {
            return;
        }
        handler.removeCallbacks(this.run_RunnableMain9000);
    }


    @Override
    public Class<lpSplashActViewModelLp> createViewModel() {
        return lpSplashActViewModelLp.class;
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
        IntentFromSetting = false;
        isNoNetwork = false;
        actPauseRes = false;
        splashActivity = this;
        try {
            ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).requestNetwork(new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build(), this.callback);
        } catch (Exception e) {
            try {
                e.printStackTrace();
                forActivityResults();
                Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResultt(intent, 100);
            } catch (Exception e2) {
                e2.printStackTrace();
                forActivityResults();
                Intent intent2 = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                intent2.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResultt(intent2, 100);
            }
        }
    }

    private final void forActivityResults() {
        this.resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public void onActivityResult(Object obj) {
                onActivityResult((ActivityResult) obj);
            }

            public void onActivityResult(ActivityResult activityResult) {
                lpSplashActivity lpsplashactivity = lpSplashActivity.this;
                lpsplashactivity.onActivityResult(lpsplashactivity.requestcode, activityResult.getResultCode(), activityResult.getData());
                lpSplashActivity.this.requestcode = -1;
            }
        });
    }

    public void startActivityForResultt(Intent intent, int i) {
        this.requestcode = i;
        ActivityResultLauncher<Intent> activityResultLauncher = this.resultLauncher;
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(intent);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            try {
                ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).requestNetwork(new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build(), this.callback);
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    Intent intent2 = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                    intent2.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResultt(intent2, 100);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Intent intent3 = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
                    intent3.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResultt(intent3, 100);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("VoiceChanger", "SplashAct_onBack");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void dismissDialog() {
        Dialog dialog = this.dialogNetwork;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        isNoNetwork = false;
        this.dialogNetwork.dismiss();
        this.isShowingDissmissed = true;
    }

    @Override
    public void onPause() {
        Runnable runnable;
        super.onPause();
        Log.e("TAG", "onPause:::: " + this.handler);
        Log.e("VoiceChanger", "SplashAct_onPause");
        dismissDialog();
        actPauseRes = true;
        Handler handler = this.handler;
        if (handler == null || (runnable = this.mRunnable) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
    }

    void startHomePage() {
        if (hasPermissions(this)) {
            startActivity(new Intent(this, lpPermissionActivity.class));
        }  else {
            startActivity(new Intent(this, lpMainActivityLp.class));
            finish();
        }
        finish();
    }

    public static boolean hasPermissions(Activity activity) {
        return Build.VERSION.SDK_INT > 32 ? ContextCompat.checkSelfPermission(activity, "android.permission.READ_MEDIA_AUDIO") == 0 && ContextCompat.checkSelfPermission(activity, "android.permission.RECORD_AUDIO") == 0 : ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(activity, "android.permission.RECORD_AUDIO") == 0;
    }
}
