package com.app.voicechangereffect.viewModel;

public class lpModelEffects {
    private float lpfloatAmplify;
    private float[] lpfloatAutoWah;
    private float[] lpfloatChorus;
    private float[] lpfloatCompressor;
    private float[] lpfloatDistort;
    private float[] lpfloatEcho;
    private float[] lpfloatEcho4;
    private float[] lpfloatEq2;
    private float[] lpfloatEq3;
    private float[] lpfloatFilter;
    private float[] lpfloatFlange;
    private float[] lpfloatPhaser;
    private float lpfloatRate;
    private float[] lpfloatReverb;
    private float lpfloatRotate;
    private float[] lpfloatyEcho1;
    private String lpid;
    private int lpintPitch;
    private boolean lpisMixBool;
    private boolean lpisPlayingBool = false;
    private boolean lpisReverseBool;
    private String lpstrName;
    private String lpstrPathMix;

    public lpModelEffects(String str, String str2, int i, float f) {
        this.lpid = str;
        this.lpstrName = str2;
        this.lpintPitch = i;
        this.lpfloatRate = f;
    }

    public String getLpid() {
        return this.lpid;
    }

    public void setLpid(String str) {
        this.lpid = str;
    }

    public String getLpstrName() {
        return this.lpstrName;
    }

    public void setLpstrName(String str) {
        this.lpstrName = str;
    }

    public boolean isLpisPlayingBool() {
        return this.lpisPlayingBool;
    }

    public void setLpisPlayingBool(boolean z) {
        this.lpisPlayingBool = z;
    }

    public int getLpintPitch() {
        return this.lpintPitch;
    }

    public void setLpintPitch(int i) {
        this.lpintPitch = i;
    }

    public float getLpfloatRate() {
        return this.lpfloatRate;
    }

    public void setLpfloatRate(float f) {
        this.lpfloatRate = f;
    }

    public float[] getLpfloatReverb() {
        return this.lpfloatReverb;
    }

    public void setLpfloatReverb(float[] fArr) {
        this.lpfloatReverb = fArr;
    }

    public float[] getLpfloatFlange() {
        return this.lpfloatFlange;
    }

    public void setLpfloatFlange(float[] fArr) {
        this.lpfloatFlange = fArr;
    }

    public boolean isLpisReverseBool() {
        return this.lpisReverseBool;
    }

    public void setLpisReverseBool(boolean z) {
        this.lpisReverseBool = z;
    }

    public float[] getLpfloatEcho() {
        return this.lpfloatEcho;
    }

    public void setLpfloatEcho(float[] fArr) {
        this.lpfloatEcho = fArr;
    }

    public float[] getLpfloatyEcho1() {
        return this.lpfloatyEcho1;
    }

    public void setLpfloatyEcho1(float[] fArr) {
        this.lpfloatyEcho1 = fArr;
    }

    public float[] getLpfloatFilter() {
        return this.lpfloatFilter;
    }

    public void setLpfloatFilter(float[] fArr) {
        this.lpfloatFilter = fArr;
    }

    public float getLpfloatAmplify() {
        return this.lpfloatAmplify;
    }

    public void setLpfloatAmplify(float f) {
        this.lpfloatAmplify = f;
    }

    public float[] getLpfloatDistort() {
        return this.lpfloatDistort;
    }

    public void setLpfloatDistort(float[] fArr) {
        this.lpfloatDistort = fArr;
    }

    public float[] getLpfloatChorus() {
        return this.lpfloatChorus;
    }

    public void setLpfloatChorus(float[] fArr) {
        this.lpfloatChorus = fArr;
    }

    public float[] getLpfloatEcho4() {
        return this.lpfloatEcho4;
    }

    public void setLpfloatEcho4(float[] fArr) {
        this.lpfloatEcho4 = fArr;
    }

    public float[] getLpfloatEq2() {
        return this.lpfloatEq2;
    }

    public void setLpfloatEq2(float[] fArr) {
        this.lpfloatEq2 = fArr;
    }

    public float[] getLpfloatEq3() {
        return this.lpfloatEq3;
    }

    public void setLpfloatEq3(float[] fArr) {
        this.lpfloatEq3 = fArr;
    }

    public float getLpfloatRotate() {
        return this.lpfloatRotate;
    }

    public void setLpfloatRotate(float f) {
        this.lpfloatRotate = f;
    }

    public float[] getLpfloatPhaser() {
        return this.lpfloatPhaser;
    }

    public void setLpfloatPhaser(float[] fArr) {
        this.lpfloatPhaser = fArr;
    }

    public float[] getLpfloatAutoWah() {
        return this.lpfloatAutoWah;
    }

    public void setLpfloatAutoWah(float[] fArr) {
        this.lpfloatAutoWah = fArr;
    }

    public float[] getLpfloatCompressor() {
        return this.lpfloatCompressor;
    }

    public void setLpfloatCompressor(float[] fArr) {
        this.lpfloatCompressor = fArr;
    }

    public boolean isLpisMixBool() {
        return this.lpisMixBool;
    }

    public void setLpisMixBool(boolean z) {
        this.lpisMixBool = z;
    }

    public String getLpstrPathMix() {
        return this.lpstrPathMix;
    }

    public void setLpstrPathMix(String str) {
        this.lpstrPathMix = str;
    }
}
