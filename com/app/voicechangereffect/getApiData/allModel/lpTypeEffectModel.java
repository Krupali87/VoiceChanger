package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class lpTypeEffectModel implements Parcelable {
    public static final Parcelable.Creator<lpTypeEffectModel> CREATOR = new Creator();
    private boolean lpisActive;
    private String lptype;

    @Override
    public int describeContents() {
        return 0;
    }


    public static final class Creator implements Parcelable.Creator<lpTypeEffectModel> {

        @Override
        public lpTypeEffectModel createFromParcel(Parcel parcel) {
            return new lpTypeEffectModel(parcel.readString(), parcel.readInt() != 0);
        }


        @Override
        public lpTypeEffectModel[] newArray(int i) {
            return new lpTypeEffectModel[i];
        }
    }

    public lpTypeEffectModel copy(String str, boolean z) {
        return new lpTypeEffectModel(str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpTypeEffectModel) {
            lpTypeEffectModel lptypeeffectmodel = (lpTypeEffectModel) obj;
            return Intrinsics.areEqual(this.lptype, lptypeeffectmodel.lptype) && this.lpisActive == lptypeeffectmodel.lpisActive;
        }
        return false;
    }

    public int hashCode() {
        return (this.lptype.hashCode() * 31) + (this.lpisActive ? 1 : 0);
    }

    public String toString() {
        return "TypeEffectModel(type=" + this.lptype + ", isActive=" + this.lpisActive + ')';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.lptype);
        parcel.writeInt(this.lpisActive ? 1 : 0);
    }

    public lpTypeEffectModel(String str, boolean z) {
        this.lptype = str;
        this.lpisActive = z;
    }

    public String getLptype() {
        return this.lptype;
    }

    public void setLptype(String str) {
        this.lptype = str;
    }

    public boolean isLpisActive() {
        return this.lpisActive;
    }

    public void setLpisActive(boolean z) {
        this.lpisActive = z;
    }
}
