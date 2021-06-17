package com.utopiaxc.chest.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.utopiaxc.chest.R;

public class UIFunctions {
    public static void setThemeMode(String night_mode,Context context) {
        if (night_mode.equals(context.getString(R.string.auto_theme_value))) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (night_mode.equals(context.getString(R.string.night_theme_value))) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (night_mode.equals(context.getString(R.string.day_theme_value))) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
