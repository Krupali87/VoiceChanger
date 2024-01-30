package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

public final class lpAudioModel implements Parcelable {
    public static final Parcelable.Creator<lpAudioModel> lpCREATOR = new Creator();
    private long lpdateCreate;
    private String lpduration;
    private String lpfileName;
    private long lpid;
    private String lppath;
    private String lpsize;
    private String lptype;

    protected lpAudioModel(Parcel in) {
        lpdateCreate = in.readLong();
        lpduration = in.readString();
        lpfileName = in.readString();
        lpid = in.readLong();
        lppath = in.readString();
        lpsize = in.readString();
        lptype = in.readString();
    }

    public static final Parcelable.Creator<lpAudioModel> CREATOR = new Parcelable.Creator<lpAudioModel>() {
        @Override
        public lpAudioModel createFromParcel(Parcel in) {
            return new lpAudioModel(in);
        }

        @Override
        public lpAudioModel[] newArray(int size) {
            return new lpAudioModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public long getLpid() {
        return this.lpid;
    }

    public void setLpid(long j) {
        this.lpid = j;
    }

    public static final class Creator implements Parcelable.Creator<lpAudioModel> {

        @Override
        public lpAudioModel createFromParcel(Parcel parcel) {
            Log.e("vvv---", "createFromParcel: " + parcel.readString());
            return new lpAudioModel(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString());
        }


        @Override
        public lpAudioModel[] newArray(int i) {
            return new lpAudioModel[i];
        }
    }

    public final lpAudioModel copy(String path, String fileName, String duration, long j, String size, String type) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(duration, "duration");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(type, "type");
        return new lpAudioModel(path, fileName, duration, j, size, type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpAudioModel) {
            lpAudioModel lpaudiomodel = (lpAudioModel) obj;
            return Intrinsics.areEqual(this.lppath, lpaudiomodel.lppath) && Intrinsics.areEqual(this.lpfileName, lpaudiomodel.lpfileName) && Intrinsics.areEqual(this.lpduration, lpaudiomodel.lpduration) && this.lpdateCreate == lpaudiomodel.lpdateCreate && Intrinsics.areEqual(this.lpsize, lpaudiomodel.lpsize) && Intrinsics.areEqual(this.lptype, lpaudiomodel.lptype);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.lppath.hashCode() * 31) + this.lpfileName.hashCode()) * 31) + this.lpduration.hashCode()) * 31) + lpExternalSyntex.lpcustSyntex(this.lpdateCreate)) * 31) + this.lpsize.hashCode()) * 31) + this.lptype.hashCode();
    }

    public String toString() {
        return "AudioModel(path=" + this.lppath + ", fileName=" + this.lpfileName + ", duration=" + this.lpduration + ", dateCreate=" + this.lpdateCreate + ", size=" + this.lpsize + ", type=" + this.lptype + ')';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.lppath);
        parcel.writeString(this.lpfileName);
        parcel.writeString(String.valueOf(this.lpduration));
        parcel.writeLong(this.lpdateCreate);
        parcel.writeString(this.lpsize);
        parcel.writeString(this.lptype);
    }

    public lpAudioModel(String str, String str2, String str3, long j, String str4, String str5) {
        this.lppath = str;
        this.lpfileName = str2;
        this.lpduration = str3;
        this.lpdateCreate = j;
        this.lpsize = str4;
        this.lptype = str5;
    }



    public final String getLppath() {
        return this.lppath;
    }

    public final void setLppath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lppath = str;
    }

    public final String getLpfileName() {
        return this.lpfileName;
    }

    public final void setLpfileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lpfileName = str;
    }

    public final String getLpduration() {
        return this.lpduration;
    }

    public final void setLpduration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lpduration = str;
    }

    public final long getLpdateCreate() {
        return this.lpdateCreate;
    }

    public final void setLpdateCreate(long j) {
        this.lpdateCreate = j;
    }

    public final String getLpsize() {
        return this.lpsize;
    }

    public final void setLpsize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lpsize = str;
    }

    public final String getLptype() {
        return this.lptype;
    }

    public final void setLptype(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lptype = str;
    }
}
