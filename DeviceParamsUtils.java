package com.google.cardboard.sdk.deviceparams;

import android.util.Log;
import com.google.cardboard.proto.CardboardDevice;
import com.google.cardboard.sdk.UsedByNative;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes.dex */
public class DeviceParamsUtils {
    @UsedByNative
    public static CardboardDevice.DeviceParams parseCardboardDeviceParams(byte[] bArr) {
        try {
            return CardboardDevice.DeviceParams.parseFrom(bArr, ExtensionRegistryLite.a());
        } catch (InvalidProtocolBufferException e) {
            Log.w("DeviceParamsUtils", "Parsing cardboard parameters from buffer failed: " + e);
            return null;
        }
    }
}
