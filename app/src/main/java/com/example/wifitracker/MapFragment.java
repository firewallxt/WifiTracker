package com.example.wifitracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // initialize view
        View view = inflater.inflate(R.layout.fragment_map, container,  false);

        // initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                // when map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                        // when map is clicked on, initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();

                        // set position of marker
                        markerOptions.position(latLng);

                        // set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        // remove markers
                        googleMap.clear();

                        // marker animation
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                        // add marker on map
                        googleMap.addMarker(markerOptions);

                    }
                });
            }
        });

        // return view
        return view;
    }
}