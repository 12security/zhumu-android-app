package com.tencent.StubShell;

import com.tencent.bugly.legu.crashreport.CrashReport;

class d implements Runnable {
    final /* synthetic */ TxAppEntry a;

    d(TxAppEntry txAppEntry) {
        this.a = txAppEntry;
    }

    public void run() {
        CrashReport.postCatchedException(new SystemInfoException(TxAppEntry.mSrcPath, TxAppEntry.i));
    }
}
