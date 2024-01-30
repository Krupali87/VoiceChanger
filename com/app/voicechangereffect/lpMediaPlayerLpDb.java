package com.app.voicechangereffect;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import com.un4seen.bass.BASS;
import com.un4seen.bass.BASS_FX;
import com.un4seen.bass.BASSenc;
import com.un4seen.bass.BASSmix;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class lpMediaPlayerLpDb  {
    private static final String lplogTag = "DBMediaPlayer";
    private int lpamplifyFx;
    private int lpautoEffectFx;
    private int lpbigQuedEffects;
    private int lpchannelPlay;
    private int lpchorosEffectFx;
    private int lpcompressorEffects;
    private int lpeffectDisort;
    private int lpeffectEQ2;
    private int lpeffectEQ3;
    private int lpeffectEcho;
    private int lpeffectFlenger;
    private int lpeffectPhaser;
    private int lpeffectReverb;
    private int lpeffectRotate;
    private int lpeffextEQ1;
    private int lpeffextEQ4;
    private ArrayList<Integer> lpintegerArrayList;
    private boolean lpisMixNeed;
    private boolean lpisThisReverse;
    private lpDBMediaListener lpmediaListener;
    private final String lpstrMediaPath;
    private String lpstrMixPath;
    private int lptempChanel;
    private int lpintCurrPosition = 0;
    private int lpintDuration = 0;
    private boolean lpisPlaying = false;
    private final Handler lpmHandler = new Handler(Looper.getMainLooper()) { // from class: com.reactlibrary.basseffect.lpMediaPlayerLpDb.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            lpMediaPlayerLpDb lpmediaplayerlpdb = lpMediaPlayerLpDb.this;
            lpmediaplayerlpdb.lpintCurrPosition = lpmediaplayerlpdb.lpgetChannelPos();
            lpMediaPlayerLpDb lpmediaplayerlpdb2 = lpMediaPlayerLpDb.this;
            lpmediaplayerlpdb2.lpintDuration = lpmediaplayerlpdb2.lpgetChanLength();
            if (!lpMediaPlayerLpDb.this.lpisThisReverse) {
                if (lpMediaPlayerLpDb.this.lpintCurrPosition >= lpMediaPlayerLpDb.this.lpintDuration) {
                    removeMessages(0);
                    if (lpMediaPlayerLpDb.this.lpmediaListener != null) {
                        lpMediaPlayerLpDb.this.lpmediaListener.onMediaCompleteListener();
                        return;
                    }
                    return;
                }
                sendEmptyMessageDelayed(0, 50L);
            } else if (lpMediaPlayerLpDb.this.lpintCurrPosition <= 0) {
                removeMessages(0);
                if (lpMediaPlayerLpDb.this.lpmediaListener != null) {
                    lpMediaPlayerLpDb.this.lpmediaListener.onMediaCompleteListener();
                }
            } else {
                sendEmptyMessageDelayed(0, 50L);
            }
        }
    };

    public lpMediaPlayerLpDb(String str) {
        this.lpstrMediaPath = str;
    }

    public void setLpisMixNeed(boolean z) {
        this.lpisMixNeed = z;
    }

    public void setPathMix(String str) {
        this.lpstrMixPath = str;
    }

    public boolean lpaudioPrepare() {
        String str = this.lpstrMediaPath;
        if (str == null || str.equals("")) {
            return false;
        }
        if (this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("mp3") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("wav") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("ogg") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("flac") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("aac") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("mid") || this.lpstrMediaPath.toLowerCase(Locale.getDefault()).endsWith("wma")) {
            lpmediaInit();
            return true;
        }
        new Exception("DBMidiPlayer:can not support file format").printStackTrace();
        return false;
    }

    public void lpaudioStart() {
        this.lpisPlaying = true;
        int i = this.lpchannelPlay;
        if (i != 0) {
            BASS.BASS_ChannelPlay(i, false);
        }
        this.lpmHandler.sendEmptyMessage(0);

    }

    public void lpsetPitchAudio(int i) {
        int i2 = this.lpchannelPlay;
        if (i2 != 0) {
            BASS.BASS_ChannelSetAttribute(i2, 65537, i);
        }
    }

    public void lpsetRateAudio(float f) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            BASS.BASS_ChannelSetAttribute(i, 65536, f);
        }
    }

    public void lpsetReverbAudio(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectReverb == 0) {
                    this.lpeffectReverb = BASS.BASS_ChannelSetFX(i, 8, 0);
                }
                if (this.lpeffectReverb != 0) {
                    BASS.BASS_DX8_REVERB bass_dx8_reverb = new BASS.BASS_DX8_REVERB();
                    BASS.BASS_FXGetParameters(this.lpeffectReverb, bass_dx8_reverb);
                    bass_dx8_reverb.fReverbMix = fArr[0];
                    bass_dx8_reverb.fReverbTime = fArr[1];
                    bass_dx8_reverb.fHighFreqRTRatio = fArr[2];
                    BASS.BASS_FXSetParameters(this.lpeffectReverb, bass_dx8_reverb);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectReverb;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectReverb = 0;
            }
        }
    }

    public void lpsetEchoAudio(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectEcho == 0) {
                    this.lpeffectEcho = BASS.BASS_ChannelSetFX(i, 3, 0);
                }
                if (this.lpeffectEcho != 0) {
                    BASS.BASS_DX8_ECHO bass_dx8_echo = new BASS.BASS_DX8_ECHO();
                    BASS.BASS_FXGetParameters(this.lpeffectEcho, bass_dx8_echo);
                    bass_dx8_echo.fLeftDelay = fArr[0];
                    bass_dx8_echo.fRightDelay = fArr[1];
                    bass_dx8_echo.fFeedback = fArr[2];
                    if (fArr.length == 4) {
                        bass_dx8_echo.fWetDryMix = fArr[3];
                    }
                    BASS.BASS_FXSetParameters(this.lpeffectEcho, bass_dx8_echo);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectEcho;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectEcho = 0;
            }
        }
    }

    public void lpsetAmpli(float f) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (f != 0.0f) {
                if (this.lpamplifyFx == 0) {
                    this.lpamplifyFx = BASS.BASS_ChannelSetFX(i, 65544, 0);
                }
                if (this.lpamplifyFx != 0) {
                    BASS_FX.BASS_BFX_DAMP bass_bfx_damp = new BASS_FX.BASS_BFX_DAMP();
                    BASS.BASS_FXGetParameters(this.lpamplifyFx, bass_bfx_damp);
                    bass_bfx_damp.fGain = f;
                    BASS.BASS_FXSetParameters(this.lpamplifyFx, bass_bfx_damp);
                    return;
                }
                return;
            }
            int i2 = this.lpamplifyFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpamplifyFx = 0;
            }
        }
    }

    public void lpdisortSet(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectDisort == 0) {
                    this.lpeffectDisort = BASS.BASS_ChannelSetFX(i, 2, 0);
                }
                if (this.lpeffectDisort != 0) {
                    BASS.BASS_DX8_DISTORTION bass_dx8_distortion = new BASS.BASS_DX8_DISTORTION();
                    BASS.BASS_FXGetParameters(this.lpeffectDisort, bass_dx8_distortion);
                    bass_dx8_distortion.fEdge = fArr[0];
                    bass_dx8_distortion.fGain = fArr[1];
                    bass_dx8_distortion.fPostEQBandwidth = fArr[2];
                    bass_dx8_distortion.fPostEQCenterFrequency = fArr[3];
                    bass_dx8_distortion.fPreLowpassCutoff = fArr[4];
                    BASS.BASS_FXSetParameters(this.lpeffectDisort, bass_dx8_distortion);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectDisort;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectDisort = 0;
            }
        }
    }

    public void lpchorusSet(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpchorosEffectFx == 0) {
                    this.lpchorosEffectFx = BASS.BASS_ChannelSetFX(i, BASS_FX.BASS_FX_BFX_CHORUS, 0);
                }
                if (this.lpchorosEffectFx != 0) {
                    BASS_FX.BASS_BFX_CHORUS bass_bfx_chorus = new BASS_FX.BASS_BFX_CHORUS();
                    BASS.BASS_FXGetParameters(this.lpchorosEffectFx, bass_bfx_chorus);
                    bass_bfx_chorus.fDryMix = fArr[0];
                    bass_bfx_chorus.fWetMix = fArr[1];
                    bass_bfx_chorus.fFeedback = fArr[2];
                    bass_bfx_chorus.fMinSweep = fArr[3];
                    bass_bfx_chorus.fMaxSweep = fArr[4];
                    bass_bfx_chorus.fRate = fArr[5];
                    BASS.BASS_FXSetParameters(this.lpchorosEffectFx, bass_bfx_chorus);
                    return;
                }
                return;
            }
            int i2 = this.lpchorosEffectFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpchorosEffectFx = 0;
            }
        }
    }

    public void lpsetFilterQuad(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpbigQuedEffects == 0) {
                    this.lpbigQuedEffects = BASS.BASS_ChannelSetFX(i, 65555, 0);
                }
                if (this.lpbigQuedEffects != 0) {
                    BASS_FX.BASS_BFX_BQF bass_bfx_bqf = new BASS_FX.BASS_BFX_BQF();
                    BASS.BASS_FXGetParameters(this.lpbigQuedEffects, bass_bfx_bqf);
                    bass_bfx_bqf.lFilter = (int) fArr[0];
                    bass_bfx_bqf.fCenter = fArr[1];
                    bass_bfx_bqf.fBandwidth = fArr[2];
                    BASS.BASS_FXSetParameters(this.lpbigQuedEffects, bass_bfx_bqf);
                    return;
                }
                return;
            }
            int i2 = this.lpbigQuedEffects;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpbigQuedEffects = 0;
            }
        }
    }

    public void lpsetEffectEcho4(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffextEQ4 == 0) {
                    this.lpeffextEQ4 = BASS.BASS_ChannelSetFX(i, 65556, 0);
                }
                if (this.lpeffextEQ4 != 0) {
                    BASS_FX.BASS_BFX_ECHO4 bass_bfx_echo4 = new BASS_FX.BASS_BFX_ECHO4();
                    bass_bfx_echo4.fDryMix = (int) fArr[0];
                    bass_bfx_echo4.fWetMix = fArr[1];
                    bass_bfx_echo4.fFeedback = fArr[2];
                    bass_bfx_echo4.fDelay = fArr[3];
                    bass_bfx_echo4.bStereo = false;
                    BASS.BASS_FXSetParameters(this.lpeffextEQ4, bass_bfx_echo4);
                    return;
                }
                return;
            }
            int i2 = this.lpeffextEQ4;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffextEQ4 = 0;
            }
        }
    }

    public void lpsetEQ1Audio(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffextEQ1 == 0) {
                    this.lpeffextEQ1 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.lpeffextEQ1 != 0) {
                    BASS.BASS_DX8_PARAMEQ bass_dx8_parameq = new BASS.BASS_DX8_PARAMEQ();
                    bass_dx8_parameq.fCenter = fArr[0];
                    bass_dx8_parameq.fBandwidth = fArr[1];
                    bass_dx8_parameq.fGain = fArr[2];
                    BASS.BASS_FXSetParameters(this.lpeffextEQ1, bass_dx8_parameq);
                    return;
                }
                return;
            }
            int i2 = this.lpeffextEQ1;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffextEQ1 = 0;
            }
        }
    }

    public void lpsetEQ2Audio(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectEQ2 == 0) {
                    this.lpeffectEQ2 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.lpeffectEQ2 != 0) {
                    BASS.BASS_DX8_PARAMEQ bass_dx8_parameq = new BASS.BASS_DX8_PARAMEQ();
                    bass_dx8_parameq.fCenter = fArr[0];
                    bass_dx8_parameq.fBandwidth = fArr[1];
                    bass_dx8_parameq.fGain = fArr[2];
                    BASS.BASS_FXSetParameters(this.lpeffectEQ2, bass_dx8_parameq);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectEQ2;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectEQ2 = 0;
            }
        }
    }

    public void lpsetEQ3Audio(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectEQ3 == 0) {
                    this.lpeffectEQ3 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.lpeffectEQ3 != 0) {
                    BASS.BASS_DX8_PARAMEQ bass_dx8_parameq = new BASS.BASS_DX8_PARAMEQ();
                    bass_dx8_parameq.fCenter = fArr[0];
                    bass_dx8_parameq.fBandwidth = fArr[1];
                    bass_dx8_parameq.fGain = fArr[2];
                    BASS.BASS_FXSetParameters(this.lpeffectEQ3, bass_dx8_parameq);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectEQ3;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectEQ3 = 0;
            }
        }
    }

    public void lprotateSet(float f) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (f != 0.0f) {
                if (this.lpeffectRotate == 0) {
                    this.lpeffectRotate = BASS.BASS_ChannelSetFX(i, 65536, 0);
                }
                if (this.lpeffectRotate != 0) {
                    BASS_FX.BASS_BFX_ROTATE bass_bfx_rotate = new BASS_FX.BASS_BFX_ROTATE();
                    BASS.BASS_FXGetParameters(this.lpeffectRotate, bass_bfx_rotate);
                    bass_bfx_rotate.fRate = f;
                    BASS.BASS_FXSetParameters(this.lpeffectRotate, bass_bfx_rotate);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectRotate;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectRotate = 0;
            }
        }
    }

    public void lpphrserSet(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectPhaser == 0) {
                    this.lpeffectPhaser = BASS.BASS_ChannelSetFX(i, BASS_FX.BASS_FX_BFX_PHASER, 0);
                }
                if (this.lpeffectPhaser != 0) {
                    BASS_FX.BASS_BFX_PHASER bass_bfx_phaser = new BASS_FX.BASS_BFX_PHASER();
                    BASS.BASS_FXGetParameters(this.lpeffectPhaser, bass_bfx_phaser);
                    bass_bfx_phaser.fDryMix = fArr[0];
                    bass_bfx_phaser.fWetMix = fArr[1];
                    bass_bfx_phaser.fFeedback = fArr[2];
                    bass_bfx_phaser.fRate = fArr[3];
                    bass_bfx_phaser.fRange = fArr[4];
                    bass_bfx_phaser.fFreq = fArr[5];
                    BASS.BASS_FXSetParameters(this.lpeffectPhaser, bass_bfx_phaser);
                    return;
                }
                return;
            }
            int i2 = this.lpeffectPhaser;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectPhaser = 0;
            }
        }
    }

    public void lpcompressorSet(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpcompressorEffects == 0) {
                    this.lpcompressorEffects = BASS.BASS_ChannelSetFX(i, 65553, 0);
                }
                if (this.lpeffectPhaser != 0) {
                    BASS_FX.BASS_BFX_COMPRESSOR2 bass_bfx_compressor2 = new BASS_FX.BASS_BFX_COMPRESSOR2();
                    BASS.BASS_FXGetParameters(this.lpcompressorEffects, bass_bfx_compressor2);
                    bass_bfx_compressor2.fGain = fArr[0];
                    bass_bfx_compressor2.fThreshold = fArr[1];
                    bass_bfx_compressor2.fRatio = fArr[2];
                    bass_bfx_compressor2.fAttack = fArr[3];
                    bass_bfx_compressor2.fRelease = fArr[4];
                    BASS.BASS_FXSetParameters(this.lpcompressorEffects, bass_bfx_compressor2);
                    return;
                }
                return;
            }
            int i2 = this.lpcompressorEffects;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpcompressorEffects = 0;
            }
        }
    }

    public void lpsetWahAuto(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpautoEffectFx == 0) {
                    this.lpautoEffectFx = BASS.BASS_ChannelSetFX(i, BASS_FX.BASS_FX_BFX_PHASER, 0);
                }
                if (this.lpautoEffectFx != 0) {
                    BASS_FX.BASS_BFX_PHASER bass_bfx_phaser = new BASS_FX.BASS_BFX_PHASER();
                    BASS.BASS_FXGetParameters(this.lpautoEffectFx, bass_bfx_phaser);
                    bass_bfx_phaser.fDryMix = fArr[0];
                    bass_bfx_phaser.fWetMix = fArr[1];
                    bass_bfx_phaser.fFeedback = fArr[2];
                    bass_bfx_phaser.fRate = fArr[3];
                    bass_bfx_phaser.fRange = fArr[4];
                    bass_bfx_phaser.fFreq = fArr[5];
                    BASS.BASS_FXSetParameters(this.lpautoEffectFx, bass_bfx_phaser);
                    return;
                }
                return;
            }
            int i2 = this.lpautoEffectFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpautoEffectFx = 0;
            }
        }
    }

    public void lpsetEffectFlang(float[] fArr) {
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (fArr != null) {
                if (this.lpeffectFlenger == 0) {
                    this.lpeffectFlenger = BASS.BASS_ChannelSetFX(i, 4, 0);
                }
                if (this.lpeffectFlenger != 0) {
                    try {
                        BASS.BASS_DX8_FLANGER bass_dx8_flanger = new BASS.BASS_DX8_FLANGER();
                        BASS.BASS_FXGetParameters(this.lpeffectFlenger, bass_dx8_flanger);
                        bass_dx8_flanger.fWetDryMix = fArr[0];
                        bass_dx8_flanger.fDepth = fArr[1];
                        bass_dx8_flanger.fFeedback = fArr[2];
                        bass_dx8_flanger.fDelay = fArr[3];
                        bass_dx8_flanger.lPhase = (int) fArr[4];
                        if (fArr.length == 6) {
                            bass_dx8_flanger.fFrequency = fArr[5];
                        }
                        BASS.BASS_FXSetParameters(this.lpeffectFlenger, bass_dx8_flanger);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            int i2 = this.lpeffectFlenger;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.lpeffectFlenger = 0;
            }
        }
    }

    public void lpaudioPause() {
        if (!this.lpisPlaying) {
            new Exception("DBMediaPlayer pauseAudio:HanetMediaPlayer not init").printStackTrace();
            return;
        }
        int i = this.lpchannelPlay;
        if (i != 0) {
            BASS.BASS_ChannelPause(i);
        }
    }

    public void lpaudioRelease() {
        this.lpmHandler.removeMessages(0);
        int i = this.lpeffectReverb;
        if (i != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i);
            this.lpeffectReverb = 0;
        }
        int i2 = this.lpeffectFlenger;
        if (i2 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i2);
            this.lpeffectReverb = 0;
        }
        int i3 = this.lpeffectEcho;
        if (i3 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i3);
            this.lpeffectEcho = 0;
        }
        int i4 = this.lpbigQuedEffects;
        if (i4 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i4);
            this.lpbigQuedEffects = 0;
        }
        int i5 = this.lpamplifyFx;
        if (i5 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i5);
            this.lpamplifyFx = 0;
        }
        int i6 = this.lpeffectDisort;
        if (i6 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i6);
            this.lpeffectDisort = 0;
        }
        int i7 = this.lpchorosEffectFx;
        if (i7 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i7);
            this.lpchorosEffectFx = 0;
        }
        int i8 = this.lpeffextEQ4;
        if (i8 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i8);
            this.lpeffextEQ4 = 0;
        }
        int i9 = this.lpeffextEQ1;
        if (i9 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i9);
            this.lpeffextEQ1 = 0;
        }
        int i10 = this.lpeffectEQ2;
        if (i10 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i10);
            this.lpeffectEQ2 = 0;
        }
        int i11 = this.lpeffectEQ3;
        if (i11 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i11);
            this.lpeffectEQ3 = 0;
        }
        int i12 = this.lpeffectRotate;
        if (i12 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i12);
            this.lpeffectRotate = 0;
        }
        int i13 = this.lpeffectPhaser;
        if (i13 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i13);
            this.lpeffectPhaser = 0;
        }
        int i14 = this.lpautoEffectFx;
        if (i14 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i14);
            this.lpautoEffectFx = 0;
        }
        int i15 = this.lpcompressorEffects;
        if (i15 != 0) {
            BASS.BASS_ChannelRemoveFX(this.lpchannelPlay, i15);
            this.lpcompressorEffects = 0;
        }
        this.lpisPlaying = false;
        BASS.BASS_StreamFree(this.lpchannelPlay);
        BASS.BASS_StreamFree(this.lptempChanel);
        ArrayList<Integer> arrayList = this.lpintegerArrayList;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Integer> it = this.lpintegerArrayList.iterator();
            while (it.hasNext()) {
                BASS.BASS_StreamFree(it.next().intValue());
            }
            this.lpintegerArrayList.clear();
            this.lpintegerArrayList = null;
        }
        this.lptempChanel = 0;
        this.lpchannelPlay = 0;
    }

    public void lpsetOnDBMediaListener(lpDBMediaListener lpdbmedialistener) {
        this.lpmediaListener = lpdbmedialistener;
    }

    public int lpgetSkintDuration() {
        if (this.lpchannelPlay != 0) {
            this.lpintDuration = lpgetChanLength();
        }
        return this.lpintDuration;
    }

    public int lpgetSkintCurrPosition() {
        return this.lpintCurrPosition;
    }

    public void lptoSeek(int i) {
        if (!this.lpisPlaying) {
            new Exception("DBMediaPlayer seekTo:HanetMediaPlayer is not playing").printStackTrace();
            return;
        }
        this.lpintCurrPosition = i;
        lpseekToChannel(i);
    }

    private void lpmediaInit() {
        lpaudioRelease();
        String str = this.lpstrMediaPath;
        if (str != null || !str.equals("")) {
            if (!this.lpisMixNeed) {
                this.lpchannelPlay = BASS.BASS_StreamCreateFile(this.lpstrMediaPath, 0L, 0L, 2097152);
            } else {
                Log.d(lplogTag, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
                lpinitMixToMedia(false);
            }
        }
        int i = this.lpchannelPlay;
        if (i != 0) {
            if (!this.lpisMixNeed) {
                this.lpchannelPlay = BASS_FX.BASS_FX_ReverseCreate(i, 2.0f, 2162688);
            }
            int i2 = this.lpchannelPlay;
            if (i2 != 0) {
                BASS.BASS_ChannelGetInfo(i2, new BASS.BASS_CHANNELINFO());
                int BASS_FX_TempoCreate = BASS_FX.BASS_FX_TempoCreate(this.lpchannelPlay, 65536);
                this.lpchannelPlay = BASS_FX_TempoCreate;
                if (BASS_FX_TempoCreate == 0) {
                    Log.d(lplogTag, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
                    new Exception("DBMediaPlayer Couldnt create a resampled stream!").printStackTrace();
                    BASS.BASS_StreamFree(this.lpchannelPlay);
                    return;
                }
                return;
            }
            Log.d(lplogTag, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
            new Exception("DBMediaPlayer Couldnt create a resampled stream!").printStackTrace();
            BASS.BASS_StreamFree(this.lpchannelPlay);
            return;
        }
        Log.d(lplogTag, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
        new Exception("DBMediaPlayer Couldnt create a resampled stream!").printStackTrace();
        BASS.BASS_StreamFree(this.lpchannelPlay);
    }

    private void lpinitMixToMedia(boolean z) {
        File lpgetTempDirectory;
        String str;
        int BASS_Mixer_StreamCreate;
        int i;
        int i2;
        boolean BASS_Mixer_StreamAddChannelEx;
        if (!this.lpisMixNeed || (lpgetTempDirectory = lpgetTempDirectory()) == null || !lpgetTempDirectory.isDirectory() || (str = this.lpstrMixPath) == null || str.equals("")) {
            return;
        }
        File file = new File(lpgetTempDirectory, this.lpstrMixPath);
        if (file.exists() && file.isFile() && (BASS_Mixer_StreamCreate = BASSmix.BASS_Mixer_StreamCreate(44100, 2, 2097152)) != 0) {
            int BASS_StreamCreateFile = BASS.BASS_StreamCreateFile(this.lpstrMediaPath, 0L, 0L, 2097152);
            if (BASS_StreamCreateFile != 0) {
                boolean BASS_Mixer_StreamAddChannel = BASSmix.BASS_Mixer_StreamAddChannel(BASS_Mixer_StreamCreate, BASS_StreamCreateFile, 8388608);
                if (!BASS_Mixer_StreamAddChannel) {
                    BASS.BASS_StreamFree(BASS_Mixer_StreamCreate);
                    this.lpchannelPlay = BASS_StreamCreateFile;
                    return;
                }
                this.lpchannelPlay = BASS_Mixer_StreamCreate;
                this.lptempChanel = BASS_StreamCreateFile;
                long BASS_ChannelSeconds2Bytes = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, 3.0d);
                long BASS_ChannelSeconds2Bytes2 = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, 1.0d);
                int lpgetChanLength = lpgetChanLength();
                if (lpgetChanLength > 0) {
                    this.lpintegerArrayList = new ArrayList<>();
                    long BASS_ChannelSeconds2Bytes3 = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, lpgetChanLength);
                    int i3 = 0;
                    while (i3 < lpgetChanLength) {
                        if (i3 % 3 == 0) {
                            long j = i3 * BASS_ChannelSeconds2Bytes2;
                            long j2 = j + BASS_ChannelSeconds2Bytes;
                            int BASS_StreamCreateFile2 = BASS.BASS_StreamCreateFile(file.getAbsolutePath(), 0L, 0L, 2097152);
                            if (BASS_StreamCreateFile2 != 0) {
                                if (j2 < BASS_ChannelSeconds2Bytes3) {
                                    i2 = i3;
                                    BASS_Mixer_StreamAddChannelEx = BASSmix.BASS_Mixer_StreamAddChannelEx(BASS_Mixer_StreamCreate, BASS_StreamCreateFile2, 8388608, j, BASS_ChannelSeconds2Bytes);
                                } else {
                                    i2 = i3;
                                    long j3 = BASS_ChannelSeconds2Bytes3 - j;
                                    BASS_Mixer_StreamAddChannelEx = j3 > 0 ? BASSmix.BASS_Mixer_StreamAddChannelEx(BASS_Mixer_StreamCreate, BASS_StreamCreateFile2, 8388608, j, j3) : BASS_Mixer_StreamAddChannel;
                                }
                                if (BASS_Mixer_StreamAddChannelEx) {
                                    BASS.BASS_StreamFree(BASS_StreamCreateFile2);
                                } else {
                                    this.lpintegerArrayList.add(Integer.valueOf(BASS_StreamCreateFile2));
                                }
                                i = i2 + 1;
                                i3 = i + 1;
                            }
                        }
                        i = i3;
                        i3 = i + 1;
                    }
                    return;
                }
                return;
            }
            BASS.BASS_StreamFree(BASS_Mixer_StreamCreate);
        }
    }

    public boolean lpinitSolveToMedia() {
        BASS.BASS_StreamFree(this.lpchannelPlay);
        int BASS_StreamCreateFile = BASS.BASS_StreamCreateFile(this.lpstrMediaPath, 0L, 0L, 2097152);
        this.lpchannelPlay = BASS_StreamCreateFile;
        if (BASS_StreamCreateFile != 0) {
            if (!this.lpisMixNeed) {
                this.lpchannelPlay = BASS_FX.BASS_FX_ReverseCreate(BASS_StreamCreateFile, 2.0f, 2097152);
            } else {
                lpinitMixToMedia(true);
            }
            int i = this.lpchannelPlay;
            if (i != 0) {
                int BASS_FX_TempoCreate = BASS_FX.BASS_FX_TempoCreate(i, 2097152);
                this.lpchannelPlay = BASS_FX_TempoCreate;
                if (BASS_FX_TempoCreate == 0) {
                    new Exception("DBMediaPlayer Couldnt create a resampled stream!").printStackTrace();
                    BASS.BASS_StreamFree(this.lpchannelPlay);
                    return false;
                }
                return true;
            }
            new Exception("DBMediaPlayer Couldnt create a resampled stream!").printStackTrace();
            BASS.BASS_StreamFree(this.lpchannelPlay);
        }
        return false;
    }

    public void lpseekToChannel(int i) {
        int i2 = this.lpchannelPlay;
        if (i2 != 0) {
            BASS.BASS_ChannelSetPosition(i2, BASS.BASS_ChannelSeconds2Bytes(i2, i), 0);
        }
    }

    public int lpgetChannelPos() {
        double BASS_ChannelBytes2Seconds;
        int i = this.lptempChanel;
        if (i != 0) {
            BASS_ChannelBytes2Seconds = BASS.BASS_ChannelBytes2Seconds(i, BASS.BASS_ChannelGetPosition(i, 0));
        } else {
            int i2 = this.lpchannelPlay;
            if (i2 == 0) {
                return -1;
            }
            BASS_ChannelBytes2Seconds = BASS.BASS_ChannelBytes2Seconds(i2, BASS.BASS_ChannelGetPosition(i2, 0));
        }
        return (int) BASS_ChannelBytes2Seconds;
    }

    public int lpgetChanLength() {
        double BASS_ChannelBytes2Seconds;
        int i = this.lptempChanel;
        if (i != 0) {
            BASS_ChannelBytes2Seconds = BASS.BASS_ChannelBytes2Seconds(i, BASS.BASS_ChannelGetLength(i, 0));
        } else {
            int i2 = this.lpchannelPlay;
            if (i2 == 0) {
                return 0;
            }
            BASS_ChannelBytes2Seconds = BASS.BASS_ChannelBytes2Seconds(i2, BASS.BASS_ChannelGetLength(i2, 0));
        }
        return (int) BASS_ChannelBytes2Seconds;
    }

    public void lpsetSkisThisReverse(boolean z) {
        this.lpisThisReverse = z;
        int i = this.lpchannelPlay;
        if (i != 0) {
            int BASS_FX_TempoGetSource = BASS_FX.BASS_FX_TempoGetSource(i);
            BASS.FloatValue floatValue = new BASS.FloatValue();
            floatValue.value = 0.0f;
            BASS.BASS_ChannelGetAttribute(BASS_FX_TempoGetSource, BASS_FX.BASS_ATTRIB_REVERSE_DIR, floatValue);
            if (z) {
                BASS.BASS_ChannelSetAttribute(BASS_FX_TempoGetSource, BASS_FX.BASS_ATTRIB_REVERSE_DIR, -1.0f);
            } else {
                BASS.BASS_ChannelSetAttribute(BASS_FX_TempoGetSource, BASS_FX.BASS_ATTRIB_REVERSE_DIR, 1.0f);
            }
        }
    }

    public void lpsaveAsFile(String str) {
        int i;
        int BASS_ChannelGetData;
        if (str == null || str.equals("") || (i = this.lpchannelPlay) == 0 || BASSenc.BASS_Encode_Start(i, str, 262208, null, 0) == 0) {
            return;
        }
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
            do {
                BASS_ChannelGetData = BASS.BASS_ChannelGetData(this.lpchannelPlay, allocateDirect, allocateDirect.capacity());
                if (BASS_ChannelGetData == -1) {
                    return;
                }
            } while (BASS_ChannelGetData != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File lpgetTempDirectory() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory(),  ".temp");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, "VoiceEffects");
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                return file2;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
