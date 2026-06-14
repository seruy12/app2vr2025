package com.google.cardboard.sdk;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.cardboard.sdk.qrcode.CardboardParamsUtils;
import net.xq.appvrplayer.R;

/* JADX INFO: loaded from: classes.dex */
public class QrCodeCaptureActivity extends AppCompatActivity {
    private native void nativeIncrementDeviceParamsChangedCount();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) throws Throwable {
        super.onCreate(bundle);
        Log.i("QrCodeCaptureActivity", "QR scanning is disabled because external scanner services are not used.");
        CardboardParamsUtils.c(getApplicationContext());
        Toast.makeText(this, R.string.viewer_detected, 0).show();
        nativeIncrementDeviceParamsChangedCount();
        finish();
    }
}
