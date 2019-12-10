package com.example.ud5_ejemplo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     public static ConstraintLayout fondo;
     public static TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = findViewById(R.id.fondo);
        texto = findViewById(R.id.texto);

        // Controlamos si volvemos desde la actividad ActividadPreferencias.
        SharedPreferences prefe = PreferenceManager.getDefaultSharedPreferences(this);
        boolean modoNoche = prefe.getBoolean("ModoNoche", true);

        if(modoNoche){
            MainActivity.fondo.setBackgroundColor(getResources().getColor(android.R.color.black));
            MainActivity.texto.setTextColor(getResources().getColor(android.R.color.white));
        }
        else{
            MainActivity.fondo.setBackgroundColor(getResources().getColor(android.R.color.white));
            MainActivity.texto.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // "Inflamos" el menú diseñado
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Si se pulsa sobre el item "Preferencias" abrimos la actividad ActividadPreferencias
        if(item.getItemId() == R.id.preferencias){
            Intent abrirSettings = new Intent(this, ActividadPreferencias.class);
            startActivity(abrirSettings);

            return true;
        }
        return super.onContextItemSelected(item);
    }

}
