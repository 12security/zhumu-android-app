package com.tencent.bugly.legu.proguard;

import com.tencent.bugly.legu.crashreport.crash.jni.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class f extends j {
    private static byte[] k = null;
    private static Map<String, String> l = null;
    private static /* synthetic */ boolean m;
    public short a = 0;
    public int b = 0;
    public String c = null;
    public String d = null;
    public byte[] e;
    private byte f = 0;
    private int g = 0;
    private int h = 0;
    private Map<String, String> i;
    private Map<String, String> j;

    static {
        boolean z;
        if (!f.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        m = z;
    }

    public final boolean equals(Object o) {
        f fVar = (f) o;
        if (!k.a(1, (int) fVar.a) || !k.a(1, (int) fVar.f) || !k.a(1, fVar.g) || !k.a(1, fVar.b) || !k.a((Object) Integer.valueOf(1), (Object) fVar.c) || !k.a((Object) Integer.valueOf(1), (Object) fVar.d) || !k.a((Object) Integer.valueOf(1), (Object) fVar.e) || !k.a(1, fVar.h) || !k.a((Object) Integer.valueOf(1), (Object) fVar.i) || !k.a((Object) Integer.valueOf(1), (Object) fVar.j)) {
            return false;
        }
        return true;
    }

    public final Object clone() {
        boolean z = false;
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            if (m) {
                return z;
            }
            throw new AssertionError();
        }
    }

    public final void a(i iVar) {
        iVar.a(this.a, 1);
        iVar.a(this.f, 2);
        iVar.a(this.g, 3);
        iVar.a(this.b, 4);
        iVar.a(this.c, 5);
        iVar.a(this.d, 6);
        iVar.a(this.e, 7);
        iVar.a(this.h, 8);
        iVar.a(this.i, 9);
        iVar.a(this.j, 10);
    }

    public final void a(h hVar) {
        try {
            this.a = hVar.a(this.a, 1, true);
            this.f = hVar.a(this.f, 2, true);
            this.g = hVar.a(this.g, 3, true);
            this.b = hVar.a(this.b, 4, true);
            this.c = hVar.b(5, true);
            this.d = hVar.b(6, true);
            if (k == null) {
                k = new byte[]{0};
            }
            byte[] bArr = k;
            this.e = hVar.c(7, true);
            this.h = hVar.a(this.h, 8, true);
            if (l == null) {
                HashMap hashMap = new HashMap();
                l = hashMap;
                hashMap.put("", "");
            }
            this.i = (Map) hVar.a(l, 9, true);
            if (l == null) {
                HashMap hashMap2 = new HashMap();
                l = hashMap2;
                hashMap2.put("", "");
            }
            this.j = (Map) hVar.a(l, 10, true);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("RequestPacket decode error " + e.a(this.e));
            throw new RuntimeException(e2);
        }
    }

    public final void a(StringBuilder sb, int i2) {
        b bVar = new b(sb, i2);
        bVar.a(this.a, "iVersion");
        bVar.a(this.f, "cPacketType");
        bVar.a(this.g, "iMessageType");
        bVar.a(this.b, "iRequestId");
        bVar.a(this.c, "sServantName");
        bVar.a(this.d, "sFuncName");
        bVar.a(this.e, "sBuffer");
        bVar.a(this.h, "iTimeout");
        bVar.a(this.i, "context");
        bVar.a(this.j, "status");
    }
}
