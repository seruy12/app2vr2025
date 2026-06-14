package net.xq.appvrplayer.Utils;

import com.umeng.analytics.pro.bc;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DepthPostProcessor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4234b;
    public boolean c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float[] f4235d;
    public float[] e;
    public float[] f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f4236g;
    public float[] h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f4237i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float[] f4238j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float[] f4239k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f4240l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4241m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4242n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4243o;

    public static final class Range {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f4244a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4245b;

        public Range(float f, float f2) {
            this.f4244a = f;
            this.f4245b = f2;
        }
    }

    public static float b(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    public static void e(float[] fArr, int i2, int i3, float[] fArr2) {
        int[] iArr = {21, 31, 21, 31, 48, 31, 21, 31, 21};
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                float f = 0.0f;
                int i6 = 0;
                for (int i7 = -1; i7 <= 1; i7++) {
                    int i8 = i4 + i7;
                    int i9 = i3 - 1;
                    if (i8 < 0) {
                        i8 = 0;
                    } else if (i8 > i9) {
                        i8 = i9;
                    }
                    int i10 = i8 * i2;
                    int i11 = -1;
                    while (i11 <= 1) {
                        int i12 = i5 + i11;
                        int i13 = i2 - 1;
                        if (i12 < 0) {
                            i12 = 0;
                        } else if (i12 > i13) {
                            i12 = i13;
                        }
                        f += fArr[i12 + i10] * iArr[i6];
                        i11++;
                        i6++;
                    }
                }
                fArr2[(i4 * i2) + i5] = f / 256.0f;
            }
        }
    }

    public static void f(float[] fArr, int i2, int i3, float[] fArr2, int i4) {
        int i5 = i4 / 2;
        for (int i6 = 0; i6 < i3; i6++) {
            for (int i7 = 0; i7 < i2; i7++) {
                float f = -3.4028235E38f;
                for (int i8 = -i5; i8 <= i5; i8++) {
                    int i9 = i6 + i8;
                    int i10 = i3 - 1;
                    if (i9 < 0) {
                        i9 = 0;
                    } else if (i9 > i10) {
                        i9 = i10;
                    }
                    int i11 = i9 * i2;
                    for (int i12 = -1; i12 <= 1; i12++) {
                        int i13 = i7 + i12;
                        int i14 = i2 - 1;
                        if (i13 < 0) {
                            i13 = 0;
                        } else if (i13 > i14) {
                            i13 = i14;
                        }
                        float f2 = fArr[i13 + i11];
                        if (f2 > f) {
                            f = f2;
                        }
                    }
                }
                fArr2[(i6 * i2) + i7] = f;
            }
        }
    }

    public static void g(float[] fArr, float[] fArr2, float[] fArr3, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            float f = fArr3[i3];
            fArr[i3] = (fArr2[i3] * f) + ((1.0f - f) * fArr[i3]);
        }
    }

    public static float i(float f, float f2, float f3) {
        float fB = b((f3 - f) / (f2 - f));
        return (3.0f - (fB * 2.0f)) * fB * fB;
    }

    public final void a(float[] fArr, int i2, int i3) {
        float fAbs;
        float[] fArr2 = this.f4236g;
        int i4 = i2 * i3;
        int i5 = 0;
        int i6 = 0;
        float f = 0.0f;
        while (i6 < i3) {
            int i7 = i5;
            while (i7 < i2) {
                int iMax = Math.max(i5, i6 - 1);
                float f2 = -3.4028235E38f;
                float f3 = Float.MAX_VALUE;
                while (iMax <= Math.min(i3 - 1, i6 + 1)) {
                    int i8 = iMax * i2;
                    for (int iMax2 = Math.max(i5, i7 - 1); iMax2 <= Math.min(i2 - 1, i7 + 1); iMax2++) {
                        float f4 = fArr[i8 + iMax2];
                        if (f4 < f3) {
                            f3 = f4;
                        }
                        if (f4 > f2) {
                            f2 = f4;
                        }
                    }
                    iMax++;
                    i5 = 0;
                }
                float f5 = f2 - f3;
                fArr2[(i6 * i2) + i7] = f5;
                f += f5;
                i7++;
                i5 = 0;
            }
            i6++;
            i5 = 0;
        }
        float f6 = i4;
        float f7 = f / f6;
        float f8 = 0.0f;
        for (int i9 = 0; i9 < i4; i9++) {
            float f9 = fArr2[i9] - f7;
            f8 += f9 * f9;
        }
        float fSqrt = (float) Math.sqrt(f8 / f6);
        float f10 = -3.4028235E38f;
        float f11 = Float.MAX_VALUE;
        for (int i10 = 0; i10 < i4; i10++) {
            float f12 = (fArr2[i10] - f7) / (1.0E-6f + fSqrt);
            if (f12 < -3.0f) {
                f12 = -3.0f;
            }
            if (f12 > 3.0f) {
                f12 = 3.0f;
            }
            fArr2[i10] = f12;
            if (f12 < f11) {
                f11 = f12;
            }
            if (f12 > f10) {
                f10 = f12;
            }
        }
        float f13 = f10 - f11;
        if (f13 <= 1.0E-6f) {
            fAbs = 0.0f;
            Arrays.fill(fArr2, 0, i4, 0.0f);
        } else {
            fAbs = 0.0f;
            for (int i11 = 0; i11 < i4; i11++) {
                fArr2[i11] = b((fArr2[i11] - f11) / f13);
            }
        }
        float[] fArr3 = this.f4236g;
        float[] fArr4 = this.h;
        if (this.f4243o) {
            for (int i12 = 0; i12 < i4; i12++) {
                float[] fArr5 = this.f4237i;
                float f14 = (fArr3[i12] * 0.25f) + (fArr5[i12] * 0.75f);
                fArr4[i12] = f14;
                fArr5[i12] = f14;
            }
        } else {
            System.arraycopy(fArr3, 0, fArr4, 0, i4);
            System.arraycopy(fArr3, 0, this.f4237i, 0, i4);
        }
        e(fArr, i2, i3, this.e);
        f(this.e, i2, i3, this.f, 3);
        g(fArr, this.f, this.h, i4);
        e(fArr, i2, i3, this.e);
        f(this.e, i2, i3, this.f, 1);
        g(fArr, this.f, this.h, i4);
        e(fArr, i2, i3, this.e);
        f(this.e, i2, i3, this.f, 1);
        g(fArr, this.f, this.h, i4);
        float[] fArr6 = this.h;
        if (!this.f4243o) {
            System.arraycopy(fArr, 0, this.f4238j, 0, i4);
            this.f4243o = true;
            return;
        }
        for (int i13 = 0; i13 < i4; i13++) {
            fAbs += Math.abs(fArr[i13] - this.f4238j[i13]);
        }
        if (fAbs / f6 > 0.18f) {
            System.arraycopy(fArr, 0, this.f4238j, 0, i4);
            return;
        }
        for (int i14 = 0; i14 < i4; i14++) {
            float fB = b(fArr6[i14]);
            if (fB <= 0.01f) {
                this.f4238j[i14] = fArr[i14];
            } else {
                float fI = fB * 0.65f * (1.0f - i(0.03f, 0.12f, Math.abs(fArr[i14] - this.f4238j[i14])));
                float f15 = (1.0f - fI) * fArr[i14];
                float[] fArr7 = this.f4238j;
                float f16 = (fArr7[i14] * fI) + f15;
                fArr[i14] = f16;
                fArr7[i14] = f16;
            }
        }
    }

    public final void c(int i2) {
        float[] fArr = this.f4235d;
        if (fArr == null || fArr.length != i2) {
            this.f4235d = new float[i2];
            this.e = new float[i2];
            this.f = new float[i2];
            this.f4236g = new float[i2];
            this.h = new float[i2];
            this.f4237i = new float[i2];
            this.f4238j = new float[i2];
            this.f4239k = new float[i2];
            this.f4241m = 0;
            this.f4242n = false;
            this.f4243o = false;
        }
        if (this.f4240l == null) {
            this.f4240l = new int[bc.e];
        }
    }

    public final Range d(float[] fArr, int i2) {
        int i3;
        float f = Float.MAX_VALUE;
        float f2 = -3.4028235E38f;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            float f3 = fArr[i5];
            if (!Float.isNaN(f3) && !Float.isInfinite(f3)) {
                if (f3 < f) {
                    f = f3;
                }
                if (f3 > f2) {
                    f2 = f3;
                }
            }
        }
        if (f2 <= f) {
            return new Range(0.0f, 1.0f);
        }
        if (!DepthOptimizationSettings.a().getBoolean("DepthOptEmaNormalize", false)) {
            return new Range(f, f2);
        }
        Arrays.fill(this.f4240l, 0);
        float f4 = f2 - f;
        float f5 = 255.0f / f4;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i6 >= i2) {
                break;
            }
            float f6 = fArr[i6];
            if (!Float.isNaN(f6) && !Float.isInfinite(f6)) {
                int i8 = (int) ((f6 - f) * f5);
                if (i8 < 0) {
                    i8 = 0;
                }
                i3 = i8 < 256 ? i8 : 255;
                int[] iArr = this.f4240l;
                iArr[i3] = iArr[i3] + 1;
                i7++;
            }
            i6++;
        }
        if (i7 <= 0) {
            return new Range(f, f2);
        }
        float f7 = i7;
        int iMax = Math.max(0, Math.round(0.01f * f7));
        int iMax2 = Math.max(0, Math.round(f7 * 0.99f));
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i9 >= 256) {
                i9 = 0;
                break;
            }
            i10 += this.f4240l[i9];
            if (i10 >= iMax) {
                break;
            }
            i9++;
        }
        int i11 = 0;
        while (true) {
            if (i4 >= 256) {
                break;
            }
            i11 += this.f4240l[i4];
            if (i11 >= iMax2) {
                i3 = i4;
                break;
            }
            i4++;
        }
        float f8 = ((i9 / 255.0f) * f4) + f;
        float f9 = ((i3 / 255.0f) * f4) + f;
        return f9 <= f8 ? new Range(f, f2) : new Range(f8, f9);
    }

    public final synchronized float[] h(float[] fArr, int i2, int i3) {
        if (i2 > 0 && i3 > 0) {
            try {
                int i4 = i2 * i3;
                if (fArr.length >= i4) {
                    c(i4);
                    Range rangeD = d(fArr, i4);
                    if (DepthOptimizationSettings.a().getBoolean("DepthOptEmaNormalize", false)) {
                        if (this.c) {
                            this.f4233a = (rangeD.f4244a * 0.100000024f) + (this.f4233a * 0.9f);
                            this.f4234b = (rangeD.f4245b * 0.100000024f) + (this.f4234b * 0.9f);
                        } else {
                            this.f4233a = rangeD.f4244a;
                            this.f4234b = rangeD.f4245b;
                            this.c = true;
                        }
                        rangeD.f4244a = this.f4233a;
                        rangeD.f4245b = this.f4234b;
                    } else {
                        this.c = false;
                    }
                    float[] fArr2 = this.f4235d;
                    float f = rangeD.f4244a;
                    float f2 = rangeD.f4245b - f;
                    if (f2 <= 1.0E-6f) {
                        Arrays.fill(fArr2, 0, i4, 1.0f);
                    } else {
                        float f3 = 1.0f / f2;
                        for (int i5 = 0; i5 < i4; i5++) {
                            fArr2[i5] = b((fArr[i5] - f) * f3);
                        }
                    }
                    if (DepthOptimizationSettings.a().getBoolean("DepthOptSoftplusMapper", false)) {
                        int i6 = !DepthOptimizationSettings.a().getBoolean("DepthOptSoftplusMapper", false) ? 0 : DepthOptimizationSettings.a().getInt("DepthOptForegroundMapperLevel", 2);
                        if (this.f4241m != i6) {
                            this.f4242n = false;
                            this.f4241m = i6;
                        }
                        System.arraycopy(this.f4235d, 0, this.e, 0, i4);
                        float[] fArr3 = this.f4235d;
                        double d2 = i6 != 1 ? i6 != 3 ? 0.515f : 0.687f : 0.343f;
                        double d3 = 12.0f;
                        double dLog1p = Math.log1p(Math.exp((0.0d - d2) * d3));
                        double dLog1p2 = Math.log1p(Math.exp((1.0d - d2) * d3)) - dLog1p;
                        if (dLog1p2 > 1.0E-9d) {
                            for (int i7 = 0; i7 < i4; i7++) {
                                fArr3[i7] = b((float) ((Math.log1p(Math.exp((fArr3[i7] - r13) * 12.0f)) - dLog1p) / dLog1p2));
                            }
                        }
                        j(this.f4235d, this.e, i4);
                    } else {
                        this.f4242n = false;
                        this.f4241m = 0;
                    }
                    if (DepthOptimizationSettings.a().getBoolean("DepthOptEdgeDilation", false)) {
                        a(this.f4235d, i2, i3);
                    }
                    return this.f4235d;
                }
            } finally {
            }
        }
        return fArr;
    }

    public final void j(float[] fArr, float[] fArr2, int i2) {
        if (!this.f4242n) {
            System.arraycopy(fArr, 0, this.f4239k, 0, i2);
            this.f4242n = true;
            return;
        }
        float fAbs = 0.0f;
        for (int i3 = 0; i3 < i2; i3++) {
            fAbs += Math.abs(fArr[i3] - this.f4239k[i3]);
        }
        if (fAbs / i2 > 0.2f) {
            System.arraycopy(fArr, 0, this.f4239k, 0, i2);
            return;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            float fI = i(0.015f, 0.1f, Math.abs(fArr[i4] - fArr2[i4]));
            if (fI <= 0.01f) {
                this.f4239k[i4] = fArr[i4];
            } else {
                float fI2 = fI * 0.45f * (1.0f - i(0.025f, 0.1f, Math.abs(fArr[i4] - this.f4239k[i4])));
                float f = (1.0f - fI2) * fArr[i4];
                float[] fArr3 = this.f4239k;
                float f2 = (fArr3[i4] * fI2) + f;
                fArr[i4] = f2;
                fArr3[i4] = f2;
            }
        }
    }
}
