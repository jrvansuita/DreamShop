package com.vanhackathon.dreamshop.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;

import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.api.RestDream;
import com.vanhackathon.dreamshop.apt.DreamsAdapter;
import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.view.AutoFitRecycleView;

import java.util.List;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class MyDreamsActivity extends AppCompatActivity {

    private AutoFitRecycleView recycleDreams;
    private DreamsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dreams_activity);
        this.recycleDreams = (AutoFitRecycleView) findViewById(R.id.recycle_dreams);
        setup();
        init();

    }

    private void setup() {
        recycleDreams.setItemAnimator(new DefaultItemAnimator());
        recycleDreams.setHasFixedSize(true);


        adapter = new DreamsAdapter(this);
        recycleDreams.setAdapter(adapter);

    }

    public void init() {
        onRefresh();

    }


    public void onRefresh() {
        RestDream.get().getMyAll(new OnResult<List<Dream>>() {
            @Override
            public void onResult(List<Dream> result) {
                adapter.set(result);
            }
        });

    }
}
