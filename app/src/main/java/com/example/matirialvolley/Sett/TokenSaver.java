package com.example.matirialvolley.Sett;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.matirialvolley.LogInActivity;

public class TokenSaver {
    public static boolean IsDelevery(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getBoolean("isDelevery", false);
    }

    public static void setIsDelevery(Context c, boolean isDelevery) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isDelevery", isDelevery);
        editor.apply();
    }


    public static boolean IsFirst(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getBoolean("IsFirst", true);
    }

    public static void setIsFirst(Context c, boolean IsFirst) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("IsFirst", IsFirst);
        editor.apply();
    }

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getString("TOKEN", "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("TOKEN", token);
        editor.apply();
    }

    public static int getPositionLat(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getInt("PositionLat", 0);
    }

    public static void setPositionLat(Context c, int PositionLat) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("PositionLat", PositionLat);
        editor.apply();
    }
    public static int getPositionLong(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getInt("PositionLong",0);
    }

    public static void setPositionLong(Context c, int PositionLong) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("PositionLong", PositionLong);
        editor.apply();
    }
    public static void logout(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        c.startActivity(new Intent(c, LogInActivity.class));
    }

}
