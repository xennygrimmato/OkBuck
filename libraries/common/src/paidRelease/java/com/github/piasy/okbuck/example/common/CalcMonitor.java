package com.github.okbuilds.okbuck.example.common;

import android.content.Context;
import android.widget.Toast;

public class CalcMonitor {

    private final Context mContext;

    public CalcMonitor(Context context) {
        mContext = context;
    }

    public void addCalled(String config) {
        Toast.makeText(mContext, "addCalled from paidRelease: " + config + ", " + mContext
                .getString(R.string.common_string), Toast.LENGTH_SHORT).show();
    }
}
