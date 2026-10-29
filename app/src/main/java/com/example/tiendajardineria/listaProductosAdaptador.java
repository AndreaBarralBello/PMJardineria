package com.example.tiendajardineria;

import android.content.Context;
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
        return null;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        categoriaProducto.get((categorias).get(groupPosition)).size();


        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        //nombre de la clave
        return categoriaProducto.keySet().toArray()[groupPosition];
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
        tvCategoria.setText(categorias.get(groupPosition));

        return null;
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
