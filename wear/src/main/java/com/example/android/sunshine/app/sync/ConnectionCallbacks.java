package com.example.android.sunshine.app.sync;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

//  ConnectionCallbacks provides a basis for SunshineDataListener to be able to sync the
//  weather data from the handheld app to the wearable

public class ConnectionCallbacks implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        WeatherInformationListener {

    private final GoogleApiClient googleApiClient;
    private final SunshineDataListener sunshineDataListener;
    private final WeatherInformationListener weatherInformationListener;

    public static ConnectionCallbacks initialize(Context context, WeatherInformationListener weatherInformationListener) {
        return new ConnectionCallbacks(context, weatherInformationListener);
    }

    private ConnectionCallbacks(Context context, WeatherInformationListener weatherInformationListener) {
        this.weatherInformationListener = weatherInformationListener;
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build();
        sunshineDataListener = new SunshineDataListener(googleApiClient, this);
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Wearable.DataApi.addListener(googleApiClient, sunshineDataListener);
    }

    public void performSync() {
        sunshineDataListener.requestWeatherInformationFromHandheld();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onTemperatureFetchSuccess(double high, double low) {
        weatherInformationListener.onTemperatureFetchSuccess(high, low);
    }

    @Override
    public void onWeatherIconFetchSuccess(Bitmap icon) {
        weatherInformationListener.onWeatherIconFetchSuccess(icon);
    }

    @Override
    public void onWeatherInformationFetchFailure() {
        weatherInformationListener.onWeatherInformationFetchFailure();
    }
}
