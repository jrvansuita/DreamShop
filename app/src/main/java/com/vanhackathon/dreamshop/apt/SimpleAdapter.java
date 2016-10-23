package com.vanhackathon.dreamshop.apt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vanhackathon.dreamshop.R;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class SimpleAdapter extends ArrayAdapter {



    public SimpleAdapter(Context context, int resource) {
        this(context, context.getResources().getStringArray(resource));
    }

    public SimpleAdapter(Context context, String[] resource) {
        super(context, R.layout.text_spinner, resource);
        setDropDownViewResource(R.layout.dropdow_spinner);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);


        return view;
    }


}
