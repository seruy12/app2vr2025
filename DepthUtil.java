package net.xq.appvrplayer.Utils;

import java.util.concurrent.atomic.AtomicBoolean;
import org.tensorflow.lite.examples.classification.tflite.Classifier;

/* JADX INFO: loaded from: classes.dex */
public class DepthUtil {
    public int c = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Classifier f4246a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f4247b = new AtomicBoolean(false);

    public final boolean a() {
        return this.f4246a != null;
    }
}
