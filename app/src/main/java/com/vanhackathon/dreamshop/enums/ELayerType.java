package com.vanhackathon.dreamshop.enums;

/**
 * Created by jrvansuita on 22/10/16.
 */

public enum ELayerType {

    PHOTO("photo"), VIDEO("video"), PRODUCT("product");

    final String key;

    ELayerType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


}
