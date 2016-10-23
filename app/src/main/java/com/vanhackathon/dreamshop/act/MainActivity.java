package com.vanhackathon.dreamshop.act;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.api.RestDream;
import com.vanhackathon.dreamshop.apt.DreamsAdapter;
import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.frag.DreamCardFragment;
import com.vanhackathon.dreamshop.icon.Icon;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.view.AutoFitRecycleView;
import com.vanhackathon.dreamshop.view.SwipeToRefreshLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ImageView btUser;
    private ImageView btMessages;
    private ImageView btSearch;
    private ImageView btLogout;

    private DreamsAdapter adapter;
    private FirebaseAuth auth;

    private TextView tvCreateDream;


    private SwipeToRefreshLayout swipe;
    private AutoFitRecycleView recycleDreams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.auth = FirebaseAuth.getInstance();


        this.recycleDreams = (AutoFitRecycleView) findViewById(R.id.recycle_dreams);
        this.swipe = (SwipeToRefreshLayout) findViewById(R.id.swipe);


        tvCreateDream = (TextView) findViewById(R.id.add_dream);

        btUser = (ImageView) findViewById(R.id.user);
        btMessages = (ImageView) findViewById(R.id.messages);
        btSearch = (ImageView) findViewById(R.id.search);
        btLogout = (ImageView) findViewById(R.id.logout);

        btUser.setOnClickListener(this);
        btMessages.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        tvCreateDream.setOnClickListener(this);
        btLogout.setOnClickListener(this);


        Icon.left(tvCreateDream).black(R.mipmap.add).put();
        Icon.put(btUser, R.mipmap.user);
        Icon.put(btMessages, R.mipmap.messages);
        Icon.put(btSearch, R.mipmap.search);

        setup();
        init();

    }

    private void setup() {

        swipe.setScrollableView(recycleDreams);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.primary), getResources().getColor(R.color.accent));

        recycleDreams.setItemAnimator(new DefaultItemAnimator());
        recycleDreams.setHasFixedSize(true);


        adapter = new DreamsAdapter(this);
        adapter.setOnDreamSelected(new DreamsAdapter.OnDreamSelected() {
            @Override
            public void onDreamSelected(Dream dream) {
                Intent i = new Intent(MainActivity.this, DreamLayersActivity.class);
                i.putExtra(DreamCardFragment.DREAM_ID_TAG, dream.getId());

                startActivity(i);
            }
        });


        //adapter.set(Temp.getStatic());

        recycleDreams.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    public void init() {
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
            }
        });
        onRefresh();

    }

    private FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
            FirebaseUser user = auth.getCurrentUser();

            if (user != null) {

            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;

            case R.id.user:
                break;
            case R.id.messages:
                break;

            case R.id.search:
                break;

            case R.id.add_dream:
                startActivity(new Intent(this, DreamComposition.class));
                break;


        }

    }

    @Override
    public void onRefresh() {
        RestDream.get().getFeedAll(new OnResult<List<Dream>>() {
            @Override
            public void onResult(List<Dream> result) {
                adapter.set(result);
                swipe.setRefreshing(false);
            }
        });

    }
}
