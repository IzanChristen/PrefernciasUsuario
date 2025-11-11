package com.example.preferenciasdeusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenSettings, btnShowConfig;
    private TextView tvConfigData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenSettings = findViewById(R.id.btnOpenSettings);
        btnShowConfig = findViewById(R.id.btnShowConfig);
        tvConfigData = findViewById(R.id.tvConfigData);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(this, "Atenció: Android massa antic!", Toast.LENGTH_LONG).show();
        }

        btnOpenSettings.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Error obrint configuració: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnShowConfig.setOnClickListener(v -> {
            try {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String nom = prefs.getString("nom_cognoms", "Sense nom");
                Set<String> races = prefs.getStringSet("races_preferides", null);
                boolean notif = prefs.getBoolean("notificacions", false);
                String peli = prefs.getString("pelicula_preferida", "Ninguna seleccionada");
                boolean modeFosc = prefs.getBoolean("mode_fosc", false);

                String dades = "Nombre:\t " + nom +
                        "\nRazas:\t " + (races != null ? races.toString() : "Ninguna") +
                        "\nNotificacions:\t " + (notif ? "On" : "Off") +
                        "\nPelicula:\t " + peli +
                        "\nModo Oscuro:\t " + (modeFosc ? "On" : "Off");

                tvConfigData.setText(dades);
            } catch (Exception e) {
                Toast.makeText(this, "Error llegint dades: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
