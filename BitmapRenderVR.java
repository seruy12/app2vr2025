package net.xq.appvrplayer.VideoProcessor;

import android.graphics.Bitmap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;
import net.xq.appvrplayer.Utils.SettingTaskInfo;
import net.xq.appvrplayer.application.MyApplication;
import net.xq.appvrplayer.ui.activity.SpatialMainActivity;

/* JADX INFO: loaded from: classes.dex */
public class BitmapRenderVR extends RenderBase {
    public final Bitmap L;
    public final Bitmap M;
    public final boolean U;
    public Bitmap K = null;
    public long N = -1;
    public long O = -1;
    public long P = -1;
    public long Q = -1;
    public long R = -1;
    public boolean S = false;
    public boolean T = false;
    public String V = "";
    public int W = -1;
    public int X = -1;
    public boolean Y = false;
    public long Z = -1;
    public boolean a0 = false;

    public BitmapRenderVR() {
        this.L = null;
        this.M = null;
        this.f4256b = 1.0f;
        SettingTaskInfo settingTaskInfo = MyApplication.f;
        boolean z = settingTaskInfo.f4250a;
        this.U = z;
        float f = settingTaskInfo.c;
        this.c = (int) (FloatingWindowManager.d() / f);
        int iC = (int) (FloatingWindowManager.c() / f);
        this.f4257d = iC;
        if (z) {
            int iMin = Math.min(this.c, iC);
            int iMax = Math.max(this.c, this.f4257d);
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMax, config);
            this.L = bitmapCreateBitmap;
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(Math.max(this.c, this.f4257d), Math.min(this.c, this.f4257d), config);
            this.M = bitmapCreateBitmap2;
            bitmapCreateBitmap.setPremultiplied(false);
            bitmapCreateBitmap2.setPremultiplied(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 921
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.xq.appvrplayer.VideoProcessor.BitmapRenderVR.h():void");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        AtomicBoolean atomicBoolean = this.f4264n;
        AtomicBoolean atomicBoolean2 = this.f4263m;
        if (atomicBoolean2.compareAndSet(false, true)) {
            try {
                atomicBoolean.set(true);
                h();
            } finally {
                atomicBoolean.set(false);
                atomicBoolean2.set(false);
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.c = i2;
        this.f4257d = i3;
        int iE = FloatingWindowManager.e();
        SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
        spatialMainActivity.nativeSetOrientation(spatialMainActivity.A, iE);
        boolean z = !MyApplication.d(MyApplication.f4272d) && i2 < i3;
        SpatialMainActivity spatialMainActivity2 = SpatialMainActivity.H;
        spatialMainActivity2.nativeSetRotateMesh90(spatialMainActivity2.A, z);
        SpatialMainActivity spatialMainActivity3 = SpatialMainActivity.H;
        spatialMainActivity3.nativeSetScreenParams(spatialMainActivity3.A, i2, i3);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        SpatialMainActivity.H.x(FloatingWindowManager.d(), FloatingWindowManager.c());
    }
}
