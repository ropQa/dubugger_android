package com.renkataoka.util;

import android.app.Activity;

/**
 * Instrumented unit testのためのUtilityクラス。
 */
public class InstrumentUtil {
    private InstrumentUtil() {
    }

    /**
     * Activityが破棄されるまで待つ。
     *
     * @param activity 破棄対象のActivity。
     * @param retry    破棄状態の最大確認回数。
     */
    public static void waitForDestroy(Activity activity, int retry) {
        int count = 0;
        if (activity != null) {
            for (; !activity.isDestroyed() && count < retry; count++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
