package com.vanhackathon.dreamshop.apt;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.frag.DreamCardFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jrvansuita on 19/10/16.
 */

public class LayersAdapter extends RecyclerView.Adapter<LayersAdapter.ViewHolder> {

    private Context context;
    private List<Layer> data = new ArrayList();

    public LayersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics());

        FrameLayout frameLayout = new FrameLayout(parent.getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) height));
        return new ViewHolder(frameLayout);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int fix = new Random().nextInt(600) + position;
        Layer layer = data.get(position);

        holder.layout.setId(fix);
        DreamCardFragment.place((FragmentActivity) context, fix, layer.getDream_id(), position, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Layer getItem(int position) {
        return data.get(position);
    }

    public void add(Layer list) {
        this.data.add(list);

        notifyItemInserted(getItemCount() - 1);
    }

    public void set(List<Layer> list) {
        int size = getItemCount();
        this.data = list;
        notifyItemRangeInserted(size, getItemCount());
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout layout;


        public ViewHolder(FrameLayout v) {
            super(v);
            this.layout = v;
        }
    }


}
