package net.xq.appvrplayer.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class PremiumManager {
    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("premium_prefs", 0);
    }

    public static boolean b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("premium_prefs", 0);
        return sharedPreferences.getBoolean("is_premium", false) && "activation_code".equals(sharedPreferences.getString("source", ""));
    }

    public static void c(Context context, String str, String str2, String str3, String str4, int i2, int i3) {
        SharedPreferences.Editor editorPutString = context.getSharedPreferences("premium_prefs", 0).edit().putBoolean("is_premium", true).putString("plan_id", str).putString("source", "activation_code").putString("license_token", str2);
        if (str3 == null) {
            str3 = "";
        }
        editorPutString.putString("activation_code", str3).putString("expire_time", str4).putInt("remaining_effective_times", i3).putInt("check_interval_days", i2 > 0 ? Math.min(i2, 14) : 14).putLong("last_check_time", System.currentTimeMillis()).apply();
    }
}
