package com.example.matirialvolley.Sett;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.matirialvolley.LogInActivity;

public class   TokenSaver {
      private static Context ctx;


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
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LogInActivity.class));
    }

}
