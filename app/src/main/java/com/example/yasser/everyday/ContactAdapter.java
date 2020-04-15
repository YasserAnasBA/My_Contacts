package com.example.yasser.everyday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    Context context;
    int resource ;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView txtvw_name = (TextView) convertView.findViewById(R.id.name_txtvw);
        TextView txtvw_phone = (TextView) convertView.findViewById(R.id.phone_txtvw);
        ImageView imguser = (ImageView)convertView.findViewById(R.id.imguser);
        Contact contactinfo = getItem(position);

        txtvw_name.setText(contactinfo.getName());
        txtvw_phone.setText(String.valueOf(contactinfo.getNum()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(contactinfo.getImage(),0,contactinfo.getImage().length);
        imguser.setImageBitmap(bitmap);
        return convertView;

    }
}
