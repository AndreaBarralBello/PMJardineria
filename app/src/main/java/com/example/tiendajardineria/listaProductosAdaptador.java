package com.example.tiendajardineria;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class listaProductosAdaptador extends BaseExpandableListAdapter {

    HashMap<String, List <Producto> >categoriaProducto = new HashMap<>();
    List <String> categorias = new ArrayList<>();


    //GROUP: categorías
    //CHILDREN: productos

    //Genero un constructor con el hash
    public listaProductosAdaptador( HashMap<String, List<Producto>> categoriaProducto) {
        this.categoriaProducto = categoriaProducto;

        //la lista de categorías es la lista formada por las claves del HashMap
        categorias = new ArrayList<>(categoriaProducto.keySet());
        Log.i("andrea", categorias.toString());
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //en el grupo i, un objeto concreto

        return categoriaProducto.get(categorias.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.i("Andrea","Inflando el producto");
        Producto producto = (Producto) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater)  parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lista_productos,null);
        }
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvPrecio = convertView.findViewById(R.id.tvPrecio);
        TextView tvDescripcion = convertView.findViewById(R.id.tvDescripcion);

        tvNombre.setText(producto.getNombre());
        tvDescripcion.setText(producto.getDescripcion());
        tvPrecio.setText(producto.getPrecio());

        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        categoriaProducto.get((categorias).get(groupPosition)).size();


        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        //nombre de la clave
        return categorias.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        //el tamaño del hashmap, que es la cantidad de categorías
        return categoriaProducto.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //es el layout lista_categorías

        //lo que queremos que aparezca: 1º el nombre de la categoría
        String nombreCategoria = categorias.get(groupPosition);

        //si el layout no esta definido, lo definimos ahora
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lista_categorias, null);
        }

        TextView tvCategoria = convertView.findViewById(R.id.tvCategorias);
        tvCategoria.setText(nombreCategoria);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {


        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return false;
    }
}
