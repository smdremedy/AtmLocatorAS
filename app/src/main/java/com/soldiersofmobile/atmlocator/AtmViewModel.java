package com.soldiersofmobile.atmlocator;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class AtmViewModel extends BaseObservable {

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    private boolean withNote = true;

    @Bindable
    public boolean isWithNote() {
        return withNote;
    }


    public void setWithNote(final boolean withNote) {
        this.withNote = withNote;
        notifyPropertyChanged(BR.withNote);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
        notifyPropertyChanged(BR.latitude);
    }

    @Bindable
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
        notifyPropertyChanged(BR.longitude);
    }
}
