package com.tencent.bugly.legu.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class ak extends j {
    private static ArrayList<aj> A = new ArrayList<>();
    private static Map<String, String> B = new HashMap();
    private static Map<String, String> C = new HashMap();
    private static Map<String, String> v = new HashMap();
    private static ai w = new ai();
    private static ah x = new ah();
    private static ArrayList<ah> y = new ArrayList<>();
    private static ArrayList<ah> z = new ArrayList<>();
    public String a = "";
    public long b = 0;
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";
    public String g = "";
    public Map<String, String> h = null;
    public String i = "";
    public ai j = null;
    public int k = 0;
    public String l = "";
    public String m = "";
    public ah n = null;
    public ArrayList<ah> o = null;
    public ArrayList<ah> p = null;
    public ArrayList<aj> q = null;
    public Map<String, String> r = null;
    public Map<String, String> s = null;
    public String t = "";
    private boolean u = true;

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        iVar.a(this.c, 2);
        if (this.d != null) {
            iVar.a(this.d, 3);
        }
        if (this.e != null) {
            iVar.a(this.e, 4);
        }
        if (this.f != null) {
            iVar.a(this.f, 5);
        }
        if (this.g != null) {
            iVar.a(this.g, 6);
        }
        if (this.h != null) {
            iVar.a(this.h, 7);
        }
        if (this.i != null) {
            iVar.a(this.i, 8);
        }
        if (this.j != null) {
            iVar.a((j) this.j, 9);
        }
        iVar.a(this.k, 10);
        if (this.l != null) {
            iVar.a(this.l, 11);
        }
        if (this.m != null) {
            iVar.a(this.m, 12);
        }
        if (this.n != null) {
            iVar.a((j) this.n, 13);
        }
        if (this.o != null) {
            iVar.a((Collection<T>) this.o, 14);
        }
        if (this.p != null) {
            iVar.a((Collection<T>) this.p, 15);
        }
        if (this.q != null) {
            iVar.a((Collection<T>) this.q, 16);
        }
        if (this.r != null) {
            iVar.a(this.r, 17);
        }
        if (this.s != null) {
            iVar.a(this.s, 18);
        }
        if (this.t != null) {
            iVar.a(this.t, 19);
        }
        iVar.a(this.u, 20);
    }

    static {
        v.put("", "");
        y.add(new ah());
        z.add(new ah());
        A.add(new aj());
        B.put("", "");
        C.put("", "");
    }

    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        this.b = hVar.a(this.b, 1, true);
        this.c = hVar.b(2, true);
        this.d = hVar.b(3, false);
        this.e = hVar.b(4, false);
        this.f = hVar.b(5, false);
        this.g = hVar.b(6, false);
        this.h = (Map) hVar.a(v, 7, false);
        this.i = hVar.b(8, false);
        this.j = (ai) hVar.a((j) w, 9, false);
        this.k = hVar.a(this.k, 10, false);
        this.l = hVar.b(11, false);
        this.m = hVar.b(12, false);
        this.n = (ah) hVar.a((j) x, 13, false);
        this.o = (ArrayList) hVar.a(y, 14, false);
        this.p = (ArrayList) hVar.a(z, 15, false);
        this.q = (ArrayList) hVar.a(A, 16, false);
        this.r = (Map) hVar.a(B, 17, false);
        this.s = (Map) hVar.a(C, 18, false);
        this.t = hVar.b(19, false);
        boolean z2 = this.u;
        this.u = hVar.a(20, false);
    }
}
