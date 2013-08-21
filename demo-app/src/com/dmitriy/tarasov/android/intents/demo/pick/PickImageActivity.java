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

package com.dmitriy.tarasov.android.intents.demo.pick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.dmitriy.tarasov.android.intents.demo.BaseActivity;
import com.dmitriy.tarasov.android.intents.demo.R;

/**
 * @author Dmitriy Tarasov
 */
public class PickImageActivity extends BaseActivity {

    private static final int PICK_IMAGE_REQ = 0;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQ && resultCode == RESULT_OK) {
            Uri image = data.getData();
            imageView.setImageURI(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void pickImageClick(View view) {
        Intent pickImage = IntentUtils.pickImage();
        startActivityForResult(pickImage, PICK_IMAGE_REQ);
    }
}
