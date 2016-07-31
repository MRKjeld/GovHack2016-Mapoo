package com.loocrew.mapoo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static LatLng myPos = new LatLng(-41.2808,174.7765);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//        mMap.addMarker(new MarkerOptions().position(myPos).title("Me"));
    }

    @Override
    public void onLocationChanged(Location loc) {
        myPos = new LatLng(loc.getLatitude(), loc.getLongitude());
//        mMap.clear();
//        mMap.addMarker(new MarkerOptions().position(myPos).title("Me"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPos));
    }

    public void ratePoo(View view) {
        ImageButton btnYay = (ImageButton)findViewById(R.id.btnYay);
        ImageButton btnNay = (ImageButton)findViewById(R.id.btnNay);
        if(btnYay.getVisibility()==Button.INVISIBLE) {
            btnYay.setVisibility(Button.VISIBLE);
            btnNay.setVisibility(Button.VISIBLE);
        }
        else{
            btnYay.setVisibility(Button.INVISIBLE);
            btnNay.setVisibility(Button.INVISIBLE);
        }
    }

    public void sendRating(View view){
        //demo due to time constraints will just show info taken.


        TextView tv = (TextView)findViewById(R.id.text);
        tv.setText("Toilet rating for: " + myPos.toString()+ "sent.");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(myPos).title("Here I am"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPos));
    }

    public void clearText(View view){
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText("Please use Mapoo");
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.loocrew.mapoo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.loocrew.mapoo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
