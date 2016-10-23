package com.vanhackathon.dreamshop.enums;

import com.vanhackathon.dreamshop.R;

/**
 * Created by jrvansuita on 22/10/16.
 */

public enum ECategoryTypes {

    POPULAR("Popular", R.mipmap.popular), TRAVEL("Travel", R.mipmap.travel),
    OUTDOOR("Outdoor", R.mipmap.outdoor), SPORTS("Sports", R.mipmap.sports),
    PRODUCTS("Products", R.mipmap.products), OTHERS("Others", R.mipmap.others);

    final int icon;
    final String key;


    ECategoryTypes(String key, int icon) {
        this.key = key;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public int getIcon() {
        return icon;
    }





    public static int getIconRes(String key) {
        for (ECategoryTypes e : values()) {
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
