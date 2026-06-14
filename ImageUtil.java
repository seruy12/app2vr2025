package net.xq.appvrplayer.Utils;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImageUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static byte[] f4248a;

    static {
        System.loadLibrary("cardboard_jni");
        f4248a = null;
    }

    public static void a(Bitmap bitmap, Image image, Rect rect) {
        byte[] bArr;
        int width = image.getWidth();
        int height = image.getHeight();
        int iMax = Math.max(0, rect.left);
        int iMax2 = Math.max(0, rect.top);
        int iMin = Math.min(width, rect.right);
        int iMin2 = Math.min(height, rect.bottom);
        bitmap.getWidth();
        bitmap.getHeight();
        if (iMin - iMax != bitmap.getWidth()) {
            iMin = bitmap.getWidth() + iMax;
        }
        if (iMin2 - iMax2 != bitmap.getHeight()) {
            iMin2 = bitmap.getHeight() + iMax2;
        }
        int i2 = iMin - iMax;
        int i3 = iMin2 - iMax2;
        if (i2 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Invalid crop region: " + rect);
        }
        Image.Plane plane = image.getPlanes()[0];
        ByteBuffer buffer = plane.getBuffer();
        int pixelStride = plane.getPixelStride();
        int rowStride = plane.getRowStride();
        int i4 = i3 * i2 * 4;
        synchronized (ImageUtil.class) {
            try {
                byte[] bArr2 = f4248a;
                if (bArr2 == null || bArr2.length < i4) {
                    f4248a = new byte[i4 + 1024];
                }
                bArr = f4248a;
            } catch (Throwable th) {
                throw th;
            }
        }
        int i5 = 0;
        while (iMax2 < iMin2) {
            buffer.position((iMax * pixelStride) + (iMax2 * rowStride));
            int i6 = i2 * pixelStride;
            buffer.get(bArr, i5, i6);
            i5 += i6;
            iMax2++;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 0, i5);
        byteBufferWrap.rewind();
        bitmap.copyPixelsFromBuffer(byteBufferWrap);
        byteBufferWrap.clear();
    }

    public static void b(Bitmap bitmap, Image image, Rect rect) {
        Bitmap bitmap2;
        int iMax = Math.max(0, rect.left);
        int iMax2 = Math.max(0, rect.top);
        int iMin = Math.min(image.getWidth(), rect.right);
        int iMin2 = Math.min(image.getHeight(), rect.bottom);
        if (iMin - iMax != bitmap.getWidth()) {
            iMin = bitmap.getWidth() + iMax;
        }
        if (iMin2 - iMax2 != bitmap.getHeight()) {
            iMin2 = bitmap.getHeight() + iMax2;
        }
        if (iMax < 0 || iMax2 < 0 || iMin > image.getWidth() || iMin2 > image.getHeight()) {
            a(bitmap, image, rect);
            return;
        }
        Image.Plane plane = image.getPlanes()[0];
        ByteBuffer buffer = plane.getBuffer();
        if (!buffer.isDirect() || plane.getPixelStride() != 4) {
            a(bitmap, image, rect);
            return;
        }
        try {
            bitmap2 = bitmap;
            try {
                nativeCropImageToBitmap(buffer, plane.getRowStride(), plane.getPixelStride(), bitmap2, (plane.getPixelStride() * iMax) + (plane.getRowStride() * iMax2), iMin - iMax, iMin2 - iMax2);
            } catch (RuntimeException | UnsatisfiedLinkError unused) {
                a(bitmap2, image, rect);
            }
        } catch (RuntimeException | UnsatisfiedLinkError unused2) {
            bitmap2 = bitmap;
        }
    }

    private static native void nativeCropImageToBitmap(ByteBuffer byteBuffer, int i2, int i3, Bitmap bitmap, int i4, int i5, int i6);
}
