package net.xq.appvrplayer.FloatingWindow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.material.datepicker.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import net.xq.appvrplayer.FloatingBall.FloatingBallService;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;
import net.xq.appvrplayer.R;
import net.xq.appvrplayer.Utils.DepthUtil;
import net.xq.appvrplayer.Utils.PremiumManager;
import net.xq.appvrplayer.VideoProcessor.CustomGLSurfaceView;
import net.xq.appvrplayer.VideoProcessor.RenderBase;
import net.xq.appvrplayer.application.MyApplication;
import net.xq.appvrplayer.ui.activity.SpatialMainActivity;
import org.json.JSONObject;
import org.tensorflow.lite.examples.classification.tflite.Classifier;
import t.c;

/* JADX INFO: loaded from: classes.dex */
public class FloatingPlayerView extends FrameLayout implements FloatingPlayerInterface {
    public static FloatingPlayerView G;
    public final InnerReceiver A;
    public final WebView B;
    public int C;
    public boolean D;
    public boolean E;
    public final c F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RenderBase f4162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final GLSurfaceView f4163b;
    public final Handler c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final GestureDetector f4164d;
    public final ScaleGestureDetector e;
    public float f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final FrameLayout f4165g;
    public final LinearLayout h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ImageButton f4166i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ImageButton f4167j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ImageButton f4168k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ImageButton f4169l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ImageButton f4170m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ImageButton f4171n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ImageButton f4172o;
    public final ImageButton p;
    public final RelativeLayout q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final RelativeLayout f4173r;
    public final FrameLayout s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Handler f4174t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Runnable f4175u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4176v;
    public int w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4177x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final AtomicBoolean f4178y;
    public final AtomicInteger z;

