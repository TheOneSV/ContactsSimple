package com.example.yonko.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String picUrl;
    private String name;
    private String phoneNumber;

    public Contact(String picUrl, String name, String phoneNumber) {
        this.picUrl = picUrl;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    protected Contact(Parcel in) {
        picUrl = in.readString();
        name = in.readString();
        phoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picUrl);
        dest.writeString(name);
        dest.writeString(phoneNumber);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
