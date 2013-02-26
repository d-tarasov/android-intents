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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Tarasov
 */
public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(fillItems());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListItem item = (ListItem) getListAdapter().getItem(position);
        startActivity(item.intent);
    }

    private List<ListItem> fillItems() {
        List<ListItem> items = new ArrayList<ListItem>();
        items.add(new ListItem(R.string.capture_photo, new Intent(this, CapturePhotoActivity.class)));
        items.add(new ListItem(R.string.crop_image, new Intent(this, CropImageActivity.class)));
        items.add(new ListItem(R.string.call_phone, new Intent(this, CallPhoneActivity.class)));
        items.add(new ListItem(R.string.dial_phone, new Intent(this, DialPhoneActivity.class)));
        return items;
    }

    private void initViews(List<ListItem> listItems) {
        // TODO two rows list item with description at second row
        ListAdapter adapter = new ArrayAdapter<ListItem>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listItems);
        setListAdapter(adapter);
    }

    private class ListItem {

        private ListItem(int textId, Intent intent) {
            this.text = getString(textId);
            this.intent = intent;
        }

        String text;
        Intent intent;

        @Override
        public String toString() {
            return text;
        }
    }
}