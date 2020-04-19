package com.tencent.bugly.legu.proguard;

/* compiled from: BUGLY */
public final class ao extends j implements Cloneable {
    public String a = "";
    private String b = "";

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
    }

    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        this.b = hVar.b(1, true);
    }

    public final void a(StringBuilder sb, int i) {
    }
}
