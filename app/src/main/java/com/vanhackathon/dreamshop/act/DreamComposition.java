package com.vanhackathon.dreamshop.act;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.youtube.player.YouTubeIntents;
import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.anim.Animate;
import com.vanhackathon.dreamshop.api.RestDream;
import com.vanhackathon.dreamshop.api.RestLayer;
import com.vanhackathon.dreamshop.api.RestShopify;
import com.vanhackathon.dreamshop.apt.LayersAdapter;
import com.vanhackathon.dreamshop.apt.ProductListAdapter;
import com.vanhackathon.dreamshop.apt.SimpleAdapter;
import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.bean.Product;
import com.vanhackathon.dreamshop.bundle.ProductBundle;
import com.vanhackathon.dreamshop.dia.Dialog;
import com.vanhackathon.dreamshop.enums.ECategoryTypes;
import com.vanhackathon.dreamshop.enums.ESubCategoryTypes;
import com.vanhackathon.dreamshop.fire.ImageStore;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.utils.Utils;
import com.vanhackathon.dreamshop.val.Validation;
import com.vanhackathon.dreamshop.view.AutoFitRecycleView;

import java.util.List;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class DreamComposition extends AppCompatActivity implements View.OnClickListener {


    private ProgressBar pbProgress;
    private Spinner sCategory;
    private Spinner sSubcategory;
    private EditText edTitle;

    private AutoFitRecycleView preview;
    private LayersAdapter adapter;

    private Dream dream = new Dream();
    private Layer layer = new Layer();

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dream_composition);


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();



        /*
        if (getIntent().getScheme().equals("content") || getIntent().getScheme().equals("file")) {

            getIntent();

        }*/


        sCategory = (Spinner) findViewById(R.id.category);
        sSubcategory = (Spinner) findViewById(R.id.subcategory);
        edTitle = (EditText) findViewById(R.id.title);
        preview = (AutoFitRecycleView) findViewById(R.id.layers);

        preview.setItemAnimator(new DefaultItemAnimator());
        preview.setHasFixedSize(true);

        adapter = new LayersAdapter(this);
        preview.setAdapter(adapter);
        pbProgress = (ProgressBar) findViewById(R.id.progress);

        findViewById(R.id.photo).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);
        findViewById(R.id.product).setOnClickListener(this);


        sCategory.setAdapter(new SimpleAdapter(this, ECategoryTypes.toArrayStr()));
        sSubcategory.setAdapter(new SimpleAdapter(this, ESubCategoryTypes.toArrayStr()));
        onClear();

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        if (Intent.ACTION_SEND.equals(action)) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    getState();
                    sharedPref.edit().clear().commit();
                    handleVideo(sharedText);
                }
            }
        }

    }


    public void onRefreshPreview() {
        if (dream.getId() > 0)
            RestLayer.get().getAllOfDream(dream.getId(), new OnResult<List<Layer>>() {
                @Override
                public void onResult(List<Layer> result) {
                    adapter.set(result);
                }
            });
    }

    private static final int PICK_PHOTO = 1;
    private static final int PICK_VIDEO = 2;


    private boolean isTitleValid() {
        boolean ok = true;

        if (Validation.isEmpty(edTitle.getText().toString())) {
            edTitle.setError(getString(R.string.no_title));
            ok = false;
        }

        return ok;
    }


    @Override
    public void onClick(View view) {
        if (isTitleValid()) {
            showProgress();
            switch (view.getId()) {
                case R.id.photo:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO);

                    break;
                case R.id.video:
                    saveState();
                    intent = YouTubeIntents.createSearchIntent(this, edTitle.getText().toString());
                    startActivityForResult(intent, PICK_VIDEO);
                    break;
                case R.id.product:
                    RestShopify.build().requestProducts(new OnResult<ProductBundle>() {
                        @Override
                        public void onResult(ProductBundle result) {
                            new Dialog(DreamComposition.this).setTitle(R.string.choose_product).setMessage(R.string.add)
                                    .setOptions(new ProductListAdapter(DreamComposition.this, result.getProducts()))
                                    .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Product product = (Product) adapterView.getAdapter().getItem(i);

                                            layer.setType("product");
                                            layer.setProyductId(String.valueOf(product.getId()));

                                            sendDream();
                                        }
                                    })
                                    .show();
                        }
                    });

                    break;
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent extra) {
        super.onActivityResult(requestCode, resultCode, extra);
        if (requestCode == PICK_VIDEO) {
            sharedPref.edit().clear().commit();

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            String url = clipboard.getText().toString();
            clipboard.setText("");


            handleVideo(url);
            hideProgress();


        } else if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
            if (extra == null) {
                //Display an error
                return;
            }

            ImageStore.with(this).setOnResult(new OnResult<String>() {
                @Override
                public void onResult(String result) {
                    layer.setUrl(result);
                    layer.setType("photo");

                    sendDream();

                }
            }).store(extra.getData());

        }
    }


    private void sendDream() {
        if (dream.getId() == 0) {
            dream.setCategory(ECategoryTypes.values()[sCategory.getSelectedItemPosition()].getKey());
            dream.setSubcategory(ESubCategoryTypes.values()[sSubcategory.getSelectedItemPosition()].getKey());

            RestDream.get().newDream(dream, new OnResult<Dream>() {
                @Override
                public void onResult(Dream result) {
                    dream = result;
                    sendLayer();
                }
            });
        } else {
            sendLayer();
        }

    }

    private void buildLayer() {
        layer.setDream_id(dream.getId());
        layer.setDescription(edTitle.getText().toString());
    }

    private void sendLayer() {
        if (layer != null) {
            buildLayer();

            RestLayer.get().newLayer(layer, new OnResult<Layer>() {
                @Override
                public void onResult(Layer result) {
                    onClear();
                    onRefreshPreview();
                }
            });
        }
    }


    private void onClear() {
        layer = new Layer();

        findViewById(R.id.categories).setVisibility(dream.getId() == 0 ? View.VISIBLE : View.GONE);

        sCategory.setSelection(-1);
        sSubcategory.setSelection(-1);

        edTitle.setText("");
        hideProgress();
    }


    private void showProgress() {
        Animate.builder(pbProgress, R.anim.fab_in).start(true);
    }

    private void hideProgress() {
        Animate.builder(pbProgress, R.anim.fab_out).start(false);
    }


    private void saveState() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(String.valueOf(sCategory.getId()), sCategory.getSelectedItemPosition());
        editor.putInt(String.valueOf(sSubcategory.getId()), sSubcategory.getSelectedItemPosition());
        editor.putString(String.valueOf(edTitle.getId()), edTitle.getText().toString());
        editor.putInt("DREAMID", dream.getId());

        editor.commit();
    }


    private void getState() {
        sCategory.setSelection(sharedPref.getInt(String.valueOf(sCategory.getId()),0));
        sSubcategory.setSelection(sharedPref.getInt(String.valueOf(sSubcategory.getId()),0));
        edTitle.setText(sharedPref.getString(String.valueOf(edTitle.getId()),""));
        dream.setId(sharedPref.getInt("DREAMID",0));
    }

    private void handleVideo(String url){
        if (Utils.isValidUrl(url) && Utils.isYoutubeUrl(url)) {
            layer.setUrl(url);
            layer.setType("video");

            sendDream();
        }
    }
}
