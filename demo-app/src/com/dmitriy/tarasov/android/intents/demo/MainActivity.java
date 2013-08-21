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

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dmitriy.tarasov.android.intents.demo.open.OpenAudioActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenImageActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenLinkActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenPlayStoreActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenTextActivity;
import com.dmitriy.tarasov.android.intents.demo.open.OpenVideoActivity;
import com.dmitriy.tarasov.android.intents.demo.other.CallPhoneActivity;
import com.dmitriy.tarasov.android.intents.demo.other.CapturePhotoActivity;
import com.dmitriy.tarasov.android.intents.demo.other.CropImageActivity;
import com.dmitriy.tarasov.android.intents.demo.other.DialPhoneActivity;
import com.dmitriy.tarasov.android.intents.demo.other.FindLocationActivity;
import com.dmitriy.tarasov.android.intents.demo.other.ShareTextActivity;
import com.dmitriy.tarasov.android.intents.demo.pick.PickContactActivity;
import com.dmitriy.tarasov.android.intents.demo.pick.PickFileActivity;
import com.dmitriy.tarasov.android.intents.demo.pick.PickImageActivity;
import com.dmitriy.tarasov.android.intents.demo.send.SendEmailActivity;
import com.dmitriy.tarasov.android.intents.demo.send.SendSmsActivity;
import com.dmitriy.tarasov.android.intents.demo.show.ShowLocationActivity;
import com.dmitriy.tarasov.android.intents.demo.show.ShowLocationServicesActivity;
import com.dmitriy.tarasov.android.intents.demo.show.ShowStreetViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Tarasov
 */
public class MainActivity extends ExpandableListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(fillItems());
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        GroupItem item = (GroupItem) getExpandableListAdapter().getChild(groupPosition, childPosition);
        startActivity(item.intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("About");
                builder.setView(View.inflate(this, R.layout.dialog_about, null));
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Group> fillItems() {
        List<Group> groups = new ArrayList<Group>();
        addGroup(groups, R.string.group_open);
        addGroup(groups, R.string.group_pick);
        addGroup(groups, R.string.group_send);
        addGroup(groups, R.string.group_show);
        addGroup(groups, R.string.group_other);

        addGroupItem(groups.get(0), R.string.open_play_store, R.string.open_play_store_descr, OpenPlayStoreActivity.class);
        addGroupItem(groups.get(0), R.string.open_link, R.string.open_link_descr, OpenLinkActivity.class);
        addGroupItem(groups.get(0), R.string.open_audio, R.string.open_audio_descr, OpenAudioActivity.class);
        addGroupItem(groups.get(0), R.string.open_video, R.string.open_video_descr, OpenVideoActivity.class);
        addGroupItem(groups.get(0), R.string.open_image, R.string.open_image_descr, OpenImageActivity.class);
        addGroupItem(groups.get(0), R.string.open_text, R.string.open_text_descr, OpenTextActivity.class);

        addGroupItem(groups.get(1), R.string.pick_file, R.string.pick_file_descr, PickFileActivity.class);
        addGroupItem(groups.get(1), R.string.pick_contact, R.string.pick_contact_descr, PickContactActivity.class);
        addGroupItem(groups.get(1), R.string.pick_image, R.string.pick_image_descr, PickImageActivity.class);

        addGroupItem(groups.get(2), R.string.send_sms, R.string.send_sms_descr, SendSmsActivity.class);
        addGroupItem(groups.get(2), R.string.send_email, R.string.send_email_descr, SendEmailActivity.class);

        addGroupItem(groups.get(3), R.string.show_location_services, R.string.show_location_services_descr, ShowLocationServicesActivity.class);
        addGroupItem(groups.get(3), R.string.show_street_view, R.string.show_street_view_descr, ShowStreetViewActivity.class);
        addGroupItem(groups.get(3), R.string.show_location, R.string.show_location_descr, ShowLocationActivity.class);

        addGroupItem(groups.get(4), R.string.share_text, R.string.share_text_descr, ShareTextActivity.class);
        addGroupItem(groups.get(4), R.string.capture_photo, R.string.capture_photo_descr, CapturePhotoActivity.class);
        addGroupItem(groups.get(4), R.string.crop_image, R.string.crop_image_descr, CropImageActivity.class);
        addGroupItem(groups.get(4), R.string.call_phone, R.string.call_phone_descr, CallPhoneActivity.class);
        addGroupItem(groups.get(4), R.string.dial_phone, R.string.dial_phone_descr, DialPhoneActivity.class);
        addGroupItem(groups.get(4), R.string.find_location, R.string.find_location_descr, FindLocationActivity.class);

        return groups;
    }

    private void addGroup(List<Group> groups, int titleId) {
        groups.add(new Group(titleId));
    }

    private void addGroupItem(Group group, int titleId, int descriptionId, Class<?> clazz) {
        group.items.add(new GroupItem(titleId, descriptionId, new Intent(this, clazz)));
    }

    private void initViews(List<Group> groups) {
        ExpandableListAdapter adapter = new ItemsAdapter(this, groups);
        setListAdapter(adapter);
    }

    private static class ItemsAdapter extends BaseExpandableListAdapter {

        private final Context context;
        private final List<Group> groups;

        private ItemsAdapter(Context context, List<Group> groups) {
            this.context = context;
            this.groups = groups;
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groups.get(groupPosition).items.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return groups.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition | childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                rowView = View.inflate(context, R.layout.list_item_group, null);
                GroupHolder holder = new GroupHolder();
                holder.title = (TextView) rowView.findViewById(R.id.text);
                rowView.setTag(holder);
            }

            Group group = (Group) getGroup(groupPosition);
            GroupHolder holder = (GroupHolder) rowView.getTag();
            holder.title.setText(group.title);
            return rowView;

        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                rowView = View.inflate(context, android.R.layout.simple_list_item_2, null);
                GroupItemHolder holder = new GroupItemHolder();
                holder.title = (TextView) rowView.findViewById(android.R.id.text1);
                holder.description = (TextView) rowView.findViewById(android.R.id.text2);
                rowView.setTag(holder);
            }

            GroupItem item = (GroupItem) getChild(groupPosition, childPosition);
            GroupItemHolder holder = (GroupItemHolder) rowView.getTag();
            holder.title.setText(item.text);
            holder.description.setText(item.description);
            return rowView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private static class GroupHolder {
            TextView title;
        }

        private static class GroupItemHolder {
            TextView title;
            TextView description;
        }
    }

    private class GroupItem {

        private GroupItem(int textId, int descriptionId, Intent intent) {
            this.text = getString(textId);
            this.description = getString(descriptionId);
            this.intent = intent;
        }

        String text;
        String description;
        Intent intent;

    }

    private class Group {
        private Group(int titleId) {
            this.title = getString(titleId);
            items = new ArrayList<GroupItem>();
        }

        String title;
        List<GroupItem> items;
    }
}