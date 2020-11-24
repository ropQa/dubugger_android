package com.renkataoka.dubugger.module.main.router;

import android.content.Context;

import com.renkataoka.dubugger.module.main.contract.MainContract;

/**
 * メイン画面のRouterクラス。
 */
public class MainRouter implements MainContract.Router {
    private Context context;

    @Override
    public void onDisassemble() {
        //Router側でContextを破棄することで、繋ぐViewとの結合を解除する。
        context = null;
    }
}
