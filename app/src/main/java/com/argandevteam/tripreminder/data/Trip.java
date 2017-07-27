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

    private int id;
    private long remoteId;
    private String mTitle;
    private String mStartDate;
    private String mEndDate;
    private int mNumPersons;
    private String mTotalCost;
    private List<Item> mItemsList;

    public Trip(String mTitle, String mStartDate, String mEndDate, int mNumPersons, String mTotalCost) {
        this.mTitle = mTitle;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mNumPersons = mNumPersons;
        this.mTotalCost = mTotalCost;
        this.mItemsList = new ArrayList<Item>(0);
    }

    public Trip(int id, String mTitle, String mStartDate, String mEndDate, int mNumPersons, String mTotalCost) {
        this(id, mTitle, mStartDate, mEndDate, mNumPersons, mTotalCost, new ArrayList<Item>());
    }

    public Trip(int id, String mTitle, String mStartDate, String mEndDate, int mNumPersons, String mTotalCost, List<Item> mItemsList) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
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

        if (getId() != trip.getId()) return false;
        if (mNumPersons != trip.mNumPersons) return false;
        if (mTitle != null ? !mTitle.equals(trip.mTitle) : trip.mTitle != null) return false;
        if (mStartDate != null ? !mStartDate.equals(trip.mStartDate) : trip.mStartDate != null)
            return false;
        if (mEndDate != null ? !mEndDate.equals(trip.mEndDate) : trip.mEndDate != null)
            return false;
        if (mTotalCost != null ? !mTotalCost.equals(trip.mTotalCost) : trip.mTotalCost != null)
            return false;
        return mItemsList != null ? mItemsList.equals(trip.mItemsList) : trip.mItemsList == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mStartDate != null ? mStartDate.hashCode() : 0);
        result = 31 * result + (mEndDate != null ? mEndDate.hashCode() : 0);
        result = 31 * result + mNumPersons;
        result = 31 * result + (mTotalCost != null ? mTotalCost.hashCode() : 0);
        result = 31 * result + (mItemsList != null ? mItemsList.hashCode() : 0);
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
