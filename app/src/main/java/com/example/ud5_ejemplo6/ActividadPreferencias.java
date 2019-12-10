package com.example.ud5_ejemplo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class ActividadPreferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creamos la transacción y añadimos el PreferenceFragment creado
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }

    // Método para cambiar el modo noche
    public void CambiarModo(){
        // Obtenemos las preferencias creadas en el fichero "preferencias.xml"
        SharedPreferences prefe = PreferenceManager.getDefaultSharedPreferences(ActividadPreferencias.this);

        // Guardamos el valor del SwitchPreference
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

    // Si se pulsa el botón de "Atrás" cambiamos también el modo.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CambiarModo();
    }

}
