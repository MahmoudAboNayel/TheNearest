package com.example.it.thenearest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by it on 10/6/2017.
 */

public class PlaceAdapter extends ArrayAdapter {
    public PlaceAdapter(@NonNull Context context, @NonNull PlaceModel[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_place_raw, parent, false);
        }
        PlaceModel placeModel = (PlaceModel) getItem(position);
        TextView name =(TextView) convertView.findViewById(R.id.name);
        RatingBar r = (RatingBar) convertView.findViewById(R.id.ratingBar);
        ImageView i = (ImageView) convertView.findViewById(R.id.icon);
        Picasso.with(getContext()).load(placeModel.getIcon()).into(i);
        r.setIsIndicator(true);
        r.setRating(placeModel.getRating());
        name.setText(placeModel.getName());
        return convertView;
    }
}
