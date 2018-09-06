package com.example.dwi_n.sampleapplication.CommonClass;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isNotEmptyTextOrNull(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Check weather given text is matching with pattern
     *
     * @param text    text to check
     * @param pattern pattern to check with
     * @return boolean
     */
    public static boolean isPatternValid(String text, Pattern pattern) {
        return pattern.matcher(text).matches();
    }

    /**
     * Method to convert the class to json string
     * @param convertJson
     * @return
     */
    public static <T> String convertClassToJson(T convertJson){
        //check whether the class not not null.
        if(convertJson != null) {
            Gson gson = new GsonBuilder()
                    .serializeSpecialFloatingPointValues()
                    .setPrettyPrinting()
                    .create();
            return gson.toJson(convertJson);
        }
        return null;
    }

    /**
     * Method to convert the json string to class
     * @param convertString
     * @param convertClass
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T convertJsonToClass(String convertString, Class<T> convertClass) throws JsonSyntaxException {

        if(!TextUtils.isEmpty(convertString)) {
            try {
                return (new Gson()).fromJson(convertString, convertClass);
            } catch (Exception e){
                Log.e("ERROR","an exception was thrown",e);
                return null;
            }
        }
        return  null;
    }

    public static <T> T convertStreamToJsonClass(final Context aContext, final String aFileName, Class<T> convertClass){
        if (aContext != null && aFileName != null && convertClass != null) {
            try {
                InputStream is = aContext.getAssets().open(aFileName);
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                return (new Gson()).fromJson(reader, convertClass);
            } catch (IOException e) {
                Log.e("ERROR","an exception was thrown",e);
            }
            return null;
        }
        return null;
    }
}
