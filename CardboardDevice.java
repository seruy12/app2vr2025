package com.google.cardboard.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CardboardDevice {

    /* JADX INFO: renamed from: com.google.cardboard.proto.CardboardDevice$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[1] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class DeviceParams extends GeneratedMessageLite<DeviceParams, Builder> implements DeviceParamsOrBuilder {
        private static final DeviceParams DEFAULT_INSTANCE;
        public static final int DISTORTION_COEFFICIENTS_FIELD_NUMBER = 7;
        public static final int INTER_LENS_DISTANCE_FIELD_NUMBER = 4;
        public static final int LEFT_EYE_FIELD_OF_VIEW_ANGLES_FIELD_NUMBER = 5;
        public static final int MODEL_FIELD_NUMBER = 2;
        private static volatile Parser<DeviceParams> PARSER = null;
        public static final int PRIMARY_BUTTON_FIELD_NUMBER = 12;
        public static final int SCREEN_TO_LENS_DISTANCE_FIELD_NUMBER = 3;
        public static final int TRAY_TO_LENS_DISTANCE_FIELD_NUMBER = 6;
        public static final int VENDOR_FIELD_NUMBER = 1;
        public static final int VERTICAL_ALIGNMENT_FIELD_NUMBER = 11;
        private int bitField0_;
        private float interLensDistance_;
        private float screenToLensDistance_;
        private float trayToLensDistance_;
        private int verticalAlignment_;
        private int leftEyeFieldOfViewAnglesMemoizedSerializedSize = -1;
        private int distortionCoefficientsMemoizedSerializedSize = -1;
        private String vendor_ = "";
        private String model_ = "";
        private Internal.FloatList leftEyeFieldOfViewAngles_ = GeneratedMessageLite.emptyFloatList();
        private Internal.FloatList distortionCoefficients_ = GeneratedMessageLite.emptyFloatList();
        private int primaryButton_ = 1;

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceParams, Builder> implements DeviceParamsOrBuilder {
            public /* synthetic */ Builder(int i2) {
                this();
            }

            public Builder addAllDistortionCoefficients(Iterable<? extends Float> iterable) {
                copyOnWrite();
                ((DeviceParams) this.instance).addAllDistortionCoefficients(iterable);
                return this;
            }

            public Builder addAllLeftEyeFieldOfViewAngles(Iterable<? extends Float> iterable) {
                copyOnWrite();
                ((DeviceParams) this.instance).addAllLeftEyeFieldOfViewAngles(iterable);
                return this;
            }

            public Builder addDistortionCoefficients(float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).addDistortionCoefficients(f);
                return this;
            }

            public Builder addLeftEyeFieldOfViewAngles(float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).addLeftEyeFieldOfViewAngles(f);
                return this;
            }

            public Builder clearDistortionCoefficients() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearDistortionCoefficients();
                return this;
            }

            public Builder clearInterLensDistance() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearInterLensDistance();
                return this;
            }

            public Builder clearLeftEyeFieldOfViewAngles() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearLeftEyeFieldOfViewAngles();
                return this;
            }

            public Builder clearModel() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearModel();
                return this;
            }

            public Builder clearPrimaryButton() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearPrimaryButton();
                return this;
            }

            public Builder clearScreenToLensDistance() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearScreenToLensDistance();
                return this;
            }

            public Builder clearTrayToLensDistance() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearTrayToLensDistance();
                return this;
            }

            public Builder clearVendor() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearVendor();
                return this;
            }

            public Builder clearVerticalAlignment() {
                copyOnWrite();
                ((DeviceParams) this.instance).clearVerticalAlignment();
                return this;
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public float getDistortionCoefficients(int i2) {
                return ((DeviceParams) this.instance).getDistortionCoefficients(i2);
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public int getDistortionCoefficientsCount() {
                return ((DeviceParams) this.instance).getDistortionCoefficientsCount();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public List<Float> getDistortionCoefficientsList() {
                return Collections.unmodifiableList(((DeviceParams) this.instance).getDistortionCoefficientsList());
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public float getInterLensDistance() {
                return ((DeviceParams) this.instance).getInterLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public float getLeftEyeFieldOfViewAngles(int i2) {
                return ((DeviceParams) this.instance).getLeftEyeFieldOfViewAngles(i2);
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public int getLeftEyeFieldOfViewAnglesCount() {
                return ((DeviceParams) this.instance).getLeftEyeFieldOfViewAnglesCount();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public List<Float> getLeftEyeFieldOfViewAnglesList() {
                return Collections.unmodifiableList(((DeviceParams) this.instance).getLeftEyeFieldOfViewAnglesList());
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public String getModel() {
                return ((DeviceParams) this.instance).getModel();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public ByteString getModelBytes() {
                return ((DeviceParams) this.instance).getModelBytes();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public ButtonType getPrimaryButton() {
                return ((DeviceParams) this.instance).getPrimaryButton();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public float getScreenToLensDistance() {
                return ((DeviceParams) this.instance).getScreenToLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public float getTrayToLensDistance() {
                return ((DeviceParams) this.instance).getTrayToLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public String getVendor() {
                return ((DeviceParams) this.instance).getVendor();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public ByteString getVendorBytes() {
                return ((DeviceParams) this.instance).getVendorBytes();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public VerticalAlignmentType getVerticalAlignment() {
                return ((DeviceParams) this.instance).getVerticalAlignment();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasInterLensDistance() {
                return ((DeviceParams) this.instance).hasInterLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasModel() {
                return ((DeviceParams) this.instance).hasModel();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasPrimaryButton() {
                return ((DeviceParams) this.instance).hasPrimaryButton();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasScreenToLensDistance() {
                return ((DeviceParams) this.instance).hasScreenToLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasTrayToLensDistance() {
                return ((DeviceParams) this.instance).hasTrayToLensDistance();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasVendor() {
                return ((DeviceParams) this.instance).hasVendor();
            }

            @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
            public boolean hasVerticalAlignment() {
                return ((DeviceParams) this.instance).hasVerticalAlignment();
            }

            public Builder setDistortionCoefficients(int i2, float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).setDistortionCoefficients(i2, f);
                return this;
            }

            public Builder setInterLensDistance(float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).setInterLensDistance(f);
                return this;
            }

            public Builder setLeftEyeFieldOfViewAngles(int i2, float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).setLeftEyeFieldOfViewAngles(i2, f);
                return this;
            }

            public Builder setModel(String str) {
                copyOnWrite();
                ((DeviceParams) this.instance).setModel(str);
                return this;
            }

            public Builder setModelBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceParams) this.instance).setModelBytes(byteString);
                return this;
            }

            public Builder setPrimaryButton(ButtonType buttonType) {
                copyOnWrite();
                ((DeviceParams) this.instance).setPrimaryButton(buttonType);
                return this;
            }

            public Builder setScreenToLensDistance(float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).setScreenToLensDistance(f);
                return this;
            }

            public Builder setTrayToLensDistance(float f) {
                copyOnWrite();
                ((DeviceParams) this.instance).setTrayToLensDistance(f);
                return this;
            }

            public Builder setVendor(String str) {
                copyOnWrite();
                ((DeviceParams) this.instance).setVendor(str);
                return this;
            }

            public Builder setVendorBytes(ByteString byteString) {
                copyOnWrite();
                ((DeviceParams) this.instance).setVendorBytes(byteString);
                return this;
            }

            public Builder setVerticalAlignment(VerticalAlignmentType verticalAlignmentType) {
                copyOnWrite();
                ((DeviceParams) this.instance).setVerticalAlignment(verticalAlignmentType);
                return this;
            }

            private Builder() {
                super(DeviceParams.DEFAULT_INSTANCE);
            }
        }

        public enum ButtonType implements Internal.EnumLite {
            NONE(0),
            MAGNET(1),
            TOUCH(2),
            INDIRECT_TOUCH(3);

            public static final int INDIRECT_TOUCH_VALUE = 3;
            public static final int MAGNET_VALUE = 1;
            public static final int NONE_VALUE = 0;
            public static final int TOUCH_VALUE = 2;
            private static final Internal.EnumLiteMap<ButtonType> internalValueMap = new Internal.EnumLiteMap<ButtonType>() { // from class: com.google.cardboard.proto.CardboardDevice.DeviceParams.ButtonType.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public ButtonType m0findValueByNumber(int i2) {
                    return ButtonType.forNumber(i2);
                }
            };
            private final int value;

            public static final class ButtonTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ButtonTypeVerifier();

                private ButtonTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i2) {
                    return ButtonType.forNumber(i2) != null;
                }
            }

            ButtonType(int i2) {
                this.value = i2;
            }

            public static ButtonType forNumber(int i2) {
                if (i2 == 0) {
                    return NONE;
                }
                if (i2 == 1) {
                    return MAGNET;
                }
                if (i2 == 2) {
                    return TOUCH;
                }
                if (i2 != 3) {
                    return null;
                }
                return INDIRECT_TOUCH;
            }

            public static Internal.EnumLiteMap<ButtonType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ButtonTypeVerifier.INSTANCE;
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ButtonType valueOf(int i2) {
                return forNumber(i2);
            }
        }

        public enum VerticalAlignmentType implements Internal.EnumLite {
            BOTTOM(0),
            CENTER(1),
            TOP(2);

            public static final int BOTTOM_VALUE = 0;
            public static final int CENTER_VALUE = 1;
            public static final int TOP_VALUE = 2;
            private static final Internal.EnumLiteMap<VerticalAlignmentType> internalValueMap = new Internal.EnumLiteMap<VerticalAlignmentType>() { // from class: com.google.cardboard.proto.CardboardDevice.DeviceParams.VerticalAlignmentType.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public VerticalAlignmentType m1findValueByNumber(int i2) {
                    return VerticalAlignmentType.forNumber(i2);
                }
            };
            private final int value;

            public static final class VerticalAlignmentTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new VerticalAlignmentTypeVerifier();

                private VerticalAlignmentTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i2) {
                    return VerticalAlignmentType.forNumber(i2) != null;
                }
            }

            VerticalAlignmentType(int i2) {
                this.value = i2;
            }

            public static VerticalAlignmentType forNumber(int i2) {
                if (i2 == 0) {
                    return BOTTOM;
                }
                if (i2 == 1) {
                    return CENTER;
                }
                if (i2 != 2) {
                    return null;
                }
                return TOP;
            }

            public static Internal.EnumLiteMap<VerticalAlignmentType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return VerticalAlignmentTypeVerifier.INSTANCE;
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static VerticalAlignmentType valueOf(int i2) {
                return forNumber(i2);
            }
        }

        static {
            DeviceParams deviceParams = new DeviceParams();
            DEFAULT_INSTANCE = deviceParams;
            GeneratedMessageLite.registerDefaultInstance(DeviceParams.class, deviceParams);
        }

        private DeviceParams() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllDistortionCoefficients(Iterable<? extends Float> iterable) {
            ensureDistortionCoefficientsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.distortionCoefficients_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllLeftEyeFieldOfViewAngles(Iterable<? extends Float> iterable) {
            ensureLeftEyeFieldOfViewAnglesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.leftEyeFieldOfViewAngles_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDistortionCoefficients(float f) {
            ensureDistortionCoefficientsIsMutable();
            this.distortionCoefficients_.c(f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLeftEyeFieldOfViewAngles(float f) {
            ensureLeftEyeFieldOfViewAnglesIsMutable();
            this.leftEyeFieldOfViewAngles_.c(f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDistortionCoefficients() {
            this.distortionCoefficients_ = GeneratedMessageLite.emptyFloatList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInterLensDistance() {
            this.bitField0_ &= -9;
            this.interLensDistance_ = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLeftEyeFieldOfViewAngles() {
            this.leftEyeFieldOfViewAngles_ = GeneratedMessageLite.emptyFloatList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearModel() {
            this.bitField0_ &= -3;
            this.model_ = getDefaultInstance().getModel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrimaryButton() {
            this.bitField0_ &= -65;
            this.primaryButton_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScreenToLensDistance() {
            this.bitField0_ &= -5;
            this.screenToLensDistance_ = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTrayToLensDistance() {
            this.bitField0_ &= -33;
            this.trayToLensDistance_ = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVendor() {
            this.bitField0_ &= -2;
            this.vendor_ = getDefaultInstance().getVendor();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVerticalAlignment() {
            this.bitField0_ &= -17;
            this.verticalAlignment_ = 0;
        }

        private void ensureDistortionCoefficientsIsMutable() {
            Internal.FloatList floatList = this.distortionCoefficients_;
            if (floatList.i()) {
                return;
            }
            this.distortionCoefficients_ = GeneratedMessageLite.mutableCopy(floatList);
        }

        private void ensureLeftEyeFieldOfViewAnglesIsMutable() {
            Internal.FloatList floatList = this.leftEyeFieldOfViewAngles_;
            if (floatList.i()) {
                return;
            }
            this.leftEyeFieldOfViewAngles_ = GeneratedMessageLite.mutableCopy(floatList);
        }

        public static DeviceParams getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DeviceParams parseDelimitedFrom(InputStream inputStream) {
            return (DeviceParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceParams parseFrom(ByteBuffer byteBuffer) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DeviceParams> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDistortionCoefficients(int i2, float f) {
            ensureDistortionCoefficientsIsMutable();
            this.distortionCoefficients_.h(i2, f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInterLensDistance(float f) {
            this.bitField0_ |= 8;
            this.interLensDistance_ = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLeftEyeFieldOfViewAngles(int i2, float f) {
            ensureLeftEyeFieldOfViewAnglesIsMutable();
            this.leftEyeFieldOfViewAngles_.h(i2, f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModel(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.model_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setModelBytes(ByteString byteString) {
            this.model_ = byteString.t();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrimaryButton(ButtonType buttonType) {
            this.primaryButton_ = buttonType.getNumber();
            this.bitField0_ |= 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScreenToLensDistance(float f) {
            this.bitField0_ |= 4;
            this.screenToLensDistance_ = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTrayToLensDistance(float f) {
            this.bitField0_ |= 32;
            this.trayToLensDistance_ = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVendor(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.vendor_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVendorBytes(ByteString byteString) {
            this.vendor_ = byteString.t();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerticalAlignment(VerticalAlignmentType verticalAlignmentType) {
            this.verticalAlignment_ = verticalAlignmentType.getNumber();
            this.bitField0_ |= 16;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\f\t\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005$\u0006ခ\u0005\u0007$\u000bဌ\u0004\fဌ\u0006", new Object[]{"bitField0_", "vendor_", "model_", "screenToLensDistance_", "interLensDistance_", "leftEyeFieldOfViewAngles_", "trayToLensDistance_", "distortionCoefficients_", "verticalAlignment_", VerticalAlignmentType.internalGetVerifier(), "primaryButton_", ButtonType.internalGetVerifier()});
                case 3:
                    return new DeviceParams();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    Parser<DeviceParams> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (DeviceParams.class) {
                        try {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser();
                                PARSER = defaultInstanceBasedParser;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public float getDistortionCoefficients(int i2) {
            return this.distortionCoefficients_.a(i2);
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public int getDistortionCoefficientsCount() {
            return this.distortionCoefficients_.size();
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public List<Float> getDistortionCoefficientsList() {
            return this.distortionCoefficients_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public float getInterLensDistance() {
            return this.interLensDistance_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public float getLeftEyeFieldOfViewAngles(int i2) {
            return this.leftEyeFieldOfViewAngles_.a(i2);
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public int getLeftEyeFieldOfViewAnglesCount() {
            return this.leftEyeFieldOfViewAngles_.size();
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public List<Float> getLeftEyeFieldOfViewAnglesList() {
            return this.leftEyeFieldOfViewAngles_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public String getModel() {
            return this.model_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public ByteString getModelBytes() {
            return ByteString.m(this.model_);
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public ButtonType getPrimaryButton() {
            ButtonType buttonTypeForNumber = ButtonType.forNumber(this.primaryButton_);
            return buttonTypeForNumber == null ? ButtonType.MAGNET : buttonTypeForNumber;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public float getScreenToLensDistance() {
            return this.screenToLensDistance_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public float getTrayToLensDistance() {
            return this.trayToLensDistance_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public String getVendor() {
            return this.vendor_;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public ByteString getVendorBytes() {
            return ByteString.m(this.vendor_);
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public VerticalAlignmentType getVerticalAlignment() {
            VerticalAlignmentType verticalAlignmentTypeForNumber = VerticalAlignmentType.forNumber(this.verticalAlignment_);
            return verticalAlignmentTypeForNumber == null ? VerticalAlignmentType.BOTTOM : verticalAlignmentTypeForNumber;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasInterLensDistance() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasModel() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasPrimaryButton() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasScreenToLensDistance() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasTrayToLensDistance() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasVendor() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.cardboard.proto.CardboardDevice.DeviceParamsOrBuilder
        public boolean hasVerticalAlignment() {
            return (this.bitField0_ & 16) != 0;
        }

        public static Builder newBuilder(DeviceParams deviceParams) {
            return DEFAULT_INSTANCE.createBuilder(deviceParams);
        }

        public static DeviceParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceParams parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DeviceParams parseFrom(ByteString byteString) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DeviceParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceParams parseFrom(byte[] bArr) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceParams parseFrom(InputStream inputStream) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceParams parseFrom(CodedInputStream codedInputStream) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DeviceParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface DeviceParamsOrBuilder extends MessageLiteOrBuilder {
        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* synthetic */ MessageLite getDefaultInstanceForType();

        float getDistortionCoefficients(int i2);

        int getDistortionCoefficientsCount();

        List<Float> getDistortionCoefficientsList();

        float getInterLensDistance();

        float getLeftEyeFieldOfViewAngles(int i2);

        int getLeftEyeFieldOfViewAnglesCount();

        List<Float> getLeftEyeFieldOfViewAnglesList();

        String getModel();

        ByteString getModelBytes();

        DeviceParams.ButtonType getPrimaryButton();

        float getScreenToLensDistance();

        float getTrayToLensDistance();

        String getVendor();

        ByteString getVendorBytes();

        DeviceParams.VerticalAlignmentType getVerticalAlignment();

        boolean hasInterLensDistance();

        boolean hasModel();

        boolean hasPrimaryButton();

        boolean hasScreenToLensDistance();

        boolean hasTrayToLensDistance();

        boolean hasVendor();

        boolean hasVerticalAlignment();

        /* synthetic */ boolean isInitialized();
    }

    private CardboardDevice() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
