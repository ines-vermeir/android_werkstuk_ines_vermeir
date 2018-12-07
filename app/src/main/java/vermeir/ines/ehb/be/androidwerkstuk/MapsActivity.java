package vermeir.ines.ehb.be.androidwerkstuk;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    //markers op maps
    private List<Marker> markers = new ArrayList<>();
    //standbeelden
    private List<Statue> statues = new ArrayList<>();
    //huidige standbeeld
    private Statue currentStatue;
    private LatLngBounds.Builder latLngBounds;

    private ImageButton qrButton;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    private static final int MY_PERMISION_REQUEST_CAMERA = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        qrButton = findViewById(R.id.qrButton);

        setAllStatues();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Lay out map
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapsstyle));

            if (!success) {
                Log.e("this", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("this", "Can't find style. Error: ", e);
        }



        //ask user location
        //TODO maak er een aparte methode van
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            }

        }else{
            mMap.setMyLocationEnabled(true);
        }


        //functions on map and location
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.stopAnimation();
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);




        //place all markers
        initializeMarkers();


    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mMap.setMyLocationEnabled(true);
                    Toast.makeText(this, "location permission granted", Toast.LENGTH_LONG).show();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "location permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case MY_PERMISION_REQUEST_CAMERA:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                    QRCodeUtil.startQRScan(this);

                } else {

                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onClick(View v) {
        // Request the permission
        /*ActivityCompat.requestPermissions(MapsActivity.this,
                new String[]{Manifest.permission.CAMERA},
                MY_PERMISSIONS_REQUEST_LOCATION);*/
    }


    private void initializeMarkers() {
        latLngBounds = new LatLngBounds.Builder();

        for (Statue statue : statues) {
            mMap.addMarker(new MarkerOptions()
                    .position(statue.getLatLng())
                    .title(statue.getName()));
            latLngBounds.include(statue.getLatLng());
        }




        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLngBounds bounds = latLngBounds.build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100);
                mMap.moveCamera(cameraUpdate);
            }
        });

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public void setAllStatues(){
        Statue mannekePis = new Statue("1","Manneke Pis", "Dit is manneke pis", 50.84546, 4.34915);
        mannekePis.addQuestion("Hoe groot is manneke pis?", "53 cm");

        Statue jeanekePis = new Statue("2","Jaeneke Pis", "Dit is jeaneke pis", 50.84848, 4.35404);
        jeanekePis.addQuestion("Tussen welke huisnummers staat Jeaneke pis?", "10 & 12");

        statues.add(mannekePis);
        statues.add(jeanekePis);
    }

    public void scanForQRCode(View view){
        //camera permision for qr code
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISION_REQUEST_CAMERA);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Snackbar snackbar = Snackbar
                        .make(view, getString(R.string.snackbarCameraPermission), Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        }else{
            QRCodeUtil.startQRScan(this);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = QRCodeUtil.onScanResult(this, requestCode,resultCode,data);
        for (Statue statue : statues) {
            if(statue.getId().equals(result)){
                goToQuestion(statue);
                break;
            }else{
                Toast.makeText(this, "Kan het standbeeld niet vinden", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void goToQuestion(Statue statue) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("statue", statue);
        startActivity(intent);
    }

}
