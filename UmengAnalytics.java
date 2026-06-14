package net.xq.appvrplayer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.bc;
import com.umeng.commonsdk.UMConfigure;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class UmengAnalytics {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f4252a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f4253b = false;

    public static String a(Context context, String str) {
        Object obj;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), bc.f2984d).metaData;
            if (bundle != null && (obj = bundle.get(str)) != null) {
                return obj.toString().trim();
            }
            return "";
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("UmengAnalytics", "Failed to read manifest meta-data: ".concat(str), e);
            return "";
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return context.getApplicationContext().getSharedPreferences("umeng_analytics", 0).getBoolean("privacy_policy_granted", false);
    }

    public static void c(Context context) {
        Context applicationContext = context.getApplicationContext();
        String strA = a(applicationContext, "UMENG_APPKEY");
        if (TextUtils.isEmpty(strA)) {
            Log.w("UmengAnalytics", "Umeng analytics disabled: UMENG_APPKEY is empty.");
            return;
        }
        String strA2 = a(applicationContext, "UMENG_CHANNEL");
        if (TextUtils.isEmpty(strA2)) {
            strA2 = "official";
        }
        UMConfigure.setLogEnabled(false);
        if (!f4252a) {
            UMConfigure.preInit(applicationContext, strA, strA2);
            f4252a = true;
        }
        if (!b(applicationContext) || f4253b) {
            return;
        }
        UMConfigure.submitPolicyGrantResult(applicationContext, true);
        UMConfigure.init(applicationContext, strA, strA2, 1, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        MobclickAgent.setCatchUncaughtExceptions(true);
        f4253b = true;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("umeng_analytics", 0);
        if (sharedPreferences.getBoolean("first_open_reported", false)) {
            return;
        }
        HashMap map = new HashMap();
        map.put("channel", strA2);
        MobclickAgent.onEvent(applicationContext, "app_first_open", map);
        sharedPreferences.edit().putBoolean("first_open_reported", true).apply();
    }
}
