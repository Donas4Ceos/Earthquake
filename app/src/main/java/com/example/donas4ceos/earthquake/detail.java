package com.example.donas4ceos.earthquake;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class detail extends ActionBarActivity {

    GoogleMap googleMap;
    TextView magnitude,date,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        magnitude =(TextView)this.findViewById(R.id.magnitude);
        date =(TextView)this.findViewById(R.id.date);
        location=(TextView)this.findViewById(R.id.location);

        Bundle extras = getIntent().getExtras();
        String coordenadas[] = extras.getString("coordenate").split(",");
        location.setText(extras.getString("place"));
        magnitude.setText(extras.getString("magnitude"));
        date.setText(extras.getString("time"));
        createMapView();
        addMarker(coordenadas,extras.getString("place"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker(String coordenadas[],String name) {

        /** Make sure that the map has been initialised **/
        if (null != googleMap) {
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(coordenadas[1]),Double.parseDouble(coordenadas[0])))
                            .title(name)
                            .draggable(false)
            ).showInfoWindow();


            CameraUpdate center;
            CameraUpdate zoom;
            center = CameraUpdateFactory.newLatLng(new LatLng(
                    Double.parseDouble(coordenadas[1]), Double.parseDouble(coordenadas[0])));
            zoom = CameraUpdateFactory.zoomTo(10);


            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
        }
    }
}
