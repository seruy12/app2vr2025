package net.xq.appvrplayer.VideoProcessor;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.umeng.analytics.pro.bc;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;
import net.xq.appvrplayer.Utils.DepthConvergenceEstimator;
import net.xq.appvrplayer.Utils.DepthOptimizationSettings;
import net.xq.appvrplayer.application.MyApplication;

/* JADX INFO: loaded from: classes.dex */
public class BitmapRender3D extends RenderBase {
    public final boolean B0;
    public ByteBuffer G0;
    public int K;
    public int P;
    public float[] R;
    public final float[] S;
    public final short[] T;
    public final FloatBuffer U;
    public final FloatBuffer V;
    public final ShortBuffer W;
    public final FloatBuffer X;
    public final FloatBuffer Y;
    public final ShortBuffer Z;
    public int b0;
    public int c0;
    public int d0;
    public int e0;
    public int f0;
    public int g0;
    public int h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public int t0;
    public int u0;
    public boolean y0;
    public final float z0;
    public int L = -1;
    public int M = -1;
    public int N = -1;
    public int O = -1;
    public int Q = -1;
    public final int[] a0 = new int[3];
    public boolean v0 = false;
    public boolean w0 = false;
    public boolean x0 = false;
    public float[] A0 = null;
    public float[] C0 = null;
    public boolean D0 = false;
    public int E0 = bc.e;
    public int F0 = bc.e;
    public int H0 = 0;
    public int I0 = 0;
    public long J0 = -1;
    public long K0 = -1;
    public boolean L0 = false;

