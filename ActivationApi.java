package net.xq.appvrplayer.Utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivationApi {
    public static LinkedHashMap a(Context context) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null || string.trim().isEmpty()) {
            string = "unknown";
        }
        linkedHashMap.put("DEVICE_ID", string);
        String str = "";
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                str = str2;
            }
        } catch (Exception unused) {
        }
        linkedHashMap.put("APP_VERSION", str);
        linkedHashMap.put("DEVICE_NAME", Build.MANUFACTURER + " " + Build.MODEL);
        return linkedHashMap;
    }

    public static JSONObject b(String str, LinkedHashMap linkedHashMap) throws IOException {
        String string;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://ser.pingxingyan.com:8081/".concat(str)).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(12000);
        httpURLConnection.setReadTimeout(12000);
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
}
