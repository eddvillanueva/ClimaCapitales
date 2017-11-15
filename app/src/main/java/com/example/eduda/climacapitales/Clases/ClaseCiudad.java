package com.example.eduda.climacapitales.Clases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eduda on 05/11/2017.
 */

public class ClaseCiudad {
    private int id;
    private String name;
    @SerializedName("main")
    private ClaseClima clima;

    public ClaseCiudad(int id, String name, ClaseClima clima) {
        this.id = id;
        this.name = name;
        this.clima = clima;
    }

    public ClaseCiudad(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClaseClima getClima() {
        return clima;
    }

    public void setClima(ClaseClima clima) {
        this.clima = clima;
    }

    public static ClaseClima parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        ClaseClima clima = gson.fromJson(response,ClaseClima.class);
        return clima;
    }
}