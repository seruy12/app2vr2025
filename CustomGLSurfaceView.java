package net.xq.appvrplayer.VideoProcessor;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;

/* JADX INFO: loaded from: classes.dex */
public class CustomGLSurfaceView extends GLSurfaceView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RenderBase f4254a;

    public CustomGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextClientVersion(2);
        if (FloatingWindowManager.f4194a == FloatingWindowManager.FloatingWindowMode.f4199a) {
            this.f4254a = new BitmapRenderVR();
        } else {
            this.f4254a = new BitmapRender3D();
        }
        setRenderer(this.f4254a);
        setRenderMode(0);
    }

    public RenderBase getRenderer() {
        return this.f4254a;
    }
}
