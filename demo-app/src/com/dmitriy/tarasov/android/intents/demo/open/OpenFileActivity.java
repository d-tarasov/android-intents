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

package com.dmitriy.tarasov.android.intents.demo.open;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.dmitriy.tarasov.android.intents.demo.R;

/**
 * @author Dmitriy Tarasov
 */
public abstract class OpenFileActivity extends Activity {

    private static final int PICK_FILE = 0;

    protected EditText pathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        pathView = (EditText) findViewById(R.id.path);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE && resultCode == RESULT_OK) {
            Uri file = data.getData();
            String path = file.getPath();

            pathView.setText(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void pickFileClick(View view) {
        Intent pick = IntentUtils.pickFile();
        startActivityForResult(pick, PICK_FILE);
    }

    public void openFileClick(View view) {
        startActivity(getOpenIntent());
    }

    public abstract Intent getOpenIntent();
}
