package com.example.ud5_ejemplo6;

import android.os.Bundle;

import androidx.preference.PreferenceFragment;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Añadimos las preferencias creadas en el fichero "preferencias.xml"
        addPreferencesFromResource(R.xml.preferencias);
    }
}
