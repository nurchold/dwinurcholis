package com.example.dwi_n.sampleapplication.CommonClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import java.util.Locale;

public class PreferenceManager {

    public static String getString(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
