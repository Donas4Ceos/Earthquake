package com.example.donas4ceos.earthquake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Vos.USGSVo;

/**
 * Created by Donas4Ceos on 13/05/15.
 */
public class adapter extends BaseAdapter {
    Context context;
    List<USGSVo> lista;

    private List<USGSVo> filteredModelItemsArray;


    public adapter(Context context, List<USGSVo> items) {
        this.context = context;

        this.lista = new ArrayList<USGSVo>();
        lista.addAll(items);

    }


    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        FrameLayout lyDetalle = (FrameLayout) mInflater.inflate(R.layout.row, null);

        final USGSVo detalle = (USGSVo) getItem(position);


        ((TextView) lyDetalle.findViewById(R.id.tv_cae)).setText(detalle.getMagnitude());
        ((TextView) lyDetalle.findViewById(R.id.tv_cae2)).setText(detalle.getPlace());


        lyDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, detail.class);
                intent.putExtra("coordenate",detalle.getGeometria().replace("[","").replace("]",""));
                intent.putExtra("place",detalle.getPlace());
                intent.putExtra("magnitude",detalle.getMagnitude());
                intent.putExtra("time",detalle.getTime());
                context.startActivity(intent);
            }
        });


        if (Double.parseDouble(detalle.getMagnitude())>0.0 && Double.parseDouble(detalle.getMagnitude())<0.9 ){
            lyDetalle.setBackgroundColor(Color.GREEN);
        }
        if (Double.parseDouble(detalle.getMagnitude())> 9.0 && Double.parseDouble(detalle.getMagnitude())<9.9 ){
            lyDetalle.setBackgroundColor(Color.RED);
        }


        return lyDetalle;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.indexOf(getItem(position));
    }


}