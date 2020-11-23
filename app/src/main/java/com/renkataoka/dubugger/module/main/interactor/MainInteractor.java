package com.renkataoka.dubugger.module.main.interactor;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.viper.InteractorCallback;

/**
 * Main画面のInteractorクラス。
 */
public class MainInteractor implements MainContract.Interactor {

    /**
     * InteractorCallbackクラスのオブジェクト。
     */
    private MainContract.InteractorCallback callback;

    /**
     * コンストラクタ
     */
    public MainInteractor() {
    }

    @Override
    public void setInteractorCallback(InteractorCallback callback) {
        if (callback instanceof MainContract.InteractorCallback) {
            this.callback = (MainContract.InteractorCallback) callback;
        }
    }

    @Override
    public void onDisassemble() {
        callback = null;
    }
}
