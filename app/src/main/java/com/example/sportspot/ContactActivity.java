package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.sportspot.util.Const;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        SupportMapFragment  mapView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.contact_map);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng csie = new LatLng(44.447867,26.0991195);
        gMap.addMarker(new MarkerOptions().position(csie).title("CSIE ASE"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(csie));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
    }
}
