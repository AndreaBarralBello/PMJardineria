package com.example.tiendajardineria;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView selector;
    HashMap <String, List<Producto>> categoriaProducto = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        selector = findViewById(R.id.selector);


        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonarrayrequest = new JsonArrayRequest(

                //url
                "https://www.moncho.colexio-karbo.com/2025-27/tiendajardineria.php",
                //listener correcto
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        //ver si carga el array
                        Log.d("Andrea", jsonArray.toString());


                        //recorro el array y meto los productos en mi hashmap
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //cada objeto
                            try {
                                JSONObject producto = jsonArray.getJSONObject(i);

                                String nombreCategoria = producto.getString("categoria");


                                if(categoriaProducto.get(nombreCategoria)==null){

                                    categoriaProducto.put(
                                            nombreCategoria,
                                            new ArrayList<>()
                                    );

                                }
                                categoriaProducto.get(nombreCategoria).add(new Producto(producto.getString("nombre"),
                                        producto.getString("descripcion"),
                                        producto.getString("precio"),
                                        null));

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        listaProductosAdaptador listaProductosAdaptador= new listaProductosAdaptador(categoriaProducto);

                        selector.setAdapter(listaProductosAdaptador);
                    }
                },

                //listener si hubo error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }


        );



        queue.add(jsonarrayrequest);
    }
}