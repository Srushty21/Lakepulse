package com.example.lakepulse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.Marker;

public class HomeActivity extends AppCompatActivity {

    // Existing Views
    TextView latestAlertText;
    Button viewAlertsBtn, mapBtn, aboutBtn;

    // New Views
    TextView lakeLevel, temperature, riskLevel, recentAlerts;
    MapView mapPreview; // Change this from ImageView to MapView
    Button emergencyBtn;
    Switch notificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Bind Views
        latestAlertText = findViewById(R.id.latestAlertText);
        viewAlertsBtn = findViewById(R.id.viewAlertsBtn);
        mapBtn = findViewById(R.id.mapBtn);
        aboutBtn = findViewById(R.id.aboutBtn);

        // New bindings
        lakeLevel = findViewById(R.id.lakeLevel);
        temperature = findViewById(R.id.temperature);
        mapPreview = findViewById(R.id.mapPreview); // Change to MapView
        riskLevel = findViewById(R.id.riskLevel);
        recentAlerts = findViewById(R.id.recentAlerts);

        emergencyBtn = findViewById(R.id.emergencyBtn);
        notificationSwitch = findViewById(R.id.notificationSwitch);

        // Sample data (replace with live database/API later)
        latestAlertText.setText("⚠️ Water level rising at Tsho Rolpa.");
        lakeLevel.setText("Level: 234.5m");
        temperature.setText("Temperature: 4.2°C");
        riskLevel.setText("Risk Level: HIGH");
        recentAlerts.setText("• Minor overflow warning\n• Lake temperature drop\n• Ice dam formation detected");

        // Button Actions
        viewAlertsBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        mapBtn.setOnClickListener(v -> {
            // Open the MapActivity when mapBtn is clicked
            startActivity(new Intent(this, MapActivity.class));
        });

        aboutBtn.setOnClickListener(v -> {
            // Open AboutActivity (to be implemented)
            // startActivity(new Intent(this, AboutActivity.class));
        });

        emergencyBtn.setOnClickListener(v -> {
            // Open EmergencyInfoActivity (to be implemented)
            // startActivity(new Intent(this, EmergencyInfoActivity.class));
        });

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Enable push notifications (implement logic here)
            } else {
                // Disable push notifications (implement logic here)
            }
        });

        // Set up the MapView for the map preview
        mapPreview.setTileSource(TileSourceFactory.MAPNIK); // Use the default tile source
        GeoPoint previewLocation = new GeoPoint(28.3949, 84.1240); // Example coordinates for a glacial lake
        mapPreview.getController().setZoom(9.0); // Set zoom level
        mapPreview.getController().setCenter(previewLocation); // Center map at the preview location

        // Add a marker on the map (optional)
        Marker marker = new Marker(mapPreview);
        marker.setPosition(previewLocation);
        marker.setTitle("Glacial Lake");
        mapPreview.getOverlays().add(marker);

        // Optional: Add click listener for map preview
        mapPreview.setOnClickListener(v -> {
            // Open the MapActivity when the map preview is clicked
            startActivity(new Intent(this, MapActivity.class));
        });
    }
}
