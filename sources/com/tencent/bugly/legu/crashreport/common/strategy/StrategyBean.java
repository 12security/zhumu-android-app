package com.tencent.bugly.legu.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.legu.proguard.a;
import java.util.Map;

/* compiled from: BUGLY */
public class StrategyBean implements Parcelable {
    public static final Creator<StrategyBean> CREATOR = new Creator<StrategyBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new StrategyBean[i];
        }
    };
    public static String a;
    private static String u = "http://android.bugly.qq.com/rqd/async";
    private static String v = "http://android.bugly.qq.com/rqd/async";
    private static String w = "http://rqd.uu.qq.com/rqd/sync";
    public long b;
    public long c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public long l;
    public long m;
    public String n;
    public String o;
    public String p;
    public String q;
    public Map<String, String> r;
    public int s;
    public long t;

    public StrategyBean() {
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000;
        this.n = u;
        this.o = v;
        this.p = w;
        this.s = 10;
        this.t = 300000;
        this.c = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L").append("@)");
        a = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K").append("@!");
        this.q = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7 = true;
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000;
        this.n = u;
        this.o = v;
        this.p = w;
        this.s = 10;
        this.t = 300000;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("S(@L@L").append("@)");
            a = sb.toString();
            this.c = parcel.readLong();
            this.d = parcel.readByte() == 1;
            if (parcel.readByte() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.e = z;
            if (parcel.readByte() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f = z2;
            this.n = parcel.readString();
            this.o = parcel.readString();
            this.q = parcel.readString();
            this.r = a.b(parcel);
            if (parcel.readByte() == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            this.g = z3;
            if (parcel.readByte() == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            this.j = z4;
            if (parcel.readByte() == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            this.k = z5;
            this.m = parcel.readLong();
            if (parcel.readByte() == 1) {
                z6 = true;
            } else {
                z6 = false;
            }
            this.h = z6;
            if (parcel.readByte() != 1) {
                z7 = false;
            }
            this.i = z7;
            this.l = parcel.readLong();
            this.s = parcel.readInt();
            this.t = parcel.readLong();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = 1;
        parcel.writeLong(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        if (this.e) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        if (this.f) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.q);
        a.b(parcel, this.r);
        if (this.g) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        parcel.writeByte((byte) i5);
        if (this.j) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        parcel.writeByte((byte) i6);
        if (this.k) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        parcel.writeByte((byte) i7);
        parcel.writeLong(this.m);
        if (this.h) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        parcel.writeByte((byte) i8);
        if (!this.i) {
            i9 = 0;
        }
        parcel.writeByte((byte) i9);
        parcel.writeLong(this.l);
        parcel.writeInt(this.s);
        parcel.writeLong(this.t);
    }
}
