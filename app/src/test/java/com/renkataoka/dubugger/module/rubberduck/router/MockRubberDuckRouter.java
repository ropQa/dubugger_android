package com.renkataoka.dubugger.module.rubberduck.router;

import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;
import com.renkataoka.util.MethodCallCounter;

/**
 * RubberDuckモジュールのRouterのMockクラス。
 */
public class MockRubberDuckRouter implements RubberDuckContract.Router {
    MethodCallCounter counter = new MethodCallCounter();

    public void clear() {
        counter.clear();
    }

    @Override
    public void onDisassemble() {
        counter.increment("onDisassemble");
    }

    public int getCountOnDisassemble() {
        return counter.getCount("onDisassemble");
    }
}
