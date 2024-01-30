package com.app.voicechangereffect;

import com.app.voicechangereffect.viewModel.lpModelEffects;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class lpParsingJsonObjects  {
    public static final String lpTAG = "JsonParsingUtils";

    public static lpModelEffects lpjsonToObjectEffects(String str) {
        String str2;
        boolean z;
        JSONArray jSONArray;
        int length;
        JSONArray jSONArray2;
        int length2;
        JSONArray jSONArray3;
        int length3;
        JSONArray jSONArray4;
        int length4;
        JSONArray jSONArray5;
        int length5;
        JSONArray jSONArray6;
        int length6;
        JSONArray jSONArray7;
        int length7;
        JSONArray jSONArray8;
        int length8;
        JSONArray jSONArray9;
        int length9;
        JSONArray jSONArray10;
        int length10;
        JSONArray jSONArray11;
        int length11;
        JSONArray jSONArray12;
        int length12;
        JSONArray jSONArray13;
        int length13;
        if (str == null && str.equals("")) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("id");
            String string2 = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            int i = jSONObject.getInt("pitch");
            int i2 = jSONObject.getInt("rate");
            if (jSONObject.opt("reverse") != null) {
                z = jSONObject.getBoolean("reverse");
                str2 = "echo4";
            } else {
                str2 = "echo4";
                z = false;
            }
            lpModelEffects lpmodeleffects = new lpModelEffects(string, string2, i, i2);
            lpmodeleffects.setLpisReverseBool(z);
            if (jSONObject.opt("amplify") != null) {
                lpmodeleffects.setLpfloatAmplify((float) jSONObject.getDouble("amplify"));
            }
            if (jSONObject.opt("isMix") != null) {
                lpmodeleffects.setLpisMixBool(jSONObject.getBoolean("isMix"));
                lpmodeleffects.setLpstrPathMix(jSONObject.getString("path"));
            }
            if (jSONObject.opt("rotate") != null) {
                lpmodeleffects.setLpfloatRotate((float) jSONObject.getDouble("rotate"));
            }
            if (jSONObject.opt("reverb") != null && (length13 = (jSONArray13 = jSONObject.getJSONArray("reverb")).length()) > 0) {
                float[] fArr = new float[length13];
                for (int i3 = 0; i3 < length13; i3++) {
                    fArr[i3] = (float) jSONArray13.getDouble(i3);
                }
                lpmodeleffects.setLpfloatReverb(fArr);
            }
            if (jSONObject.opt("distort") != null && (length12 = (jSONArray12 = jSONObject.getJSONArray("distort")).length()) > 0) {
                float[] fArr2 = new float[length12];
                for (int i4 = 0; i4 < length12; i4++) {
                    fArr2[i4] = (float) jSONArray12.getDouble(i4);
                }
                lpmodeleffects.setLpfloatDistort(fArr2);
            }
            if (jSONObject.opt("chorus") != null && (length11 = (jSONArray11 = jSONObject.getJSONArray("chorus")).length()) > 0) {
                float[] fArr3 = new float[length11];
                for (int i5 = 0; i5 < length11; i5++) {
                    fArr3[i5] = (float) jSONArray11.getDouble(i5);
                }
                lpmodeleffects.setLpfloatChorus(fArr3);
            }
            if (jSONObject.opt("flanger") != null && (length10 = (jSONArray10 = jSONObject.getJSONArray("flanger")).length()) > 0) {
                float[] fArr4 = new float[length10];
                for (int i6 = 0; i6 < length10; i6++) {
                    fArr4[i6] = (float) jSONArray10.getDouble(i6);
                }
                lpmodeleffects.setLpfloatFlange(fArr4);
            }
            if (jSONObject.opt("filter") != null && (length9 = (jSONArray9 = jSONObject.getJSONArray("filter")).length()) > 0) {
                float[] fArr5 = new float[length9];
                for (int i7 = 0; i7 < length9; i7++) {
                    fArr5[i7] = (float) jSONArray9.getDouble(i7);
                }
                lpmodeleffects.setLpfloatFilter(fArr5);
            }
            if (jSONObject.opt("echo") != null && (length8 = (jSONArray8 = jSONObject.getJSONArray("echo")).length()) > 0) {
                float[] fArr6 = new float[length8];
                for (int i8 = 0; i8 < length8; i8++) {
                    fArr6[i8] = (float) jSONArray8.getDouble(i8);
                }
                lpmodeleffects.setLpfloatEcho(fArr6);
            }
            String str3 = str2;
            if (jSONObject.opt(str3) != null && (length7 = (jSONArray7 = jSONObject.getJSONArray(str3)).length()) > 0) {
                float[] fArr7 = new float[length7];
                for (int i9 = 0; i9 < length7; i9++) {
                    fArr7[i9] = (float) jSONArray7.getDouble(i9);
                }
                lpmodeleffects.setLpfloatEcho4(fArr7);
            }
            if (jSONObject.opt("eq1") != null && (length6 = (jSONArray6 = jSONObject.getJSONArray("eq1")).length()) > 0) {
                float[] fArr8 = new float[length6];
                for (int i10 = 0; i10 < length6; i10++) {
                    fArr8[i10] = (float) jSONArray6.getDouble(i10);
                }
                lpmodeleffects.setLpfloatyEcho1(fArr8);
            }
            if (jSONObject.opt("eq2") != null && (length5 = (jSONArray5 = jSONObject.getJSONArray("eq2")).length()) > 0) {
                float[] fArr9 = new float[length5];
                for (int i11 = 0; i11 < length5; i11++) {
                    fArr9[i11] = (float) jSONArray5.getDouble(i11);
                }
                lpmodeleffects.setLpfloatEq2(fArr9);
            }
            if (jSONObject.opt("eq3") != null && (length4 = (jSONArray4 = jSONObject.getJSONArray("eq3")).length()) > 0) {
                float[] fArr10 = new float[length4];
                for (int i12 = 0; i12 < length4; i12++) {
                    fArr10[i12] = (float) jSONArray4.getDouble(i12);
                }
                lpmodeleffects.setLpfloatEq3(fArr10);
            }
            if (jSONObject.opt("phaser") != null && (length3 = (jSONArray3 = jSONObject.getJSONArray("phaser")).length()) > 0) {
                float[] fArr11 = new float[length3];
                for (int i13 = 0; i13 < length3; i13++) {
                    fArr11[i13] = (float) jSONArray3.getDouble(i13);
                }
                lpmodeleffects.setLpfloatPhaser(fArr11);
            }
            if (jSONObject.opt("autowah") != null && (length2 = (jSONArray2 = jSONObject.getJSONArray("autowah")).length()) > 0) {
                float[] fArr12 = new float[length2];
                for (int i14 = 0; i14 < length2; i14++) {
                    fArr12[i14] = (float) jSONArray2.getDouble(i14);
                }
                lpmodeleffects.setLpfloatAutoWah(fArr12);
            }
            if (jSONObject.opt("compressor") != null && (length = (jSONArray = jSONObject.getJSONArray("compressor")).length()) > 0) {
                float[] fArr13 = new float[length];
                for (int i15 = 0; i15 < length; i15++) {
                    fArr13[i15] = (float) jSONArray.getDouble(i15);
                }
                lpmodeleffects.setLpfloatCompressor(fArr13);
            }
            return lpmodeleffects;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
