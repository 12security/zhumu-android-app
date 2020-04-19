package com.tencent.StubShell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TxReceiver extends BroadcastReceiver {
    public static String TX_RECIEVER = "com.tencent.StubShell.TxReceiver";

    public void onReceive(Context context, Intent intent) {
        TxAppEntry.a(intent);
    }
}
