package com.argandevteam.tripreminder.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.argandevteam.tripreminder.tripsdetail.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markc on 21/07/2017.
 */

public final class Trip implements Parcelable {

    private String id;
    private String mTitle;
    private String mStartDate;
    private String mEndDate;
    private int mNumPersons;
    private String mTotalCost;
    private List<Item> mItemsList;

    public Trip(String id, String mTitle, String mStartDate, String mEndDate, int mNumPersons, String mTotalCost) {
        this(id, mTitle, mStartDate, mEndDate, mNumPersons, mTotalCost, new ArrayList<Item>());
    }

    public Trip(String id, String mTitle, String mStartDate, String mEndDate, int mNumPersons, String mTotalCost, List<Item> mItemsList) {
        this.id = id;
        this.mTitle = mTitle;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mNumPersons = mNumPersons;
        this.mTotalCost = mTotalCost;
        this.mItemsList = mItemsList;
    }

    protected Trip(Parcel in) {
        mTitle = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        this.mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        this.mEndDate = endDate;
    }

    public int getNumPersons() {
        return mNumPersons;
    }

    public void setNumPersons(int numPersons) {
        this.mNumPersons = numPersons;
    }

    public List<Item> getItemsList() {
        return mItemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.mItemsList = itemsList;
    }

    public String getTotalCost() {
        return mTotalCost;
    }

    public void setTotalCost(String totalCost) {
        this.mTotalCost = totalCost;
    }

    //Parcelable
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
    }

    //Generic methods
    //TODO: Use TextUtils or Strings to null-check Strings
    public boolean isEmpty() {
        return mTitle == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (getNumPersons() != trip.getNumPersons()) return false;
        if (!getId().equals(trip.getId())) return false;
        if (!getTitle().equals(trip.getTitle())) return false;
        if (getStartDate() != null ? !getStartDate().equals(trip.getStartDate()) : trip.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(trip.getEndDate()) : trip.getEndDate() != null)
            return false;
        if (getTotalCost() != null ? !getTotalCost().equals(trip.getTotalCost()) : trip.getTotalCost() != null)
            return false;
        return getItemsList() != null ? getItemsList().equals(trip.getItemsList()) : trip.getItemsList() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + getNumPersons();
        result = 31 * result + (getTotalCost() != null ? getTotalCost().hashCode() : 0);
        result = 31 * result + (getItemsList() != null ? getItemsList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mStartDate='" + mStartDate + '\'' +
                ", mEndDate='" + mEndDate + '\'' +
                ", mNumPersons=" + mNumPersons +
                ", mTotalCost='" + mTotalCost + '\'' +
                ", mItemsList=" + mItemsList +
                '}';
    }
}
