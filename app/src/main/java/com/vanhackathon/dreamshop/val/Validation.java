package com.vanhackathon.dreamshop.val;

import android.util.Patterns;

/**
 * Created by jrvansuita place 20/10/16.
 */

public class Validation {

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }


    public static boolean isValidEmail(String email) {
        return !isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
