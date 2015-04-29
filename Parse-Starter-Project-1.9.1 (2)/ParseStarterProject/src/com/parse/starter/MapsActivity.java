package com.parse.starter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.ParseUser;

public class MapsActivity extends FragmentActivity {
    Button logout;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        setUpMapIfNeeded();
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.5733945, -122.3065896)).title("Oracle")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.oracle)));

    /*
        mMap.addMarker(new MarkerOptions().position(new LatLng( 37.5717945, -122.3097683)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(  37.5731557, -122.3112765)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(  37.5704092, -122.3146351)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng( 37.5288597, -122.2731657)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng( 37.525401, -122.2715016)).title("Marker"));*/
        //mMap.addMarker(new MarkerOptions().position(new LatLng(  37.527073, -122.2689592)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.528349, -122.266537)).title("Home")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.home)));



        //  Make an array of the LatLng's of the markers you want to show
        LatLngBounds.Builder a = new LatLngBounds.Builder();
        a.include(new LatLng(37.5733945, -122.3065896));
        a.include(new LatLng(37.528349, -122.266537));
        a.include(new LatLng(37.5717945, -122.3097683));
        a.include(new LatLng(37.5731557, -122.3112765));
        a.include(new LatLng(37.5704092, -122.3146351));
        a.include(new LatLng(37.5288597, -122.2731657));
        a.include(new LatLng(37.525401, -122.2715016));
        a.include(new LatLng(37.527073, -122.2689592));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.528349, -122.266537), 14.0f));
        LatLngBounds bounds = a.build();

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(37.5733945, -122.3065896), new LatLng( 37.5717945, -122.3097683),new LatLng(  37.5731557, -122.3112765),new LatLng(  37.5704092, -122.3146351),
                        new LatLng( 37.5288597, -122.2731657),new LatLng( 37.525401, -122.2715016),new LatLng(  37.527073, -122.2689592),new LatLng(37.528349, -122.266537))
                .width(5)
                .color(Color.RED));



        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 25, 25, 5));
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

       CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, height,width,5);
        mMap.animateCamera(cu);
//  Create a new viewpoint bound
        // LatLngBounds bounds = new LatLngBounds();
//


    }



}
