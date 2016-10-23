package com.vanhackathon.dreamshop.view;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jrvansuita place 20/10/16.
 */

public class Snack {


    public static void show(View v, int resStr) {
        show(v, v.getContext().getString(resStr));
    }

    public static void show(View v, String resStr) {
        show(v, resStr, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nothing;
            }
        });
    }


    public static void show(View v, String resStr, View.OnClickListener listener) {
        Snackbar snackBar = Snackbar.make(v, resStr, Snackbar.LENGTH_LONG).setAction(android.R.string.ok, listener);

        View snackbarView = snackBar.getView();
        TextView textView = (TextView)snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackBar.show();
    }
}
