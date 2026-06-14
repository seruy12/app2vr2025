package net.xq.appvrplayer.VideoProcessor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import net.xq.appvrplayer.FloatingWindow.FloatingPlayerView;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;
import net.xq.appvrplayer.Utils.DepthConvergenceEstimator;
import net.xq.appvrplayer.Utils.DepthOptimizationSettings;
import net.xq.appvrplayer.Utils.DepthPostProcessor;
import net.xq.appvrplayer.application.MyApplication;
import net.xq.appvrplayer.ui.activity.SpatialMainActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class RenderBase implements GLSurfaceView.Renderer {
    public long A;
    public long B;
    public int C;
    public long D;
    public final AtomicLong E;
    public final AtomicBoolean F;
    public final AtomicLong G;
    public final AtomicBoolean H;
    public final Object I;
    public int J;
    public int c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4257d;
    public final Bitmap f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bitmap f4258g;
    public Bitmap h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Bitmap f4259i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f4260j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f4261k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile float[] f4262l;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final DepthPostProcessor f4267t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final DepthConvergenceEstimator f4268u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AtomicLong f4269v;
    public volatile float w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f4270x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4271y;
    public int z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile float f4255a = 1.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4256b = 0.98f;
    public Bitmap e = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f4263m = new AtomicBoolean(false);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicBoolean f4264n = new AtomicBoolean(false);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Canvas f4265o = new Canvas();
    public final AtomicLong p = new AtomicLong(0);
    public final AtomicLong q = new AtomicLong(0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AtomicBoolean f4266r = new AtomicBoolean(false);
    public final ExecutorService s = Executors.newSingleThreadExecutor();

    public RenderBase() {
        this.f = null;
        this.f4258g = null;
        this.h = null;
        this.f4259i = null;
        DepthPostProcessor depthPostProcessor = new DepthPostProcessor();
        depthPostProcessor.f4241m = 0;
        this.f4267t = depthPostProcessor;
        this.f4268u = new DepthConvergenceEstimator();
        this.f4269v = new AtomicLong(0L);
        this.w = 0.5f;
        this.f4270x = 0;
        this.f4271y = false;
        this.z = 0;
        this.A = 0L;
        this.B = 0L;
        this.C = 1;
        this.D = 140L;
        this.E = new AtomicLong(-1L);
        this.F = new AtomicBoolean(false);
        this.G = new AtomicLong(-1L);
        this.H = new AtomicBoolean(false);
        this.I = new Object();
        this.J = 0;
        float f = MyApplication.f.c;
        this.c = (int) (FloatingWindowManager.d() / f);
        int iC = (int) (FloatingWindowManager.c() / f);
        this.f4257d = iC;
        int iMin = Math.min(this.c, iC);
        int iMax = Math.max(this.c, this.f4257d);
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        this.f = Bitmap.createBitmap(iMin, iMax, config);
        this.f4258g = Bitmap.createBitmap(Math.max(this.c, this.f4257d), Math.min(this.c, this.f4257d), config);
        if (MyApplication.f.f4250a) {
            this.h = Bitmap.createBitmap(Math.min(this.c, this.f4257d), Math.max(this.c, this.f4257d), config);
            this.f4259i = Bitmap.createBitmap(Math.max(this.c, this.f4257d), Math.min(this.c, this.f4257d), config);
            this.f4260j = new int[this.c * this.f4257d];
        }
        this.f4262l = new float[65536];
        Arrays.fill(this.f4262l, 1.0f);
    }

    public final void a(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap.getWidth() > bitmap.getHeight() ? this.f4258g : this.f;
        this.e = bitmap2;
        Canvas canvas = this.f4265o;
        canvas.setBitmap(bitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.setBitmap(null);
        this.p.incrementAndGet();
    }

    public final void b() {
        if (this.f4260j == null) {
            this.f4260j = new int[this.c * this.f4257d];
        }
        if (this.h == null) {
            this.h = Bitmap.createBitmap(Math.min(this.c, this.f4257d), Math.max(this.c, this.f4257d), Bitmap.Config.ARGB_8888);
        }
        if (this.f4259i == null) {
            this.f4259i = Bitmap.createBitmap(Math.max(this.c, this.f4257d), Math.min(this.c, this.f4257d), Bitmap.Config.ARGB_8888);
        }
    }

    public final void c(long j2) {
        long j3 = this.G.get();
        if (j3 < 0 || j2 - j3 < 5000 || !this.H.compareAndSet(false, true)) {
            return;
        }
        synchronized (this.I) {
            Arrays.fill(this.f4262l, 1.0f);
            this.q.incrementAndGet();
        }
        SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
        if (spatialMainActivity != null) {
            spatialMainActivity.y(0.0f, 0.0f, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.xq.appvrplayer.VideoProcessor.RenderBase.d(int, int):boolean");
    }

    public final void e(Bitmap bitmap) {
        FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
        if (floatingPlayerView == null) {
            floatingPlayerView = null;
        }
        if (floatingPlayerView != null) {
            if (!MyApplication.f.f4250a) {
                a(bitmap);
                FloatingPlayerView floatingPlayerView2 = FloatingWindowManager.f4195b;
                (floatingPlayerView2 != null ? floatingPlayerView2 : null).h();
                return;
            }
            b();
            bitmap.getPixels(this.f4260j, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (MyApplication.f.f4251b) {
                d(bitmap.getWidth(), bitmap.getHeight());
            } else {
                AtomicBoolean atomicBoolean = this.F;
                if (atomicBoolean.get()) {
                    c(System.currentTimeMillis());
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    AtomicLong atomicLong = this.E;
                    if (atomicLong.get() == -1) {
                        atomicLong.set(jCurrentTimeMillis);
                    } else if (jCurrentTimeMillis - atomicLong.get() > 60000) {
                        if (atomicBoolean.compareAndSet(false, true)) {
                            this.G.set(jCurrentTimeMillis);
                        }
                        c(jCurrentTimeMillis);
                    }
                    if (!atomicBoolean.get()) {
                        d(bitmap.getWidth(), bitmap.getHeight());
                    }
                }
            }
            if (DepthOptimizationSettings.a().getBoolean("DepthOptAdaptiveInference", false) && this.e != null && System.currentTimeMillis() - this.B < this.D) {
                return;
            }
            int[] iArr = this.f4260j;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap bitmap2 = width > height ? this.f4258g : this.f;
            this.e = bitmap2;
            bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
            this.p.incrementAndGet();
            this.B = System.currentTimeMillis();
            FloatingPlayerView floatingPlayerView3 = FloatingWindowManager.f4195b;
            (floatingPlayerView3 != null ? floatingPlayerView3 : null).h();
        }
    }

    public final void f(float f) {
        float f2 = this.f4256b * f;
        if (Math.abs(this.f4255a - f2) < 1.0E-4f) {
            return;
        }
        this.f4255a = f2;
        FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
        if ((floatingPlayerView == null ? null : floatingPlayerView) != null) {
            if (floatingPlayerView == null) {
                floatingPlayerView = null;
            }
            floatingPlayerView.h();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x00e7 A[Catch: all -> 0x0080, TryCatch #0 {all -> 0x0080, blocks: (B:18:0x0031, B:21:0x0038, B:24:0x0049, B:26:0x004e, B:28:0x0058, B:37:0x006c, B:40:0x0083, B:41:0x0086, B:43:0x008b, B:45:0x0090, B:50:0x0095, B:51:0x0096, B:61:0x00c5, B:63:0x00c9, B:66:0x00d9, B:68:0x00eb, B:67:0x00e7, B:54:0x00ae, B:71:0x00ef, B:73:0x00f4, B:81:0x010f, B:44:0x008c, B:72:0x00f0), top: B:84:0x0031, inners: #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(float[] r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.xq.appvrplayer.VideoProcessor.RenderBase.g(float[], int, int):void");
    }
}
