package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class lpEffectModel implements Parcelable {
    public static final Parcelable.Creator<lpEffectModel> CREATOR = new Creator();
    private int lpiconSelected;
    private int lpiconUnSelected;
    private int lpid;
    private boolean lpisActive;
    private String lpname;
    private String lpnameOrigin;
    private int lpthumb;

    @Override
    public int describeContents() {
        return 0;
    }


    public static final class Creator implements Parcelable.Creator<lpEffectModel> {

        @Override
        public final lpEffectModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new lpEffectModel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
        }


        @Override
        public final lpEffectModel[] newArray(int i) {
            return new lpEffectModel[i];
        }
    }

    public final lpEffectModel lpcopy(int i, String name, String str, int i2, int i3, int i4, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new lpEffectModel(i, name, str, i2, i3, i4, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpEffectModel) {
            lpEffectModel lpeffectmodel = (lpEffectModel) obj;
            return this.lpid == lpeffectmodel.lpid && Intrinsics.areEqual(this.lpname, lpeffectmodel.lpname) && Intrinsics.areEqual(this.lpnameOrigin, lpeffectmodel.lpnameOrigin) && this.lpiconSelected == lpeffectmodel.lpiconSelected && this.lpthumb == lpeffectmodel.lpthumb && this.lpisActive == lpeffectmodel.lpisActive;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.lpid * 31) + this.lpname.hashCode()) * 31;
        String str = this.lpnameOrigin;
        return ((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.lpiconSelected) * 31) + this.lpthumb) * 31) + (this.lpisActive ? 1 : 0);
    }

    public String toString() {
        return "EffectModel(id=" + this.lpid + ", name=" + this.lpname + ", nameOrigin=" + this.lpnameOrigin + ", icon=" + this.lpiconSelected + ", thumb=" + this.lpthumb + ", isActive=" + this.lpisActive + ')';
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.lpid);
        out.writeString(this.lpname);
        out.writeString(this.lpnameOrigin);
        out.writeInt(this.lpiconSelected);
        out.writeInt(this.lpthumb);
        out.writeInt(this.lpisActive ? 1 : 0);
    }

    public lpEffectModel(int i, String str, String str2, int i2, int i3, int i4, boolean z) {
        this.lpid = i;
        this.lpname = str;
        this.lpnameOrigin = str2;
        this.lpiconSelected = i2;
        this.lpiconUnSelected = i3;
        this.lpthumb = i4;
        this.lpisActive = z;
    }

    public lpEffectModel(int i, String str, String str2, int i2, int i3, int i4, boolean z, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i5 & 4) != 0 ? null : str2, i2, i3, i4, (i5 & 32) != 0 ? false : z);
    }

    public final int getLpid() {
        return this.lpid;
    }

    public final void setLpid(int i) {
        this.lpid = i;
    }

    public final String getLpname() {
        return this.lpname;
    }

    public final void setLpname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lpname = str;
    }

    public final String getLpnameOrigin() {
        return this.lpnameOrigin;
    }

    public final void setLpnameOrigin(String str) {
        this.lpnameOrigin = str;
    }

    public final int getLpiconSelected() {
        return this.lpiconSelected;
    }

    public int getLpiconUnSelected() {
        return this.lpiconUnSelected;
    }

    public void setLpiconUnSelected(int i) {
        this.lpiconUnSelected = i;
    }

    public final void setLpiconSelected(int i) {
        this.lpiconSelected = i;
    }

    public final int getLpthumb() {
        return this.lpthumb;
    }

    public final void setLpthumb(int i) {
        this.lpthumb = i;
    }

    public final boolean isLpisActive() {
        return this.lpisActive;
    }

    public final void setLpisActive(boolean z) {
        this.lpisActive = z;
    }

    public lpEffectModel(String name) {
        this(0, name, null, 0, 0, 0, false);
        Intrinsics.checkNotNullParameter(name, "name");
    }
}
