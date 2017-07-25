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
    private String title;
    private String startDate;
    private String endDate;
    private int numPersons;
    private String totalCost;
    private List<Item> itemsList;

    public Trip(String id, String title, String startDate, String endDate, int numPersons, String totalCost) {
        this(id, title, startDate, endDate, numPersons, totalCost, new ArrayList<Item>());
    }

    public Trip(String id, String title, String startDate, String endDate, int numPersons, String totalCost, List<Item> itemsList) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numPersons = numPersons;
        this.totalCost = totalCost;
        this.itemsList = itemsList;
    }

    protected Trip(Parcel in) {
        title = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
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
        dest.writeString(title);
    }

    //Generic methods


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
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numPersons=" + numPersons +
                ", totalCost='" + totalCost + '\'' +
                ", itemsList=" + itemsList +
                '}';
    }
}
