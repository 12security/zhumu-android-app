package com.tencent.bugly.legu.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: BUGLY */
public class c extends a {
    protected HashMap<String, byte[]> d = null;
    private HashMap<String, Object> e = new HashMap<>();
    private h f = new h();

    public final /* bridge */ /* synthetic */ void a(String str) {
        super.a(str);
    }

    public void p() {
        this.d = new HashMap<>();
    }

    public <T> void a(String str, T t) {
        if (this.d == null) {
            super.a(str, t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            i iVar = new i();
            iVar.a(this.b);
            iVar.a((Object) t, 0);
            this.d.put(str, k.a(iVar.a()));
        }
    }

    public final <T> T b(String str, T t) throws b {
        byte[] bArr;
        if (this.d != null) {
            if (!this.d.containsKey(str)) {
                return null;
            }
            if (this.e.containsKey(str)) {
                return this.e.get(str);
            }
            try {
                this.f.a((byte[]) this.d.get(str));
                this.f.a(this.b);
                T a = this.f.a(t, 0, true);
                if (a == null) {
                    return a;
                }
                this.e.put(str, a);
                return a;
            } catch (Exception e2) {
                throw new b(e2);
            }
        } else if (!this.a.containsKey(str)) {
            return null;
        } else {
            if (this.e.containsKey(str)) {
                return this.e.get(str);
            }
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f.a(bArr);
                this.f.a(this.b);
                T a2 = this.f.a(t, 0, true);
                this.e.put(str, a2);
                return a2;
            } catch (Exception e3) {
                throw new b(e3);
            }
        }
    }

    public byte[] a() {
        if (this.d == null) {
            return super.a();
        }
        i iVar = new i(0);
        iVar.a(this.b);
        iVar.a((Map<K, V>) this.d, 0);
        return k.a(iVar.a());
    }

    public void a(byte[] bArr) {
        try {
            super.a(bArr);
        } catch (Exception e2) {
            this.f.a(bArr);
            this.f.a(this.b);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.d = this.f.a((Map<K, V>) hashMap, 0, false);
        }
    }
}
