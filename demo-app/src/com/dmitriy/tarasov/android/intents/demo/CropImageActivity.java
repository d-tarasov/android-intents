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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.dmitriy.tarasov.android.intents.IntentUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Dmitriy Tarasov
 */
public class CropImageActivity extends Activity {

    private static final String TAG = CropImageActivity.class.getName();

    private static final int CROP_REQUEST_CODE = 0;
    private static final int QUALITY = 100;

    private static final int DEFAULT_OUTPUT_X = 200;
    private static final int DEFAULT_OUTPUT_Y = 200;
    private static final int DEFAULT_ASPECT_X = 1;
    private static final int DEFAULT_ASPECT_Y = 1;

    private static final boolean DEFAULT_SCALE = true;

    private File inImage;
    private Bitmap bmp;

    private EditText outputX;
    private EditText outputY;
    private EditText aspectX;
    private EditText aspectY;

    private CheckBox scale;

    private ImageView in;
    private ImageView out;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        bmp = BitmapFactory.decodeResource(getResources(), R.raw.sample);
        try {
            inImage = new File("/sdcard/tmp.jpg");
            bmp.compress(Bitmap.CompressFormat.JPEG, QUALITY, new FileOutputStream(inImage));
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Cannot write image to SDCARD", e);
        }

        initViews();
    }

    private void initViews() {
        outputX = (EditText) findViewById(R.id.output_x);
        outputY = (EditText) findViewById(R.id.output_y);
        aspectX = (EditText) findViewById(R.id.aspect_x);
        aspectY = (EditText) findViewById(R.id.aspect_y);
        scale = (CheckBox) findViewById(R.id.scale);
        in = (ImageView) findViewById(R.id.in);
        out = (ImageView) findViewById(R.id.out);

        outputX.setText(String.valueOf(DEFAULT_OUTPUT_X));
        outputY.setText(String.valueOf(DEFAULT_OUTPUT_Y));
        aspectX.setText(String.valueOf(DEFAULT_ASPECT_X));
        aspectY.setText(String.valueOf(DEFAULT_ASPECT_Y));
        scale.setChecked(DEFAULT_SCALE);
        in.setImageBitmap(bmp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CROP_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap cropped = extras.getParcelable("data");
                    out.setImageBitmap(cropped);
                    Toast.makeText(this, R.string.cropped, Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.crop_cancelled, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropClick(View view) {
        boolean isCropAvailable = IntentUtils.isCropAvailable(CropImageActivity.this);
        if(isCropAvailable) {
            Intent intent = IntentUtils.cropImage(CropImageActivity.this,
                    inImage,
                    Integer.valueOf(outputX.getText().toString()),
                    Integer.valueOf(outputY.getText().toString()),
                    Integer.valueOf(aspectX.getText().toString()),
                    Integer.valueOf(aspectX.getText().toString()),
                    scale.isChecked());
            startActivityForResult(intent, CROP_REQUEST_CODE);
        } else {
            Toast.makeText(CropImageActivity.this, R.string.crop_app_unavailable, Toast.LENGTH_SHORT).show();
        }
    }
}