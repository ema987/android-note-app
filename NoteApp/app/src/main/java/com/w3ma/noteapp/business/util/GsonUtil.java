package com.w3ma.noteapp.business.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w3ma.noteapp.network.deserializer.GsonDateDeserializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Emanuele on 25/07/2016.
 */
public class GsonUtil {

    final Gson gson;

    public GsonUtil() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateDeserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    public <T extends Object> T toObject(final String jsonString, final Class<T> tClass) {
        return gson.fromJson(jsonString, tClass);
    }

    public <T> T toObject(final String jsonString, final Type type) {
        return gson.fromJson(jsonString, type);
    }

    public Gson getGson() {
        return gson;
    }

    public String toJson(final Object object) {
        return gson.toJson(object);
    }
}
