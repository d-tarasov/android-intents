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
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.dmitriy.tarasov.android.intents.demo.BaseActivity;
import com.dmitriy.tarasov.android.intents.demo.R;

/**
 * @author Dmitriy Tarasov
 */
public class PickContactActivity extends BaseActivity {

    private static final int PICK_REQ = 0;

    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contact);

        infoView = (TextView) findViewById(R.id.info);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_REQ && resultCode == RESULT_OK) {
            showInfo(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void pickFromAllClick(View view) {
        Intent pick = IntentUtils.pickContact();
        startActivityForResult(pick, PICK_REQ);
    }

    public void pickWithPhonesClick(View view) {
        Intent pick;
        if (isSupportsContactsV2()) {
            pick = IntentUtils.pickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        } else { // pre Eclair, use old contacts API
            pick = IntentUtils.pickContact(Contacts.Phones.CONTENT_TYPE);
        }
        startActivityForResult(pick, PICK_REQ);
    }

    public void pickWithEmailsClick(View view) {
        if (isSupportsContactsV2()) {
            Intent pick = IntentUtils.pickContact(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
            startActivityForResult(pick, PICK_REQ);
        } else {
            Toast.makeText(this, getString(R.string.eclair_required), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isSupportsContactsV2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
    }

    private void showInfo(Uri contact) {
        String[] projection;
        if (isSupportsContactsV2()) {
            projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
        } else {
            projection = new String[]{Contacts.People.DISPLAY_NAME};
        }

        Cursor cursor = getContentResolver().query(contact, projection, null, null, null);
        cursor.moveToFirst();
        String name;
        if (isSupportsContactsV2()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        } else {
            name = cursor.getString(cursor.getColumnIndex(Contacts.People.DISPLAY_NAME));
        }
        cursor.close();

        infoView.setText(name);
    }
}
