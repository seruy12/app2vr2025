package net.xq.appvrplayer.Utils;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class PaywallExperimentConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f4249a;

    public static String a() {
        Context context = f4249a;
        String str = null;
        if (context != null && (context.getApplicationInfo().flags & 2) != 0) {
            String string = f4249a.getSharedPreferences("paywall_debug_overrides", 0).getString("debug_paywall_variant", "");
            if ("control".equals(string) || "native_free_game_paid".equals(string)) {
                str = string;
            }
        }
        return str != null ? str : "control";
    }

    public static boolean b() {
        return "native_free_game_paid".equals(a());
    }

    public static boolean c() {
        return "native_free_game_paid".equals(a());
    }
}
