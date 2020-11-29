package com.renkataoka.dubugger.module.rubberduck.router;

import android.content.Context;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

/**
 * RubberDuck画面のRouterクラス。
 */
public class RubberDuckRouter implements RubberDuckContract.Router {
    private Context context;

    public RubberDuckRouter(Context context) {
        this.context = context;
    }

    @Override
    public void onDisassemble() {
        context = null;
    }
}
