package com.example.lakepulse;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.File;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the OSMDroid configuration
        File osmdroidTileCache = new File(getExternalCacheDir(), "osmdroid");
        Configuration.getInstance().setOsmdroidBasePath(osmdroidTileCache);
        Configuration.getInstance().setOsmdroidTileCache(osmdroidTileCache);

        setContentView(R.layout.activity_map);

        // Initialize MapView
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);

        // Set a marker at a location (Glacial Lake example)
        GeoPoint lakeLocation = new GeoPoint(28.3949, 84.1240);
        Marker marker = new Marker(mapView);
        marker.setPosition(lakeLocation);
        marker.setTitle("Glacial Lake");
        mapView.getOverlays().add(marker);

        // Center and zoom to the marker location
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(lakeLocation);

        Log.d("MapActivity", "Map is ready and marker added at: " + lakeLocation);
    }
}
