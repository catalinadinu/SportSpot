package com.example.sportspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
    private TextView visitSite;

    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        visitSite = findViewById(R.id.contact_site);

        visitSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pdm.ase.ro/"));
                startActivity(intent);
            }
        });

        SupportMapFragment  mapView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.contact_map);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng csie = new LatLng(44.447867,26.0991195);
        gMap.addMarker(new MarkerOptions().position(csie).title(getString(R.string.csie_ase)));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(csie));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
    }
}
