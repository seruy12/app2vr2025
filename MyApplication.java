package net.xq.appvrplayer.application;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.e;
import java.util.concurrent.atomic.AtomicBoolean;
import net.xq.appvrplayer.FloatingBall.FloatingBallService;
import net.xq.appvrplayer.FloatingWindow.FloatingPlayerAccessibilityService;
import net.xq.appvrplayer.FloatingWindow.FloatingPlayerViewService;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;
import net.xq.appvrplayer.Utils.DepthUtil;
import net.xq.appvrplayer.Utils.PaywallExperimentConfig;
import net.xq.appvrplayer.Utils.PremiumManager;
import net.xq.appvrplayer.Utils.SettingTaskInfo;
import net.xq.appvrplayer.Utils.UmengAnalytics;
import x.a;

/* JADX INFO: loaded from: classes.dex */
public class MyApplication extends Application {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Context f4272d;
    public static DepthUtil e;
    public static SettingTaskInfo f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static MyApplication f4273g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4274a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f4275b = new Object();
    public final AtomicBoolean c = new AtomicBoolean(false);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class MACHINE_STATUS {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final MACHINE_STATUS f4276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final MACHINE_STATUS f4277b;
        public static final MACHINE_STATUS c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final /* synthetic */ MACHINE_STATUS[] f4278d;

        static {
            MACHINE_STATUS machine_status = new MACHINE_STATUS("NONE", 0);
            f4276a = machine_status;
            MACHINE_STATUS machine_status2 = new MACHINE_STATUS("LOW_OS", 1);
            MACHINE_STATUS machine_status3 = new MACHINE_STATUS("NO_FLOATING_PERMISSION", 2);
            f4277b = machine_status3;
            MACHINE_STATUS machine_status4 = new MACHINE_STATUS("NO_FLOATING_BATTERY", 3);
            c = machine_status4;
            f4278d = new MACHINE_STATUS[]{machine_status, machine_status2, machine_status3, machine_status4};
        }

        public static MACHINE_STATUS valueOf(String str) {
            return (MACHINE_STATUS) Enum.valueOf(MACHINE_STATUS.class, str);
        }

        public static MACHINE_STATUS[] values() {
            return (MACHINE_STATUS[]) f4278d.clone();
        }
    }

    public static boolean a() {
        return f4272d.getSharedPreferences("AppSettings", 0).getBoolean("UseGyroscope ", false);
    }

    public static boolean b() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) f4272d.getSystemService("accessibility");
        if (accessibilityManager != null) {
            String name = FloatingPlayerAccessibilityService.class.getName();
            for (AccessibilityServiceInfo accessibilityServiceInfo : accessibilityManager.getEnabledAccessibilityServiceList(-1)) {
                if (accessibilityServiceInfo != null && accessibilityServiceInfo.getResolveInfo() != null && accessibilityServiceInfo.getResolveInfo().serviceInfo != null) {
                    String strG = accessibilityServiceInfo.getResolveInfo().serviceInfo.name;
                    if (strG != null && strG.startsWith(".")) {
                        strG = e.g(new StringBuilder(), accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, strG);
                    } else if (strG != null && !strG.contains(".")) {
                        strG = accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName + "." + strG;
                    }
                    if (name.equals(strG)) {
                        return true;
                    }
                }
            }
        }
        String string = Settings.Secure.getString(f4272d.getContentResolver(), "enabled_accessibility_services");
        if (string != null && !string.isEmpty()) {
            String strFlattenToString = new ComponentName(f4272d, (Class<?>) FloatingPlayerAccessibilityService.class).flattenToString();
            for (String str : string.split(":")) {
                if (strFlattenToString.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean c() {
        return "game".equals(f4272d.getSharedPreferences("ControlModePrefs", 0).getString("selected_control_mode", "viewing")) && (!PaywallExperimentConfig.b() || PremiumManager.a(f4272d).getBoolean("is_premium", false));
    }

    public static boolean d(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f2 = displayMetrics.widthPixels;
        float f3 = displayMetrics.density;
        return Math.min(f2 / f3, ((float) displayMetrics.heightPixels) / f3) >= 600.0f;
    }

    public static void e(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public static void f() {
        f4273g.c.set(false);
        if (c() && b()) {
            FloatingWindowManager.f4197g = true;
            new Handler(Looper.getMainLooper()).post(new a(25, 0));
        } else {
            FloatingWindowManager.f4197g = false;
            f4272d.startService(new Intent(f4272d, (Class<?>) FloatingPlayerViewService.class));
        }
        f4272d.startService(new Intent(f4272d, (Class<?>) FloatingBallService.class));
    }

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        f4273g = this;
        f4272d = getApplicationContext();
        e = new DepthUtil();
        SettingTaskInfo settingTaskInfo = new SettingTaskInfo();
        settingTaskInfo.f4250a = false;
        settingTaskInfo.c = 1.0f;
        f = settingTaskInfo;
        PaywallExperimentConfig.f4249a = getApplicationContext();
        UmengAnalytics.c(this);
    }
}
