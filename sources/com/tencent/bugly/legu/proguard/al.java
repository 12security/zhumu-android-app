package com.tencent.bugly.legu.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* compiled from: BUGLY */
public final class al extends j implements Cloneable {
    private static ArrayList<ak> b;
    public ArrayList<ak> a = null;

    public final void a(i iVar) {
        iVar.a((Collection<T>) this.a, 0);
    }

    public final void a(h hVar) {
        if (b == null) {
            b = new ArrayList<>();
            b.add(new ak());
        }
        this.a = (ArrayList) hVar.a(b, 0, true);
    }

    public final void a(StringBuilder sb, int i) {
    }
}
