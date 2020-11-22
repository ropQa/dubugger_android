package com.renkataoka.dubugger.module.main.presenter;

import com.renkataoka.dubugger.module.main.contract.MainContract;
import com.renkataoka.util.MethodCallCounter;

/**
 * MainPresenterのMockクラス。
 */
public class MockMainPresenter implements MainContract.Presenter {
    private MethodCallCounter counter = new MethodCallCounter();

    @Override
    public void disassembleModules() {
        counter.increment("disassembleModules");
    }

    public int getCountDisassembleModules() {
        return counter.getCount("disassembleModules");
    }
}
