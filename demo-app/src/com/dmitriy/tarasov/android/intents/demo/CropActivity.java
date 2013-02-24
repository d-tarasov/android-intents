package com.dmitriy.tarasov.android.intents.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.dmitriy.tarasov.android.intents.IntentUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Dmitriy Tarasov
 */
public class CropActivity extends Activity {

    private static final int CROP_REQUEST_CODE = 0;

    private File inImage;

    private ImageView in;
    private ImageView out;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.raw.sample);
        try {
            inImage = new File("/sdcard/tmp.jpg");
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(inImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        in = (ImageView) findViewById(R.id.in);
        out = (ImageView) findViewById(R.id.out);

        in.setImageBitmap(bmp);

        Button crop = (Button) findViewById(R.id.crop);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCropAvailable = IntentUtils.isCropAvailable(CropActivity.this);
                if(isCropAvailable) {
                    Intent intent = IntentUtils.crop(CropActivity.this, inImage);
                    startActivityForResult(intent, CROP_REQUEST_CODE);
                } else {
                    Toast.makeText(CropActivity.this, R.string.crop_app_unavailable, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CROP_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap cropped = extras.getParcelable("data");
                    out.setImageBitmap(cropped);
                    Toast.makeText(this, R.string.cropped, Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.cancelled, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}