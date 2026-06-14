package net.xq.appvrplayer.Utils;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class CardboardParamSettings {
    public static int a(int i2, int i3, int i4) {
        return Math.max(i3, Math.min(i4, i2));
    }

    public static int b(AppCompatActivity appCompatActivity, String str, int i2) {
        return appCompatActivity.getSharedPreferences("AppSettings", 0).getInt(str, i2);
    }

    public static void c(Context context, int i2, String str) {
        context.getSharedPreferences("AppSettings", 0).edit().putInt(str, i2).apply();
    }
}
