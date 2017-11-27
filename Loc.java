package com.example.lenovo.employeetrackingsystem;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 19-11-2017.
 */

class Loc {
    double lat;
    double lng;
    Loc()
    {
        this.lat=lat;
        this.lng=lng;
    }

    public double  getLat() {
        return lat;
    }
    public double getLng(){ return lng;}
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    @Exclude
    public Map<String, Object> toLMap() {
        HashMap<String, Object> location = new HashMap<>();
        location.put("Latitude", lat);
        location.put("Longitude", lng);

        return location;
    }


}
