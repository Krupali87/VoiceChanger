package com.un4seen.bass;

import com.un4seen.bass.BASS;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class BASSmix {
    public static final int BASS_ATTRIB_MIXER_LATENCY = 86016;
    public static final int BASS_ATTRIB_SPLIT_ASYNCBUFFER = 86032;
    public static final int BASS_ATTRIB_SPLIT_ASYNCPERIOD = 86033;
    public static final int BASS_CONFIG_MIXER_BUFFER = 67073;
    public static final int BASS_CONFIG_MIXER_POSEX = 67074;
    public static final int BASS_CONFIG_SPLIT_BUFFER = 67088;
    public static final int BASS_CTYPE_STREAM_MIXER = 67584;
    public static final int BASS_CTYPE_STREAM_SPLIT = 67585;
    public static final int BASS_MIXER_BUFFER = 8192;
    public static final int BASS_MIXER_CHAN_ABSOLUTE = 4096;
    public static final int BASS_MIXER_CHAN_BUFFER = 8192;
    public static final int BASS_MIXER_CHAN_DOWNMIX = 4194304;
    public static final int BASS_MIXER_CHAN_LIMIT = 16384;
    public static final int BASS_MIXER_CHAN_MATRIX = 65536;
    public static final int BASS_MIXER_CHAN_NORAMPIN = 8388608;
    public static final int BASS_MIXER_CHAN_PAUSE = 131072;
    public static final int BASS_MIXER_DOWNMIX = 4194304;
    public static final int BASS_MIXER_END = 65536;
    public static final int BASS_MIXER_ENV_FREQ = 1;
    public static final int BASS_MIXER_ENV_LOOP = 65536;
    public static final int BASS_MIXER_ENV_PAN = 3;
    public static final int BASS_MIXER_ENV_REMOVE = 131072;
    public static final int BASS_MIXER_ENV_VOL = 2;
    public static final int BASS_MIXER_LIMIT = 16384;
    public static final int BASS_MIXER_MATRIX = 65536;
    public static final int BASS_MIXER_NONSTOP = 131072;
    public static final int BASS_MIXER_NORAMPIN = 8388608;
    public static final int BASS_MIXER_PAUSE = 131072;
    public static final int BASS_MIXER_POSEX = 8192;
    public static final int BASS_MIXER_RESUME = 4096;
    public static final int BASS_POS_MIXER_RESET = 65536;
    public static final int BASS_SPLIT_POS = 8192;
    public static final int BASS_SPLIT_SLAVE = 4096;
    public static final int BASS_SYNC_MIXER_ENVELOPE = 66048;
    public static final int BASS_SYNC_MIXER_ENVELOPE_NODE = 66049;

    public static native int BASS_Mixer_ChannelFlags(int i, int i2, int i3);

    public static native int BASS_Mixer_ChannelGetData(int i, ByteBuffer byteBuffer, int i2);

    public static native long BASS_Mixer_ChannelGetEnvelopePos(int i, int i2, Float f);

    public static native int BASS_Mixer_ChannelGetLevel(int i);

    public static native boolean BASS_Mixer_ChannelGetLevelEx(int i, float[] fArr, float f, int i2);

    public static native boolean BASS_Mixer_ChannelGetMatrix(int i, float[][] fArr);

    public static native int BASS_Mixer_ChannelGetMixer(int i);

    public static native long BASS_Mixer_ChannelGetPosition(int i, int i2);

    public static native long BASS_Mixer_ChannelGetPositionEx(int i, int i2, int i3);

    public static native boolean BASS_Mixer_ChannelRemove(int i);

    public static native boolean BASS_Mixer_ChannelRemoveSync(int i, int i2);

    public static native boolean BASS_Mixer_ChannelSetEnvelope(int i, int i2, BASS_MIXER_NODE[] bass_mixer_nodeArr, int i3);

    public static native boolean BASS_Mixer_ChannelSetEnvelopePos(int i, int i2, long j);

    public static native boolean BASS_Mixer_ChannelSetMatrix(int i, float[][] fArr);

    public static native boolean BASS_Mixer_ChannelSetMatrixEx(int i, float[][] fArr, float f);

    public static native boolean BASS_Mixer_ChannelSetPosition(int i, long j, int i2);

    public static native int BASS_Mixer_ChannelSetSync(int i, int i2, long j, BASS.SYNCPROC syncproc, Object obj);

    public static native int BASS_Mixer_GetVersion();

    public static native boolean BASS_Mixer_StreamAddChannel(int i, int i2, int i3);

    public static native boolean BASS_Mixer_StreamAddChannelEx(int i, int i2, int i3, long j, long j2);

    public static native int BASS_Mixer_StreamCreate(int i, int i2, int i3);

    public static native int BASS_Mixer_StreamGetChannels(int i, int[] iArr, int i2);

    public static native int BASS_Split_StreamCreate(int i, int i2, int[] iArr);

    public static native int BASS_Split_StreamGetAvailable(int i);

    public static native int BASS_Split_StreamGetSource(int i);

    public static native int BASS_Split_StreamGetSplits(int i, int[] iArr, int i2);

    public static native boolean BASS_Split_StreamReset(int i);

    public static native boolean BASS_Split_StreamResetEx(int i, int i2);

    /* loaded from: classes3.dex */
    public static class BASS_MIXER_NODE {
        public long pos;
        public float value;

        public BASS_MIXER_NODE() {
        }

        public BASS_MIXER_NODE(long j, float f) {
            this.pos = j;
            this.value = f;
        }
    }

    static {
        System.loadLibrary("bassmix");
    }
}
