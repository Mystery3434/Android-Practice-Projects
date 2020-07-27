package com.example.android.quakereport;

public class LocationItem {
    private double mMagnitude;
    private String mCity;
    private long mDate;
    private String mURL;


    public LocationItem(double magnitude, String city, long date, String URL)
    {
        mMagnitude = magnitude;
        mCity = city;
        mDate = date;
        mURL = URL;
    }

    public double getMagnitude()
    {
        return mMagnitude;
    }
    public String getCity(){
        return mCity;
    }
    public long getDate(){
        return mDate;
    }

    public String getURL()
    {
        return mURL;
    }

}
