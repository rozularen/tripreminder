package com.argandevteam.tripreminder.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by markc on 21/07/2017.
 */

public class Trip implements Parcelable {



    private String id;

    private String name;


    public Trip() {
        //Empty constructor
    }

    //TODO: Model correctly Trip Object
    public Trip(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    protected Trip(Parcel in) {
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

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
}
