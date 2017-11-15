package com.example.eduda.climacapitales.Main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduda.climacapitales.R;
import com.example.eduda.climacapitales.Servicios.SQLiteCiudad;

/**
 * Created by eduda on 11/11/2017.
 */

public class MainGuardar extends AppCompatActivity {

    private TextView txtID;
    private TextView txtCiudad;
    private Button btnGuardar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);
        context = getBaseContext();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        txtID = (TextView) findViewById(R.id.txtID);
        txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

    }

    public void guardarDatos()
    {
        SQLiteCiudad SQLiteHelper = new SQLiteCiudad(context);
        SQLiteDatabase db = SQLiteHelper.getWritableDatabase();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre", txtCiudad.getText().toString() );
        nuevoRegistro.put("key", Integer.parseInt(txtID.getText().toString()) );
        db.insert(SQLiteHelper.CIUDADES_TABLE_NAME, null, nuevoRegistro);

        Toast.makeText(MainGuardar.this, "Dato guardado con Exito", Toast.LENGTH_SHORT).show();
        txtID.setText("");
        txtCiudad.setText("");
        db.close();
    }

}
