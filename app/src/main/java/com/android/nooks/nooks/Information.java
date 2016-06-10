package com.android.nooks.nooks;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SAYAN on 04-03-2016.
 */
public class Information implements Parcelable {

    public int iconId;

    //Common..
    public int imageId;
    public String title;
    public String details;
    public String details2;
    public String details3;
    public boolean starred;
    public int dataId;
    public String dataName;

    protected Information(Parcel in) {
        iconId = in.readInt();
        imageId = in.readInt();
        title = in.readString();
        details = in.readString();
        details2 = in.readString();
        details3 = in.readString();
        starred = in.readByte() != 0;
        dataId = in.readInt();
        dataName=in.readString();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };

    public Information() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(iconId);
        dest.writeInt(imageId);
        dest.writeString(title);
        dest.writeString(details);
        dest.writeString(details2);
        dest.writeString(details3);
        dest.writeByte((byte) (starred ? 1 : 0));
        dest.writeInt(dataId);
        dest.writeString(dataName);
    }
}
