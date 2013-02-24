package com.dmitriy.tarasov.android.intents.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.dmitriy.tarasov.android.intents.IntentUtils;

/**
 * @author Dmitriy Tarasov
 */
public class CapturePhotoActivity extends Activity {

    private static final int PHOTO_REQUEST_CODE = 0;

    private EditText fileName;
    private ImageView capturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photo);

        fileName = (EditText) findViewById(R.id.file_name);
        capturedImage = (ImageView) findViewById(R.id.captured_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // TODO draw captured image
                Toast.makeText(this, R.string.captured, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.cancelled, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void captureClick(View view) {
        Intent captureIntent = IntentUtils.photoCapture(fileName.getText().toString());
        startActivityForResult(captureIntent, PHOTO_REQUEST_CODE);
    }
}
