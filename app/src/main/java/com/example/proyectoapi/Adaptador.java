package com.example.proyectoapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    JSONArray jsonArray;
    Context context;
    public Adaptador (Context context,JSONArray jsonArray ){
        this.context = context;
        this.jsonArray=jsonArray;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //atributos
        ImageView imagenUser;
        TextView tvNombre,tvApellido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //relacion de los atributos con la vista
            imagenUser=itemView.findViewById(R.id.imageView);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvApellido=itemView.findViewById(R.id.tvApellido);
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
    //colocar los datos del json a cada elemento o vista
        try {
            holder.tvNombre.setText(jsonArray.getJSONObject(position).get("first_name").toString());
            holder.tvApellido.setText(jsonArray.getJSONObject(position).get("last_name").toString());
            //holder.imagenUser.setImageResource(R.mipmap.ic_launcher);
            String url=jsonArray.getJSONObject(position).get("avatar").toString();
            //con picasso le paso el contexto, con el load la url, y en el into el imagenView
            Picasso.with(this.context).load(url).into(holder.imagenUser);
        }catch (JSONException e){
            //error no se pudo obtener el json
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


}
