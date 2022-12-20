package com.example.matirialvolley;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.matirialvolley.Sett.TokenSaver;
import com.example.matirialvolley.databinding.ActivityMaps2Binding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double plat = TokenSaver.getPositionLat(getApplicationContext());
        double pLong = TokenSaver.getPositionLong(getApplicationContext());
        plat = plat * 0.1;
        pLong = pLong *0.001;
        Toast.makeText(this, "lat = " + plat + "\nlong =" + pLong, Toast.LENGTH_SHORT).show();
        Log.e("statee","lat = " + plat + "\nlong =" + pLong);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(plat, pLong);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18), 5000, new GoogleMap.CancelableCallback() {
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