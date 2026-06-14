package net.xq.appvrplayer.FloatingWindow;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import net.xq.appvrplayer.FloatingBall.FloatingBallService;
import net.xq.appvrplayer.FloatingWindow.FloatingWindowManager;

/* JADX INFO: loaded from: classes.dex */
public class FloatingPlayerViewService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static FloatingPlayerViewService f4191b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ScreenStateReceiver f4192a;

    /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerViewService$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* JADX INFO: renamed from: net.xq.appvrplayer.FloatingWindow.FloatingPlayerViewService$1$1, reason: invalid class name and collision with other inner class name */
        class RunnableC00171 implements Runnable {
            @Override // java.lang.Runnable
            public final void run() {
                FloatingBallService.f4144k.a();
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
            if (floatingPlayerView == null) {
                floatingPlayerView = null;
            }
            if (floatingPlayerView == null || !floatingPlayerView.isAttachedToWindow()) {
                return;
            }
            FloatingWindowManager.e.removeView(floatingPlayerView);
            new Handler().postDelayed(new RunnableC00171(), 200L);
        }
    }

    public class ScreenStateReceiver extends BroadcastReceiver {
        public ScreenStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean zEquals = "android.intent.action.SCREEN_ON".equals(action);
            FloatingPlayerViewService floatingPlayerViewService = FloatingPlayerViewService.this;
            if (zEquals) {
                FloatingPlayerViewService floatingPlayerViewService2 = FloatingPlayerViewService.f4191b;
                Log.d("FloatingPlayerViewService", "screen turned on");
                Intent intent2 = new Intent("com.example.SCREEN_STATE_CHANGED");
                intent2.putExtra("screen_state", true);
                floatingPlayerViewService.sendBroadcast(intent2);
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                FloatingPlayerViewService floatingPlayerViewService3 = FloatingPlayerViewService.f4191b;
                Log.d("FloatingPlayerViewService", "screen turned off");
                Intent intent3 = new Intent("com.example.SCREEN_STATE_CHANGED");
                intent3.putExtra("screen_state", false);
                floatingPlayerViewService.sendBroadcast(intent3);
                new Handler().postDelayed(new AnonymousClass1(), 100L);
                return;
            }
            if ("android.intent.action.USER_PRESENT".equals(action)) {
                Log.d("FloatingPlayerViewService", "user present");
                FloatingPlayerViewService floatingPlayerViewService4 = FloatingPlayerViewService.f4191b;
                floatingPlayerViewService.sendBroadcast(new Intent("com.example.USER_PRESENT"));
                FloatingBallService.f4144k.c();
            }
        }
    }

    public static void a() {
        Rect rectB = FloatingWindowManager.b();
        WindowManager.LayoutParams layoutParams = FloatingWindowManager.c;
        layoutParams.gravity = 17;
        FloatingWindowManager.FloatingWindowMode floatingWindowMode = FloatingWindowManager.f4194a;
        if (floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4200b || floatingWindowMode == FloatingWindowManager.FloatingWindowMode.f4199a || floatingWindowMode == FloatingWindowManager.FloatingWindowMode.c) {
            layoutParams.width = rectB.width();
            layoutParams.height = rectB.height();
            layoutParams.y = 0;
            layoutParams.x = 0;
        }
        FloatingPlayerView floatingPlayerView = FloatingWindowManager.f4195b;
        if (floatingPlayerView == null) {
            floatingPlayerView = null;
        }
        if (floatingPlayerView == null || floatingPlayerView.getParent() != null) {
            return;
        }
        FloatingWindowManager.e.addView(floatingPlayerView, layoutParams);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        f4191b = this;
        FloatingWindowManager.f(getApplicationContext());
        this.f4192a = new ScreenStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.f4192a, intentFilter);
        Log.d("FloatingPlayerViewService", "screen state receiver registered");
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        ScreenStateReceiver screenStateReceiver = this.f4192a;
        if (screenStateReceiver != null) {
            unregisterReceiver(screenStateReceiver);
            this.f4192a = null;
            Log.d("FloatingPlayerViewService", "screen state receiver unregistered");
        }
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        FloatingWindowManager.f(getApplicationContext());
        FloatingWindowManager.a(false);
        return 1;
    }
}
