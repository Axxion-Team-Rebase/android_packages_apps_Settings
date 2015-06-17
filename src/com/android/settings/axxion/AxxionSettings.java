/*
 * Copyright (C) 2013 SlimRoms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.axxion;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceCategory;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class AxxionSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String GLOBAL_ACTIONS_POSITION = "global_action_position";

    ListPreference mGlobalActionsPosition;    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.axxion_settings);

	    mGlobalActionsPosition = (ListPreference) findPreference(GLOBAL_ACTIONS_POSITION);
        mGlobalActionsPosition.setValue(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.GLOBAL_ACTIONS_POSITION, 
                0) + "");    
        mGlobalActionsPosition.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference == mGlobalActionsPosition) {
            int index = mGlobalActionsPosition.findIndexOfValue((String) newValue);												
			int value = Integer.parseInt((String) newValue);			
			Settings.System.putInt(getActivity().getContentResolver(),
						Settings.System.GLOBAL_ACTIONS_POSITION, value);
			CharSequence entry = mGlobalActionsPosition.getEntries()[index];			
			String option = entry.toString();
			preference.setSummary(option);       						
			return true;
		}		
        return false;
    }
}
