package ca.ulaval.ima.tp3;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListOffreAdapter extends ArrayAdapter<ItemOffre>{

   List<ItemOffre> itemOffres;
    Context context;
    int resource;

    public ListOffreAdapter(Context context, int resource, List<ItemOffre> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_item_offre,null,true);
        }

        ItemOffre itemOffre=getItem(position);

        ImageView imageViewListOffre=(ImageView) convertView.findViewById(R.id.ImageViewListOffre);

        Picasso.with(context).load(itemOffre.getImageItemOffre()).resize(120,120).into(imageViewListOffre);

        TextView textPrixListOffre=(TextView) convertView.findViewById(R.id.txtPrixListOffre);
        textPrixListOffre.setText(itemOffre.getPrixItemOffre());

        TextView textMarqueListOffre=(TextView) convertView.findViewById(R.id.txtMarqueListOffre);
        textMarqueListOffre.setText(itemOffre.getMarqueItemOffre());

        TextView textModeleListOffre=(TextView) convertView.findViewById(R.id.txtModeleListOffre);
        textModeleListOffre.setText(itemOffre.getModeleItemOffre());

        TextView textKilomListOffre=(TextView) convertView.findViewById(R.id.txtKilomListOffre);
        textKilomListOffre.setText(itemOffre.getKilomItemOffre().toString());

        TextView textDateListOffre=(TextView) convertView.findViewById(R.id.txtDateListOffre);
        textDateListOffre.setText(itemOffre.getDateItemOffre());

        return convertView;

    }
}
