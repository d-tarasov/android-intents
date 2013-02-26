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

package com.dmitriy.tarasov.android.intents;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author Dmitriy Tarasov
 */
public class IntentUtils {

    /**
     * Open a browser window to the URL specified.
     *
     * @param url Target url
     */
    public static Intent openLink(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * @see #openLink(String)
     */
    public static Intent openLink(URL url) {
        return openLink(url.toString());
    }

    /**
     * Calls the entered phone number. Valid telephone numbers as defined in the IETF RFC 3966 are accepted.
     * Valid examples include the following:
     * tel:2125551212
     * tel: (212) 555 1212
     *
     * Note: This requires your application to request the following permission in your manifest:
     * <code>&lt;uses-permission android:name="android.permission.CALL_PHONE"/&gt;</code>
     *
     * @param phoneNumber Phone number
     */
    public static Intent callPhone(String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Dials (but does not actually initiate the call) the number given.
     * Telephone number normalization described for {@link #callPhone(String)} applies to dial as well.
     *
     * @param phoneNumber Phone number
     */
    public static Intent dialPhone(String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Check that cropping application is available
     *
     * @param context Application context
     * @return true if cropping app is available
     * @see #cropImage(android.content.Context, java.io.File, int, int, int, int, boolean)
     */
    public static boolean isCropAvailable(Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        return IntentUtils.isIntentAvailable(context, intent);
    }

    /**
     * Crop image. Before using, cropImage requires especial check that differs from
     * {@link #isIntentAvailable(android.content.Context, android.content.Intent)}
     * see {@link #isCropAvailable(android.content.Context)} instead
     *
     * @param context Application context
     * @param image   Image that will be used for cropping. This image is not changed during the cropImage
     * @param outputX Output image width
     * @param outputY Output image height
     * @param aspectX Crop frame aspect X
     * @param aspectY Crop frame aspect Y
     * @param scale   Scale or not cropped image if output image and cropImage frame sizes differs
     * @return Intent with <code>data</code>-extra in <code>onActivityResult</code> which contains result as a
     *         {@link android.graphics.Bitmap}. See demo app for details
     */
    public static Intent cropImage(Context context, File image, int outputX, int outputY, int aspectX, int aspectY, boolean scale) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
        ResolveInfo res = list.get(0);

        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("scale", scale);
        intent.putExtra("return-data", true);
        intent.setData(Uri.fromFile(image));

        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
        return intent;
    }

    /**
     * Call standard camera application for capturing an image
     *
     * @param file Full path to captured file
     */
    public static Intent photoCapture(String file) {
        Uri uri = Uri.fromFile(new File(file));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * Check that in the system exists application which can handle this intent
     *
     * @param context Application context
     * @param intent  Checked intent
     * @return true if intent consumer exists, false otherwise
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
