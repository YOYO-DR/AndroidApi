package com.example.proyectoapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    JSONArray jsonArray;
    public Adaptador (Context context,JSONArray jsonArray ){
    this.jsonArray=jsonArray;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //atributos
        ImageView imagenUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //relacion de los atributos con la vista
        }
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_usuarios,null);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
    //colocar los datos del json a cadaelemnto o vista

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


}
