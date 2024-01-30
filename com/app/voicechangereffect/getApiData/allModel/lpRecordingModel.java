package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class lpRecordingModel implements Parcelable {
    public static final Parcelable.Creator<lpRecordingModel> lpCREATOR = new Creator();
    private int lpfav;
    private long lplength;
    private String lpname;
    private String lppath;
    private long lptimeAdded;

    protected lpRecordingModel(Parcel in) {
        lpfav = in.readInt();
        lplength = in.readLong();
        lpname = in.readString();
        lppath = in.readString();
        lptimeAdded = in.readLong();
    }

    public static final Parcelable.Creator<lpRecordingModel> CREATOR = new Parcelable.Creator<lpRecordingModel>() {
        @Override
        public lpRecordingModel createFromParcel(Parcel in) {
            return new lpRecordingModel(in);
        }

        @Override
        public lpRecordingModel[] newArray(int size) {
            return new lpRecordingModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public static final class Creator implements Parcelable.Creator<lpRecordingModel> {

        @Override
        public final lpRecordingModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new lpRecordingModel(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt());
        }


        @Override
        public  lpRecordingModel[] newArray(int i) {
            return new lpRecordingModel[i];
        }
    }

    public  lpRecordingModel copy(String name, String path, long j, long j2, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        return new lpRecordingModel(name, path, j, j2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpRecordingModel) {
            lpRecordingModel lprecordingmodel = (lpRecordingModel) obj;
            return Intrinsics.areEqual(this.lpname, lprecordingmodel.lpname) && Intrinsics.areEqual(this.lppath, lprecordingmodel.lppath) && this.lplength == lprecordingmodel.lplength && this.lptimeAdded == lprecordingmodel.lptimeAdded && this.lpfav == lprecordingmodel.lpfav;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.lpname.hashCode() * 31) + this.lppath.hashCode()) * 31) + lpExternalSyntex.lpcustSyntex(this.lplength)) * 31) + lpExternalSyntex.lpcustSyntex(this.lptimeAdded)) * 31) + this.lpfav;
    }

    public String toString() {
        return "RecordingModel(name=" + this.lpname + ", path=" + this.lppath + ", length=" + this.lplength + ", timeAdded=" + this.lptimeAdded + ", fav=" + this.lpfav + ')';
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.lpname);
        out.writeString(this.lppath);
        out.writeLong(this.lplength);
        out.writeLong(this.lptimeAdded);
        out.writeInt(this.lpfav);
    }

    public lpRecordingModel(String str, String str2, long j, long j2, int i) {
        this.lpname = str;
        this.lppath = str2;
        this.lplength = j;
        this.lptimeAdded = j2;
        this.lpfav = i;
    }

    public final String getLpname() {
        return this.lpname;
    }

    public final void setLpname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lpname = str;
    }

    public final String getLppath() {
        return this.lppath;
    }

    public final void setLppath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lppath = str;
    }

    public final long getLplength() {
        return this.lplength;
    }

    public final void setLplength(long j) {
        this.lplength = j;
    }
}
