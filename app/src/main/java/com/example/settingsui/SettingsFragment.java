package com.example.settingsui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("text_preference")) {
            String text = sharedPreferences.getString("text_preference", "");
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);

        } else if (key.equals("background_preference")) {
            String backgroundColor = sharedPreferences.getString("background_preference", "#FFFFFF");
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("color", backgroundColor);
            startActivity(intent);
        }
    }
}