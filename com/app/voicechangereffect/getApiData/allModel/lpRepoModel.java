package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class lpRepoModel implements Parcelable {
    public static final Parcelable.Creator<lpRepoModel> lpCREATOR = new Creator();
    private final String lpfull_name;
    private final int lpid;
    private final String lpname;
    private final String lpnode_id;
    private final lpOwner lpowner;
    private int lpposition;

    protected lpRepoModel(Parcel in) {
        lpfull_name = in.readString();
        lpid = in.readInt();
        lpname = in.readString();
        lpnode_id = in.readString();
        lpowner = in.readParcelable(lpOwner.class.getClassLoader());
        lpposition = in.readInt();
    }

    public static final Parcelable.Creator<lpRepoModel> CREATOR = new Parcelable.Creator<lpRepoModel>() {
        @Override
        public lpRepoModel createFromParcel(Parcel in) {
            return new lpRepoModel(in);
        }

        @Override
        public lpRepoModel[] newArray(int size) {
            return new lpRepoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public static final class Creator implements Parcelable.Creator<lpRepoModel> {

        @Override
        public final lpRepoModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new lpRepoModel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), lpOwner.lpCREATOR.createFromParcel(parcel), parcel.readInt());
        }


        @Override
        public final lpRepoModel[] newArray(int i) {
            return new lpRepoModel[i];
        }
    }

    public lpRepoModel copy(int i, String node_id, String name, String full_name, lpOwner owner, int i2) {
        Intrinsics.checkNotNullParameter(node_id, "node_id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(full_name, "full_name");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return new lpRepoModel(i, node_id, name, full_name, owner, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpRepoModel) {
            lpRepoModel lprepomodel = (lpRepoModel) obj;
            return this.lpid == lprepomodel.lpid && Intrinsics.areEqual(this.lpnode_id, lprepomodel.lpnode_id) && Intrinsics.areEqual(this.lpname, lprepomodel.lpname) && Intrinsics.areEqual(this.lpfull_name, lprepomodel.lpfull_name) && Intrinsics.areEqual(this.lpowner, lprepomodel.lpowner) && this.lpposition == lprepomodel.lpposition;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.lpid * 31) + this.lpnode_id.hashCode()) * 31) + this.lpname.hashCode()) * 31) + this.lpfull_name.hashCode()) * 31) + this.lpowner.hashCode()) * 31) + this.lpposition;
    }

    public String toString() {
        return "RepoModel(id=" + this.lpid + ", node_id=" + this.lpnode_id + ", name=" + this.lpname + ", full_name=" + this.lpfull_name + ", owner=" + this.lpowner + ", position=" + this.lpposition + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.lpid);
        out.writeString(this.lpnode_id);
        out.writeString(this.lpname);
        out.writeString(this.lpfull_name);
        this.lpowner.writeToParcel(out, i);
        out.writeInt(this.lpposition);
    }

    public lpRepoModel(int i, String node_id, String name, String full_name, lpOwner owner, int i2) {
        Intrinsics.checkNotNullParameter(node_id, "node_id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(full_name, "full_name");
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.lpid = i;
        this.lpnode_id = node_id;
        this.lpname = name;
        this.lpfull_name = full_name;
        this.lpowner = owner;
        this.lpposition = i2;
    }

    public final int getLpid() {
        return this.lpid;
    }

    public final String getLpnode_id() {
        return this.lpnode_id;
    }

    public final String getLpname() {
        return this.lpname;
    }

    public final String getLpfull_name() {
        return this.lpfull_name;
    }

    public final lpOwner getLpowner() {
        return this.lpowner;
    }

    public final int getLpposition() {
        return this.lpposition;
    }

    public final void setLpposition(int i) {
        this.lpposition = i;
    }
}
