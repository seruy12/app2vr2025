package net.xq.appvrplayer.FloatingWindow;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import net.xq.appvrplayer.FloatingBall.FloatingBallService;
import net.xq.appvrplayer.FloatingWindow.FloatingPlayerViewService;
import net.xq.appvrplayer.R;
import net.xq.appvrplayer.application.MyApplication;

/* JADX INFO: loaded from: classes.dex */
public abstract class FloatingWindowManager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static FloatingPlayerView f4195b;
    public static WindowManager.LayoutParams c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Context f4196d;
    public static WindowManager e;
    public static Display f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static FloatingWindowMode f4194a = FloatingWindowMode.f4200b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f4197g = false;
    public static final AtomicBoolean h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicInteger f4198i = new AtomicInteger(0);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class FloatingWindowMode {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final FloatingWindowMode f4199a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final FloatingWindowMode f4200b;
        public static final FloatingWindowMode c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final /* synthetic */ FloatingWindowMode[] f4201d;

        static {
            FloatingWindowMode floatingWindowMode = new FloatingWindowMode("LandscapeVR", 0);
            f4199a = floatingWindowMode;
            FloatingWindowMode floatingWindowMode2 = new FloatingWindowMode("FullScreen", 1);
            f4200b = floatingWindowMode2;
            FloatingWindowMode floatingWindowMode3 = new FloatingWindowMode("Anaglyphy", 2);
            c = floatingWindowMode3;
            f4201d = new FloatingWindowMode[]{floatingWindowMode, floatingWindowMode2, floatingWindowMode3};
        }

        public static FloatingWindowMode valueOf(String str) {
            return (FloatingWindowMode) Enum.valueOf(FloatingWindowMode.class, str);
        }

        public static FloatingWindowMode[] values() {
            return (FloatingWindowMode[]) f4201d.clone();
        }
    }

    public static void a(boolean z) {
        if (f4196d == null || e == null) {
            f(MyApplication.f4272d);
        }
        if (f4196d == null || e == null) {
            Log.e("FloatingWindowManager", "Unable to create floating window: context is not initialized");
            return;
        }
        if (f4195b == null) {
            f4195b = new FloatingPlayerView(f4196d);
        }
        if (c == null) {
            c = new WindowManager.LayoutParams();
        }
        if (z) {
            Log.d("ACCESSIBILITY", "TYPE_ACCESSIBILITY_OVERLAY");
            c.type = 2032;
        } else {
            Log.d("ACCESSIBILITY", "no TYPE_ACCESSIBILITY_OVERLAY");
            c.type = 2038;
        }
        WindowManager.LayoutParams layoutParams = c;
        layoutParams.format = 1;
        layoutParams.gravity = 17;
        layoutParams.flags = 936;
        layoutParams.screenBrightness = 1.0f;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.setFitInsetsTypes(0);
        Rect rectB = b();
        c.height = rectB.width();
        c.width = rectB.height();
        WindowManager.LayoutParams layoutParams2 = c;
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        layoutParams2.windowAnimations = R.style.FloatingWindowAnimation;
    }

    public static Rect b() {
        Rect rect = new Rect();
        rect.set(0, 0, d(), c());
        return rect;
    }

    public static int c() {
        f = e.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        f.getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int d() {
        f = e.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        f.getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int e() {
        int rotation = e.getDefaultDisplay().getRotation();
        AtomicInteger atomicInteger = f4198i;
        atomicInteger.set(rotation);
        int i2 = atomicInteger.get();
        if (i2 == 0) {
            return 2;
        }
        if (i2 != 2) {
            return i2 != 3 ? 0 : 1;
        }
        return 3;
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        f4196d = context;
        if (e == null) {
            e = (WindowManager) context.getSystemService("window");
        }
        WindowManager windowManager = e;
        if (windowManager == null) {
            return;
        }
        f = windowManager.getDefaultDisplay();
        f.getRealMetrics(new DisplayMetrics());
    }

    public static boolean g() {
        WindowManager windowManager = e;
        if (windowManager == null) {
            return false;
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        f4198i.set(rotation);
        AtomicBoolean atomicBoolean = h;
        atomicBoolean.set(rotation == 1 || rotation == 3);
        return atomicBoolean.get();
    }

    public static void h() {
        FloatingPlayerView floatingPlayerView;
        if (!f4197g) {
            FloatingPlayerViewService.f4191b.getClass();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.gravity = 17;
            layoutParams.type = 2038;
            layoutParams.format = 1;
            layoutParams.windowAnimations = R.style.FloatingWindowAnimation;
            layoutParams.width = 0;
            layoutParams.height = 0;
            layoutParams.y = 0;
            layoutParams.x = 0;
            FloatingPlayerView floatingPlayerView2 = f4195b;
            floatingPlayerView = floatingPlayerView2 != null ? floatingPlayerView2 : null;
            if (floatingPlayerView == null || floatingPlayerView.getParent() != null) {
                return;
            }
            e.addView(floatingPlayerView, layoutParams);
            return;
        }
        FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.h;
        if (floatingPlayerAccessibilityService != null) {
            FloatingPlayerView floatingPlayerView3 = f4195b;
            floatingPlayerView = floatingPlayerView3 != null ? floatingPlayerView3 : null;
            if (floatingPlayerView == null || floatingPlayerView.getParent() != null) {
                return;
            }
            WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
            layoutParams2.gravity = 17;
            layoutParams2.type = 2032;
            layoutParams2.format = 1;
            layoutParams2.flags = 24;
            layoutParams2.width = 0;
            layoutParams2.height = 0;
            layoutParams2.x = 0;
            layoutParams2.y = 0;
            try {
                floatingPlayerAccessibilityService.f4157a.addView(floatingPlayerView, layoutParams2);
                floatingPlayerAccessibilityService.c = true;
                Log.d("FloatingPlayerA11yService", "accessibility placeholder view added");
            } catch (Exception e2) {
                Log.e("FloatingPlayerA11yService", "failed to add accessibility placeholder view: " + e2.getMessage());
            }
        }
    }

    public static void i() {
        if (!f4197g) {
            FloatingPlayerViewService.f4191b.getClass();
            try {
                FloatingPlayerView floatingPlayerView = f4195b;
                if (floatingPlayerView != null) {
                    floatingPlayerView = floatingPlayerView;
                }
                if (floatingPlayerView == null) {
                    return;
                }
                if (floatingPlayerView.getParent() == null) {
                    FloatingBallService floatingBallService = FloatingBallService.f4144k;
                    WindowManager.LayoutParams layoutParams = floatingBallService.f4146b;
                    floatingBallService.f(layoutParams.x, layoutParams.y);
                    return;
                } else {
                    if (floatingPlayerView.isAttachedToWindow()) {
                        e.removeView(floatingPlayerView);
                    }
                    FloatingBallService.f4144k.d();
                    FloatingPlayerViewService.a();
                    return;
                }
            } catch (Exception e2) {
                Log.e("Floating3DPlayerService", "Error in changePortraitOrLandscape", e2);
                return;
            }
        }
        FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.h;
        if (floatingPlayerAccessibilityService != null) {
            FloatingPlayerView floatingPlayerView2 = f4195b;
            floatingPlayerView = floatingPlayerView2 != null ? floatingPlayerView2 : null;
            if (floatingPlayerView == null) {
                return;
            }
            if (floatingPlayerView.getParent() == null) {
                FloatingBallService floatingBallService2 = FloatingBallService.f4144k;
                if (floatingBallService2 != null) {
                    WindowManager.LayoutParams layoutParams2 = floatingBallService2.f4146b;
                    floatingBallService2.f(layoutParams2.x, layoutParams2.y);
                    return;
                }
                return;
            }
            if (floatingPlayerView.isAttachedToWindow()) {
                try {
                    floatingPlayerAccessibilityService.f4157a.removeView(floatingPlayerView);
                    floatingPlayerAccessibilityService.c = false;
                } catch (Exception e3) {
                    Log.e("FloatingPlayerA11yService", "changePortraitOrLandscape failed to remove view: " + e3.getMessage());
                }
            }
            FloatingBallService floatingBallService3 = FloatingBallService.f4144k;
            if (floatingBallService3 != null) {
                floatingBallService3.d();
            }
            floatingPlayerAccessibilityService.a();
        }
    }

    public static void j() {
        if (f4197g) {
            FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.h;
            if (floatingPlayerAccessibilityService != null) {
                floatingPlayerAccessibilityService.c();
                return;
            }
            return;
        }
        FloatingPlayerViewService.f4191b.getClass();
        FloatingPlayerView floatingPlayerView = f4195b;
        if (floatingPlayerView == null) {
            floatingPlayerView = null;
        }
        if (floatingPlayerView != null) {
            Context context = MyApplication.f4272d;
            if (f4195b != null) {
                try {
                    if (e == null) {
                        e = (WindowManager) context.getSystemService("window");
                    }
                    FloatingPlayerView floatingPlayerView2 = f4195b;
                    if (floatingPlayerView2.isAttachedToWindow()) {
                        e.removeView(floatingPlayerView2);
                    }
                    f4195b.getBitmapRenderer().s.shutdownNow();
                    e.removeView(null);
                } catch (IllegalArgumentException unused) {
                }
                f4195b = null;
            }
        }
    }

    public static void k() {
        if (!f4197g) {
            FloatingPlayerViewService.f4191b.getClass();
            new Handler().postDelayed(new FloatingPlayerViewService.AnonymousClass1(), 100L);
        } else {
            FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.h;
            if (floatingPlayerAccessibilityService != null) {
                floatingPlayerAccessibilityService.d();
            }
        }
    }

    public static void l(int i2, int i3) {
        FloatingPlayerView floatingPlayerView;
        WindowManager.LayoutParams layoutParams;
        if (!f4197g) {
            FloatingPlayerViewService.f4191b.getClass();
            WindowManager.LayoutParams layoutParams2 = c;
            layoutParams2.x += i2;
            layoutParams2.y += i3;
            FloatingPlayerView floatingPlayerView2 = f4195b;
            floatingPlayerView = floatingPlayerView2 != null ? floatingPlayerView2 : null;
            if (floatingPlayerView != null) {
                e.updateViewLayout(floatingPlayerView, layoutParams2);
                return;
            }
            return;
        }
        FloatingPlayerAccessibilityService floatingPlayerAccessibilityService = FloatingPlayerAccessibilityService.h;
        if (floatingPlayerAccessibilityService == null || (layoutParams = floatingPlayerAccessibilityService.f4158b) == null || !floatingPlayerAccessibilityService.c) {
            return;
        }
        layoutParams.x += i2;
        layoutParams.y += i3;
        FloatingPlayerView floatingPlayerView3 = f4195b;
        floatingPlayerView = floatingPlayerView3 != null ? floatingPlayerView3 : null;
        if (floatingPlayerView != null) {
            try {
                floatingPlayerAccessibilityService.f4157a.updateViewLayout(floatingPlayerView, layoutParams);
            } catch (Exception e2) {
                Log.e("FloatingPlayerA11yService", "failed to move accessibility overlay: " + e2.getMessage());
            }
        }
    }

    public static void m(boolean z) {
        WindowManager.LayoutParams layoutParams;
        FloatingPlayerView floatingPlayerView;
        if (!f4197g || (layoutParams = c) == null || (floatingPlayerView = f4195b) == null || e == null) {
            return;
        }
        if (z) {
            layoutParams.flags |= 24;
        } else {
            layoutParams.flags = (layoutParams.flags & (-17)) | 8;
        }
        try {
            if (floatingPlayerView.isAttachedToWindow()) {
                e.updateViewLayout(floatingPlayerView, c);
            }
        } catch (Exception unused) {
        }
    }
}
