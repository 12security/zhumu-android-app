package com.tencent.bugly.legu.proguard;

/* compiled from: BUGLY */
public final class aj extends j implements Cloneable {
    private static byte[] d;
    private byte a = 0;
    private String b = "";
    private byte[] c = null;

    public aj() {
    }

    public aj(byte b2, String str, byte[] bArr) {
        this.a = b2;
        this.b = str;
        this.c = bArr;
    }

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
    }

    public final void a(h hVar) {
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.b(1, true);
        if (d == null) {
            byte[] bArr = new byte[1];
            d = bArr;
            bArr[0] = 0;
        }
        byte[] bArr2 = d;
        this.c = hVar.c(2, false);
    }

    public final void a(StringBuilder sb, int i) {
    }
}
