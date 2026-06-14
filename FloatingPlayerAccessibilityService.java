package net.xq.appvrplayer.FloatingWindow;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.webkit.WebView;
import net.xq.appvrplayer.FloatingBall.FloatingBallService;
import net.xq.appvrplayer.R;
import net.xq.appvrplayer.application.MyApplication;
import net.xq.appvrplayer.ui.activity.SettingsActivity;
import net.xq.appvrplayer.ui.activity.SpatialMainActivity;
import t.a;

/* JADX INFO: loaded from: classes.dex */
public class FloatingPlayerAccessibilityService extends AccessibilityService {
    public static FloatingPlayerAccessibilityService h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WindowManager f4157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WindowManager.LayoutParams f4158b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ScreenStateReceiver f4159d;
    public boolean c = false;
    public long e = 0;
    public long f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4160g = false;

    public class ScreenStateReceiver extends BroadcastReceiver {
        public ScreenStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            FloatingBallService floatingBallService;
            String action = intent.getAction();
            boolean zEquals = "android.intent.action.SCREEN_ON".equals(action);
            FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.this;
            if (zEquals) {
                FloatingPlayerAccessibilityService floatingPlayerAccessibilityService2 = FloatingPlayerAccessibilityService.h;
                floatingPlayerAccessibilityService.getClass();
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                FloatingPlayerAccessibilityService floatingPlayerAccessibilityService3 = FloatingPlayerAccessibilityService.h;
                floatingPlayerAccessibilityService.getClass();
                floatingPlayerAccessibilityService.d();
            } else {
                if (!"android.intent.action.USER_PRESENT".equals(action) || (floatingBallService = FloatingBallService.f4144k) == null) {
                    return;
                }
                floatingBallService.c();
            }
        }
    }

    public final void a() {
        FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
        if (floatingPlayerView == null) {
            floatingPlayerView = null;
        }
        if (floatingPlayerView == null || this.f4158b == null) {
            return;
        }
        if (floatingPlayerView.getParent() != null) {
            try {
                this.f4158b.gravity = 17;
                Rect rectB = FloatingWindowManager.b();
                this.f4158b.width = rectB.width();
                this.f4158b.height = rectB.height();
                WindowManager.LayoutParams layoutParams = this.f4158b;
                layoutParams.y = 0;
                layoutParams.x = 0;
                this.f4157a.updateViewLayout(floatingPlayerView, layoutParams);
                this.c = true;
                return;
            } catch (Exception e) {
                Log.e("FloatingPlayerA11yService", "failed to update accessibility overlay layout: " + e.getMessage());
                return;
            }
        }
        try {
            this.f4158b.gravity = 17;
            Rect rectB2 = FloatingWindowManager.b();
            this.f4158b.width = rectB2.width();
            this.f4158b.height = rectB2.height();
            WindowManager.LayoutParams layoutParams2 = this.f4158b;
            layoutParams2.y = 0;
            layoutParams2.x = 0;
            this.f4157a.addView(floatingPlayerView, layoutParams2);
            this.c = true;
            Log.d("FloatingPlayerA11yService", "accessibility overlay added to WindowManager");
            if (this.f4160g) {
                return;
            }
            new Handler().postDelayed(new a(this, 0), 800L);
        } catch (Exception e2) {
            Log.e("FloatingPlayerA11yService", "failed to add accessibility overlay: " + e2.getMessage());
        }
    }

    public final void b() {
        FloatingWindowManager.f(getApplicationContext());
        FloatingWindowManager.a(true);
        WindowManager.LayoutParams layoutParams = FloatingWindowManager.c;
        this.f4158b = layoutParams;
        layoutParams.type = 2032;
        layoutParams.format = 1;
        layoutParams.gravity = 17;
        layoutParams.flags = 920;
        layoutParams.screenBrightness = 1.0f;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.setFitInsetsTypes(0);
        this.f4158b.windowAnimations = R.style.FloatingWindowAnimation;
        Rect rectB = FloatingWindowManager.b();
        this.f4158b.height = rectB.width();
        this.f4158b.width = rectB.height();
        WindowManager.LayoutParams layoutParams2 = this.f4158b;
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        Log.d("FloatingPlayerA11yService", "accessibility overlay params created; touch passthrough enabled");
    }

    public final void c() {
        FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
        if (floatingPlayerView == null) {
            floatingPlayerView = null;
        }
        if (floatingPlayerView != null && floatingPlayerView.isAttachedToWindow()) {
            try {
                this.f4157a.removeView(floatingPlayerView);
            } catch (Exception e) {
                Log.e("FloatingPlayerA11yService", "failed to destroy accessibility overlay: " + e.getMessage());
            }
            this.c = false;
        }
        FloatingWindowManager.f4195b = null;
    }

    public final void d() {
        FloatingPlayerView floatingPlayerView = FloatingPlayerView.getInstance();
        if (floatingPlayerView != null && floatingPlayerView.e()) {
            floatingPlayerView.C = 0;
            floatingPlayerView.D = false;
            floatingPlayerView.c.removeCallbacks(floatingPlayerView.F);
            WebView webView = floatingPlayerView.B;
            if (webView != null) {
                webView.setVisibility(8);
            }
        }
        new Handler().postDelayed(new a(this, 1), 100L);
    }

    public final void e() {
        try {
            Vibrator vibrator = (Vibrator) getSystemService("vibrator");
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(50L, -1));
                return;
            }
            Log.d("FloatingPlayerA11yService", "triggerVibration: no vibrator available");
        } catch (Exception e) {
            Log.w("FloatingPlayerA11yService", "triggerVibration failed", e);
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        h = this;
        this.f4157a = (WindowManager) getSystemService("window");
        this.f4159d = new ScreenStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.f4159d, intentFilter);
        Log.d("FloatingPlayerA11yService", "screen state receiver registered");
        Log.d("FloatingPlayerA11yService", "accessibility service created");
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        h = null;
        this.f4160g = false;
        Context context = MyApplication.f4272d;
        ScreenStateReceiver screenStateReceiver = this.f4159d;
        if (screenStateReceiver != null) {
            try {
                unregisterReceiver(screenStateReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.f4159d = null;
        }
        Log.d("FloatingPlayerA11yService", "accessibility service destroyed");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
        Log.d("FloatingPlayerA11yService", "accessibility service interrupted");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final boolean onKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 24) {
            if (keyEvent.getAction() == 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j2 = this.e;
                if (jCurrentTimeMillis - j2 < 400 && j2 > 0) {
                    this.e = 0L;
                    if (this.c) {
                        e();
                        FloatingPlayerView floatingPlayerView = FloatingPlayerView.getInstance();
                        if (floatingPlayerView == null || !floatingPlayerView.e() || floatingPlayerView.getGuideStep() != 2) {
                            if (floatingPlayerView != null && floatingPlayerView.e()) {
                                Log.d("FloatingPlayerA11yService", "guide: current step is gyroscope setup; ignoring volume input");
                                return true;
                            }
                            Log.d("FloatingPlayerA11yService", "volume up double click detected; hide floating window and show floating ball");
                            d();
                            return true;
                        }
                        Log.d("FloatingPlayerA11yService", "guide: volume up double click, exit guide");
                        if (floatingPlayerView.D && floatingPlayerView.C == 2) {
                            floatingPlayerView.C = 0;
                            floatingPlayerView.D = false;
                            floatingPlayerView.c.removeCallbacks(floatingPlayerView.F);
                            WebView webView = floatingPlayerView.B;
                            if (webView != null) {
                                webView.setVisibility(8);
                            }
                            int i2 = SettingsActivity.W;
                            SharedPreferences sharedPreferences = MyApplication.f4272d.getSharedPreferences("AppSettings", 0);
                            sharedPreferences.edit().putInt("NumAccessibilityGuide", sharedPreferences.getInt("NumAccessibilityGuide", 0) + 1).apply();
                        }
                        d();
                        return true;
                    }
                }
                this.e = jCurrentTimeMillis;
                return false;
            }
        } else {
            if (keyEvent.getKeyCode() != 25) {
                return super.onKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                long j3 = this.f;
                if (jCurrentTimeMillis2 - j3 < 400 && j3 > 0) {
                    this.f = 0L;
                    if (this.c) {
                        e();
                        FloatingPlayerView floatingPlayerView2 = FloatingPlayerView.getInstance();
                        if (floatingPlayerView2 != null && floatingPlayerView2.e()) {
                            Log.d("FloatingPlayerA11yService", "guide: volume down double click");
                            floatingPlayerView2.g();
                            return true;
                        }
                        Log.d("FloatingPlayerA11yService", "volume down double click detected; reset Cardboard center");
                        SpatialMainActivity spatialMainActivity = SpatialMainActivity.H;
                        if (spatialMainActivity != null) {
                            spatialMainActivity.nativeOnDoubleTapEvent(spatialMainActivity.A);
                        }
                        return true;
                    }
                }
                this.f = jCurrentTimeMillis2;
            }
        }
        return false;
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onServiceConnected() {
        super.onServiceConnected();
        Context context = MyApplication.f4272d;
        if (FloatingWindowManager.f4197g) {
            b();
        }
        Log.d("FloatingPlayerA11yService", "accessibility service connected");
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        Context context = MyApplication.f4272d;
        c();
        return super.onUnbind(intent);
    }
}
