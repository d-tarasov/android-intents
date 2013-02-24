package com.dmitriy.tarasov.android.intents.demo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.dmitriy.tarasov.android.intents.IntentUtils;

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
        items.add(new ListItem(R.string.crop_image, new Intent(this, CropActivity.class)));
        return items;
    }

    private void initViews(List<ListItem> listItems) {
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