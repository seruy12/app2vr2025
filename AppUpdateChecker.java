package net.xq.appvrplayer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import g.a;
import y.i;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppUpdateChecker {

    public interface Callback {
        void a(UpdateInfo updateInfo, Exception exc);
    }

    public static final class UpdateInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f4228a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f4229b;
        public final String c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f4230d;
        public final String e;
        public final String f;

        public UpdateInfo(boolean z, int i2, String str, String str2, String str3, String str4) {
            this.f4228a = z;
            this.f4229b = i2;
            this.c = str == null ? "" : str;
            this.f4230d = str2 == null ? "" : str2;
            this.e = str3 == null ? "" : str3;
            this.f = str4 == null ? "" : str4;
        }
    }

    public static void a(AppCompatActivity appCompatActivity, i iVar) {
        Context applicationContext = appCompatActivity.getApplicationContext();
        if (System.currentTimeMillis() - applicationContext.getSharedPreferences("AppUpdate", 0).getLong("last_check_time", 0L) < 259200000) {
            c(iVar, b(applicationContext), null);
        } else {
            new Thread(new a(applicationContext.getApplicationContext(), 4, iVar)).start();
        }
    }

    public static UpdateInfo b(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("AppUpdate", 0);
        return new UpdateInfo(sharedPreferences.getBoolean("has_update", false), sharedPreferences.getInt("version_code", 0), sharedPreferences.getString("version_name", ""), sharedPreferences.getString("url", ""), sharedPreferences.getString("url1", ""), sharedPreferences.getString("url2", ""));
    }

    public static void c(Callback callback, UpdateInfo updateInfo, Exception exc) {
        if (callback == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new v.a(callback, updateInfo, exc, 0));
    }

    public static void d(Context context, UpdateInfo updateInfo) {
        context.getSharedPreferences("AppUpdate", 0).edit().putLong("last_check_time", System.currentTimeMillis()).putBoolean("has_update", updateInfo.f4228a).putInt("version_code", updateInfo.f4229b).putString("version_name", updateInfo.c).putString("url", updateInfo.f4230d).putString("url1", updateInfo.e).putString("url2", updateInfo.f).apply();
    }
}
