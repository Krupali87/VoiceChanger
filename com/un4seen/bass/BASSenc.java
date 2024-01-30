package com.un4seen.bass;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class BASSenc {
    public static final int BASS_CONFIG_ENCODE_CAST_BIND = 66322;
    public static final int BASS_CONFIG_ENCODE_CAST_PROXY = 66321;
    public static final int BASS_CONFIG_ENCODE_CAST_TIMEOUT = 66320;
    public static final int BASS_CONFIG_ENCODE_PRIORITY = 66304;
    public static final int BASS_CONFIG_ENCODE_QUEUE = 66305;
    public static final int BASS_CONFIG_ENCODE_SERVER_CERT = 66336;
    public static final int BASS_CONFIG_ENCODE_SERVER_KEY = 66337;
    public static final int BASS_ENCODE_AIFF = 16384;
    public static final int BASS_ENCODE_AUTOFREE = 262144;
    public static final int BASS_ENCODE_BIGEND = 16;
    public static final int BASS_ENCODE_CAST_NOLIMIT = 4096;
    public static final int BASS_ENCODE_COUNT_CAST = 2;
    public static final int BASS_ENCODE_COUNT_IN = 0;
    public static final int BASS_ENCODE_COUNT_IN_FP = 6;
    public static final int BASS_ENCODE_COUNT_OUT = 1;
    public static final int BASS_ENCODE_COUNT_QUEUE = 3;
    public static final int BASS_ENCODE_COUNT_QUEUE_FAIL = 5;
    public static final int BASS_ENCODE_COUNT_QUEUE_LIMIT = 4;
    public static final int BASS_ENCODE_DITHER = 32768;
    public static final int BASS_ENCODE_FP_16BIT = 4;
    public static final int BASS_ENCODE_FP_24BIT = 6;
    public static final int BASS_ENCODE_FP_32BIT = 8;
    public static final int BASS_ENCODE_FP_8BIT = 2;
    public static final int BASS_ENCODE_FP_AUTO = 14;
    public static final int BASS_ENCODE_LIMIT = 8192;
    public static final int BASS_ENCODE_NOHEAD = 1;
    public static final int BASS_ENCODE_NOTIFY_CAST = 2;
    public static final int BASS_ENCODE_NOTIFY_CAST_TIMEOUT = 65536;
    public static final int BASS_ENCODE_NOTIFY_ENCODER = 1;
    public static final int BASS_ENCODE_NOTIFY_FREE = 65538;
    public static final int BASS_ENCODE_NOTIFY_QUEUE_FULL = 65537;
    public static final int BASS_ENCODE_NOTIFY_SERVER = 3;
    public static final int BASS_ENCODE_PAUSE = 32;
    public static final int BASS_ENCODE_PCM = 64;
    public static final int BASS_ENCODE_QUEUE = 512;
    public static final int BASS_ENCODE_RF64 = 128;
    public static final int BASS_ENCODE_SERVER_META = 2;
    public static final int BASS_ENCODE_SERVER_NOHTTP = 1;
    public static final int BASS_ENCODE_SERVER_SSL = 4;
    public static final int BASS_ENCODE_STATS_ICE = 1;
    public static final int BASS_ENCODE_STATS_ICESERV = 2;
    public static final int BASS_ENCODE_STATS_SHOUT = 0;
    public static final String BASS_ENCODE_TYPE_AAC = "audio/aacp";
    public static final String BASS_ENCODE_TYPE_MP3 = "audio/mpeg";
    public static final String BASS_ENCODE_TYPE_OGG = "audio/ogg";
    public static final int BASS_ENCODE_WFEXT = 1024;
    public static final int BASS_ERROR_CAST_DENIED = 2100;
    public static final int BASS_ERROR_SERVER_CERT = 2101;

    /* loaded from: classes3.dex */
    public interface ENCODECLIENTPROC {
        boolean ENCODECLIENTPROC(int i, boolean z, String str, StringBuffer stringBuffer, Object obj);
    }

    /* loaded from: classes3.dex */
    public interface ENCODENOTIFYPROC {
        void ENCODENOTIFYPROC(int i, int i2, Object obj);
    }

    /* loaded from: classes3.dex */
    public interface ENCODEPROC {
        void ENCODEPROC(int i, int i2, ByteBuffer byteBuffer, int i3, Object obj);
    }

    /* loaded from: classes3.dex */
    public interface ENCODEPROCEX {
        void ENCODEPROCEX(int i, int i2, ByteBuffer byteBuffer, int i3, long j, Object obj);
    }

    /* loaded from: classes3.dex */
    public interface ENCODERPROC {
        int ENCODERPROC(int i, int i2, ByteBuffer byteBuffer, int i3, int i4, Object obj);
    }

    public static native boolean BASS_Encode_AddChunk(int i, String str, ByteBuffer byteBuffer, int i2);

    public static native String BASS_Encode_CastGetStats(int i, int i2, String str);

    public static native boolean BASS_Encode_CastInit(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, boolean z);

    public static native boolean BASS_Encode_CastSendMeta(int i, int i2, ByteBuffer byteBuffer, int i3);

    public static native boolean BASS_Encode_CastSetTitle(int i, String str, String str2);

    public static native int BASS_Encode_GetChannel(int i);

    public static native long BASS_Encode_GetCount(int i, int i2);

    public static native int BASS_Encode_GetVersion();

    public static native int BASS_Encode_IsActive(int i);

    public static native int BASS_Encode_ServerInit(int i, String str, int i2, int i3, int i4, ENCODECLIENTPROC encodeclientproc, Object obj);

    public static native boolean BASS_Encode_ServerKick(int i, String str);

    public static native boolean BASS_Encode_SetChannel(int i, int i2);

    public static native boolean BASS_Encode_SetNotify(int i, ENCODENOTIFYPROC encodenotifyproc, Object obj);

    public static native boolean BASS_Encode_SetPaused(int i, boolean z);

    public static native int BASS_Encode_Start(int i, String str, int i2, ENCODEPROC encodeproc, Object obj);

    public static native int BASS_Encode_StartLimit(int i, String str, int i2, ENCODEPROC encodeproc, Object obj, int i3);

    public static native int BASS_Encode_StartUser(int i, String str, int i2, ENCODERPROC encoderproc, Object obj);

    public static native boolean BASS_Encode_Stop(int i);

    public static native boolean BASS_Encode_StopEx(int i, boolean z);

    public static native boolean BASS_Encode_UserOutput(int i, long j, ByteBuffer byteBuffer, int i2);

    public static native boolean BASS_Encode_Write(int i, ByteBuffer byteBuffer, int i2);

    static {
        System.loadLibrary("bassenc");
    }
}
