package com.vanhackathon.dreamshop.utils;

import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class Temp {

    public static List<Dream> getStatic() {
        List<Layer> l = new ArrayList();
        List<Dream> dreams = new ArrayList();

        l.add(new Layer(1, "photo", "Skydiving", "http://www.skydivefresno.com/images/timeline/003.jpg", "3"));
        l.add(new Layer(2, "product", " teste teste teste", "", "9300252484l"));
        l.add(new Layer(3, "video", "Youtube video", "https://www.youtube.com/watch?v=CjParYCqoMQ",""));
        dreams.add(new Dream(1, "Travel", "Adventure", l, new User("0", "Haruki Murakami")));


        l = new ArrayList();
        l.add(new Layer(4, "photo", "Swiming with Dolphins", "https://www.kaplaninternational.com/blog/wp-content/uploads/2015/07/dolphins.jpg", "3"));
        dreams.add(new Dream(2, "Travel", "Adventure", l, new User("0", "Emma Fielda")));


        l = new ArrayList();
        l.add(new Layer(5, "photo", "Meet the sphinx", "http://67.media.tumblr.com/0cc7983471c2e6a67a6f8e71591ca724/tumblr_o1ctzbxj3h1sx5xuzo1_1280.jpg", "3"));
        dreams.add(new Dream(3, "Travel", "Desert", l, new User("0", "Haruki Murakami")));


        l = new ArrayList();
        l.add(new Layer(6, "photo", "Watch the stars while camping", "http://www.idahostatesman.com/outdoors/camping/6rgpjt/picture79729062/ALTERNATES/FREE_640/Anthony%20Lake%20Cove%20Trip%20Summer%202014%2035", "3"));
        dreams.add(new Dream(4, "Travel", "Camping", l, new User("0", "Emma Fielda")));


        l = new ArrayList();
        l.add(new Layer(7, "video", "Run a trucking race", "https://www.youtube.com/watch?v=JXSEPb6pjzQ", "3"));
        dreams.add(new Dream(5, "Outdoor", "Adventure", l, new User("0", "Mr. Pilot")));


        l = new ArrayList();
        l.add(new Layer(8, "video", "Going to Hawaii", "https://www.youtube.com/watch?v=SobT-0l1Wv8", "9300263172l"));
        dreams.add(new Dream(6, "Outdoor", "Adventure", l, new User("0", "Mr. Pilot")));

        return dreams;
    }


    public static Dream getOne(int id){
        for (Dream d : getStatic()){
            if (d.getId() == id)
                return d;
        }

        return null;
    }
}
