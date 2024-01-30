package com.app.voicechangereffect.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseActivity;
import com.app.voicechangereffect.allBaseAct.lpBaseFragment;
import com.app.voicechangereffect.allBaseAct.lpBasePopupMenu;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.custUi.constatnt.lpTapClick;
import com.app.voicechangereffect.custUi.lpAppConstant;
import com.app.voicechangereffect.custUi.lpFileMethods;
import com.app.voicechangereffect.databinding.ActivityTxtToAudioBinding;
import com.app.voicechangereffect.viewModel.lpTextToAudioViewModel;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;


@SuppressLint("WrongConstant")
public final class lpTxtToAudioActivity extends lpBaseActivity<lpTextToAudioViewModel, ActivityTxtToAudioBinding> {
    Bundle bundle;
    public String code = "en";
    public boolean isClickNext;
    private int label;

    Ref.ObjectRef objectRef;
    private lpTxtToAudioActivity toAudioActivity;
    public TextToSpeech tts;

    @Override
    public void navigate(int i, Bundle bundle, boolean z) {
    }

    @Override
    public void navigateUp() {
    }

    @Override
    public Class<lpTextToAudioViewModel> createViewModel() {
        return lpTextToAudioViewModel.class;
    }

    @Override
    public int getContent() {
        this.toAudioActivity = this;
        Log.e("VoiceChanger", "TxtToAudioAct_onCreate");
        return R.layout.activity_txt_to_audio;
    }

