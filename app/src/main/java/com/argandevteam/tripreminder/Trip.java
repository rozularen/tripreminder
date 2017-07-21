package com.argandevteam.tripreminder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by markc on 21/07/2017.
 */

class Trip implements Parcelable {

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };
    private String name;

    public Trip() {
        //Empty constructor
    }

    public Trip(String name) {
        this.name = name;
    }

    protected Trip(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
