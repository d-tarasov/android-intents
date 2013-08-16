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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmitriy.tarasov.android.intents.demo.open.OpenAudioActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenImageActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenLinkActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenPlayStoreActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenTextActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenVideoActivity;
import com.dmitriy.tarasov.android.intents.demo.pick.PickContactActivity;
import com.dmitriy.tarasov.android.intents.demo.pick.PickFileActivity;

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
        addListItem(items, R.string.share_text, R.string.share_text_descr, ShareTextActivity.class);
        addListItem(items, R.string.send_sms, R.string.send_sms_descr, SendSmsActivity.class);
        addListItem(items, R.string.send_email, R.string.send_email_descr, SendEmailActivity.class);
        addListItem(items, R.string.capture_photo, R.string.capture_photo_descr, CapturePhotoActivity.class);
        addListItem(items, R.string.crop_image, R.string.crop_image_descr, CropImageActivity.class);
        addListItem(items, R.string.call_phone, R.string.call_phone_descr, CallPhoneActivity.class);
        addListItem(items, R.string.dial_phone, R.string.dial_phone_descr, DialPhoneActivity.class);
        addListItem(items, R.string.open_play_store, R.string.open_play_store_descr, OpenPlayStoreActivity.class);
        addListItem(items, R.string.open_link, R.string.open_link_descr, OpenLinkActivity.class);
        addListItem(items, R.string.open_audio, R.string.open_audio_descr, OpenAudioActivity.class);
        addListItem(items, R.string.open_video, R.string.open_video_descr, OpenVideoActivity.class);
        addListItem(items, R.string.open_image, R.string.open_image_descr, OpenImageActivity.class);
        addListItem(items, R.string.open_text, R.string.open_text_descr, OpenTextActivity.class);
        addListItem(items, R.string.pick_file, R.string.pick_file_descr, PickFileActivity.class);
        addListItem(items, R.string.pick_contact, R.string.pick_contact_descr, PickContactActivity.class);
        addListItem(items, R.string.show_street_view, R.string.show_street_view_descr, ShowStreetViewActivity.class);
        addListItem(items, R.string.show_location, R.string.show_location_descr, ShowLocationActivity.class);
        addListItem(items, R.string.find_location, R.string.find_location_descr, FindLocationActivity.class);
        return items;
    }

    private void addListItem(List<ListItem> list, int titleId, int descriptionId, Class<?> clazz) {
        list.add(new ListItem(titleId, descriptionId, new Intent(this, clazz)));
    }

    private void initViews(List<ListItem> listItems) {
        ListAdapter adapter = new ItemsAdapter(this, listItems);
        setListAdapter(adapter);
    }

    private static class ItemsAdapter extends BaseAdapter {

        private final Context context;
        private final List<ListItem> items;

        private ItemsAdapter(Context context, List<ListItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                rowView = View.inflate(context, android.R.layout.simple_list_item_2, null);
                ViewHolder holder = new ViewHolder();
                holder.title = (TextView) rowView.findViewById(android.R.id.text1);
                holder.description = (TextView) rowView.findViewById(android.R.id.text2);
                rowView.setTag(holder);
            }

            ListItem item = (ListItem) getItem(position);
            ViewHolder holder = (ViewHolder) rowView.getTag();
            holder.title.setText(item.text);
            holder.description.setText(item.description);
            return rowView;
        }

        private static class ViewHolder {
            TextView title;
            TextView description;
        }
    }

    private class ListItem {

        private ListItem(int textId, int descriptionId, Intent intent) {
            this.text = getString(textId);
            this.description = getString(descriptionId);
            this.intent = intent;
        }

        String text;
        String description;
        Intent intent;

    }
}