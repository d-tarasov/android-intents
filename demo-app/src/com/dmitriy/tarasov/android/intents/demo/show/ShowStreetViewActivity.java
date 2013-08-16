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

package com.dmitriy.tarasov.android.intents.demo.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.dmitriy.tarasov.android.intents.demo.R;

/**
 * @author Dmitriy Tarasov
 */
public class ShowStreetViewActivity extends Activity {

    private static final float DEFAULT_LAT = 55.830981f;
    private static final float DEFAULT_LONG = 37.505016f;
    private static final float DEFAULT_YAW = 262.59f;
    private static final int DEFAULT_PITCH = 0;
    private static final float DEFAULT_ZOOM = -1.5f;
    private static final int DEFAULT_MAP_ZOOM = 13;

    private EditText latitude;
    private EditText longitude;
    private EditText yaw;
    private EditText pitch;
    private EditText zoom;
    private SeekBar mapZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_street_view);

        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        yaw = (EditText) findViewById(R.id.yaw);
        pitch = (EditText) findViewById(R.id.pitch);
        zoom = (EditText) findViewById(R.id.zoom);
        mapZoom = (SeekBar) findViewById(R.id.map_zoom);

        latitude.setText(String.valueOf(DEFAULT_LAT));
        longitude.setText(String.valueOf(DEFAULT_LONG));
        yaw.setText(String.valueOf(DEFAULT_YAW));
        pitch.setText(String.valueOf(DEFAULT_PITCH));
        zoom.setText(String.valueOf(DEFAULT_ZOOM));
        mapZoom.setProgress(DEFAULT_MAP_ZOOM);
    }

    public void streetViewClick(View view) {
        float lat = Float.valueOf(latitude.getText().toString());
        float lon = Float.valueOf(longitude.getText().toString());
        float yaw = Float.valueOf(this.yaw.getText().toString());
        int pitch = Integer.valueOf(this.pitch.getText().toString());
        float zoom = Float.valueOf(this.zoom.getText().toString());
        int mapZoom = this.mapZoom.getProgress();
        Intent intent = IntentUtils.showStreetView(lat, lon, yaw, pitch, zoom, mapZoom);
        startActivity(intent);
    }
}
