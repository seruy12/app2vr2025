package com.google.cardboard.sdk.qrcode;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.google.cardboard.sdk.UsedByNative;
import com.umeng.analytics.pro.bv;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class CardboardParamsUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Uri f2706a = new Uri.Builder().scheme("https").authority("g.co").appendEncodedPath("cardboard").build();

    public static Uri a(Uri uri) throws IOException {
        HttpURLConnection httpURLConnection;
        URLConnection uRLConnectionOpenConnection;
        int i2 = 0;
        while (uri != null && !f2706a.equals(uri) && (!"https".equals(uri.getScheme()) || !"google.com".equals(uri.getAuthority()) || !"/cardboard/cfg".equals(uri.getPath()))) {
            if (i2 >= 5) {
                Log.d("CardboardParamsUtils", "Exceeding the number of maximum redirects: 5");
                return null;
            }
            try {
                uRLConnectionOpenConnection = new URL(uri.buildUpon().scheme("https").build().toString()).openConnection();
            } catch (MalformedURLException e) {
                Log.w(bv.av, e.toString());
                httpURLConnection = null;
            }
            if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                Log.w(bv.av, "Expected HttpURLConnection");
                throw new IllegalArgumentException("Expected HttpURLConnection");
            }
            httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            if (httpURLConnection == null) {
                uri = null;
            } else {
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setDoInput(false);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setRequestProperty("Accept-Encoding", "");
                try {
                    httpURLConnection.setRequestMethod("HEAD");
                } catch (ProtocolException e2) {
                    Log.w("CardboardParamsUtils", e2.toString());
                }
                try {
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    Log.i("CardboardParamsUtils", "Response code: " + responseCode);
                    if (responseCode == 301 || responseCode == 302) {
                        String headerField = httpURLConnection.getHeaderField("Location");
                        if (headerField == null) {
                            Log.d("CardboardParamsUtils", "Returning null because of null location.");
                        } else {
                            Log.i("CardboardParamsUtils", "Location: ".concat(headerField));
                            Uri uri2 = Uri.parse(headerField.replaceFirst("http://", "https://"));
                            if (uri2 != null && uri2.compareTo(uri) != 0) {
                                Log.i("CardboardParamsUtils", "Param URI redirect to " + uri2);
                                httpURLConnection.disconnect();
                                uri = uri2;
                            }
                            Log.d("CardboardParamsUtils", "Returning null because of wrong redirect URI.");
                        }
                        httpURLConnection.disconnect();
                    } else {
                        httpURLConnection.disconnect();
                    }
                    uri = null;
                } catch (Throwable th) {
                    httpURLConnection.disconnect();
                    throw th;
                }
            }
            i2++;
        }
        return uri;
    }

    public static File b(Context context, int i2) {
        File file = new File(i2 == 1 ? context.getFilesDir() : Environment.getExternalStorageDirectory(), "Cardboard");
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            throw new IllegalStateException(file + " already exists as a file, but is expected to be a directory.");
        }
        return new File(file, "current_device_params");
    }

    public static void c(Context context) throws Throwable {
        byte[] byteArray = a.a.a().toByteArray();
        Log.d("CardboardParamsUtils", "Writing device parameters to scoped storage.");
        boolean zD = d(byteArray, context);
        StringBuilder sb = new StringBuilder("Could ");
        sb.append(!zD ? "not " : "");
        sb.append("save Cardboard V1 device parameters.");
        Log.d("CardboardParamsUtils", sb.toString());
    }

    public static boolean d(byte[] bArr, Context context) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(b(context, 1)));
                    try {
                        boolean zE = e(bArr, bufferedOutputStream);
                        bufferedOutputStream.close();
                        return zE;
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedOutputStream2 = bufferedOutputStream;
                        Log.e("CardboardParamsUtils", "Parameters file not found for writing: " + e);
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        return false;
                    } catch (IllegalStateException e2) {
                        e = e2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        Log.w("CardboardParamsUtils", "Error writing parameters: " + e);
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                } catch (IllegalStateException e4) {
                    e = e4;
                }
            } catch (IOException unused2) {
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = bufferedOutputStream2;
        }
    }

    public static boolean e(byte[] bArr, BufferedOutputStream bufferedOutputStream) {
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
            byteBufferAllocate.putInt(894990891);
            byteBufferAllocate.putInt(bArr.length);
            bufferedOutputStream.write(byteBufferAllocate.array());
            bufferedOutputStream.write(bArr);
            return true;
        } catch (IOException e) {
            Log.w("CardboardParamsUtils", "Error writing parameters: " + e);
            return false;
        }
    }

    public static byte[] f(BufferedInputStream bufferedInputStream) {
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
            if (bufferedInputStream.read(byteBufferAllocate.array(), 0, byteBufferAllocate.array().length) == -1) {
                Log.e("CardboardParamsUtils", "Error parsing param record: end of stream.");
                return null;
            }
            int i2 = byteBufferAllocate.getInt();
            int i3 = byteBufferAllocate.getInt();
            if (i2 != 894990891) {
                Log.e("CardboardParamsUtils", "Error parsing param record: incorrect sentinel.");
                return null;
            }
            byte[] bArr = new byte[i3];
            if (bufferedInputStream.read(bArr, 0, i3) != -1) {
                return bArr;
            }
            Log.e("CardboardParamsUtils", "Error parsing param record: end of stream.");
            return null;
        } catch (IOException e) {
            Log.w("CardboardParamsUtils", "Error reading parameters: " + e);
            return null;
        }
    }

    public static byte[] g(Context context, int i2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        byte[] bArrF = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(b(context, i2)));
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
            }
            try {
                bArrF = f(bufferedInputStream);
                try {
                    bufferedInputStream.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                try {
                    throw th;
                } catch (FileNotFoundException e) {
                    Log.d("CardboardParamsUtils", "Parameters file not found for reading: " + e);
                }
            }
        } catch (IllegalStateException e2) {
            Log.w("CardboardParamsUtils", "Error reading parameters: " + e2);
        }
        return bArrF;
    }

    @UsedByNative
    public static byte[] readDeviceParams(Context context) throws Throwable {
        Log.d("CardboardParamsUtils", "Reading device parameters from both scoped and external storage.");
        byte[] bArrG = g(context, 2);
        byte[] bArrG2 = g(context, 1);
        if (bArrG == null || bArrG2 != null) {
            return bArrG2;
        }
        Log.d("CardboardParamsUtils", "About to copy external device parameters to scoped storage.");
        if (!d(bArrG, context)) {
            Log.e("CardboardParamsUtils", "Error writing device parameters to scoped storage.");
        }
        return bArrG;
    }

    @UsedByNative
    public static void saveParamsFromUri(byte[] bArr, Context context) throws Throwable {
        a aVar;
        byte[] bArrDecode;
        String str = new String(bArr);
        Uri uri = Uri.parse(str);
        if (uri == null) {
            Log.e("CardboardParamsUtils", "Error when parsing URI: " + uri);
            aVar = new a(1, null);
        } else {
            if (uri.getScheme() == null) {
                uri = Uri.parse("https://" + uri);
            } else if (uri.getScheme().equals("http")) {
                uri = Uri.parse(uri.toString().replaceFirst("http://", "https://"));
            }
            try {
                Log.d("CardboardParamsUtils", "Following redirects for original URI: " + uri);
                Uri uriA = a(uri);
                if (uriA == null) {
                    Log.e("CardboardParamsUtils", "Error when following URI redirects");
                    aVar = new a(1, null);
                } else {
                    if (f2706a.equals(uriA)) {
                        bArrDecode = a.a.a().toByteArray();
                    } else {
                        if ("https".equals(uriA.getScheme()) && "google.com".equals(uriA.getAuthority()) && "/cardboard/cfg".equals(uriA.getPath())) {
                            String queryParameter = uriA.getQueryParameter(bv.ax);
                            if (queryParameter == null) {
                                Log.w("CardboardParamsUtils", "No Cardboard parameters in URI.");
                            } else {
                                try {
                                    bArrDecode = Base64.decode(queryParameter, 11);
                                } catch (Exception e) {
                                    Log.w("CardboardParamsUtils", "Parsing Cardboard parameters from URI failed: " + e);
                                    bArrDecode = null;
                                }
                            }
                        } else {
                            Log.w("CardboardParamsUtils", String.format("URI \"%s\" not recognized as Cardboard viewer.", uriA));
                        }
                        bArrDecode = null;
                    }
                    if (bArrDecode == null) {
                        Log.e("CardboardParamsUtils", "Error when parsing device parameters from URI query string: " + uriA);
                        aVar = new a(1, null);
                    } else {
                        aVar = new a(0, bArrDecode);
                    }
                }
            } catch (IOException e2) {
                Log.w("CardboardParamsUtils", "Error while following URL redirect " + e2);
                aVar = new a(2, null);
            }
        }
        if (aVar.f2707a != 0) {
            Log.e("CardboardParamsUtils", "Error when trying to get the Cardboard device params from URI: ".concat(str));
            return;
        }
        Log.d("CardboardParamsUtils", "Writing device parameters to scoped storage.");
        boolean zD = d(aVar.f2708b, context);
        StringBuilder sb = new StringBuilder("Could ");
        sb.append(!zD ? "not " : "");
        sb.append("save Cardboard device parameters.");
        Log.d("CardboardParamsUtils", sb.toString());
    }
}
