package com.example.matirialvolley.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.matirialvolley.R;
import com.example.matirialvolley.databinding.ActivityBindorderBinding;
import com.example.matirialvolley.databinding.ActivityCustumerHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class BindOrderActivity extends AppCompatActivity {
    ActivityBindorderBinding binding ;
    private MapView mapView;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBindorderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int work = getIntent().getIntExtra("work", 1);
        String phone = binding.editTextPhone.getText().toString();
        String locationdet = binding.multiAutoCompleteTextView.getText().toString();

        binding.next.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LastAfterPostActivity.class);
            intent.putExtra("work", work);
            intent.putExtra("phone", phone);
            intent.putExtra("locdetails", locationdet);
            startActivity(intent);
            finish();
        });
         // Initialize the MapView
        mapView = findViewById(R.id.mapView);
      //  mapView.onCreate(savedInstanceState);

        // Initialize the FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // Check for location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request location permission if it has not been granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        // Set an OnMapReadyCallback on the MapView
        mapView.getMapAsync(googleMap -> {
            // Add a Marker to the map at the current location
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //end on Success
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(BindOrderActivity.this, location -> {
                        if (location != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.getUiSettings().setCompassEnabled(true);
                            googleMap.getUiSettings().setZoomControlsEnabled(true);

                            googleMap.addMarker(new MarkerOptions().position(currentLatLng));
                            googleMap.setOnMapClickListener(latLng -> {
// Remove any existing markers from the map
                                 googleMap.clear();

                                // Add a new marker to the map at the clicked location
                                googleMap.addMarker(new MarkerOptions().position(latLng));
                            });

                        }//end if
                    });//end OnSuccessListener

            // Add an OnMarkerClickListener to the map
            googleMap.setOnMarkerClickListener(marker -> {
                // Get the position of the Marker
                LatLng markerPosition = marker.getPosition();

                // Store the Marker position in an IntentExtra
                Intent intent = new Intent(BindOrderActivity.this, LastAfterPostActivity.class);
                Log.d("statee", markerPosition + "");
                intent.putExtra("marker_position", markerPosition);

                // Start the second activity
                startActivity(intent);
                return true;
            });
        });
    }


}

