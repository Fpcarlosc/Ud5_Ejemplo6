# Ud5_Ejemplo6
_Ejemplo 6 de la Unidad 5._

Vamos a implementar una aplicación en la que haremos uso tanto de _PreferenceFragment_ como de _SharedPreferences_. La aplicación 
consistirá en cambiar a modo noche según las preferencias guardadas por el usuario. Básicamente se cambiará el fondo de blanco a 
negro y el color del texto de negro a blanco.


Para ello tenemos que seguir los siguientes pasos (antes deberemos incluir la dependencia _implementation 'com.google.android.material:material:1.0.0'_ en el fichero _build.gradle(Module:app)_:

## Paso 1: Añadir dependencias al _gradle_

El primer paso será añadir al fichero _build.gradle(Module:app)_ la dependencia _implementation 'androidx.preference:preference:1.0.0'_ 
para poder hacer uso de las preferencias.
El primer paso será crear los _layouts_ tanto para el _MainActivity_ como para el diálogo creado:

## Paso 2: Creación del fichero _preferencias.xml_

En él añadiremos los pares de clave/valor que queremos guardar. en este caso de tipo _SwitchPreference_.

```
<PreferenceScreen xmlns:app="http://schemas.android.com/apk/res-auto">
    <SwitchPreference
        app:key="ModoNoche"
        app:title="Modo noche"
        app:defaultValue="false"
        app:summaryOn="Activado"
        app:summaryOff="Desactivado"/>
</PreferenceScreen>
```

## Paso 3: Creación de la clase _PreferenciasFragment_

En ella añadiremos las preferencias creadas en el fichero _preferencias.xml_. Ésta heredará de _PreferenceFragmentCompat_.

```
public class PreferenciasFragment extends PreferenceFragmentCompat  {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Añadimos las preferencias creadas en el fichero "preferencias.xml"
        addPreferencesFromResource(R.xml.preferencias);
    }
}
```

## Paso 4: Creación de la actividad _ActividadPreferencias_

Será la encargada de mostrar el _Fragment_ anterior y de cambiar el modo si se pulsa el botón de _Atrás_.

```
public class ActividadPreferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creamos la transacción y añadimos el PreferenceFragment creado
        getSupportFragmentManager().beginTransaction()
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
```

## Paso 5: Creación de la actividad _MainActivity_
En ella mostraremos el menú de opciones y cambiaremos el modo si pulsamos sobre el botón de regreso desde la actividad _ActividadPreferencias_.

### _activity_main.xml_
```
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/fondo"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/texto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### _menu.xml_
```
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item android:id="@+id/preferencias"
        android:title="Preferencias"
        app:showAsAction="never">
    </item>
</menu>
```

### _MainActivity.java_
```
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
```
