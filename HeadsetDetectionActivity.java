package com.google.cardboard.sdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.cardboard.sdk.qrcode.CardboardParamsUtils;
import net.xq.appvrplayer.R;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetDetectionActivity extends AppCompatActivity {
    public static final Uri z = new Uri.Builder().scheme("cardboard").authority("v1.0.0").build();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) throws Throwable {
        super.onCreate(bundle);
        if (getIntent() != null) {
            Intent intent = getIntent();
            if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction()) && intent.getData() != null) {
                if (z.equals(intent.getData())) {
                    CardboardParamsUtils.c(getApplicationContext());
                }
                Toast.makeText(this, R.string.viewer_detected, 0).show();
            }
        }
        finish();
    }
}
