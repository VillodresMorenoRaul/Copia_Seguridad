package com.example.toolbarpreferences;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.DialogPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class MySettingsFragment extends PreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}