    /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView$11, reason: invalid class name */
    class AnonymousClass11 extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
        }
    }

    /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView$2, reason: invalid class name */
    class AnonymousClass2 implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f4181a = new Handler(Looper.getMainLooper());

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f4182b = false;
        public int c;

        public AnonymousClass2() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            Handler handler = this.f4181a;
            if (action == 0) {
                this.c = view.getId();
                this.f4182b = false;
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        anonymousClass2.f4182b = true;
                        int i2 = anonymousClass2.c;
                        anonymousClass2.getClass();
                        Log.d("FloatingPlayerView", "button long press: " + i2);
                        FloatingPlayerView floatingPlayerView = FloatingPlayerView.this;
                        if (i2 == R.id.btn_up) {
                            FloatingPlayerView.b(floatingPlayerView, 0, -1);
                            return;
                        }
                        if (i2 == R.id.btn_down) {
                            FloatingPlayerView.b(floatingPlayerView, 0, 1);
                        } else if (i2 == R.id.btn_left) {
                            FloatingPlayerView.b(floatingPlayerView, -1, 0);
                        } else if (i2 == R.id.btn_right) {
                            FloatingPlayerView.b(floatingPlayerView, 1, 0);
                        }
                    }
                }, 500L);
                return true;
            }
            if (action == 1 || action == 3) {
                handler.removeCallbacksAndMessages(null);
                boolean z = this.f4182b;
                FloatingPlayerView floatingPlayerView = FloatingPlayerView.this;
                if (z) {
                    FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.G;
                    floatingPlayerView.i();
                    this.f4182b = false;
                    return true;
                }
                int id = view.getId();
                Log.d("FloatingPlayerView", "button click: " + id);
                if (id == R.id.btn_up) {
                    FloatingPlayerView.c(floatingPlayerView, 0, -5);
                    return true;
                }
                if (id == R.id.btn_down) {
                    FloatingPlayerView.c(floatingPlayerView, 0, 5);
                    return true;
                }
                if (id == R.id.btn_left) {
                    FloatingPlayerView.c(floatingPlayerView, -5, 0);
                    return true;
                }
                if (id == R.id.btn_right) {
                    FloatingPlayerView.c(floatingPlayerView, 5, 0);
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView$6, reason: invalid class name */
    class AnonymousClass6 implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
            spatialMainActivity.nativeOnDoubleTapEvent(spatialMainActivity.A);
        }
    }

    public class InnerReceiver extends BroadcastReceiver {
        public InnerReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (!"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) || (stringExtra = intent.getStringExtra("reason")) == null) {
                return;
            }
            Log.d("FloatingPlayerView", "system dialog close reason: ".concat(stringExtra));
            if (stringExtra.equals("homekey")) {
                Log.d("FloatingPlayerView", "user action: home");
            } else if (stringExtra.equals("recentapps")) {
                Log.d("FloatingPlayerView", "user action: recent apps");
            }
            FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
            FloatingPlayerView.this.d();
            FloatingWindowManager.k();
        }
    }

    public FloatingPlayerView(Context context) {
        super(context);
        this.f = 1.0f;
        this.f4174t = new Handler(Looper.getMainLooper());
        this.f4176v = 0;
        this.w = 0;
        this.f4177x = false;
        this.f4178y = new AtomicBoolean(false);
        this.z = new AtomicInteger(0);
        this.C = 0;
        this.D = false;
        this.E = false;
        this.F = new c(this, 0);
        G = this;
        setFocusable(false);
        setFocusableInTouchMode(false);
        this.A = new InnerReceiver();
        context.registerReceiver(this.A, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), null, null, 4);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.activity_gl, this);
        this.f4163b = (GLSurfaceView) viewInflate.findViewById(R.id.preview_view_gl);
        this.f4162a = ((CustomGLSurfaceView) findViewById(R.id.preview_view_gl)).getRenderer();
        this.c = new Handler(Looper.getMainLooper());
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.ui_progress_layout);
        this.s = frameLayout;
        DepthUtil depthUtil = MyApplication.e;
        boolean zEquals = MyApplication.f4272d.getSharedPreferences("AppSettings", 0).getString("ai_model", "speed").equals("speed");
        depthUtil.getClass();
        int i2 = !zEquals ? 2 : 1;
        boolean z = MyApplication.f4272d.getSharedPreferences("AppSettings", 0).getBoolean("true_3d_rendering1", false);
        PremiumManager.a(MyApplication.f4272d).getBoolean("is_premium", false);
        if (z && !(depthUtil.a() && i2 == depthUtil.c)) {
            frameLayout.setOnClickListener(new a(1, this));
            Executors.newSingleThreadExecutor().execute(new g.a(this, 1, new Handler(Looper.getMainLooper())));
            DepthUtil depthUtil2 = MyApplication.e;
            AtomicBoolean atomicBoolean = depthUtil2.f4247b;
            if (!atomicBoolean.get()) {
                boolean z2 = MyApplication.f4272d.getSharedPreferences("AppSettings", 0).getBoolean("true_3d_rendering1", false);
                PremiumManager.a(MyApplication.f4272d).getBoolean("is_premium", false);
                if (z2 && (!depthUtil2.a() || i2 != depthUtil2.c)) {
                    depthUtil2.f4246a = null;
                    depthUtil2.c = i2;
                    atomicBoolean.set(true);
                    try {
                        Classifier.create(new n.a(depthUtil2), SpatialMainActivity.H, Classifier.Model.QUANTIZED_EFFICIENTNET, Classifier.Device.GPU, 4);
                        Log.d(" DepthUtil", "Model initialization success");
                    } catch (Exception e) {
                        atomicBoolean.set(false);
                        depthUtil2.f4246a = null;
                        depthUtil2.c = -1;
                        String message = e.getMessage();
                        Objects.requireNonNull(message);
                        Log.d("DepthUtil", message);
                    }
                }
            }
        } else {
            frameLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.ui_alignment_marker_portrait);
        this.q = relativeLayout;
        RelativeLayout relativeLayout2 = (RelativeLayout) viewInflate.findViewById(R.id.ui_alignment_marker_landscape);
        this.f4173r = relativeLayout2;
        if (FloatingWindowManager.f4194a != FloatingWindowManager.FloatingWindowMode.f4199a) {
            relativeLayout.setTransitionAlpha(0.0f);
            relativeLayout2.setTransitionAlpha(0.0f);
        } else if (MyApplication.d(MyApplication.f4272d) || FloatingWindowManager.d() > FloatingWindowManager.c()) {
            relativeLayout.setTransitionAlpha(0.0f);
            relativeLayout2.setTransitionAlpha(1.0f);
        } else {
            relativeLayout.setTransitionAlpha(1.0f);
            relativeLayout2.setTransitionAlpha(0.0f);
        }
        setClickable(true);
        setFocusable(false);
        this.f4165g = (FrameLayout) viewInflate.findViewById(R.id.mask_layer);
        this.h = (LinearLayout) viewInflate.findViewById(R.id.control_panel);
        this.f4166i = (ImageButton) viewInflate.findViewById(R.id.btn_up);
        this.f4167j = (ImageButton) viewInflate.findViewById(R.id.btn_down);
        this.f4168k = (ImageButton) viewInflate.findViewById(R.id.btn_left);
        this.f4169l = (ImageButton) viewInflate.findViewById(R.id.btn_right);
        this.f4170m = (ImageButton) viewInflate.findViewById(R.id.btn_close);
        this.f4171n = (ImageButton) viewInflate.findViewById(R.id.btn_hide);
        this.f4172o = (ImageButton) viewInflate.findViewById(R.id.btn_help);
        this.p = (ImageButton) viewInflate.findViewById(R.id.btn_reset);
        if (FloatingWindowManager.f4194a == FloatingWindowManager.FloatingWindowMode.c) {
            this.f4172o.setVisibility(0);
        } else {
            this.f4172o.setVisibility(8);
        }
        this.f4165g.setOnClickListener(new View.OnClickListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
                FloatingPlayerView.this.d();
            }
        });
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        ImageButton imageButton = this.f4172o;
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.3

                /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView$3$1, reason: invalid class name */
                class AnonymousClass1 implements Runnable {
                    @Override // java.lang.Runnable
                    public final void run() {
                        FloatingBallService floatingBallService = FloatingBallService.f4144k;
                        if (floatingBallService != null) {
                            floatingBallService.c();
                            FloatingWindowManager.FloatingWindowMode floatingWindowMode = FloatingWindowManager.f4194a;
                            if (floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4200b) {
                                SpatialMainActivity.H.B = true;
                            } else if (floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4199a) {
                                SpatialMainActivity.H.C = true;
                            }
                        }
                    }
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Log.d("FloatingPlayerView", "exit button clicked");
                    FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
                    FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.this;
                    floatingPlayerView2.d();
                    floatingPlayerView2.f4172o.setVisibility(8);
                    floatingPlayerView2.s.setVisibility(8);
                    new Handler().postDelayed(new AnonymousClass1(), 100L);
                }
            });
        }
        ImageButton imageButton2 = this.f4166i;
        if (imageButton2 != null) {
            imageButton2.setOnTouchListener(anonymousClass2);
        }
        ImageButton imageButton3 = this.f4167j;
        if (imageButton3 != null) {
            imageButton3.setOnTouchListener(anonymousClass2);
        }
        ImageButton imageButton4 = this.f4168k;
        if (imageButton4 != null) {
            imageButton4.setOnTouchListener(anonymousClass2);
        }
        ImageButton imageButton5 = this.f4169l;
        if (imageButton5 != null) {
            imageButton5.setOnTouchListener(anonymousClass2);
        }
        ImageButton imageButton6 = this.f4170m;
        if (imageButton6 != null) {
            imageButton6.setOnClickListener(new View.OnClickListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.4

                /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView$4$1, reason: invalid class name */
                class AnonymousClass1 implements Runnable {
                    @Override // java.lang.Runnable
                    public final void run() {
                        FloatingBallService floatingBallService = FloatingBallService.f4144k;
                        if (floatingBallService != null) {
                            floatingBallService.c();
                        }
                    }
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Log.d("FloatingPlayerView", "exit button clicked");
                    FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
                    FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.this;
                    floatingPlayerView2.d();
                    floatingPlayerView2.s.setVisibility(8);
                    new Handler().postDelayed(new AnonymousClass1(), 100L);
                }
            });
        }
        ImageButton imageButton7 = this.f4171n;
        if (imageButton7 != null) {
            imageButton7.setOnClickListener(new View.OnClickListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
                    FloatingPlayerView.this.d();
                    FloatingWindowManager.k();
                }
            });
        }
        ImageButton imageButton8 = this.p;
        if (imageButton8 != null) {
            imageButton8.setOnClickListener(new AnonymousClass6());
        }
        this.f4165g.setBackgroundColor(0);
        this.h.setVisibility(8);
        this.f4172o.setVisibility(8);
        this.e = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.9
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                FloatingPlayerView floatingPlayerView = FloatingPlayerView.this;
                if (floatingPlayerView.f4177x) {
                    return false;
                }
                float scaleFactor = floatingPlayerView.f * scaleGestureDetector.getScaleFactor();
                floatingPlayerView.f = scaleFactor;
                float fMax = Math.max(0.2f, Math.min(scaleFactor, 5.0f));
                floatingPlayerView.f = fMax;
                floatingPlayerView.f4162a.f(fMax);
                return true;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return !FloatingPlayerView.this.f4177x;
            }
        });
        GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.10
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public final boolean onDoubleTap(MotionEvent motionEvent) {
                Log.d("FloatingPlayerView", "onDoubleTap called");
                FloatingPlayerView floatingPlayerView = FloatingPlayerView.this;
                if (floatingPlayerView.f4177x) {
                    return true;
                }
                if (FloatingWindowManager.f4194a == FloatingWindowManager.FloatingWindowMode.f4199a) {
                    SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
                    spatialMainActivity.nativeOnDoubleTapEvent(spatialMainActivity.A);
                    return true;
                }
                floatingPlayerView.f = 1.0f;
                floatingPlayerView.f4162a.f(1.0f);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return FloatingPlayerView.this.f4177x;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                Log.d("FloatingPlayerView", "onSingleTapConfirmed called");
                FloatingPlayerView floatingPlayerView = FloatingPlayerView.G;
                StringBuilder sb = new StringBuilder("toggleMaskLayer, isMaskVisible: ");
                FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.this;
                sb.append(floatingPlayerView2.f4177x);
                Log.d("FloatingPlayerView", sb.toString());
                boolean z3 = floatingPlayerView2.f4177x;
                if (z3) {
                    floatingPlayerView2.d();
                } else if (floatingPlayerView2.f4165g != null && !z3) {
                    Log.d("FloatingPlayerView", "show mask layer");
                    floatingPlayerView2.f4177x = true;
                    floatingPlayerView2.h.setVisibility(0);
                    if (FloatingWindowManager.f4194a != FloatingWindowManager.FloatingWindowMode.c) {
                        floatingPlayerView2.f4172o.setVisibility(0);
                    }
                    ImageButton imageButton9 = floatingPlayerView2.f4166i;
                    if (imageButton9 != null) {
                        imageButton9.setVisibility(0);
                    }
                    ImageButton imageButton10 = floatingPlayerView2.f4167j;
                    if (imageButton10 != null) {
                        imageButton10.setVisibility(0);
                    }
                    ImageButton imageButton11 = floatingPlayerView2.f4168k;
                    if (imageButton11 != null) {
                        imageButton11.setVisibility(0);
                    }
                    ImageButton imageButton12 = floatingPlayerView2.f4169l;
                    if (imageButton12 != null) {
                        imageButton12.setVisibility(0);
                    }
                    ImageButton imageButton13 = floatingPlayerView2.f4170m;
                    if (imageButton13 != null) {
                        imageButton13.setVisibility(0);
                    }
                    ImageButton imageButton14 = floatingPlayerView2.f4171n;
                    if (imageButton14 != null) {
                        imageButton14.setVisibility(0);
                    }
                    if (FloatingWindowManager.f4197g) {
                        FloatingWindowManager.m(false);
                    }
                }
                FloatingBallService floatingBallService = FloatingBallService.f4144k;
                if (floatingBallService != null) {
                    try {
                        ImageButton imageButton15 = floatingBallService.f4147d;
                        if (imageButton15 != null) {
                            imageButton15.setAlpha(1.0f);
                        }
                    } catch (Exception e2) {
                        Log.e("FloatingBallService", "Error in setFloatingBallAlpha", e2);
                    }
                }
                return true;
            }
        });
        this.f4164d = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.z.set(FloatingWindowManager.e());
        this.B = new WebView(getContext());
        this.B.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.B.setClickable(false);
        this.B.setFocusable(false);
        this.B.setVisibility(8);
        this.B.setBackgroundColor(0);
        WebSettings settings = this.B.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        this.B.setWebViewClient(new AnonymousClass11());
        addView(this.B);
    }

    public static void a(FloatingPlayerView floatingPlayerView) {
        Bitmap bitmap;
        FrameLayout frameLayout = floatingPlayerView.s;
        if (frameLayout != null && frameLayout.getVisibility() == 0) {
            DepthUtil depthUtil = MyApplication.e;
            if (depthUtil.a() && depthUtil.f4247b.get()) {
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
            }
        }
        if (MyApplication.e.a()) {
            RenderBase renderBase = floatingPlayerView.f4162a;
            if (renderBase.e != null && MyApplication.f.f4250a && MyApplication.e.a() && (bitmap = renderBase.e) != null) {
                renderBase.d(bitmap.getWidth(), renderBase.e.getHeight());
            }
            floatingPlayerView.h();
        }
    }

    public static void b(FloatingPlayerView floatingPlayerView, int i2, int i3) {
        floatingPlayerView.i();
        floatingPlayerView.f4176v = i2;
        floatingPlayerView.w = i3;
        Runnable runnable = floatingPlayerView.f4175u;
        Handler handler = floatingPlayerView.f4174t;
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: net.xq.appvrplayer.FloatingWindow.FloatingPlayerView.7
            @Override // java.lang.Runnable
            public final void run() {
                FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.this;
                FloatingPlayerView.c(floatingPlayerView2, floatingPlayerView2.f4176v, floatingPlayerView2.w);
                floatingPlayerView2.f4174t.postDelayed(this, 16L);
            }
        };
        floatingPlayerView.f4175u = runnable2;
        handler.postDelayed(runnable2, 16L);
    }

    public static void c(FloatingPlayerView floatingPlayerView, int i2, int i3) {
        floatingPlayerView.getClass();
        try {
            FloatingPlayerView floatingPlayerView2 = FloatingWindowManager.f4195b;
            if (floatingPlayerView2 == null) {
                floatingPlayerView2 = null;
            }
            if (floatingPlayerView2 != null) {
                synchronized (MyApplication.f4273g.f4275b) {
                    floatingPlayerView.f4178y.set(true);
                    FloatingWindowManager.l(i2, i3);
                }
            }
        } catch (Exception e) {
            Log.e("FloatingPlayerView", "failed to move floating window: " + e.getMessage());
        }
    }

    public static FloatingPlayerView getInstance() {
        return G;
    }

    private void setGuideHintText(String str) {
        WebView webView = this.B;
        if (webView == null || webView == null || str == null) {
            return;
        }
        try {
            webView.evaluateJavascript("setHintText(" + JSONObject.quote(str) + ")", null);
        } catch (Exception e) {
            Log.w("FloatingPlayerView", "setGuideHintText failed", e);
        }
    }

    public final void d() {
        if (this.f4165g == null || !this.f4177x) {
            return;
        }
        Log.d("FloatingPlayerView", "hide mask layer");
        this.f4177x = false;
        this.h.setVisibility(8);
        this.f4172o.setVisibility(8);
        this.f4165g.setBackgroundColor(0);
        i();
        ImageButton imageButton = this.f4166i;
        if (imageButton != null) {
            imageButton.setPressed(false);
            this.f4166i.clearAnimation();
        }
        ImageButton imageButton2 = this.f4167j;
        if (imageButton2 != null) {
            imageButton2.setPressed(false);
            this.f4167j.clearAnimation();
        }
        ImageButton imageButton3 = this.f4168k;
        if (imageButton3 != null) {
            imageButton3.setPressed(false);
            this.f4168k.clearAnimation();
        }
        ImageButton imageButton4 = this.f4169l;
        if (imageButton4 != null) {
            imageButton4.setPressed(false);
            this.f4169l.clearAnimation();
        }
        ImageButton imageButton5 = this.f4170m;
        if (imageButton5 != null) {
            imageButton5.setPressed(false);
            this.f4170m.clearAnimation();
        }
        ImageButton imageButton6 = this.f4171n;
        if (imageButton6 != null) {
            imageButton6.setPressed(false);
            this.f4171n.clearAnimation();
        }
        if (FloatingWindowManager.f4197g) {
            FloatingWindowManager.m(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() == 1) {
            Log.d("FloatingPlayerView", "Back gesture detected via dispatchKeyEvent");
            d();
            FloatingWindowManager.k();
        }
        return true;
    }

    public final boolean e() {
        return this.D && this.C > 0;
    }

    public final void f(String str) {
        WebView webView = this.B;
        if (webView == null) {
            return;
        }
        webView.setVisibility(0);
        String string = "double_tap_volume_down_reset_gyro.html".equals(str) ? getContext().getString(R.string.accessibility_guide_gyro_reset) : "double_tap_volume_up_exit_page.html".equals(str) ? getContext().getString(R.string.accessibility_guide_exit_page) : "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getContext().getAssets().open(str), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    String strReplace = sb.toString().replace("{{HINT_TEXT}}", string);
                    this.B.loadDataWithBaseURL("file:///android_asset/" + str, strReplace, "text/html", "UTF-8", null);
                    return;
                }
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            Log.w("FloatingPlayerView", "Failed to load guide animation: ".concat(str), e);
        }
    }

    public final void g() {
        if (this.D && this.C == 1) {
            if (MyApplication.a()) {
                SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
                if (spatialMainActivity != null) {
                    spatialMainActivity.nativeOnDoubleTapEvent(spatialMainActivity.A);
                }
                this.C = 2;
                f("double_tap_volume_up_exit_page.html");
                return;
            }
            boolean z = this.E;
            Handler handler = this.c;
            c cVar = this.F;
            if (z) {
                handler.removeCallbacks(cVar);
                this.C = 2;
                f("double_tap_volume_up_exit_page.html");
            } else {
                this.E = true;
                setGuideHintText(getContext().getString(R.string.accessibility_guide_gyro_disabled));
                handler.postDelayed(cVar, 1000L);
            }
        }
    }

    @Override // net.xq.appvrplayer.FloatingWindow.FloatingPlayerInterface
    public RenderBase getBitmapRenderer() {
        return this.f4162a;
    }

    public View getFloatingView() {
        return null;
    }

    public int getGuideStep() {
        return this.C;
    }

    public TextureView getPreviewView() {
        return null;
    }

    public int getXLeft() {
        return 0;
    }

    public int getYTop() {
        return 0;
    }

    public final void h() {
        this.f4163b.requestRender();
    }

    public final void i() {
        Runnable runnable = this.f4175u;
        if (runnable != null) {
            this.f4174t.removeCallbacks(runnable);
            this.f4175u = null;
            Log.d("FloatingPlayerView", "continuous move stopped");
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.f4177x;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (FloatingWindowManager.f4194a == FloatingWindowManager.FloatingWindowMode.f4199a) {
            boolean zD = MyApplication.d(MyApplication.f4272d);
            RelativeLayout relativeLayout = this.f4173r;
            RelativeLayout relativeLayout2 = this.q;
            if (zD) {
                relativeLayout2.setTransitionAlpha(0.0f);
                relativeLayout.setTransitionAlpha(1.0f);
            } else if (FloatingWindowManager.d() > FloatingWindowManager.c()) {
                relativeLayout2.setTransitionAlpha(0.0f);
                relativeLayout.setTransitionAlpha(1.0f);
            } else {
                relativeLayout2.setTransitionAlpha(1.0f);
                relativeLayout.setTransitionAlpha(0.0f);
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f4177x) {
            return true;
        }
        ScaleGestureDetector scaleGestureDetector = this.e;
        if (scaleGestureDetector != null) {
            scaleGestureDetector.onTouchEvent(motionEvent);
        }
        GestureDetector gestureDetector = this.f4164d;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
