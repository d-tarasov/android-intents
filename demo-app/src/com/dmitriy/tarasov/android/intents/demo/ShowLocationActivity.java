/*
 * Copyright 2013 Dmitriy Tarasov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dmitriy.tarasov.android.intents.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import com.dmitriy.tarasov.android.intents.IntentUtils;

/**
 * @author Dmitriy Tarasov
 */
public class ShowLocationActivity extends Activity {

    private static final float DEFAULT_LAT = 53.2333f;
    private static final float DEFAULT_LONG = 50.1667f;
    private static final int DEFAULT_ZOOM = 13;

    private EditText longitude;
    private EditText latitude;
    private SeekBar zoomLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        longitude = (EditText) findViewById(R.id.longitude);
        latitude = (EditText) findViewById(R.id.latitude);
        zoomLevel = (SeekBar) findViewById(R.id.zoom_level);

        longitude.setText(String.valueOf(DEFAULT_LONG));
        latitude.setText(String.valueOf(DEFAULT_LAT));
        zoomLevel.setProgress(DEFAULT_ZOOM);
    }

    public void showClick(View view) {
        float lat = Float.valueOf(latitude.getText().toString());
        float lon = Float.valueOf(longitude.getText().toString());
        Intent intent = IntentUtils.showLocation(lat, lon, zoomLevel.getProgress());
        startActivity(intent);
    }
}
