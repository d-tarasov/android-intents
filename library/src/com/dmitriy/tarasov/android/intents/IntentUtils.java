package com.dmitriy.tarasov.android.intents;

import android.content.ComponentName;
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
     * Check that cropping application is available
     *
     * @param context Application context
     * @return true if cropping app is available
     * @see #crop(android.content.Context, java.io.File, int, int, int, int, boolean)
     */
    public static boolean isCropAvailable(Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        return IntentUtils.isIntentAvailable(context, intent);
    }

    /**
     * Crop image. Before using, crop requires especial check that differs from
     * {@link #isIntentAvailable(android.content.Context, android.content.Intent)}
     * see {@link #isCropAvailable(android.content.Context)} instead
     *
     * @param context Application context
     * @param image   Image that will be used for cropping. This image is not changed during the crop
     * @param outputX Output image width
     * @param outputY Output image height
     * @param aspectX Crop frame aspect X
     * @param aspectY Crop frame aspect Y
     * @param scale   Scale or not cropped image if output image and crop frame sizes differs
     * @return Intent with <code>data</code>-extra in <code>onActivityResult</code> which contains result as a
     *         {@link android.graphics.Bitmap}. See demo app for details
     */
    public static Intent crop(Context context, File image, int outputX, int outputY, int aspectX, int aspectY, boolean scale) {
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
