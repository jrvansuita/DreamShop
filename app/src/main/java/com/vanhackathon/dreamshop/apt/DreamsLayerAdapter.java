package com.vanhackathon.dreamshop.apt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vanhackathon.dreamshop.frag.DreamCardFragment;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class DreamsLayerAdapter extends FragmentStatePagerAdapter {


    private int idDream;
    private int countLayers;

    public DreamsLayerAdapter(FragmentManager fm, int idDream, int countLayers) {
        super(fm);
        this.idDream = idDream;
        this.countLayers = countLayers;
    }

    @Override
    public Fragment getItem(int position) {
        return DreamCardFragment.getFrag(idDream, position, position);
    }

    @Override
    public int getCount() {
        return countLayers;
    }

}
