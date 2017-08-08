package com.argandevteam.tripreminder.data.source;

import android.util.Log;

import com.argandevteam.tripreminder.data.Item;
import com.argandevteam.tripreminder.data.Trip;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;


/**
 * Created by markc on 23/07/2017.
 */

public class TripsRepository implements TripsDataSource {

    private static final String TAG = "TripsRepository";

    private static TripsRepository INSTANCE = null;
    Map<String, Trip> mCachedTrips;
    boolean mCacheIsDirty = false;
    private TripsDataSource mTripsLocalDataSource = null;
    private TripsDataSource mTripsRemoteDataSource = null;

    public TripsRepository(TripsDataSource mTripsLocalDataSource, TripsDataSource mTripsRemoteDataSource) {
        if (mTripsLocalDataSource != null) {
            this.mTripsLocalDataSource = mTripsLocalDataSource;
        }
        if (mTripsRemoteDataSource != null) {
            this.mTripsRemoteDataSource = mTripsRemoteDataSource;
        }
    }

    public static TripsRepository getInstance(TripsDataSource mTripsLocalDataSource, TripsDataSource mTripsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TripsRepository(mTripsLocalDataSource, mTripsRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTrips(final LoadTripsCallback callback) {
        if (callback != null) {
            //Respond immediately with cache if available and not dirty
            if (mCachedTrips != null && !mCacheIsDirty) {
                callback.onTripsLoaded(new ArrayList<Trip>(mCachedTrips.values()));
                return;
            }
            if (mCacheIsDirty && mCachedTrips != null) {
                //If cache is dirty and we have cache from before
                getTripsFromRemoteDataSource(callback);
            } else if (mCacheIsDirty) {
                //If we dont have cache and dirty flag is set
                mTripsLocalDataSource.getTrips(new LoadTripsCallback() {
                    @Override
                    public void onTripsLoaded(List<Trip> trips) {
                        refreshCache(trips);
                        callback.onTripsLoaded(new ArrayList<Trip>(mCachedTrips.values()));
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getTripsFromRemoteDataSource(callback);
                    }
                });
            } else {
                mTripsLocalDataSource.getTrips(new LoadTripsCallback() {
                    @Override
                    public void onTripsLoaded(List<Trip> trips) {
                        refreshCache(trips);
                        callback.onTripsLoaded(new ArrayList<Trip>(mCachedTrips.values()));
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getTripsFromRemoteDataSource(callback);
                    }
                });
            }
        } else {
            Log.e(TAG, "getTrips: Load callback can't be null");
        }
    }

    @Override
    public void getTrip(final String tripId, final GetTripCallback callback) {
        if (tripId != null) {
            if (callback != null) {
                Trip cachedTrip = getTripWithId(tripId);

                if (cachedTrip != null) {
                    callback.onTripLoaded(cachedTrip);
                    return;
                }

                mTripsLocalDataSource.getTrip(tripId, new GetTripCallback() {
                    @Override
                    public void onTripLoaded(Trip trip) {
                        if (mCachedTrips == null) {
                            mCachedTrips = new LinkedHashMap<>();
                        }
                        mCachedTrips.put(trip.getId(), trip);
                        callback.onTripLoaded(trip);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        mTripsRemoteDataSource.getTrip(tripId, new GetTripCallback() {
                            @Override
                            public void onTripLoaded(Trip trip) {
                                if (mCachedTrips == null) {
                                    mCachedTrips = new LinkedHashMap<>();
                                }
                                mCachedTrips.put(trip.getId(), trip);
                                callback.onTripLoaded(trip);
                            }

                            @Override
                            public void onDataNotAvailable() {
                                callback.onDataNotAvailable();
                            }
                        });
                    }
                });
            }
        }
    }

    //TODO: Should return something???
    @Override
    public void saveTrip(Trip trip, SaveTripCallback callback) {
        if (trip != null) {
            mTripsRemoteDataSource.saveTrip(trip, callback);
            mTripsLocalDataSource.saveTrip(trip, callback);
            if (mCachedTrips == null) {
                mCachedTrips = new LinkedHashMap<>();
            }
            //TODO: Retrieve the last inserted id in local storage
            mCachedTrips.put(trip.getId(), trip);
        } else {
            Log.d(TAG, "saveTrip: TRIP IS NULL");
        }
    }

    @Override
    public void updateTrip(Trip trip) {
        if (trip != null) {
            mTripsRemoteDataSource.updateTrip(trip);
            mTripsLocalDataSource.updateTrip(trip);

            if (mCachedTrips == null) {
                mCachedTrips = new LinkedHashMap<>();
            }

            mCachedTrips.put(String.valueOf(trip.getId()), trip);
        }
    }

    @Override
    public void deleteTrip(Trip trip) {
        mTripsRemoteDataSource.deleteTrip(trip.getId());
        mCachedTrips.remove(trip.getId());
        mTripsLocalDataSource.deleteTrip(trip.getId());
    }


    @Override
    public void deleteTrip(String tripId) {
        if (tripId != null) {
            mTripsRemoteDataSource.deleteTrip(tripId);
            mCachedTrips.remove(tripId);
            mTripsLocalDataSource.deleteTrip(tripId);
        }
    }

    @Override
    public void deleteAllTrips() {
        mTripsRemoteDataSource.deleteAllTrips();
        mTripsLocalDataSource.deleteAllTrips();

        if (mCachedTrips == null) {
            mCachedTrips = new LinkedHashMap<>();
        }

        mCachedTrips.clear();
    }

    private Trip getTripWithId(String tripId) {
        if (tripId != null) {
            if (mCachedTrips == null || mCachedTrips.isEmpty()) {
                return null;
            } else {
                return mCachedTrips.get(tripId);
            }
        }
        return null;
    }

    @Override
    public void refreshTrips() {
        mCacheIsDirty = true;
    }


    @Override
    public void addItem(String mTripId, Item newItem, final NewItemCallback callback) {
        mTripsLocalDataSource.addItem(mTripId, newItem, new NewItemCallback() {
            @Override
            public void onItemCreated(RealmList<Item> newItem) {
                callback.onItemCreated(newItem);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    public void getTripsFromRemoteDataSource(final LoadTripsCallback callback) {
        mTripsRemoteDataSource.getTrips(new LoadTripsCallback() {
            @Override
            public void onTripsLoaded(List<Trip> trips) {
                refreshCache(trips);
                refreshLocalDataSource(trips);
                callback.onTripsLoaded(new ArrayList<>(mCachedTrips.values()));
            }


            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    private void refreshLocalDataSource(List<Trip> trips) {
        mTripsLocalDataSource.deleteAllTrips();
    }

    private void refreshCache(List<Trip> trips) {
        if (mCachedTrips == null) {
            mCachedTrips = new LinkedHashMap<>();
        }

        mCachedTrips.clear();

        for (Trip trip : trips) {
            mCachedTrips.put(trip.getId(), trip);
        }

        mCacheIsDirty = false;
    }
}
