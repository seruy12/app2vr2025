package net.xq.appvrplayer.Utils;

import android.content.SharedPreferences;
import net.xq.appvrplayer.application.MyApplication;

/* JADX INFO: loaded from: classes.dex */
public abstract class DepthOptimizationSettings {
    public static SharedPreferences a() {
        return MyApplication.f4272d.getSharedPreferences("AppSettings", 0);
    }
}
