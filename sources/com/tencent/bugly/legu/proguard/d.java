package com.tencent.bugly.legu.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class d extends c {
    private static HashMap<String, byte[]> f = null;
    private static HashMap<String, HashMap<String, byte[]>> g = null;
    private f e = new f();

    public final <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, t);
    }

    public final void p() {
        super.p();
        this.e.a = 3;
    }

    public final byte[] a() {
        if (this.e.a != 2) {
            if (this.e.c == null) {
                this.e.c = "";
            }
            if (this.e.d == null) {
                this.e.d = "";
            }
        } else if (this.e.c.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.e.d.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        i iVar = new i(0);
        iVar.a(this.b);
        if (this.e.a == 2) {
            iVar.a((Map<K, V>) this.a, 0);
        } else {
            iVar.a((Map<K, V>) this.d, 0);
        }
        this.e.e = k.a(iVar.a());
        i iVar2 = new i(0);
        iVar2.a(this.b);
        this.e.a(iVar2);
        byte[] a = k.a(iVar2.a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            h hVar = new h(bArr, 4);
            hVar.a(this.b);
            this.e.a(hVar);
            if (this.e.a == 3) {
                h hVar2 = new h(this.e.e);
                hVar2.a(this.b);
                if (f == null) {
                    HashMap<String, byte[]> hashMap = new HashMap<>();
                    f = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.d = hVar2.a((Map<K, V>) f, 0, false);
                return;
            }
            h hVar3 = new h(this.e.e);
            hVar3.a(this.b);
            if (g == null) {
                g = new HashMap<>();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("", new byte[0]);
                g.put("", hashMap2);
            }
            this.a = hVar3.a((Map<K, V>) g, 0, false);
            new HashMap();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public final void b(String str) {
        this.e.c = str;
    }

    public final void c(String str) {
        this.e.d = str;
    }

    public final void b(int i) {
        this.e.b = 1;
    }
}
