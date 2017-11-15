package com.example.eduda.climacapitales.Main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eduda.climacapitales.Servicios.AdapterCiudad;
import com.example.eduda.climacapitales.Clases.ClaseCiudad;
import com.example.eduda.climacapitales.R;
import com.example.eduda.climacapitales.Servicios.SQLiteCiudad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listciudades;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getBaseContext();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        listciudades = (ListView) findViewById(R.id.lista_ciudades);

       // cargardatos();
       listciudades.setAdapter(new AdapterCiudad(cargarCiudades()));

        listciudades.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClaseCiudad seleccion = (ClaseCiudad) listciudades.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this , MainCiudad.class);
                intent.putExtra("KEY_NOMBRE",seleccion.getName());
                intent.putExtra("KEY_ID",seleccion.getId());
                startActivity(intent);
            }
        });

    }

    private void cargardatos()
    {
     ArrayList<ClaseCiudad> lista = new ArrayList<>();

        lista.add(new ClaseCiudad(3432043,"La Plata"));
        lista.add(new ClaseCiudad(3837702,"Catamarca"));
        lista.add(new ClaseCiudad(3429577,"Resistencia"));

        listciudades.setAdapter(new AdapterCiudad(lista) {
            @Override
            public long getItemId(int position) {
                return 0;
            }
        });
    }

    private ArrayList<ClaseCiudad> cargarCiudades()
    {
        ArrayList<ClaseCiudad> ciudades = new ArrayList<>();
        int id;
        String nombre;
        SQLiteCiudad SQLiteHelper = new SQLiteCiudad(context);
        try {
            SQLiteDatabase db = SQLiteHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT nombre, key FROM ciudades", null);
            if (cursor.moveToFirst()) {

                while (cursor.isAfterLast() == false) {

                    id = cursor.getInt(cursor.getColumnIndex("key"));
                    nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    ciudades.add(new ClaseCiudad(id, nombre));
                    cursor.moveToNext();
                }
            }

            db.close();
        }catch(SQLiteException ex){
            Toast.makeText(context, "Error: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return ciudades;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSalir:
                Toast.makeText(MainActivity.this, "Salir del Programa", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.menuGuardar:
                Intent intent = new Intent(MainActivity.this, MainGuardar.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
