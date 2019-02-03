package com.transfer.spark.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

import java.lang.reflect.Type;

public class JsonUtils {

    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy";

    private static final Gson gson = new GsonBuilder()
                                    .setDateFormat(DATE_TIME_FORMAT)
                                    .create();

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJsonString(Type type, String json) {
        return gson.fromJson(json, type);
    }

    public static ResponseTransformer json() {
        return JsonUtils::toJsonString;
    }
}
