package com.example.lakepulse;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView alertsRecyclerView;
    private AlertAdapter alertAdapter;
    private List<Alert> alertList;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fab;
    private Button btnRefresh, btnSettings; // ✅ New buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        alertsRecyclerView = findViewById(R.id.alertsRecyclerView);
        fab = findViewById(R.id.fab);
        btnRefresh = findViewById(R.id.btn_refresh); // ✅ Find buttons
        btnSettings = findViewById(R.id.btn_settings);

        alertList = new ArrayList<>();
        alertAdapter = new AlertAdapter(alertList);
        alertsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alertsRecyclerView.setAdapter(alertAdapter);

        loadAlerts();

        // ✅ Refresh Button Click
        btnRefresh.setOnClickListener(view -> {
            loadAlerts();
            Toast.makeText(this, "Alerts Updated", Toast.LENGTH_SHORT).show();
        });

        // ✅ Settings Button Click (Placeholder)
        btnSettings.setOnClickListener(view -> {
            Toast.makeText(this, "Settings Clicked!", Toast.LENGTH_SHORT).show();
            // TODO: Open Settings Activity
        });

        // ✅ Floating Button Click (Same as Refresh)
        fab.setOnClickListener(view -> loadAlerts());
    }

    private void loadAlerts() {
        alertList.clear();
        Cursor cursor = dbHelper.getAllAlerts();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
                alertList.add(new Alert(message, timestamp));
            } while (cursor.moveToNext());
            cursor.close();
        }

        alertAdapter.notifyDataSetChanged();
    }
}
