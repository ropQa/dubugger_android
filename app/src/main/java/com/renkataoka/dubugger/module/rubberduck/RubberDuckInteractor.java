package com.renkataoka.dubugger.module.rubberduck;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.viper.InteractorCallback;

/**
 * RubberDuck画面のInteractorクラス。
 */
public class RubberDuckInteractor implements RubberDuckContract.Interactor {

    /**
     * InteractorCallbackクラスのオブジェクト。
     */
    private MainContract.InteractorCallback callback;

    @Override
    public void setInteractorCallback(InteractorCallback callback) {
        if (callback instanceof RubberDuckContract.InteractorCallback) {
            this.callback = (MainContract.InteractorCallback) callback;
        }
    }

    @Override
    public void onDisassemble() {
        callback = null;
    }
}
