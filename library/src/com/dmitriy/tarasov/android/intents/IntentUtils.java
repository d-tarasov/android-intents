package com.dmitriy.tarasov.android.intents;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.List;

/**
 * @author Dmitriy Tarasov
 */
public class IntentUtils {

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
