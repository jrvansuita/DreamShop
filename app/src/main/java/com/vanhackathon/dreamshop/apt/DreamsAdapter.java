package com.vanhackathon.dreamshop.apt;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.frag.DreamCardFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jrvansuita on 19/10/16.
 */

public class DreamsAdapter extends RecyclerView.Adapter<DreamsAdapter.ViewHolder> {

    private Context context;
    private List<Dream> data = new ArrayList();

    public DreamsAdapter(Context context) {
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
        final Dream dream = data.get(position);

        holder.layout.setId(fix);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDreamSelected != null)
                    onDreamSelected.onDreamSelected(dream);
            }
        });

        holder.layout.removeAllViews();

        DreamCardFragment.place((FragmentActivity) context, fix, dream.getId(), position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Dream getItem(int position) {
        return data.get(position);
    }

    public void add(Dream list) {
        this.data.add(list);

        notifyItemInserted(getItemCount() - 1);
    }

    public void set(List<Dream> list) {
        notifyItemRangeRemoved(0, getItemCount());
        this.data = list;
        notifyItemRangeInserted(0, getItemCount());
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public interface OnDreamSelected {
        void onDreamSelected(Dream dream);
    }

    private OnDreamSelected onDreamSelected;

    public void setOnDreamSelected(OnDreamSelected onDreamSelected) {
        this.onDreamSelected = onDreamSelected;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout layout;


        public ViewHolder(FrameLayout v) {
            super(v);
            this.layout = v;
        }
    }


}
