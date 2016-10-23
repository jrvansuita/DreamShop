package com.vanhackathon.dreamshop.enums;

import com.vanhackathon.dreamshop.R;

/**
 * Created by jrvansuita on 22/10/16.
 */

public enum ESubCategoryTypes {

    BEACH("beach", R.mipmap.beach), CAMPING("camping", R.mipmap.camping),
    ADVENTURE("adventure",R.mipmap.adventure), DESERT("desert",R.mipmap.desert),
    TOURISM("tourism",R.mipmap.tourism);

    final int icon;
    final String key;


    ESubCategoryTypes(String key, int icon) {
        this.key = key;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public int getIcon() {
        return icon;
    }

    public static int getIconRes(String key){
        for (ESubCategoryTypes e : values()){
             if (key.equalsIgnoreCase(e.getKey()))
                 return e.getIcon();
        }
        return R.mipmap.not_found;
    }

    public static String[] toArrayStr() {
        String[] array = new String[values().length];

        for (int i = 0; i < values().length; i++) {
            array[i] = values()[i].key;
        }

        return array;
    }

}
