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

import android.content.Intent;

import com.dmitriy.tarasov.android.intents.IntentUtils;

/**
 * @author Dmitriy Tarasov
 */
public class OpenAudioActivity extends OpenFileActivity {

    @Override
    public Intent getOpenIntent() {
        return IntentUtils.openAudio(pathView.getText().toString());
    }
}