    public BitmapRender3D() {
        float[] fArr = {-1.0f, -0.45f, -1.0f, 1.0f, -0.45f, -1.0f, -1.0f, 0.45f, -1.0f, 1.0f, 0.45f, -1.0f};
        this.R = fArr;
        float[] fArr2 = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.S = fArr2;
        short[] sArr = {0, 1, 2, 1, 3, 2};
        this.T = sArr;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        this.U = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(this.R);
        this.U.position(0);
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(fArr2.length * 4);
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer2 = byteBufferAllocateDirect2.asFloatBuffer();
        this.V = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2);
        this.V.position(0);
        ByteBuffer byteBufferAllocateDirect3 = ByteBuffer.allocateDirect(sArr.length * 2);
        byteBufferAllocateDirect3.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect3.asShortBuffer();
        this.W = shortBufferAsShortBuffer;
        shortBufferAsShortBuffer.put(sArr);
        this.W.position(0);
        ByteBuffer byteBufferAllocateDirect4 = ByteBuffer.allocateDirect(48);
        byteBufferAllocateDirect4.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer3 = byteBufferAllocateDirect4.asFloatBuffer();
        this.X = floatBufferAsFloatBuffer3;
        floatBufferAsFloatBuffer3.put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f});
        this.X.position(0);
        ByteBuffer byteBufferAllocateDirect5 = ByteBuffer.allocateDirect(32);
        byteBufferAllocateDirect5.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer4 = byteBufferAllocateDirect5.asFloatBuffer();
        this.Y = floatBufferAsFloatBuffer4;
        floatBufferAsFloatBuffer4.put(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
        this.Y.position(0);
        ByteBuffer byteBufferAllocateDirect6 = ByteBuffer.allocateDirect(sArr.length * 2);
        byteBufferAllocateDirect6.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer2 = byteBufferAllocateDirect6.asShortBuffer();
        this.Z = shortBufferAsShortBuffer2;
        shortBufferAsShortBuffer2.put(sArr);
        this.Z.position(0);
        this.y0 = FloatingWindowManager.h.get();
        this.z0 = 1.0f;
        this.B0 = MyApplication.f.f4250a;
        if (FloatingWindowManager.f4194a == FloatingWindowManager.FloatingWindowMode.c) {
            this.f4256b = 1.0f;
        }
        this.y0 = FloatingWindowManager.g();
        this.f4255a = this.f4256b * this.f4255a;
    }

    public static int i(int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        GLES20.glBindTexture(3553, i4);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, i2, i3, 0, 6408, 5121, null);
        return i4;
    }

    public final void h() {
        int i2;
        int[] iArr = new int[3];
        int i3 = this.Q;
        if (i3 > 0) {
            iArr[0] = i3;
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i4 = this.M;
        if (i4 > 0) {
            iArr[i2] = i4;
            i2++;
        }
        int i5 = this.N;
        if (i5 > 0) {
            iArr[i2] = i5;
            i2++;
        }
        if (i2 > 0) {
            GLES20.glDeleteTextures(i2, iArr, 0);
        }
        int i6 = this.O;
        if (i6 > 0) {
            GLES20.glDeleteFramebuffers(1, new int[]{i6}, 0);
        }
        this.Q = -1;
        this.M = -1;
        this.N = -1;
        this.O = -1;
        this.x0 = false;
        this.w0 = false;
        this.L0 = false;
    }

    public final void j() {
        int[] iArr = this.a0;
        GLES20.glBindBuffer(34962, iArr[0]);
        GLES20.glEnableVertexAttribArray(this.c0);
        GLES20.glVertexAttribPointer(this.c0, 3, 5126, false, 0, 0);
        GLES20.glBindBuffer(34962, iArr[1]);
        GLES20.glEnableVertexAttribArray(this.d0);
        GLES20.glVertexAttribPointer(this.d0, 2, 5126, false, 0, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.L);
        GLES20.glUniform1i(this.f0, 0);
        GLES20.glBindBuffer(34963, iArr[2]);
        GLES20.glDrawElements(4, this.T.length, 5123, 0);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glBindBuffer(34963, 0);
        GLES20.glDisableVertexAttribArray(this.c0);
        GLES20.glDisableVertexAttribArray(this.d0);
    }

    public final boolean k(int i2, int i3, float f, float f2) {
        GLES20.glBindFramebuffer(36160, this.O);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i3, 0);
        int iGlCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        if (iGlCheckFramebufferStatus != 36053) {
            Log.e("BitmapRender", "Depth blur framebuffer incomplete: " + iGlCheckFramebufferStatus);
            return false;
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i2);
        GLES20.glUniform1i(this.s0, 0);
        GLES20.glUniform2f(this.t0, 1.0f / this.E0, 1.0f / this.F0);
        GLES20.glUniform2f(this.u0, f, f2);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glBindBuffer(34963, 0);
        this.X.position(0);
        GLES20.glEnableVertexAttribArray(this.q0);
        GLES20.glVertexAttribPointer(this.q0, 3, 5126, false, 0, (Buffer) this.X);
        this.Y.position(0);
        GLES20.glEnableVertexAttribArray(this.r0);
        GLES20.glVertexAttribPointer(this.r0, 2, 5126, false, 0, (Buffer) this.Y);
        this.Z.position(0);
        GLES20.glDrawElements(4, this.T.length, 5123, this.Z);
        GLES20.glDisableVertexAttribArray(this.q0);
        GLES20.glDisableVertexAttribArray(this.r0);
        return true;
    }

    public final void l() {
        int i2;
        if (!this.x0 || this.Q == -1) {
            if (!this.D0) {
                ByteBuffer.allocateDirect(this.E0 * this.F0 * 4).order(ByteOrder.nativeOrder());
                this.D0 = true;
            }
            h();
            this.Q = i(this.E0, this.F0);
            this.M = i(this.E0, this.F0);
            this.N = i(this.E0, this.F0);
            int[] iArr = new int[1];
            GLES20.glGenFramebuffers(1, iArr, 0);
            this.O = iArr[0];
            this.x0 = true;
            this.w0 = false;
        }
        int i3 = this.E0 * this.F0 * 4;
        ByteBuffer byteBuffer = this.G0;
        if (byteBuffer == null || byteBuffer.capacity() < i3) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i3);
            this.G0 = byteBufferAllocateDirect;
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        }
        this.G0.clear();
        int length = this.C0.length;
        for (int i4 = 0; i4 < length; i4++) {
            byte b2 = (byte) (r0[i4] * 255.0f);
            this.G0.put(b2);
            this.G0.put(b2);
            this.G0.put(b2);
            this.G0.put(b2);
        }
        this.G0.flip();
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.Q);
        GLES20.glTexSubImage2D(3553, 0, 0, 0, this.E0, this.F0, 6408, 5121, this.G0);
        if (!this.x0 || (i2 = this.P) == 0 || this.O == -1) {
            this.w0 = false;
        } else {
            GLES20.glUseProgram(i2);
            GLES20.glDisable(3042);
            GLES20.glViewport(0, 0, this.E0, this.F0);
            boolean z = k(this.Q, this.M, 1.0f, 0.0f) && k(this.M, this.N, 0.0f, 1.0f);
            GLES20.glBindFramebuffer(36160, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glEnable(3042);
            GLES20.glUseProgram(this.K);
            this.w0 = z;
        }
        GLES20.glActiveTexture(33984);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        float fC;
        int iD;
        float f;
        float f2;
        int i2;
        int i3;
        this.f4264n.set(true);
        if (this.e != null) {
            long j2 = this.q.get();
            if (this.B0 && MyApplication.e.a() && (!this.L0 || this.K0 != j2)) {
                int width = MyApplication.e.f4246a.getWidth();
                int height = MyApplication.e.f4246a.getHeight();
                try {
                    float[] fArr = this.C0;
                    if (fArr == null || fArr.length != width * height) {
                        this.C0 = new float[height * width];
                        this.E0 = width;
                        this.F0 = height;
                        h();
                        this.K0 = -1L;
                        this.L0 = false;
                    }
                    if (DepthOptimizationSettings.a().getBoolean("DepthOptEmaNormalize", false) || DepthOptimizationSettings.a().getBoolean("DepthOptSoftplusMapper", false) || DepthOptimizationSettings.a().getBoolean("DepthOptEdgeDilation", false)) {
                        for (int i4 = 0; i4 < this.f4262l.length; i4++) {
                            float f3 = this.f4262l[i4];
                            if (f3 < 0.0f) {
                                f3 = 0.0f;
                            }
                            if (f3 > 1.0f) {
                                f3 = 1.0f;
                            }
                            this.C0[i4] = f3;
                        }
                    } else {
                        float fMin = 1000000.0f;
                        float fMax = -1000000.0f;
                        for (float f4 : this.f4262l) {
                            fMin = Math.min(fMin, f4);
                            fMax = Math.max(fMax, f4);
                        }
                        for (int i5 = 0; i5 < this.f4262l.length; i5++) {
                            this.C0[i5] = (float) (((double) (this.f4262l[i5] - fMin)) / (((double) (fMax - fMin)) + 1.0E-6d));
                        }
                    }
                    l();
                    this.K0 = j2;
                    this.L0 = true;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (message != null) {
                        Log.e("renderScene", message);
                    } else {
                        Log.e("renderScene", "Unknown error");
                    }
                }
            }
            Bitmap bitmap = this.e;
            if (!this.v0 || this.L == -1 || this.H0 != bitmap.getWidth() || this.I0 != bitmap.getHeight()) {
                this.H0 = bitmap.getWidth();
                this.I0 = bitmap.getHeight();
                int width2 = bitmap.getWidth();
                int height2 = bitmap.getHeight();
                int i6 = this.L;
                if (i6 > 0) {
                    GLES20.glDeleteTextures(1, new int[]{i6}, 0);
                }
                this.L = -1;
                this.v0 = false;
                this.J0 = -1L;
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                int i7 = iArr[0];
                this.L = i7;
                GLES20.glBindTexture(3553, i7);
                GLES20.glTexParameteri(3553, 10241, 9729);
                GLES20.glTexParameteri(3553, 10240, 9729);
                GLES20.glTexParameteri(3553, 10242, 33071);
                GLES20.glTexParameteri(3553, 10243, 33071);
                GLES20.glTexImage2D(3553, 0, 6408, width2, height2, 0, 6408, 5121, null);
                this.v0 = true;
                this.J0 = -1L;
            }
            long j3 = this.p.get();
            if (this.J0 != j3) {
                GLES20.glBindTexture(3553, this.L);
                GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
                this.J0 = j3;
            }
            GLES20.glUseProgram(this.K);
            if (this.j0 >= 0 && (i2 = this.Q) != -1) {
                if (this.w0 && (i3 = this.N) != -1) {
                    i2 = i3;
                }
                GLES20.glActiveTexture(33985);
                GLES20.glBindTexture(3553, i2);
                GLES20.glUniform1i(this.j0, 1);
                GLES20.glActiveTexture(33984);
            }
            if (this.A0 == null) {
                float[] fArr2 = new float[16];
                this.A0 = fArr2;
                Matrix.setIdentityM(fArr2, 0);
            }
            FloatingWindowManager.FloatingWindowMode floatingWindowMode = FloatingWindowManager.f4194a;
            FloatingWindowManager.FloatingWindowMode floatingWindowMode2 = FloatingWindowManager.FloatingWindowMode.f4200b;
            FloatingWindowManager.FloatingWindowMode floatingWindowMode3 = FloatingWindowManager.FloatingWindowMode.c;
            if (floatingWindowMode != floatingWindowMode2 && floatingWindowMode == floatingWindowMode3) {
                if (this.y0) {
                    fC = FloatingWindowManager.d();
                    iD = FloatingWindowManager.c();
                } else {
                    fC = FloatingWindowManager.c();
                    iD = FloatingWindowManager.d();
                }
                float f5 = fC / iD;
                Matrix.orthoM(this.A0, 0, -f5, f5, -1.0f, 1.0f, -1.0f, 1.0f);
            } else {
                Matrix.setIdentityM(this.A0, 0);
            }
            GLES20.glUniform1f(this.i0, this.z0 / this.f4255a);
            GLES20.glUniform1f(this.g0, 0.5f);
            GLES20.glUniform1f(this.h0, 0.5f);
            Matrix.scaleM(this.A0, 0, this.f4255a, this.f4255a, 1.0f);
            GLES20.glUniformMatrix4fv(this.e0, 1, false, this.A0, 0);
            GLES20.glUniform1i(this.b0, 0);
            FloatingWindowManager.FloatingWindowMode floatingWindowMode4 = FloatingWindowManager.f4194a;
            FloatingWindowManager.FloatingWindowMode floatingWindowMode5 = FloatingWindowManager.FloatingWindowMode.f4199a;
            boolean z = floatingWindowMode4 == floatingWindowMode5 && DepthOptimizationSettings.a().getBoolean("DepthOptVrEdgeAware", false);
            GLES20.glUniform1f(this.k0, z ? 1.8f : 1.0f);
            int i8 = this.l0;
            if (DepthOptimizationSettings.a().getBoolean("DepthOptDynamicConvergence", false)) {
                DepthConvergenceEstimator depthConvergenceEstimator = this.f4268u;
                synchronized (depthConvergenceEstimator) {
                    f = depthConvergenceEstimator.f4232b ? depthConvergenceEstimator.c : 0.5f;
                }
            }
            GLES20.glUniform1f(i8, f);
            GLES20.glUniform1f(this.n0, DepthOptimizationSettings.a().getBoolean("DepthOptScreenBorder", false) ? 1.0f : 0.0f);
            GLES20.glUniform1f(this.o0, z ? 1.0f : 0.0f);
            int i9 = this.p0;
            int i10 = this.E0;
            if (i10 > 0) {
                f = 1.0f;
                f2 = 1.0f / i10;
            } else {
                f = 1.0f;
                f2 = 1.0f;
            }
            int i11 = this.F0;
            GLES20.glUniform2f(i9, f2, i11 > 0 ? f / i11 : 1.0f);
            GLES20.glUniform1f(this.m0, -1.0f);
            if (FloatingWindowManager.f4194a == floatingWindowMode3) {
                GLES20.glViewport(0, 0, this.c, this.f4257d);
            } else {
                GLES20.glViewport(0, 0, this.c / 2, this.f4257d);
            }
            GLES20.glClear(16384);
            GLES20.glUseProgram(this.K);
            j();
            FloatingWindowManager.FloatingWindowMode floatingWindowMode6 = FloatingWindowManager.f4194a;
            if (floatingWindowMode6 == floatingWindowMode5 || floatingWindowMode6 == floatingWindowMode2) {
                GLES20.glUniform1f(this.m0, 1.0f);
                int i12 = this.c / 2;
                GLES20.glViewport(i12, 0, i12, this.f4257d);
                j();
            }
        }
        this.f4264n.set(false);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.c = i2;
        this.f4257d = i3;
        this.y0 = FloatingWindowManager.g();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        int[] iArr = this.a0;
        if (iArr[0] != 0) {
            GLES20.glDeleteBuffers(3, iArr, 0);
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
        }
        int i2 = this.L;
        if (i2 > 0) {
            GLES20.glDeleteTextures(1, new int[]{i2}, 0);
        }
        this.L = -1;
        this.v0 = false;
        this.J0 = -1L;
        h();
        int i3 = this.K;
        if (i3 != 0) {
            GLES20.glDeleteProgram(i3);
            this.K = 0;
        }
        int i4 = this.P;
        if (i4 != 0) {
            GLES20.glDeleteProgram(i4);
            this.P = 0;
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        FloatingWindowManager.FloatingWindowMode floatingWindowMode = FloatingWindowManager.f4194a;
        String str = floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4199a ? MyApplication.f.f4250a ? "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform float cen_x;uniform float cen_y;uniform float k_off;uniform sampler2D depth;\nuniform float divergence;\nuniform float convergence;\nuniform float factor;\nuniform float preserveScreenBorder;\nvoid main() {\n    vec2 normXY = vec2(texCoord.x - cen_x, texCoord.y -cen_y);\n    float r2 = dot(normXY, normXY);\n    float r4 = r2 * r2;\n    float r6 = r2 * r4;\n    float distortion = k_off + 0.4 * r2 - 0.1 * r4 + 0.05 * r6;\n    vec2 xy_d = normXY * distortion;\n    vec2 xy = xy_d + vec2(cen_x, cen_y);\n    if(xy.x < 0.0 || xy.x > 1.0 || xy.y < 0.0 || xy.y > 1.0)    { gl_FragColor = vec4(0.0); return; }    float d = texture2D(depth, xy).a;\n     float shift_size = divergence * 0.01;\n     float index_shift = (d - convergence)  * shift_size;\n     if(preserveScreenBorder > 0.5) {\n        float border = 0.04;\n        float leftWeight = smoothstep(0.0, border, xy.x);\n        float rightWeight = smoothstep(0.0, border, 1.0 - xy.x);\n        index_shift *= leftWeight * rightWeight;\n    }\n    float c_shift = xy.x + index_shift * factor;\n     c_shift = clamp(c_shift, 0.0, 1.0);\n     vec2 tex_xy   = vec2(c_shift, xy.y);\n     if(tex_xy.x < 0.0 || tex_xy.x > 1.0 || tex_xy.y < 0.0 || tex_xy.y > 1.0)    { gl_FragColor = vec4(0.0); return; }    gl_FragColor = texture2D(sTexture, tex_xy);\n}" : "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform float cen_x;uniform float cen_y;uniform float k_off;uniform float divergence;\nuniform float convergence;\nuniform float factor;\nuniform float preserveScreenBorder;\nuniform float edgeAwareDisparity;\nuniform vec2 depthTexelSize;\nvoid main() {\n    vec2 normXY = vec2(texCoord.x - cen_x, texCoord.y -cen_y);\n    float r2 = dot(normXY, normXY);\n    float r4 = r2 * r2;\n    float r6 = r2 * r4;\n    float distortion = k_off + 2.0 * r2;\n    vec2 xy_d = normXY * distortion;\n    vec2 xy = xy_d + vec2(cen_x, cen_y);\n    if(xy.x < 0.0 || xy.x > 1.0 || xy.y < 0.0 || xy.y > 1.0)    { gl_FragColor = vec4(0.0); return; }    float d = 1.0;\n     float shift_size = divergence * 0.01;\n     float index_shift = (d - convergence)  * shift_size;\n     if(preserveScreenBorder > 0.5) {\n        float border = 0.04;\n        float leftWeight = smoothstep(0.0, border, xy.x);\n        float rightWeight = smoothstep(0.0, border, 1.0 - xy.x);\n        index_shift *= leftWeight * rightWeight;\n    }\n    if(edgeAwareDisparity > 0.5) {\n        float depthL = texture2D(depth, xy + vec2(-depthTexelSize.x, 0.0)).a;\n        float depthR = texture2D(depth, xy + vec2(depthTexelSize.x, 0.0)).a;\n        float depthU = texture2D(depth, xy + vec2(0.0, -depthTexelSize.y)).a;\n        float depthD = texture2D(depth, xy + vec2(0.0, depthTexelSize.y)).a;\n        float depthEdge = max(max(abs(d - depthL), abs(d - depthR)), max(abs(d - depthU), abs(d - depthD)));\n        float localConfidence = 1.0 - smoothstep(0.035, 0.16, depthEdge);\n        float probeX = clamp(xy.x + index_shift * factor, 0.0, 1.0);\n        float shiftedDepth = texture2D(depth, vec2(probeX, xy.y)).a;\n        float occlusionConfidence = 1.0 - smoothstep(0.06, 0.20, abs(d - shiftedDepth));\n        index_shift *= max(0.22, min(localConfidence, occlusionConfidence));\n    }\n    float c_shift = xy.x + index_shift * factor;\n     c_shift = clamp(c_shift, 0.0, 1.0);\n     vec2 tex_xy   = vec2(c_shift, xy.y);\n     if(tex_xy.x < 0.0 || tex_xy.x > 1.0 || tex_xy.y < 0.0 || tex_xy.y > 1.0)    { gl_FragColor = vec4(0.0); return; }    gl_FragColor = texture2D(sTexture, tex_xy);\n}" : floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4200b ? MyApplication.f.f4250a ? "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform sampler2D depth;\nuniform float divergence;\nuniform float convergence;\nuniform float factor;\nuniform float preserveScreenBorder;\nvoid main()\n{\n    float d = texture2D(depth, texCoord).a ;\n     float shift_size = divergence * 0.01;\n     float index_shift = d  * shift_size - shift_size * convergence;\n     if(preserveScreenBorder > 0.5) {\n        float border = 0.04;\n        float leftWeight = smoothstep(0.0, border, texCoord.x);\n        float rightWeight = smoothstep(0.0, border, 1.0 - texCoord.x);\n        index_shift *= leftWeight * rightWeight;\n    }\n    float c_shift = texCoord.x + index_shift * factor;\n     c_shift = clamp(c_shift, 0.0, 1.0);\n     vec4 color    = vec4(texture2D(sTexture, vec2(c_shift, texCoord.y)));\n    gl_FragColor  = color;\n}" : "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform float factor;\nvoid main()\n{\n    float index_shift = -0.005;\n     float c_shift = texCoord.x + index_shift * factor;\n     c_shift = clamp(c_shift, 0.0, 1.0);\n     vec4 color    = vec4(texture2D(sTexture, vec2(c_shift, texCoord.y)));\n    gl_FragColor  = color;\n}" : floatingWindowMode == FloatingWindowManager.FloatingWindowMode.c ? MyApplication.f.f4250a ? "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform sampler2D depth;\nuniform float divergence;\nuniform float convergence;\nuniform float preserveScreenBorder;\nvoid main()\n{\n    float d = texture2D(depth, texCoord).a ;\n     float shift_size = divergence * 0.01;\n     float index_shift = d  * shift_size - shift_size * convergence;\n     if(preserveScreenBorder > 0.5) {\n        float border = 0.04;\n        float leftWeight = smoothstep(0.0, border, texCoord.x);\n        float rightWeight = smoothstep(0.0, border, 1.0 - texCoord.x);\n        index_shift *= leftWeight * rightWeight;\n    }\n    float x_r = texCoord.x + index_shift;\n     float x_l = texCoord.x + index_shift * -1.0;\n     x_r = clamp(x_r, 0.0, 1.0);\n     x_l = clamp(x_l, 0.0, 1.0);\n     vec2 tex_xy_r   = vec2(x_r, texCoord.y);\n     vec2 tex_xy_l   = vec2(x_l, texCoord.y);\n     vec4 color_r    = vec4(texture2D(sTexture, tex_xy_r));\n    vec4 color_l    = vec4(texture2D(sTexture, tex_xy_l));\n    color_r.r = color_l.r; \n    gl_FragColor  = color_r;\n}" : "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D sTexture;\nuniform float divergence;\nuniform float convergence;\nuniform float preserveScreenBorder;\nvoid main()\n{\n    float d = 1.0 ;\n     float shift_size = divergence * 0.01;\n     float index_shift = d  * shift_size - shift_size * convergence;\n     if(preserveScreenBorder > 0.5) {\n        float border = 0.04;\n        float leftWeight = smoothstep(0.0, border, texCoord.x);\n        float rightWeight = smoothstep(0.0, border, 1.0 - texCoord.x);\n        index_shift *= leftWeight * rightWeight;\n    }\n    float x_r = texCoord.x + index_shift;\n     float x_l = texCoord.x + index_shift * -1.0;\n     x_r = clamp(x_r, 0.0, 1.0);\n     x_l = clamp(x_l, 0.0, 1.0);\n     vec2 tex_xy_r   = vec2(x_r, texCoord.y);\n     vec2 tex_xy_l   = vec2(x_l, texCoord.y);\n     vec4 color_r    = vec4(texture2D(sTexture, tex_xy_r));\n    vec4 color_l    = vec4(texture2D(sTexture, tex_xy_l));\n    color_r.r = color_l.r; \n    gl_FragColor  = color_r;\n}" : "";
        int iGlCreateShader = GLES20.glCreateShader(35633);
        GLES20.glShaderSource(iGlCreateShader, "precision mediump float;uniform mat4 uMVPMatrix;uniform bool bSwapXY;attribute vec4 vPosition;attribute vec2 vTexCoord;varying vec2 texCoord;void main() {  gl_Position = uMVPMatrix * vPosition;if(bSwapXY)   texCoord = vec2(1.0 - vTexCoord.y, vTexCoord.x);else    texCoord = vTexCoord;}");
        GLES20.glCompileShader(iGlCreateShader);
        int iGlCreateShader2 = GLES20.glCreateShader(35632);
        GLES20.glShaderSource(iGlCreateShader2, str);
        GLES20.glCompileShader(iGlCreateShader2);
        int iGlCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iGlCreateProgram, iGlCreateShader);
        GLES20.glAttachShader(iGlCreateProgram, iGlCreateShader2);
        GLES20.glLinkProgram(iGlCreateProgram);
        GLES20.glDeleteShader(iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader2);
        this.K = iGlCreateProgram;
        int iGlCreateShader3 = GLES20.glCreateShader(35633);
        GLES20.glShaderSource(iGlCreateShader3, "precision mediump float;\nattribute vec4 vPosition;\nattribute vec2 vTexCoord;\nvarying vec2 texCoord;\nvoid main() {\n    gl_Position = vPosition;\n    texCoord = vTexCoord;\n}");
        GLES20.glCompileShader(iGlCreateShader3);
        int iGlCreateShader4 = GLES20.glCreateShader(35632);
        GLES20.glShaderSource(iGlCreateShader4, "precision mediump float;\nvarying vec2 texCoord;\nuniform sampler2D uDepthTexture;\nuniform vec2 uTexelSize;\nuniform vec2 uDirection;\nvoid main() {\n    float d = texture2D(uDepthTexture, texCoord).a * 0.227027;\n    d += texture2D(uDepthTexture, texCoord + uDirection * uTexelSize * 1.384615).a * 0.316216;\n    d += texture2D(uDepthTexture, texCoord - uDirection * uTexelSize * 1.384615).a * 0.316216;\n    d += texture2D(uDepthTexture, texCoord + uDirection * uTexelSize * 3.230769).a * 0.070270;\n    d += texture2D(uDepthTexture, texCoord - uDirection * uTexelSize * 3.230769).a * 0.070270;\n    gl_FragColor = vec4(d, d, d, d);\n}");
        GLES20.glCompileShader(iGlCreateShader4);
        int iGlCreateProgram2 = GLES20.glCreateProgram();
        GLES20.glAttachShader(iGlCreateProgram2, iGlCreateShader3);
        GLES20.glAttachShader(iGlCreateProgram2, iGlCreateShader4);
        GLES20.glLinkProgram(iGlCreateProgram2);
        GLES20.glDeleteShader(iGlCreateShader3);
        GLES20.glDeleteShader(iGlCreateShader4);
        this.P = iGlCreateProgram2;
        this.b0 = GLES20.glGetUniformLocation(this.K, "bSwapXY");
        this.c0 = GLES20.glGetAttribLocation(this.K, "vPosition");
        this.d0 = GLES20.glGetAttribLocation(this.K, "vTexCoord");
        this.e0 = GLES20.glGetUniformLocation(this.K, "uMVPMatrix");
        this.f0 = GLES20.glGetUniformLocation(this.K, "sTexture");
        this.g0 = GLES20.glGetUniformLocation(this.K, "cen_x");
        this.h0 = GLES20.glGetUniformLocation(this.K, "cen_y");
        this.i0 = GLES20.glGetUniformLocation(this.K, "k_off");
        this.j0 = GLES20.glGetUniformLocation(this.K, "depth");
        this.k0 = GLES20.glGetUniformLocation(this.K, "divergence");
        this.l0 = GLES20.glGetUniformLocation(this.K, "convergence");
        this.m0 = GLES20.glGetUniformLocation(this.K, "factor");
        this.n0 = GLES20.glGetUniformLocation(this.K, "preserveScreenBorder");
        this.o0 = GLES20.glGetUniformLocation(this.K, "edgeAwareDisparity");
        this.p0 = GLES20.glGetUniformLocation(this.K, "depthTexelSize");
        this.q0 = GLES20.glGetAttribLocation(this.P, "vPosition");
        this.r0 = GLES20.glGetAttribLocation(this.P, "vTexCoord");
        this.s0 = GLES20.glGetUniformLocation(this.P, "uDepthTexture");
        this.t0 = GLES20.glGetUniformLocation(this.P, "uTexelSize");
        this.u0 = GLES20.glGetUniformLocation(this.P, "uDirection");
        float fD = FloatingWindowManager.d() / FloatingWindowManager.c();
        float f = -fD;
        this.R = new float[]{-1.0f, f, -1.0f, 1.0f, f, -1.0f, -1.0f, fD, -1.0f, 1.0f, fD, -1.0f};
        GLES20.glGenBuffers(3, iArr, 0);
        GLES20.glBindBuffer(34962, iArr[0]);
        GLES20.glBufferData(34962, this.R.length * 4, this.U, 35044);
        GLES20.glBindBuffer(34962, iArr[1]);
        GLES20.glBufferData(34962, this.S.length * 4, this.V, 35044);
        GLES20.glBindBuffer(34963, iArr[2]);
        GLES20.glBufferData(34963, this.T.length * 2, this.W, 35044);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glBindBuffer(34963, 0);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        this.v0 = false;
        this.x0 = false;
        this.w0 = false;
        this.L = -1;
        this.Q = -1;
        this.M = -1;
        this.N = -1;
        this.O = -1;
        Log.d("BitmapRender", "onSurfaceCreated");
    }
}
