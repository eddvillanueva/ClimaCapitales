package com.example.eduda.climacapitales.Servicios;

import com.example.eduda.climacapitales.Clases.ClaseCiudad;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by eduda on 11/11/2017.
 */

public interface WeatherService {

        @GET("weather")
        Call<ClaseCiudad> getCity(@Query("q") String city, @Query("appid") String key);

        @GET("weather")
        Call<ClaseCiudad> getCity(@Query("id") int idCity, @Query("appid") String key);

        @GET("weather")
        Call<ClaseCiudad> getCity(@Query("id") int idCity, @Query("appid") String key, @Query("units") String value);

        @GET("weather")
        Call<ClaseCiudad> getCity(@Query("id") int idCity, @Query("appid") String key, @Query("units") String value, @Query("lang") String lang);

    }

