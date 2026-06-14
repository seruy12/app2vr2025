package net.xq.appvrplayer.Utils;

/* JADX INFO: loaded from: classes.dex */
public class DepthConvergenceEstimator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f4231a = new int[64];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4232b = false;
    public float c = 0.5f;

    public final float a(int i2, float f) {
        int iRound = Math.round(i2 * f);
        int iMax = Math.max(0, iRound);
        int i3 = 0;
        for (int i4 = 0; i4 < 64; i4++) {
            i3 += this.f4231a[i4];
            if (i3 >= iMax) {
                return i4 / 63.0f;
            }
        }
        return 1.0f;
    }
}
