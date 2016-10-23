package com.vanhackathon.dreamshop.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.api.RestDream;
import com.vanhackathon.dreamshop.api.RestShopify;
import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.bundle.ProductPack;
import com.vanhackathon.dreamshop.enums.ELayerType;
import com.vanhackathon.dreamshop.enums.ESubCategoryTypes;
import com.vanhackathon.dreamshop.icon.Icon;
import com.vanhackathon.dreamshop.listener.OnResult;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class DreamCardFragment extends Fragment {

    private ViewHolder holder;
    public static final String DREAM_ID_TAG = "DREAM_ID_TAG";
    public static final String LABEL_INFO = "LABEL_INFO";
    public static final String POSITION = "POSITION";

    private Dream dream;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dream_card, null, false);

        holder = new ViewHolder(v);

        holder.ivShopifyProduct.setVisibility(View.GONE);
        holder.vVideoFrame.setVisibility(View.GONE);


        int idDream = getArguments().getInt(DREAM_ID_TAG);

        RestDream.get().getOne(idDream, new OnResult<Dream>() {
            @Override
            public void onResult(final Dream result) {
                dream = result;
                bindViews();
            }
        });

        return v;
    }


    private void bindViews() {

        final Context context = getActivity();
        int labelInfo = getArguments().getInt(LABEL_INFO);
        int layerPositon = getArguments().getInt(POSITION);


        if (!dream.getLayers().isEmpty()) {
            Layer layer = dream.getLayers().get(layerPositon);
            holder.tvTitle.setText(layer.getDescription());

            if (layer.getType() != null)
                if (layer.getType().equalsIgnoreCase(ELayerType.PHOTO.getKey())) {
                    Picasso.with(context).load(layer.getUrl()).into(holder.ivPic);
                } else if (layer.getType().equalsIgnoreCase(ELayerType.VIDEO.getKey())) {

                    Icon.put(holder.vYoutubeIco,R.mipmap.youtube);
                    holder.vVideoFrame.setVisibility(View.VISIBLE);
                    holder.vVideoFrame.setId(layer.getId());
                    holder.ivPic.setVisibility(View.GONE);

                    YoutubePreviewFrag.place((FragmentActivity) context,  layer.getId(), layer.getUrl());


                } else if (layer.getType().equalsIgnoreCase(ELayerType.PRODUCT.getKey())) {

                    RestShopify.build().requestProduct(layer.getProduct_id(), new OnResult<ProductPack>() {
                        @Override
                        public void onResult(ProductPack result) {
                            if (result != null) {
                                Picasso.with(context).load(result.getProduct().getImageUrl()).into(holder.ivPic);
                                holder.tvAvailable.setText(context.getString(R.string.available_on, result.getProduct().getTitle()));
                            }

                        }
                    });

                    holder.ivShopifyProduct.setVisibility(View.VISIBLE);
                }
        }


        Icon.put(holder.ivIconCategory, ESubCategoryTypes.getIconRes(dream.getSubcategory()));

        String photo = dream.getUser().getPhoto_url();

        if (photo != null && !photo.isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(ContextCompat.getColor(context, R.color.freeze))
                    .borderWidthDp(3)
                    .cornerRadiusDp(32)
                    .oval(false)
                    .build();

            Picasso.with(context).load(dream.getUser().getPhoto_url()).fit()
                    .transform(transformation).into(holder.ivIconUser);

        } else {
            Icon.put(holder.ivIconUser, R.mipmap.user);
        }

        holder.tvSubCategory.setText(dream.getSubcategory());
        holder.tvCategory.setText(dream.getCategory());
        holder.tvUser.setText(dream.getUser().getName());

        holder.tvLabel.setText(getPoliphormeLabel(labelInfo));
    }

    public static void place(FragmentActivity activity, int layoutId, int idDream, int labelInfo) {
        place(activity, layoutId, idDream, labelInfo, 0);
    }


    public static void place(FragmentActivity activity, int layoutId, int idDream, int labelInfo, int position) {


            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(layoutId, getFrag(idDream, labelInfo, position))
                    .commit();

    }

    public static Fragment getFrag(int idDream, int labelInfo, int position) {
        DreamCardFragment frag = new DreamCardFragment();

        Bundle b = new Bundle();
        b.putInt(DreamCardFragment.DREAM_ID_TAG, idDream);
        b.putInt(DreamCardFragment.LABEL_INFO, labelInfo);
        b.putInt(DreamCardFragment.POSITION, position);
        frag.setArguments(b);

        return frag;
    }

    public static int getPoliphormeLabel(int pos) {
        if ((pos % 2) == 0) {
            return R.string.i_dream_with;
        } else {
            return R.string.my_dream_is;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvLabel;
        private TextView tvTitle;
        private ImageView ivIconCategory;
        private ImageView ivIconUser;
        private TextView tvCategory;
        private TextView tvSubCategory;
        private TextView tvAvailable;
        private TextView tvUser;
        private View vVideoFrame;
        private ImageView ivShopifyProduct;
        private View vYoutubeIco;

        public ViewHolder(View v) {
            super(v);
            this.ivPic = (ImageView) v.findViewById(R.id.pic);
            this.tvTitle = (TextView) v.findViewById(R.id.title);
            this.tvCategory = (TextView) v.findViewById(R.id.category);
            this.ivIconCategory = (ImageView) v.findViewById(R.id.category_icon);
            this.tvSubCategory = (TextView) v.findViewById(R.id.subcategory);
            this.tvUser = (TextView) v.findViewById(R.id.user);
            this.vVideoFrame = v.findViewById(R.id.video_frame);
            this.ivIconUser = (ImageView) v.findViewById(R.id.user_icon);
            this.tvLabel = (TextView) v.findViewById(R.id.label);
            this.ivShopifyProduct = (ImageView) v.findViewById(R.id.shopify_product);
            this.tvAvailable = (TextView) v.findViewById(R.id.available_on);

            this.vYoutubeIco = v.findViewById(R.id.youtube_ico);
        }
    }
}
