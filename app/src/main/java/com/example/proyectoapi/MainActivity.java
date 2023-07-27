package com.example.proyectoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    RecyclerView reciclerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reciclerLista=findViewById(R.id.reciclerView);
        reciclerLista.setLayoutManager(new LinearLayoutManager(this));
        //conectarse al servidor
        AsyncHttpClient httpCliente=new AsyncHttpClient();
        httpCliente.get(UrlServidor.urlListar, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(MainActivity.this, "Si hay conexión, code: "+statusCode, Toast.LENGTH_LONG).show();
                //convertir la respuesta de byte a string porque es mas facil de manipular
                String respuesta = new String(responseBody);
                try {
                    cargarDatos(statusCode,respuesta);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "No hay coneccion,code: "+statusCode, Toast.LENGTH_SHORT).show();
                try {
                    cargarDatos(statusCode,"Error de conexion");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void cargarDatos(int statusCode, String respuesta) throws JSONException {
        if (statusCode==200){
            //Si hay conexion
            Log.e("datos",respuesta.toString());
            //convertir el string a formato json

            JSONObject miJsonObj=new JSONObject(respuesta);
            JSONArray miJsonArray=miJsonObj.getJSONArray("data");
            //colocar datos en el listView
            //crear el adaptador
        }else{
            Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
        }
    }
}