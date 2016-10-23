package com.vanhackathon.dreamshop.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.api.RestLayer;
import com.vanhackathon.dreamshop.apt.DreamsLayerAdapter;
import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.frag.DreamCardFragment;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.view.LazyViewPager;
import com.vanhackathon.dreamshop.view.PageMarker;

import java.util.List;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class DreamLayersActivity extends AppCompatActivity {


    private LazyViewPager viewPager;
    private PageMarker pagerIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream_layers_activity);

        pagerIndicator = (PageMarker) findViewById(R.id.page_mark_down);
        viewPager = (LazyViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);

        final int idDream = getIntent().getIntExtra(DreamCardFragment.DREAM_ID_TAG, 0);

        RestLayer.get().getAllOfDream(idDream, new OnResult<List<Layer>>() {
            @Override
            public void onResult(List<Layer> layers) {
                viewPager.setAdapter(new DreamsLayerAdapter(getSupportFragmentManager(), idDream, layers.size()));
                viewPager.setCurrentItem(0);
                pagerIndicator.setViewPager(viewPager);
            }
        });


    }


}
