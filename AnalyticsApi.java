package net.xq.appvrplayer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.umeng.analytics.pro.bc;
import g.a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnalyticsApi {
    public static LinkedHashMap a(Context context, String str) {
        String strTrim;
        Object obj;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("EVENT_NAME", str);
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        String str2 = "";
        int i2 = 0;
        if (TextUtils.isEmpty(string) || "unknown".equalsIgnoreCase(string)) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("app2vr_analytics", 0);
            String string2 = sharedPreferences.getString("fallback_device_id", "");
            if (TextUtils.isEmpty(string2)) {
                string2 = "app-" + UUID.randomUUID().toString().replace("-", "");
                sharedPreferences.edit().putString("fallback_device_id", string2).apply();
            }
            strTrim = string2;
        } else {
            strTrim = string.trim();
        }
        linkedHashMap.put("DEVICE_ID", strTrim);
        linkedHashMap.put("REGISTER_CODE", PremiumManager.a(context).getString("activation_code", ""));
        try {
            String str3 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str3 != null) {
                str2 = str3;
            }
        } catch (Exception unused) {
        }
        linkedHashMap.put("APP_VERSION_NAME", str2);
        try {
            long longVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
            i2 = longVersionCode > 2147483647L ? Integer.MAX_VALUE : (int) longVersionCode;
        } catch (Exception unused2) {
        }
        linkedHashMap.put("APP_VERSION_CODE", String.valueOf(i2));
        linkedHashMap.put("DEVICE_NAME", Build.MANUFACTURER + " " + Build.MODEL);
        linkedHashMap.put("OS_VERSION", String.valueOf(Build.VERSION.RELEASE));
        String string3 = "official";
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), bc.f2984d).metaData;
            if (bundle != null && (obj = bundle.get("UMENG_CHANNEL")) != null && !TextUtils.isEmpty(obj.toString())) {
                string3 = obj.toString();
            }
        } catch (Exception unused3) {
        }
        linkedHashMap.put("CHANNEL", string3);
        linkedHashMap.put("LANGUAGE", Locale.getDefault().getLanguage());
        linkedHashMap.put("COUNTRY", Locale.getDefault().getCountry());
        return linkedHashMap;
    }

    public static JSONObject b(LinkedHashMap linkedHashMap) {
        String string;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://ser.pingxingyan.com:8081/apptovranalytics/track").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            sb.append('=');
            sb.append(URLEncoder.encode(entry.getValue() == null ? "" : (String) entry.getValue(), "UTF-8"));
        }
        bufferedWriter.write(sb.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        InputStream errorStream = (responseCode < 200 || responseCode >= 300) ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
        if (errorStream == null) {
            string = "{}";
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb2.append(line);
            }
            bufferedReader.close();
            string = sb2.toString();
        }
        httpURLConnection.disconnect();
        return new JSONObject(string);
    }

    public static void c(Context context, String str) {
        new Thread(new a(context, 3, str)).start();
    }
}