    @Override
    public void onFragmentResumed(lpBaseFragment<?, ?> fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @Override
    public void switchFragment(KClass<?> fragment, Bundle bundle, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onRestart() {
        Log.e("mk", "onRestart--");
        super.onRestart();
        Log.e("VoiceChanger", "TxtToAudioAct_onRestart");
        if (this.isClickNext) {
            return;
        }
        Log.e("mk", "onRestart");

    }

    @Override
    public void lpmainView() {
        getBindingData().tvLocate.setVisibility(0);
        getBindingData().toolbar.tvTitle.setText("Text to Audio");
       this.isClickNext = false;
    }

    @Override
    public void lpinitViews() {
        // kotlin.jvm.functions.Function1
        lpTapClick.lptap(getBindingData().toolbar.ivBack, (Function1<View, Unit>) view -> {
            lpTxtToAudioActivity.this.onBackPressed();
            return Unit.INSTANCE;
        });
        lpTapClick.lptap(getBindingData().tvNext, (Function1<View, Unit>) view -> {
            Log.d("Chekprogress", "tvnext is call-----------");
            lpTxtToAudioActivity.this.getBindingData().VNRotateloading.setVisibility(0);
            if (StringsKt.trim((CharSequence) lpTxtToAudioActivity.this.getBindingData().input.getText().toString()).toString().length() <= 2) {
                Log.d("Chekprogress", "tvnext iffffffff is call-----------");
                Toast.makeText(lpTxtToAudioActivity.this.toAudioActivity, R.string.enter_minimum_words, 0).show();
                return null;
            }
            Log.d("Chekprogress", "----------------");
            String obj = StringsKt.trim((CharSequence) lpTxtToAudioActivity.this.getBindingData().input.getText().toString()).toString();
            String lpgetMainDirPath = lpFileMethods.lpgetMainDirPath(lpTxtToAudioActivity.this.toAudioActivity);
            Intrinsics.checkNotNullExpressionValue(lpgetMainDirPath, "getDirectoryPath(this)");
            lpTxtToAudioActivity.this.initTextToSpeech(obj, lpgetMainDirPath);
            return null;
        });
        lpTapClick.lptap(getBindingData().tvLocate, (Function1<View, Unit>) view -> {
            Log.d("eee---", "invoke: click isClickNext :: " + lpTxtToAudioActivity.this.isClickNext);
            if (lpTxtToAudioActivity.this.isClickNext) {
                Log.d("eee---", "invoke: click:: ");
                return null;
            }
            new lpBasePopupMenu(lpTxtToAudioActivity.this.toAudioActivity, R.layout.layout_popup_menu_locate, new lpBasePopupMenu.PopupMenuCustomOnClickListener() {
                @Override
                public void initView(View view2) {
                }

                @Override
                public void onClick(int i, View view2) {
                    Log.d("eee---", "lpview:: ");
                    if (i == R.id.tv_english) {
                        lpTxtToAudioActivity.this.code = "en";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.english));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_english));
                    } else if (i == R.id.tv_portuguese) {
                        lpTxtToAudioActivity.this.code = "pt";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.portuguese));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_portuguese));
                    } else if (i == R.id.tv_spanish) {
                        lpTxtToAudioActivity.this.code = "es";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.spanish));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_spanish));
                    } else if (i == R.id.tv_french) {
                        lpTxtToAudioActivity.this.code = "fr";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.french));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_france));
                    } else if (i == R.id.tv_german) {
                        lpTxtToAudioActivity.this.code = "de";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.german));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_german));
                    } else if (i == R.id.tv_hindi) {
                        lpTxtToAudioActivity.this.code = "hi";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.hindi));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_hindi));
                    } else if (i == R.id.tv_indonesia) {
                        lpTxtToAudioActivity.this.code = "id";
                        lpTxtToAudioActivity.this.getBindingData().tvCountry.setText(lpTxtToAudioActivity.this.getResources().getString(R.string.indonesia));
                        lpTxtToAudioActivity.this.getBindingData().imgFlag.setImageDrawable(lpTxtToAudioActivity.this.getResources().getDrawable(R.drawable.ic_indonesia));
                    }
                }
            }).showRight(lpTxtToAudioActivity.this.getBindingData().imgArrow);
            return null;
        });
    }

    public void initTextToSpeech(final String str, final String str2) {
        this.tts = new TextToSpeech(this, i -> {
            if (i == -1) {
                Log.d("Chekprogress", "iffffffffff---------");
                Toast.makeText(lpTxtToAudioActivity.this.toAudioActivity, lpTxtToAudioActivity.this.toAudioActivity.getString(R.string.Error), 0).show();
            } else if (i == 0) {
                Log.d("Chekprogress", "else ifffffff---------");
                TextToSpeech textToSpeech = lpTxtToAudioActivity.this.tts;
                Integer valueOf = textToSpeech == null ? null : Integer.valueOf(textToSpeech.setLanguage(new Locale(lpTxtToAudioActivity.this.toAudioActivity.code)));
                if ((valueOf != null && valueOf.intValue() == -1) || (valueOf != null && valueOf.intValue() == -2)) {
                    Toast.makeText(lpTxtToAudioActivity.this.toAudioActivity, lpTxtToAudioActivity.this.toAudioActivity.getString(R.string.language_is_not_supported), 0).show();
                    lpTxtToAudioActivity.this.toAudioActivity.isClickNext = false;
                    lpTxtToAudioActivity.this.getBindingData().VNRotateloading.setVisibility(8);
                    return;
                }
                Log.d("Chekprogress", "valueOf == null---------");
                lpTxtToAudioActivity.this.toAudioActivity.promiseEffect(str, str2);
                lpTxtToAudioActivity.this.getBindingData().VNRotateloading.setVisibility(8);
            }
        }, "android.intent.action.TTS_SERVICE");
    }

    public String fileCreate(String str, String str2) {
        if (str != null && str.length() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append('/');
            String format = String.format("voice_%1$s", Arrays.copyOf(new Object[]{Long.valueOf(System.currentTimeMillis() / 1000)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sb.append(format);
            sb.append(".wav");
            String sb2 = sb.toString();
            Bundle bundle = new Bundle();
            bundle.putString("utteranceId", str);
            File file = new File(sb2);
            try {
                if (file.exists() || file.createNewFile()) {
                    TextToSpeech textToSpeech = lpTxtToAudioActivity.this.tts;
                    Intrinsics.stringPlus("synthesize returns = ", textToSpeech == null ? null : Integer.valueOf(textToSpeech.synthesizeToFile(str, bundle, file, sb2)));
                    Uri fromFile = Uri.fromFile(file);
                    String path = fromFile != null ? fromFile.getPath() : null;
                    return path == null ? "" : path;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void promiseEffect(final String str, final String str2) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        this.objectRef = objectRef;
        objectRef.element = "";
        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope(this), new Function1() {
            @Override
            public Object invoke(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (lpTxtToAudioActivity.this.label == 0) {
                    lpTxtToAudioActivity.this.getBindingData().llLoading.setVisibility(8);
                    lpTxtToAudioActivity.this.objectRef.element = "";
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }, new Function1() {
            public Object invoke(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (lpTxtToAudioActivity.this.label == 0) {
                    lpTxtToAudioActivity.this.objectRef.element = lpTxtToAudioActivity.this.fileCreate(str, str2);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }, new Function1() {
            @Override
            public Unit invoke(Object obj) {
                TextToSpeech textToSpeech = lpTxtToAudioActivity.this.tts;
                if (textToSpeech != null) {
                    final Ref.ObjectRef objectRef2 = lpTxtToAudioActivity.this.objectRef;
                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String str3) {
                            Log.e("fff---", "onStart:  ");
                        }

                        @Override
                        public void onDone(String str3) {
                            Bundle bundle = new Bundle();
                            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_PATH_VOICE(), (String) objectRef2.element);
                            bundle.putString(lpAppConstant.lpAPP_CONSTANT.getKEY_SCREEN_INTO_VOICE_EFFECTS(), "TextAudioActivity");
                            lpTxtToAudioActivity.this.nextActivity(lpChangeEffectActivity.class, bundle);
                            lpTxtToAudioActivity.this.getBindingData().input.setText("");
                            lpTxtToAudioActivity.this.getBindingData().llLoading.setVisibility(8);
                        }

                        @Override
                        public void onError(String str3) {
                            Toast.makeText(lpTxtToAudioActivity.this, lpTxtToAudioActivity.this.getString(R.string.Error), 0).show();
                            lpTxtToAudioActivity.this.getBindingData().llLoading.setVisibility(8);
                        }
                    });
                    Log.e("fff---", "invoke:  ");
                }
                return null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.e("VoiceChanger", "TxtToAudioAct_onBack");
        finish();
    }
}
