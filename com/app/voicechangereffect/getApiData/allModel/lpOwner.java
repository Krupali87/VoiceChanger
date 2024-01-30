package com.app.voicechangereffect.getApiData.allModel;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class lpOwner implements Parcelable {
    public static final Parcelable.Creator<lpOwner> lpCREATOR = new Creator();
    private final String lpavatar_url;
    private final String lpevents_url;
    private final int lpf16583id;
    private final String lpfollowers_url;
    private final String lpfollowing_url;
    private final String lpgists_url;
    private final String lpgravatar_id;
    private final String lphtml_url;
    private final String lplogin;
    private final String lpnode_id;
    private final String lporganizations_url;
    private final String lpreceived_events_url;
    private final String lprepos_url;
    private final boolean lpsite_admin;
    private final String lpstarred_url;
    private final String lpsubscriptions_url;
    private final String lptype;
    private final String lpurl;

    protected lpOwner(Parcel in) {
        lpavatar_url = in.readString();
        lpevents_url = in.readString();
        lpf16583id = in.readInt();
        lpfollowers_url = in.readString();
        lpfollowing_url = in.readString();
        lpgists_url = in.readString();
        lpgravatar_id = in.readString();
        lphtml_url = in.readString();
        lplogin = in.readString();
        lpnode_id = in.readString();
        lporganizations_url = in.readString();
        lpreceived_events_url = in.readString();
        lprepos_url = in.readString();
        lpsite_admin = in.readByte() != 0;
        lpstarred_url = in.readString();
        lpsubscriptions_url = in.readString();
        lptype = in.readString();
        lpurl = in.readString();
    }

    public static final Parcelable.Creator<lpOwner> CREATOR = new Parcelable.Creator<lpOwner>() {
        @Override
        public lpOwner createFromParcel(Parcel in) {
            return new lpOwner(in);
        }

        @Override
        public lpOwner[] newArray(int size) {
            return new lpOwner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public static final class Creator implements Parcelable.Creator<lpOwner> {

        @Override
        public final lpOwner createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new lpOwner(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }


        @Override
        public final lpOwner[] newArray(int i) {
            return new lpOwner[i];
        }
    }

    public lpOwner copy(String login, int i, String node_id, String avatar_url, String gravatar_id, String url, String html_url, String followers_url, String following_url, String gists_url, String starred_url, String subscriptions_url, String organizations_url, String repos_url, String events_url, String received_events_url, String type, boolean z) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(node_id, "node_id");
        Intrinsics.checkNotNullParameter(avatar_url, "avatar_url");
        Intrinsics.checkNotNullParameter(gravatar_id, "gravatar_id");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(html_url, "html_url");
        Intrinsics.checkNotNullParameter(followers_url, "followers_url");
        Intrinsics.checkNotNullParameter(following_url, "following_url");
        Intrinsics.checkNotNullParameter(gists_url, "gists_url");
        Intrinsics.checkNotNullParameter(starred_url, "starred_url");
        Intrinsics.checkNotNullParameter(subscriptions_url, "subscriptions_url");
        Intrinsics.checkNotNullParameter(organizations_url, "organizations_url");
        Intrinsics.checkNotNullParameter(repos_url, "repos_url");
        Intrinsics.checkNotNullParameter(events_url, "events_url");
        Intrinsics.checkNotNullParameter(received_events_url, "received_events_url");
        Intrinsics.checkNotNullParameter(type, "type");
        return new lpOwner(login, i, node_id, avatar_url, gravatar_id, url, html_url, followers_url, following_url, gists_url, starred_url, subscriptions_url, organizations_url, repos_url, events_url, received_events_url, type, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof lpOwner) {
            lpOwner lpowner = (lpOwner) obj;
            return Intrinsics.areEqual(this.lplogin, lpowner.lplogin) && this.lpf16583id == lpowner.lpf16583id && Intrinsics.areEqual(this.lpnode_id, lpowner.lpnode_id) && Intrinsics.areEqual(this.lpavatar_url, lpowner.lpavatar_url) && Intrinsics.areEqual(this.lpgravatar_id, lpowner.lpgravatar_id) && Intrinsics.areEqual(this.lpurl, lpowner.lpurl) && Intrinsics.areEqual(this.lphtml_url, lpowner.lphtml_url) && Intrinsics.areEqual(this.lpfollowers_url, lpowner.lpfollowers_url) && Intrinsics.areEqual(this.lpfollowing_url, lpowner.lpfollowing_url) && Intrinsics.areEqual(this.lpgists_url, lpowner.lpgists_url) && Intrinsics.areEqual(this.lpstarred_url, lpowner.lpstarred_url) && Intrinsics.areEqual(this.lpsubscriptions_url, lpowner.lpsubscriptions_url) && Intrinsics.areEqual(this.lporganizations_url, lpowner.lporganizations_url) && Intrinsics.areEqual(this.lprepos_url, lpowner.lprepos_url) && Intrinsics.areEqual(this.lpevents_url, lpowner.lpevents_url) && Intrinsics.areEqual(this.lpreceived_events_url, lpowner.lpreceived_events_url) && Intrinsics.areEqual(this.lptype, lpowner.lptype) && this.lpsite_admin == lpowner.lpsite_admin;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.lplogin.hashCode() * 31) + this.lpf16583id) * 31) + this.lpnode_id.hashCode()) * 31) + this.lpavatar_url.hashCode()) * 31) + this.lpgravatar_id.hashCode()) * 31) + this.lpurl.hashCode()) * 31) + this.lphtml_url.hashCode()) * 31) + this.lpfollowers_url.hashCode()) * 31) + this.lpfollowing_url.hashCode()) * 31) + this.lpgists_url.hashCode()) * 31) + this.lpstarred_url.hashCode()) * 31) + this.lpsubscriptions_url.hashCode()) * 31) + this.lporganizations_url.hashCode()) * 31) + this.lprepos_url.hashCode()) * 31) + this.lpevents_url.hashCode()) * 31) + this.lpreceived_events_url.hashCode()) * 31) + this.lptype.hashCode()) * 31) + (this.lpsite_admin ? 1 : 0);
    }

    public String toString() {
        return "Owner(login=" + this.lplogin + ", id=" + this.lpf16583id + ", node_id=" + this.lpnode_id + ", avatar_url=" + this.lpavatar_url + ", gravatar_id=" + this.lpgravatar_id + ", url=" + this.lpurl + ", html_url=" + this.lphtml_url + ", followers_url=" + this.lpfollowers_url + ", following_url=" + this.lpfollowing_url + ", gists_url=" + this.lpgists_url + ", starred_url=" + this.lpstarred_url + ", subscriptions_url=" + this.lpsubscriptions_url + ", organizations_url=" + this.lporganizations_url + ", repos_url=" + this.lprepos_url + ", events_url=" + this.lpevents_url + ", received_events_url=" + this.lpreceived_events_url + ", type=" + this.lptype + ", site_admin=" + this.lpsite_admin + ')';
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.lplogin);
        out.writeInt(this.lpf16583id);
        out.writeString(this.lpnode_id);
        out.writeString(this.lpavatar_url);
        out.writeString(this.lpgravatar_id);
        out.writeString(this.lpurl);
        out.writeString(this.lphtml_url);
        out.writeString(this.lpfollowers_url);
        out.writeString(this.lpfollowing_url);
        out.writeString(this.lpgists_url);
        out.writeString(this.lpstarred_url);
        out.writeString(this.lpsubscriptions_url);
        out.writeString(this.lporganizations_url);
        out.writeString(this.lprepos_url);
        out.writeString(this.lpevents_url);
        out.writeString(this.lpreceived_events_url);
        out.writeString(this.lptype);
        out.writeInt(this.lpsite_admin ? 1 : 0);
    }

    public lpOwner(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, boolean z) {
        this.lplogin = str;
        this.lpf16583id = i;
        this.lpnode_id = str2;
        this.lpavatar_url = str3;
        this.lpgravatar_id = str4;
        this.lpurl = str5;
        this.lphtml_url = str6;
        this.lpfollowers_url = str7;
        this.lpfollowing_url = str8;
        this.lpgists_url = str9;
        this.lpstarred_url = str10;
        this.lpsubscriptions_url = str11;
        this.lporganizations_url = str12;
        this.lprepos_url = str13;
        this.lpevents_url = str14;
        this.lpreceived_events_url = str15;
        this.lptype = str16;
        this.lpsite_admin = z;
    }

    public final int getId() {
        return this.lpf16583id;
    }

    public final String getLpurl() {
        return this.lpurl;
    }

    public final String getLptype() {
        return this.lptype;
    }
}
