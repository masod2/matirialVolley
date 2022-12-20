package com.example.matirialvolley;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.matirialvolley.Sett.TokenSaver;
import com.example.matirialvolley.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        // Add a marker in Sydney and move the camera
        Double plat = Double.valueOf(TokenSaver.getPositionLat(getApplicationContext()));
        Double pLong = Double.valueOf(TokenSaver.getPositionLong(getApplicationContext()));

         Toast.makeText(this, "lat = " + plat + "\nlong =" + pLong, Toast.LENGTH_SHORT).show();
        LatLng sydney = new LatLng(plat, pLong);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker "));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18), 20000, new GoogleMap.CancelableCallback() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }
        });
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}