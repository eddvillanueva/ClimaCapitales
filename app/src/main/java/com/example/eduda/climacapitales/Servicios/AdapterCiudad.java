package com.example.eduda.climacapitales.Servicios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eduda.climacapitales.Clases.ClaseCiudad;
import com.example.eduda.climacapitales.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduda on 05/11/2017.
 */

public class AdapterCiudad extends BaseAdapter {

    private ArrayList<ClaseCiudad> ciudades;

    public AdapterCiudad(ArrayList<ClaseCiudad> ciudades){
        this.ciudades = ciudades;

    }

    @Override
    public int getCount() {
        return ciudades.size();
    }

    @Override
    public Object getItem(int position) {
        return ciudades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ciudades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if(convertView == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ciudad, parent,false);
        else
            view = convertView;
        ClaseCiudad mensaje = (ClaseCiudad)getItem(position);
        TextView txtNombre = (TextView) view.findViewById(R.id.txtCiudad);
        TextView txtID = (TextView) view.findViewById(R.id.txtID);
        txtNombre.setText(mensaje.getName() );
        txtID.setText(String.valueOf(mensaje.getId()).toString()   );
        return view;
    }
}