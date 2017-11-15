package com.example.eduda.climacapitales.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduda.climacapitales.Clases.ClaseCiudad;
import com.example.eduda.climacapitales.R;
import com.example.eduda.climacapitales.Servicios.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eduda on 05/11/2017.
 */

public class MainCiudad extends AppCompatActivity {

    private TextView txtCiudad, txtTemperatura, txtMaxima, txtMinima, txtHumedad, txtPresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getExtras();

        String v_ciudad = parametros.getString("KEY_NOMBRE");
        int v_ID = parametros.getInt("KEY_ID");

         BuildUI();

        final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        final String KEY_API = "aae4d7fd924c6ed483194fd1394c0a81";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        WeatherService service = retrofit.create(WeatherService.class);

        Call<ClaseCiudad> cityCall = service.getCity(v_ID, KEY_API, "metric", "es"); //San Salvador Jujuy 3836564

        cityCall.enqueue(new Callback<ClaseCiudad>() {
            @Override
            public void onResponse(Call<ClaseCiudad> call, Response<ClaseCiudad> response) {
                ClaseCiudad city = response.body();

                txtCiudad.setText(String.valueOf(city.getName()));
                txtTemperatura.setText(String.valueOf(city.getClima().getTemp())+" Celsius");
                txtMaxima.setText(String.valueOf(city.getClima().getTemp_max())+" Celsius");
                txtMinima.setText(String.valueOf(city.getClima().getTemp_min())+" Celsius");
                txtHumedad.setText(String.valueOf(city.getClima().getHumidity())+" %");
                txtPresion.setText(String.valueOf(city.getClima().getPressure())+" HPA");

            }

            @Override
            public void onFailure(Call<ClaseCiudad> call, Throwable t) {
                Toast.makeText(MainCiudad.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void  BuildUI()
    {
        txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        txtTemperatura = (TextView) findViewById(R.id.txtTemepratura);
        txtMaxima = (TextView) findViewById(R.id.txtMaxima);
        txtMinima = (TextView) findViewById(R.id.txtMinima);
        txtHumedad = (TextView) findViewById(R.id.txtHumedad);
        txtPresion = (TextView) findViewById(R.id.txtPresion);

    }
}

