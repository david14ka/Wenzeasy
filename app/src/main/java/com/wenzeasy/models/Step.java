package com.wenzeasy.models;

import com.google.android.gms.maps.model.LatLng;

public class Step {

    private LatLng startLatLng;

    public Step(LatLng startLatLng) {
        this.startLatLng = startLatLng;
    }

    public LatLng getStartLatLng() {
        return startLatLng;
    }

    public void setStartLatLng(LatLng startLatLng) {
        this.startLatLng = startLatLng;
    }
}
