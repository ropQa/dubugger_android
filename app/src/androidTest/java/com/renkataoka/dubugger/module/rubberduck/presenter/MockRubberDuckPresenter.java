package com.renkataoka.dubugger.module.rubberduck.presenter;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;

/**
 * RubberDuckPresenterのMockクラス。
 */
public class MockRubberDuckPresenter implements RubberDuckContract.Presenter {
    private MethodCallCounter counter = new MethodCallCounter();

    @Override
    public void disassembleModules() {
        counter.increment("disassembleModules");
    }

    public int getCountDisassembleModules() {
        return counter.getCount("disassembleModules");
    }
}
