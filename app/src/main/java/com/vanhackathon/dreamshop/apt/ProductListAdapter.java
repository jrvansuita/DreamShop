package com.vanhackathon.dreamshop.apt;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.bean.Product;

import java.util.List;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private List<Product> products;

    public ProductListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_item, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text.setText(getItem(i).getTitle());


        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(ContextCompat.getColor(context, R.color.freeze))
                .borderWidthDp(3)
                .cornerRadiusDp(32)
                .oval(false)
                .build();

        Picasso.with(context).load(getItem(i).getImageUrl()).fit()
                .transform(transformation).into(holder.pic);

        return view;
    }

    static class ViewHolder {
        private ImageView pic;
        private TextView text;

        public ViewHolder(View v) {
            pic = (ImageView) v.findViewById(R.id.pic);
            text = (TextView) v.findViewById(R.id.name);
        }
    }
}
